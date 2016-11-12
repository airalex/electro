package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.random.Uniform;

import java.util.List;

/**
 * Created by alex on 12/11/2016.
 */
public class Solver {
    private BasicMatrix costs;
    private BasicMatrix constraints;
    private int populationSize;
    private long iterationsCount;
    private double preserver; // AKA r parameter AKA cooler. If 1.0 -> cool fast, if 0.0 -> not cool at all.

    public Solver(BasicMatrix costs, BasicMatrix constraints, int populationSize, long iterationsCount,
                  double preserver) {
        // TODO: throw exception if input matrices have incorrect size
        this.costs = costs;
        this.constraints = constraints;
        this.populationSize = populationSize;
        this.iterationsCount = iterationsCount;
        this.preserver = preserver;
    }


    List<Integer> findBestSolution(boolean printInfo) {
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;

        Objectiver objectiver = new Objectiver(costs);
        BasicMatrix lambdas = factory.makeFilled(dimensionsCount(), populationSize, new Uniform(0.0, 1.0));
        PopulationStarter starter = new PopulationStarter(constraints);
        List<Particle> population = starter.generatePopulation(objectiver, populationSize, lambdas);

        Particle worstParticle = Forcer.worstParticle(population).get();
        Particle bestParticle = Forcer.bestParticle(population).get();

        for (long iteration = 0; iteration < iterationsCount; iteration++) {
            for (int i = 0; i < populationSize; i++) {
                // Calculate i-th particle's result force
                Particle ithParticle = population.get(i);
                BasicMatrix ithParticlePos = ithParticle.getPosition();

                BasicMatrix force = factory.makeZero(dimensionsCount(), 1);
                for (int j = 0; j < populationSize; j++) {
                    BasicMatrix jthParticlePos = population.get(j).getPosition();
                    BasicMatrix worstPos = worstParticle.getPosition();
                    BasicMatrix bestPos = bestParticle.getPosition();

                    double relativeCharge = Forcer.relativeCharge(ithParticlePos, jthParticlePos,
                            worstPos, bestPos, objectiver);
                    BasicMatrix relativeForce = Forcer.relativeForce(ithParticlePos, jthParticlePos,
                            relativeCharge);
                    force = force.add(relativeForce);
                }

                // Advance i-th particle's position
//                double lambda = 0.5; // TODO: replace with random from U(0, 1)
                double lambda = new Uniform(0.0, 1.0).doubleValue();
                BasicMatrix advancedPos = Approacher.advancePosition(ithParticlePos, force, lambda,
                        iteration+1, preserver);
                ithParticle.setPosition(advancedPos);

                // Recalculate ith-particle's OFV
                double advancedPosObjectiveFunctionValue = objectiver.functionValueForPosition(advancedPos);
                ithParticle.setFunctionValue(advancedPosObjectiveFunctionValue);
            }
            // TODO: Check boundaries with BoundaryControl procedure

            // Remember worst & best particles
            worstParticle = Forcer.worstParticle(population).get();
            bestParticle = Forcer.bestParticle(population).get();

            if (printInfo) {
                printIterationInfo(iteration, bestParticle);
            }
        }

        BasicMatrix bestPosition = bestParticle.getPosition();
        return Objectiver.indicesFromPosition(bestPosition);
    }

    private long dimensionsCount() {
        return costs.countRows();
    }

    private void printIterationInfo(long iteration, Particle bestParticle) {
        BasicMatrix bestPosition = bestParticle.getPosition();
        List<Integer> bestSolution = Objectiver.indicesFromPosition(bestPosition);
        double ofv = bestParticle.getFunctionValue();
        System.out.print("The best solution in iteration "+iteration+" is: "+bestSolution+". OFV: " +
                ""+ofv+"\n");
    }
}

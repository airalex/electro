package com.alexanderjuda.electro;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by alex on 07/11/2016.
 */
public class PopulationStarter {
    private List<Pair<Double, Double>> constraints;

    public PopulationStarter(List<Pair<Double, Double>> constraints) {
        this.constraints = constraints;
    }

    // lambdas should be of size populationSize * dimensionsCount
    List<Particle> generatePopulation(Objectiver objectiver, int populationSize, List<Double> lambdas) {
//        if (lambdas.size() != populationSize * dimensionsCount()) {
//            throw new IllegalArgumentException("Argument lambdas has invalid size of "+lambdas.size()+".\n" +
//                    "Should be "+(populationSize * dimensionsCount())+".");
//        }
//
//        List<Particle> population = new ArrayList<>();
//
//        for (int i = 0; i < populationSize; i++) {
//            List<Double> position = new ArrayList<>();
//            for (int dim = 0; dim < dimensionsCount(); dim++) {
//                Pair<Double, Double> constraint = constraints.get(dim);
//                Double l = constraint.getFirst();
//                Double u = constraint.getSecond();
//                Double lambda = lambdas.get(i * dimensionsCount() + dim);
//
//                Double positionValue = l + lambda * (u - l);
//                position.add(positionValue);
//            }
//            List<Integer> indices = objectiver.indicesFromPosition(position);
//            Double functionValue = objectiver.functionValue(indices);
//            Particle particle = new Particle(position, functionValue);
//            population.add(particle);
//        }
//
//        return population;
        return new ArrayList<>();
    }

    private int dimensionsCount() {
        return constraints.size();
    }
}

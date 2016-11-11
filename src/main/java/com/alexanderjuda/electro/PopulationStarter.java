package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by alex on 07/11/2016.
 */
public class PopulationStarter {
    // n-by-2 matrix; two columns represent lower and upper bounds for i-th dimension
    private BasicMatrix constraints;

    PopulationStarter(BasicMatrix constraints) {
        this.constraints = constraints;
    }

    // j-th column in lambdas is related to j-th generated particle.
    // i-th row in lambdas column is related to i-th dimension in generated particle's position.
    List<Particle> generatePopulation(Objectiver objectiver, int populationSize, BasicMatrix lambdas)
            throws IllegalArgumentException {
        if (lambdas.countRows() != dimensionsCount()) {
            throw new IllegalArgumentException("lambdas matrix has incorrect number of columns " +
                    "("+lambdas.countRows()+" vs "+dimensionsCount()+").");
        }
        if (lambdas.countColumns() != populationSize) {
            throw new IllegalArgumentException("lambdas matrix has incorrect number of rows "+
                    "("+lambdas.countColumns()+" vs "+populationSize+").");
        }

        // Assuming following being column vectors:
        // p (position), lambda (random factor), l (lower constraint), u (upper constraint)
        // then:
        // p = l + lambda .* (u - l)
        //   = (l*(-1) + u) .* lambda + l
        BasicMatrix l = constraints.selectColumns(0);
        BasicMatrix u = constraints.selectColumns(1);
        
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            BasicMatrix lambda = lambdas.selectColumns(i);
            BasicMatrix p = l.multiply(-1.0).add(u).multiplyElements(lambda).add(l);

            double functionValue = objectiver.functionValueForPosition(p);

            Particle particle = new Particle(p, functionValue);
            particles.add(particle);
        }

        return particles;
    }

    private long dimensionsCount() {
        return constraints.countRows();
    }
}

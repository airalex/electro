package com.alexanderjuda.electro;

import java.util.ArrayList;
import java.util.List;

import org.ojalgo.matrix.BasicMatrix;

/**
 * Generates a population with given global constraints.
 */
public class PopulationStarter {
    // n-by-2 matrix; two columns represent lower and upper bounds for i-th dimension
    private BasicMatrix constraints;

    PopulationStarter(BasicMatrix constraints) {
        this.constraints = constraints;
    }

    // Lambdas matrix should have random values. It's passed as argument to keep the function pure.
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
            // // TODO: 11/11/2016 replace *(-1) with .substract()
            BasicMatrix p = u.subtract(l).multiplyElements(lambda).add(l);

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

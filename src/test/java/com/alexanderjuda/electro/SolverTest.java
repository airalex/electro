package com.alexanderjuda.electro;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * Created by alex on 12/11/2016.
 */
public class SolverTest {
    // Checks if the result has correct form. Doesn't check if found solution is the best one, since that
    // depends on randoms.
    @Test
    public void testFindBestSolutionResultForm() {
        // Given
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;

        BasicMatrix costs = factory.rows(new double[][]{
                {0.0, 1.0, 1.4, 1.3, 1.0},
                {1.0, 0.0, 1.0, 1.4, 1.7},
                {1.4, 1.0, 0.0, 1.0, 1.3},
                {1.3, 1.4, 1.0, 0.0, 1.0},
                {1.0, 1.7, 1.3, 1.0, 0.0}
        });
        BasicMatrix constraints = factory.rows(new double[][] {
                {0.0, 4.0},
                {0.0, 4.0},
                {0.0, 4.0},
                {0.0, 4.0},
                {0.0, 4.0}
        });
        int populationSize = 10;
        long iterationsCount = 20;
        double preserver = 0.5;
        Solver solver = new Solver(costs, constraints, populationSize, iterationsCount, preserver);

        // When
        List<Integer> solution = solver.findBestSolution(true);

        // Then
        Assert.assertEquals(costs.countColumns(), solution.size());
        Assert.assertTrue(solution.containsAll(Arrays.asList(0, 1, 2, 3)));
    }
}
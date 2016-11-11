package com.alexanderjuda.electro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * Created by alex on 08/11/2016.
 */
public class PopulationStarterTest {
    @Test
    public void generatePopulation() throws Exception {
        // Given
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;
        BasicMatrix constraints = factory.rows(new double[][] {
                {1.0, 11.0},
                {2.0, 12.0},
                {3.0, 13.0}
        });
        PopulationStarter starter = new PopulationStarter(constraints);

        // 0 -> 1 -> 2 -> 0 costs 1.2 + 2.3 + 1.3 = 4.8
        BasicMatrix costs = factory.rows(new double[][]{
                {0.0, 1.2, 1.3},
                {1.2, 0.0, 2.3},
                {1.3, 2.3, 0.0}
        });
        Objectiver objectiver = new Objectiver(costs);

        // each column is for a new particle
        BasicMatrix lambdas = factory.rows(new double[][] {
                {0.3, 0.4, 0.5, 0.51},
                {0.3, 0.4, 0.5, 0.52},
                {0.3, 0.4, 0.5, 0.53}
        });

        // When
        List<Particle> particles = starter.generatePopulation(objectiver, 4, lambdas);

        // Then
        List<Particle> expectedParticles = Arrays.asList(
                new Particle(new double[] {4.0, 5.0, 6.0}, 4.8),
                new Particle(new double[] {5.0, 6.0, 7.0}, 4.8),
                new Particle(new double[] {6.0, 7.0, 8.0}, 4.8),
                new Particle(new double[] {6.1, 7.2, 8.3}, 4.8)
        );
        Assert.assertEquals(expectedParticles, particles);
    }
}
package com.alexanderjuda.electro;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alex on 08/11/2016.
 */
public class PopulationStarterTest {
    @Test
    public void generatePopulation() throws Exception {
        // Given
        List<Pair<Double, Double>> constraints = Arrays.asList(
                new Pair<>(1.0, 11.0),
                new Pair<>(2.0, 12.0),
                new Pair<>(3.0, 13.0)
        );
        PopulationStarter starter = new PopulationStarter(constraints);

        // 0 -> 1 -> 2 -> 0 costs 1.2 + 2.3 + 1.3 = 4.8
        List<List<Double>> costs = Arrays.asList(
                Arrays.asList(0.0, 1.2, 1.3),
                Arrays.asList(1.2, 0.0, 2.3),
                Arrays.asList(1.3, 2.3, 0.0)
        );
        Objectiver objectiver = new Objectiver(costs);

        List<Double> lambdas = Arrays.asList(
                0.3, 0.3, 0.3,
                0.4, 0.4, 0.4,
                0.5, 0.5, 0.5,
                0.51, 0.52, 0.53
        );

        // When
        List<Particle> particles = starter.generatePopulation(objectiver, 4, lambdas);

        // Then
        List<Particle> expectedParticles = Arrays.asList(
                new Particle(Arrays.asList(4.0, 5.0, 6.0), 4.8),
                new Particle(Arrays.asList(5.0, 6.0, 7.0), 4.8),
                new Particle(Arrays.asList(6.0, 7.0, 8.0), 4.8),
                new Particle(Arrays.asList(6.1, 7.2, 8.3), 4.8)
        );
        Assert.assertEquals(expectedParticles, particles);
    }
}
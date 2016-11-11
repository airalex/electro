package com.alexanderjuda.electro;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alex on 09/11/2016.
 */
public class ForcerTest {
//    @Test
//    public void relativeCharge() {
//        // Given
//        List<List<Double>> costs = Arrays.asList(
//                Arrays.asList(0.0, 1.0, 1.4, 1.3, 1.0),
//                Arrays.asList(1.0, 0.0, 1.0, 1.4, 1.7),
//                Arrays.asList(1.4, 1.0, 0.0, 1.0, 1.3),
//                Arrays.asList(1.3, 1.4, 1.0, 0.0, 1.0),
//                Arrays.asList(1.0, 1.7, 1.3, 1.0, 0.0)
//        );
//        Objectiver objectiver = new Objectiver(costs);
//
//        // 0 -> 1 -> 2 -> 3 -> 4
//        List<Double> bestPosition = Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0);
//
//        // 0 -> 1 -> 3 -> 2 -> 4
//        List<Double> position1 = Arrays.asList(0.0, 1.0, 3.0, 2.0, 4.0);
//
//        // 0 -> 3 -> 4 -> 2 -> 1
//        List<Double> position2 = Arrays.asList(0.0, 4.0, 3.0, 1.0, 2.0);
//
//        // 0 -> 4 -> 1 -> 2 -> 3
//        List<Double> worstPosition = Arrays.asList(0.0, 2.0, 3.0, 4.0, 1.0);
//
//        // When
//        double charge = Forcer.relativeCharge(position1, position2, worstPosition, bestPosition, objectiver);
//
//        // Then
//        Assert.assertEquals(0.1, charge, 0.001);
//    }
//
//    @Test
//    public void relativePosition() {
//        // Given
//        // 0 -> 1 -> 3 -> 2 -> 4
//        List<Double> position1 = Arrays.asList(0.0, 1.0, 3.0, 2.0, 4.0);
//
//        // 0 -> 3 -> 4 -> 2 -> 1
//        List<Double> position2 = Arrays.asList(0.0, 4.0, 3.0, 1.0, 2.0);
//
//        // When
//        List<Double> relativePosition = Forcer.relativePosition(position1, position2);
//
//        // Then
//        List<Double> expected = Arrays.asList(0.0, 3.0, 0.0, -1.0, -2.0);
//        Assert.assertEquals(expected, relativePosition);
//    }
//
//    @Test
//    public void scaledPosition() {
//        // Given
//        List<Double> position = Arrays.asList(0.0, 1.0, 3.0, 2.0, 4.0);
//        double scalar = 2.5;
//
//        // When
//        List<Double> scaledPosition = Forcer.scaledPosition(position, scalar);
//
//        // Then
//        List<Double> expected = Arrays.asList(0.0, 2.5, 7.5, 5.0, 10.0);
//        Assert.assertEquals(expected, scaledPosition);
//    }
//
//    // It's an integration test
//    @Test
//    public void relativeForce() {
//        // Given
//        List<List<Double>> costs = Arrays.asList(
//                Arrays.asList(0.0, 1.0, 1.4, 1.3, 1.0),
//                Arrays.asList(1.0, 0.0, 1.0, 1.4, 1.7),
//                Arrays.asList(1.4, 1.0, 0.0, 1.0, 1.3),
//                Arrays.asList(1.3, 1.4, 1.0, 0.0, 1.0),
//                Arrays.asList(1.0, 1.7, 1.3, 1.0, 0.0)
//        );
//        Objectiver objectiver = new Objectiver(costs);
//
//        // 0 -> 1 -> 2 -> 3 -> 4
//        List<Double> bestPosition = Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0);
//
//        // 0 -> 1 -> 3 -> 2 -> 4
//        List<Double> position1 = Arrays.asList(0.0, 1.0, 3.0, 2.0, 4.0);
//
//        // 0 -> 3 -> 4 -> 2 -> 1
//        List<Double> position2 = Arrays.asList(0.0, 4.0, 3.0, 1.0, 2.0);
//
//        // 0 -> 4 -> 1 -> 2 -> 3
//        List<Double> worstPosition = Arrays.asList(0.0, 2.0, 3.0, 4.0, 1.0);
//
//        // When
//        List<Double> force = Forcer.relativeForce(position1, position2, worstPosition, bestPosition, objectiver);
//
//        // Then
//        double[] expected = {0.0, 0.3, 0.0, -0.1, -0.2};
//        double[] forceArray = force.stream().mapToDouble(Double::doubleValue).toArray();
//        Assert.assertArrayEquals(expected, forceArray, 0.0001);
//    }
}
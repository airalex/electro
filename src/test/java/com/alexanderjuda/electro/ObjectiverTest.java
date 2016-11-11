package com.alexanderjuda.electro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 07/11/2016.
 */
public class ObjectiverTest {
    @Test
    public void functionValue() throws Exception {
        // Given
        List<List<Double>> costs = Arrays.asList(
                Arrays.asList(0.0, 1.2, 1.3),
                Arrays.asList(1.2, 0.0, 2.3),
                Arrays.asList(1.3, 2.3, 0.0)
        );
        Objectiver objectiver = new Objectiver(costs);

        // When
        List<Integer> stops = Arrays.asList(0, 1, 2);
        Double value = objectiver.functionValue(stops);

        // Then
        Assert.assertEquals(1.2 + 2.3 + 1.3, value, 0.01);
    }

    @Test
    public void indicesFromSortedPosition() throws Exception {
        // Given
        List<Double> position = Arrays.asList(0.0, 2.0, 2.5, 3.1, 3.4);

        // When
        List<Integer> indices = Objectiver.indicesFromPosition(position);

        // Then
        Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4), indices);
    }

    @Test
    public void indicesFromUnsortedPosition() throws Exception {
        // Given
        //                   number in order: 0    5    1    6    2    3    4
        List<Double> position = Arrays.asList(0.0, 5.0, 2.0, 6.1, 2.5, 3.1, 3.4);
        List<Integer> expectedIndices = Arrays.asList(0, 2, 4, 5, 6, 1, 3);

        // When
        List<Integer> indices = Objectiver.indicesFromPosition(position);

        // Then
        Assert.assertEquals(expectedIndices, indices);
    }

    @Test
    public void indicesFromUnsortedPosition2() throws Exception {
        // Given
        //                   number in order: 0    5    1    6    2    3    4
        List<Double> position = Arrays.asList(0.0, 5.0, 2.0, 6.1, 2.5, 3.1, 3.4);
        List<Integer> expectedIndices = Arrays.asList(0, 2, 4, 5, 6, 1, 3);

        // When
        List<Integer> indices = Objectiver.indicesFromPosition(position);

        // Then
        Assert.assertEquals(expectedIndices, indices);
    }

    @Test
    public void functionValueFromPosition() throws Exception {
        // Given
        List<List<Double>> costs = Arrays.asList(
                Arrays.asList(0.0, 1.2, 1.3),
                Arrays.asList(1.2, 0.0, 2.3),
                Arrays.asList(1.3, 2.3, 0.0)
        );
        Objectiver objectiver = new Objectiver(costs);

        // When
        List<Double> position = Arrays.asList(0.1, 1.3, 2.4);
        Double value = objectiver.functionValueForPosition(position);

        // Then
        Assert.assertEquals(1.2 + 2.3 + 1.3, value, 0.01);
    }
}
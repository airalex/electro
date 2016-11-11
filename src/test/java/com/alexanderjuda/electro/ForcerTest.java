package com.alexanderjuda.electro;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * Created by alex on 09/11/2016.
 */
public class ForcerTest {
    @Test
    public void relativeCharge() {
        // Given
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;
        BasicMatrix costs = factory.rows(new double[][]{
                {0.0, 1.0, 1.4, 1.3, 1.0},
                {1.0, 0.0, 1.0, 1.4, 1.7},
                {1.4, 1.0, 0.0, 1.0, 1.3},
                {1.3, 1.4, 1.0, 0.0, 1.0},
                {1.0, 1.7, 1.3, 1.0, 0.0}
        });
        Objectiver objectiver = new Objectiver(costs);

        // 0 -> 1 -> 2 -> 3 -> 4
        BasicMatrix bestPosition = factory.columns(new double[] {0.0, 1.0, 2.0, 3.0, 4.0});

        // 0 -> 1 -> 3 -> 2 -> 4
        BasicMatrix position1 = factory.columns(new double[] {0.0, 1.0, 3.0, 2.0, 4.0});

        // 0 -> 3 -> 4 -> 2 -> 1
        BasicMatrix position2 = factory.columns(new double[] {0.0, 4.0, 3.0, 1.0, 2.0});

        // 0 -> 4 -> 1 -> 2 -> 3
        BasicMatrix worstPosition = factory.columns(new double[] {0.0, 2.0, 3.0, 4.0, 1.0});

        // When
        double charge = Forcer.relativeCharge(position1, position2, worstPosition, bestPosition, objectiver);

        // Then
        Assert.assertEquals(0.1, charge, 0.001);
    }

    @Test
    public void relativeForce() {
        // Given
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;
        // 0 -> 1 -> 3 -> 2 -> 4
        BasicMatrix position1 = factory.columns(new double[] {0.0, 1.0, 3.0, 2.0, 4.0});

        // 0 -> 3 -> 4 -> 2 -> 1
        BasicMatrix position2 = factory.columns(new double[] {0.0, 4.0, 3.0, 1.0, 2.0});

        double charge = 0.1;

        // When
        BasicMatrix force = Forcer.relativeForce(position1, position2, charge);

        // Then
        BasicMatrix expectedForce = factory.columns(new double[] {0.0, 0.3, 0.0, -0.1, -0.2});
        Assert.assertArrayEquals(expectedForce.toRawCopy1D(), force.toRawCopy1D(), 0.001);
    }
}
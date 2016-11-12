package com.alexanderjuda.electro;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * Created by alex on 12/11/2016.
 */
public class ApproacherTest {
    @Test
    public void advancePosition() {
        // Given
        BasicMatrix.Factory<PrimitiveMatrix> factory = PrimitiveMatrix.FACTORY;
        BasicMatrix position = factory.columns(new double[] {1.0, 1.0, 1.0});
        BasicMatrix force = factory.columns(new double[] {2.0, 2.0, 2.0});
        double lambda = 1.0;
        long iteration = 1;
        double preserver = 0.5;

        // When
        BasicMatrix advancedPosition = Approacher.advancePosition(position, force, lambda, iteration, preserver);

        // Then
        BasicMatrix expectedPosition = factory.columns(new double [] {3.0, 3.0, 3.0});
        Assert.assertArrayEquals(expectedPosition.toRawCopy1D(), advancedPosition.toRawCopy1D(), 0.001);
    }
}
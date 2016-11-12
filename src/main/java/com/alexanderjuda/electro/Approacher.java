package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;

/**
 * Created by alex on 12/11/2016.
 */
public class Approacher {
    static BasicMatrix advancePosition(BasicMatrix position, BasicMatrix force,
                                        double lambda, long iteration, double preserver) {
        double scaler = lambda / Math.pow(iteration, preserver);

        return position.add(force.multiply(scaler));
    }
}

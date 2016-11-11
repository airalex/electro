package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Particle force calculator based on
 * "Efficient Constraint Handling in Electromagnetism-Like Algorithm for Traveling Salesman Problem with Time Windows"
 * by A. Yurtkuran & E. Emel (AKA TSP paper).
 */
public class Forcer {
    static BasicMatrix relativeForce(BasicMatrix position1, BasicMatrix position2, double relativeCharge) {
        BasicMatrix positionDiff = position2.subtract(position1);
        BasicMatrix force = positionDiff.multiply(relativeCharge);

        return force;
    }

    static double relativeCharge(BasicMatrix position1, BasicMatrix position2,
                                 BasicMatrix worstPosition, BasicMatrix bestPosition,
                                 Objectiver objectiver) {

        long n1 = position1.countRows();
        long n2 = position2.countRows();
        long nWorst = worstPosition.countRows();
        long nBest = bestPosition.countRows();
        if (n2 != n1 || nWorst != n1 || nBest != n1) {
            List<String> sizeStrings = Stream.of(n1, n2, nWorst, nBest)
                    .map(Object::toString).collect(Collectors.toList());
            throw new IllegalArgumentException("Invalid position dimensions: " + String.join(", ", sizeStrings));
        }

        double f_xi = objectiver.functionValueForPosition(position1);
        double f_xj = objectiver.functionValueForPosition(position2);
        double f_worst = objectiver.functionValueForPosition(worstPosition);
        double f_best = objectiver.functionValueForPosition(bestPosition);

        return (f_xi - f_xj) / (f_worst - f_best);
    }
}

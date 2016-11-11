package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Particle force calculator based on
 * "Efficient Constraint Handling in Electromagnetism-Like Algorithm for Traveling Salesman Problem with Time Windows"
 * by A. Yurtkuran & E. Emel (AKA TSP paper).
 */
public class Forcer {
    static List<Double> relativeForce(List<Double> position1, List<Double> position2,
                                List<Double> worstPosition, List<Double> bestPosition,
                                Objectiver objectiver) {

//        List<Double> positionDiff = relativePosition(position1, position2);
//        double charge = relativeCharge(position1, position2, worstPosition, bestPosition, objectiver);
//        List<Double> force = scaledPosition(positionDiff, charge);
//
//        return force;
        return null;
    }

    static double relativeCharge(BasicMatrix position1, BasicMatrix position2,
                                 BasicMatrix worstPosition, BasicMatrix bestPosition,
                                 Objectiver objectiver) {
//
//        long n1 = position1.countRows();
//        long n2 = position2.countRows();
//        long nWorst = worstPosition.countRows();
//        long nBest = bestPosition.countRows();
//        if (n2 != n1 || nWorst != n1 || nBest != n1) {
//            List<String> sizeStrings = Stream.of(n1, n2, nWorst, nBest)
//                    .map(Object::toString).collect(Collectors.toList());
//            throw new IllegalArgumentException("Invalid position dimensions: " + String.join(", ", sizeStrings));
//        }
//
//        double f_xi = objectiver.functionValueForPosition(position1);
//        double f_xj = objectiver.functionValueForPosition(position2);
//        double f_worst = objectiver.functionValueForPosition(worstPosition);
//        double f_best = objectiver.functionValueForPosition(bestPosition);
//
//        return (f_xi - f_xj) / (f_worst - f_best);
        return -1.0;
    }

    static List<Double> relativePosition(List<Double> position, List<Double> otherPosition) {
        int n1 = position.size();
        int n2 = otherPosition.size();
        if (n2 != n1) {
            List<String> sizeStrings = Stream.of(n1, n2)
                    .map(Object::toString).collect(Collectors.toList());
            throw new IllegalArgumentException("Invalid position dimensions: " + String.join(", ", sizeStrings));
        }

        List<Double> relative = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            double diff = otherPosition.get(i) - position.get(i);
            relative.add(diff);
        }

        return relative;
    }

    static List<Double> scaledPosition(List<Double> position, Double scalar) {
        List<Double> scaledList = new ArrayList<>(position.size());
        for (Double positionValue : position) {
            double scaled = positionValue * scalar;
            scaledList.add(scaled);
        }

        return scaledList;
    }
}

package com.alexanderjuda.electro;

import org.ojalgo.matrix.BasicMatrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Objective Function Calculator implementation based on
 * "Efficient Constraint Handling in Electromagnetism-Like Algorithm for Traveling Salesman Problem with Time Windows"
 * by A. Yurtkuran & E. Emel (AKA TSP paper).
 */
public class Objectiver {
    private BasicMatrix costsMatrix;

    public Objectiver(BasicMatrix costsMatrix) throws IllegalArgumentException {
        if (!costsMatrix.isSquare()) {
            throw new IllegalArgumentException("Non-square costs matrix.");
        }
        this.costsMatrix = costsMatrix;
    }

    double functionValue(List<Integer> stopIndices) throws IllegalArgumentException {
        int stopsCount = stopIndices.size();
        if (costsMatrix.countRows() != stopsCount) {
            throw new IllegalArgumentException("Stops count invalid.\nIs "+stopsCount+", should be "+costsMatrix.countRows()+".");
        }

        List<Integer> loopedStops = new ArrayList<>(stopIndices);
        loopedStops.add(stopIndices.get(0)); // return to the first stop

        double costsAccumulator = 0.0;
        for (int i = 0; i < stopsCount; i++) {
            Integer from = loopedStops.get(i);
            Integer to = loopedStops.get(i+1);

            if (from < 0 || stopsCount <= from || to < 0 || stopsCount <= to) {
                throw new IllegalArgumentException("Proposed step ("+from+" -> "+to+") has incorrect indices.\n" +
                        "Correct values are between 0 and "+ (costsMatrix.countRows()-1) +".");
            }

            double cost = costsMatrix.doubleValue(from, to);
            costsAccumulator += cost;
        }

        return costsAccumulator;
    }

    // Helper method. Integrates functionValue() with indicesFromPosition().
    double functionValueForPosition(BasicMatrix position) {
        return functionValue(indicesFromPosition(position));
    }

    // Random Key representation (Doubles) -> indices (Integers)
    static List<Integer> indicesFromPosition(BasicMatrix position) throws IllegalArgumentException {
        if (position.countColumns() != 1) {
            throw new IllegalArgumentException("position should be a column vector. "+
                    "Now it has "+position.countColumns()+" columns.");
        }

        List<Pair<Integer, Double>> indexValuePairs = new ArrayList<>();
        for (int i = 0; i < position.countRows(); i++) {
            double positionValue = position.doubleValue(i, 0);
            indexValuePairs.add(new Pair<>(i, positionValue));
        }
        Comparator<Double> comparator = Comparator.naturalOrder();

        indexValuePairs.sort((pair1, pair2) -> comparator.compare(pair1.getSecond(), pair2.getSecond()));
        List<Integer> sortedIndices = indexValuePairs.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());

        return sortedIndices;
    }
}

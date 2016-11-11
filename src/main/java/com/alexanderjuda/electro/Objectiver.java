package com.alexanderjuda.electro;

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
    private List<List<Double>> costsMatrix;

    public Objectiver(List<List<Double>> costsMatrix) {
        int size = costsMatrix.size();
        if (size < 1 || costsMatrix.get(0).size() != size) {
            throw new IllegalArgumentException("Invalid costsMatrix size.");
        }
        this.costsMatrix = costsMatrix;
    }

    double functionValue(List<Integer> stopIndices) {
        int n = stopIndices.size();
        if (n <= 0 || costsMatrix.size() != n || costsMatrix.get(0).size() != n) {
            throw new IllegalArgumentException("Stops count invalid.\nIs "+n+", should be"+costsMatrix.size()+".");
        }

        List<Integer> loopedStops = new ArrayList<>(stopIndices);
        loopedStops.add(stopIndices.get(0)); // return to the first stop

        Double costsAccumulator = 0.0;
        for (int i = 0; i < n; i++) {
            Integer from = loopedStops.get(i);
            Integer to = loopedStops.get(i+1);

            if (from < 0 || n <= from || to < 0 || n <= to) {
                throw new IllegalArgumentException("Proposed step ("+from+" -> "+to+") has incorrect indices.\n" +
                        "Correct values are between 0 and "+ (n-1) +".");
            }

            Double cost = costsMatrix.get(from).get(to);
            costsAccumulator += cost;
        }

        return costsAccumulator;
    }

    // Helper method. Integrates functionValue() with indicesFromPosition().
    double functionValueForPosition(List<Double> position) {
        return functionValue(indicesFromPosition(position));
    }

    // Random Key representation (Doubles) -> indices (Integers)
    static List<Integer> indicesFromPosition(List<Double> position) {
        List<Pair<Integer, Double>> indexValuePairs = new ArrayList<>();
        for (int i = 0; i < position.size(); i++) {
            indexValuePairs.add(new Pair<>(i, position.get(i)));
        }
        Comparator<Double> comparator = Comparator.naturalOrder();

        indexValuePairs.sort((pair1, pair2) -> comparator.compare(pair1.getSecond(), pair2.getSecond()));
        List<Integer> sortedIndices = indexValuePairs.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());

        return sortedIndices;
    }
}

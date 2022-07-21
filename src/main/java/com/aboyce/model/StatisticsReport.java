package com.aboyce.model;

import java.util.List;

public class StatisticsReport {

    private final List<Square> mostPopulatedSquares;

    private final double averageNumberOfBeetles;

    public StatisticsReport(List<Square> mostPopulatedSquares, double averageNumberOfBeetles) {
        this.mostPopulatedSquares = mostPopulatedSquares;
        this.averageNumberOfBeetles = averageNumberOfBeetles;
    }

    public List<Square> getMostPopulatedSquares() {
        return mostPopulatedSquares;
    }

    public double getAverageNumberOfBeetles() {
        return averageNumberOfBeetles;
    }

    public void prettyPrint() {
        System.out.println("The most populated field(s) is / are: " + this.mostPopulatedSquares);
        System.out.println("The average number of beetles per occupied square is: " + this.averageNumberOfBeetles);

    }
}

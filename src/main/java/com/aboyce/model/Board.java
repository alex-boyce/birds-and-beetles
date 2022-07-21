package com.aboyce.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private final int numberOfRows;

    private final int numberOfColumns;

    private Map<Square, Long> state;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        initializeState();
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public Map<Square, Long> getState() {
        return state;
    }

    public void setState(Map<Square, Long> state) {
        this.state = state;
    }

    private void initializeState() {
        state = IntStream
                .rangeClosed(1, numberOfRows)
                .mapToObj(row -> IntStream
                        .rangeClosed(1, numberOfColumns)
                        .mapToObj(column -> new Square(row, column)))
                .flatMap(Function.identity())
                .collect(Collectors.toMap(Function.identity(), field -> 1L));
    }

    public void prettyPrint() {
        IntStream
                .rangeClosed(1, this.numberOfRows)
                .forEach(row -> {
                    IntStream
                            .rangeClosed(1, this.numberOfColumns)
                            .forEach(column -> {
                                Square square = new Square(row, column);
                                Long numberOfBeetles = this.state.getOrDefault(square, 0L);
                                System.out.printf("%1$3s", numberOfBeetles);
                            });
                    System.out.println();
                });
    }
}

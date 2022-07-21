package com.aboyce;

import com.aboyce.model.Board;
import com.aboyce.model.Square;
import com.aboyce.model.StatisticsReport;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StatisticsComputer {

    public StatisticsReport generateReport(Board board) {
        return new StatisticsReport(
                findMostPopulatedFields(board),
                computeAverageNumberOfBeetles(board)
        );
    }

    private List<Square> findMostPopulatedFields(Board board) {
        // From a performance perspective this method could be optimized (iterating twice over all elements is not
        // necessary). Nevertheless, I decided not to do this optimization and to stick with better readability instead
        Long highestPopulation = board.getState()
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
         return board.getState()
                .entrySet()
                .stream()
                .filter(record -> Objects.equals(record.getValue(), highestPopulation))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Double computeAverageNumberOfBeetles(Board board) {
        return board.getState()
                .values()
                .stream()
                .filter(population -> population > 0)
                .mapToDouble(population -> population)
                .average()
                .orElse(0D);
    }
}

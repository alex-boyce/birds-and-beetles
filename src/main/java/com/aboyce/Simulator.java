package com.aboyce;

import com.aboyce.model.Board;
import com.aboyce.model.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simulator {

    public void simulateBirdAttacks(Board board, int numberOfAttacks, Random random) {
        for (int i = 1; i <= numberOfAttacks; i++) {
            simulateBirdAttack(board, random);
        }
    }

    private void simulateBirdAttack(Board board, Random random) {
        Map<Square, Long> updatedState = board.getState()
                .entrySet()
                .stream()
                .flatMap(record -> {
                    Square square = record.getKey();
                    Long numberOfBeetles = record.getValue();
                    return IntStream
                            .rangeClosed(1, numberOfBeetles.intValue())
                            .mapToObj(beetle -> determineBeetleJumpDestination(board, square, random));
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        board.setState(updatedState);
    }

    private Square determineBeetleJumpDestination(Board board, Square startingSquare, Random random) {
        boolean canMoveUp = startingSquare.getRow() > 1;
        boolean canMoveDown = startingSquare.getRow() < board.getNumberOfRows();
        boolean canMoveLeft = startingSquare.getColumn() > 1;
        boolean canMoveRight = startingSquare.getColumn() < board.getNumberOfColumns();

        List<Square> possibleDestinations = new ArrayList<>();
        if (canMoveUp) possibleDestinations.add(new Square(startingSquare.getRow() - 1, startingSquare.getColumn()));
        if (canMoveDown) possibleDestinations.add(new Square(startingSquare.getRow() + 1, startingSquare.getColumn()));
        if (canMoveLeft) possibleDestinations.add(new Square(startingSquare.getRow(), startingSquare.getColumn() - 1));
        if (canMoveRight) possibleDestinations.add(new Square(startingSquare.getRow(), startingSquare.getColumn() + 1));

        int randomIndex = random.nextInt(possibleDestinations.size());
        return possibleDestinations.get(randomIndex);
    }
}

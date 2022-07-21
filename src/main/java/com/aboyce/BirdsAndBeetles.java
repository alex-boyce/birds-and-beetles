package com.aboyce;

import com.aboyce.model.Board;

import java.util.Random;

public class BirdsAndBeetles {

    private static final long DEFAULT_SEED = 1234;

    public static void main(String[] args) {
        long seed = parseSeed(args);
        System.out.println("Using the following seed for pseudo-random number generation: " + seed);
        Random random = new Random(seed);
        Simulator simulator = new Simulator();
        StatisticsComputer statisticsComputer = new StatisticsComputer();
        Board board = new Board(15, 15);

        simulator.simulateBirdAttacks(board, 25, random);
        System.out.println("After 25 bird attacks the board looks like this:");
        board.prettyPrint();
        statisticsComputer.generateReport(board).prettyPrint();

        simulator.simulateBirdAttacks(board, 25, random);
        System.out.println("After 50 bird attacks the board looks like this:");
        board.prettyPrint();
        statisticsComputer.generateReport(board).prettyPrint();

        simulator.simulateBirdAttacks(board, 50, random);
        System.out.println("After 100 bird attacks the board looks like this:");
        board.prettyPrint();
        statisticsComputer.generateReport(board).prettyPrint();
    }

    private static long parseSeed(String[] args) {
        if (args.length > 0) {
            try {
                return Long.parseLong(args[0]);
            } catch(Exception ex) {
                return DEFAULT_SEED;
            }
        } else {
            return DEFAULT_SEED;
        }
    }
}

package aoc2024;

import aoc2024.day1.Day1;
import aoc2024.day2.Day2;

public class Driver {
    public static void main(String[] args) {
        AdventOfCodePuzzle[] puzzles = new AdventOfCodePuzzle[24];
        final String separator = "--------------------------------------------------------------------------------";

        puzzles[0] = new Day1();
        puzzles[1] = new Day2();

        System.out.println(separator);
        for (int i = 0; i < puzzles.length && puzzles[i] != null; i++) {
            System.out.println("Day " + (i + 1) + "\n");
            puzzles[i].solve();
            System.out.println(separator);
        }
    }
}

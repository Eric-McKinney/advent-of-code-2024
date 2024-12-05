package aoc2024;

import day1.Day1;

public class Driver {
    public static void main(String[] args) {
        AdventOfCodePuzzle[] puzzles = new AdventOfCodePuzzle[24];

        puzzles[0] = new Day1();

        for (int i = 0; i < puzzles.length && puzzles[i] != null; i++) {
            puzzles[i].solve();
        }
    }
}

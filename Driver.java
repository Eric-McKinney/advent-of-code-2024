package aoc2024;

import aoc2024.day1.Day1;
import aoc2024.day2.Day2;
import aoc2024.day3.Day3;
import aoc2024.day4.Day4;
import aoc2024.day5.Day5;
import aoc2024.day6.Day6;
import aoc2024.day7.Day7;

public class Driver {
    public static void main(String[] args) {
        AdventOfCodePuzzle[] puzzles = new AdventOfCodePuzzle[24];
        final String separator = "--------------------------------------------------------------------------------";

        puzzles[0] = new Day1();
        puzzles[1] = new Day2();
        puzzles[2] = new Day3();
        puzzles[3] = new Day4();
        puzzles[4] = new Day5();
        puzzles[5] = new Day6();
        puzzles[6] = new Day7();

        if (args.length == 1) {
            int day = Integer.decode(args[0]);

            System.out.println(separator);
            System.out.println("Day " + day + "\n");
            puzzles[day - 1].solve();
            System.out.println(separator);

            System.exit(0);
        }

        System.out.println(separator);
        for (int i = 0; i < puzzles.length && puzzles[i] != null; i++) {
            System.out.println("Day " + (i + 1) + "\n");
            puzzles[i].solve();
            System.out.println(separator);
        }
    }
}

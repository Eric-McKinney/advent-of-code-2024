package aoc2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AdventOfCodePuzzle {
    public String part1(ArrayList<String> inputLines) {
        return "";
    }

    public String part2(ArrayList<String> inputLines) {
        return "";
    }

    public void solve() {
        ArrayList<String> inputLines = AdventOfCodePuzzle.readInput();
        String ans1, ans2;

        ans1 = part1(inputLines);
        ans2 = part2(inputLines);

        System.out.println("part 1: " + ans1);
        System.out.println("part 2: " + ans2);
    }

    public static ArrayList<String> readInput() {
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Couldn't find puzzle input");
            System.exit(1);
        }

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        scanner.close();

        return lines;
    }
}

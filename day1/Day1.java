package aoc2024.day1;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 extends AdventOfCodePuzzle {
    public Day1() {
        this.day = 1;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        ArrayList<Integer> firstList = new ArrayList<>(), secondList = new ArrayList<>();
        int distSum = 0;

        for (String line : inputLines) {
            String[] row = line.split(" +");

            firstList.add(Integer.decode(row[0]));
            secondList.add(Integer.decode(row[1]));
        }

        Collections.sort(firstList);
        Collections.sort(secondList);

        for (int i = 0; i < firstList.size(); i++) {
            int distance = Math.abs(firstList.get(i) - secondList.get(i));

            distSum += distance;
        }

        return Integer.toString(distSum);
    }
}

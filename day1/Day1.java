package aoc2024.day1;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    @Override
    public String part2(ArrayList<String> inputLines) {
        ArrayList<Integer> firstList = new ArrayList<>();
        HashMap<Integer, Integer> secondListCounts = new HashMap<>();
        int similaritySum = 0;

        for (String line : inputLines) {
            String[] row = line.split(" +");
            int v1 = Integer.decode(row[0]), v2 = Integer.decode(row[1]);

            firstList.add(v1);
            
            if (secondListCounts.containsKey(v2)) {
                secondListCounts.put(v2, secondListCounts.get(v2) + 1);
            } else {
                secondListCounts.put(v2, 1);
            }

            // ensure all entries in firstList have a key in secondListCounts
            if (!secondListCounts.containsKey(v1)) {
                secondListCounts.put(v1, 0);
            }
        }

        for (int i : firstList) {
            int similarity = i * secondListCounts.get(i);

            similaritySum += similarity;
        }

        return Integer.toString(similaritySum);
    }
}

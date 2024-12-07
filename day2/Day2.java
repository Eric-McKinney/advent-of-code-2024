package aoc2024.day2;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 extends AdventOfCodePuzzle {
    public Day2() {
        this.day = 2;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        int numSafe = 0;

        for (String line : inputLines) {
            List<String> row = Arrays.asList(line.split(" +"));
            List<Integer> reports = new ArrayList<>();

            for (String num : row) {
                reports.add(Integer.decode(num));
            }
            
            numSafe += isSafe(reports) ? 1 : 0;
        }

        return Integer.toString(numSafe);
    }

    private boolean isSafe(List<Integer> reports) {
        boolean prevIsDecreasing = false;

        for (int i = 1; i < reports.size(); i++) {
            int diff = reports.get(i - 1) - reports.get(i);
            boolean isDecreasing = diff > 0;
            

            if (diff == 0 || Math.abs(diff) > 3 || (i > 1 && isDecreasing != prevIsDecreasing)) {
                return false;
            }

            prevIsDecreasing = isDecreasing;
        }

        return true;
    }
}

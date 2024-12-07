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
            ArrayList<Integer> report = parseReport(line);
            numSafe += reportIsSafe(report) ? 1 : 0;
        }

        return Integer.toString(numSafe);
    }

    @Override
    public String part2(ArrayList<String> inputLines) {
        int numSafe = 0;

        for (String line : inputLines) {
            ArrayList<Integer> report = parseReport(line);
            numSafe += reportIsSafe(report, true) ? 1 : 0;
        }

        return Integer.toString(numSafe);
    }

    private ArrayList<Integer> parseReport(String line) {
        ArrayList<Integer> report = new ArrayList<>();
        List<String> row = Arrays.asList(line.split(" +"));

        for (String num : row) {
            report.add(Integer.decode(num));
        }

        return report;
    }

    private boolean reportIsSafe(ArrayList<Integer> report) {
        boolean prevIsDecreasing = false;

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i) - report.get(i + 1);
            boolean isDecreasing = diff < 0;

            if (diff == 0 || Math.abs(diff) > 3 || (i > 0 && isDecreasing != prevIsDecreasing)) {
                return false;
            }

            prevIsDecreasing = isDecreasing;
        }

        return true;
    }

    private boolean reportIsSafe(ArrayList<Integer> report, boolean tolerateOneBadLevel) {
        if (!tolerateOneBadLevel) {
            return reportIsSafe(report);
        }

        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> reportCopy = (ArrayList<Integer>) report.clone();

            reportCopy.remove(i);
            
            if (reportIsSafe(reportCopy)) {
                return true;
            }
        }

        return false;
    }
}

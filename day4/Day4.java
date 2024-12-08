package aoc2024.day4;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Day4 extends AdventOfCodePuzzle {
    public Day4() {
        this.day = 4;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        return runPart(inputLines, true);
    }

    @Override
    public String part2(ArrayList<String> inputLines) {
        return runPart(inputLines, false);
    }

    private String runPart(ArrayList<String> inputLines, boolean isPart1) {
        XmasChecker checker = isPart1 ? new P1Checker(inputLines) : new P2Checker(inputLines);
        int qtyXmas = 0;

        for (int y = 0; y < inputLines.size(); y++) {
            for (int x = 0; x < inputLines.get(y).length(); x++) {
                qtyXmas += checker.checkXmas(x, y);
            }
        }

        return Integer.toString(qtyXmas);
    }

    private interface XmasChecker {
        public int checkXmas(int x, int y);
    }

    private class P1Checker implements XmasChecker {
        private ArrayList<String> wordSearch;

        public P1Checker(ArrayList<String> wordSearch) {
            this.wordSearch = wordSearch;
        }

        public int checkXmas(int x, int y) {
            int qtyXmas = 0;

            for (int xdelta = -1; xdelta <= 1; xdelta++) {
                for (int ydelta = -1; ydelta <= 1; ydelta++) {
                    qtyXmas += isXmas(x, y, xdelta, ydelta) ? 1 : 0;
                }
            }

            return qtyXmas;
        }

        public boolean isXmas(int x, int y, int xdelta, int ydelta) {
            char[] target = {'X', 'M', 'A', 'S'};

            for (int i = 0; i < target.length; i++) {
                if (y >= wordSearch.size() || y < 0 || x >= wordSearch.get(y).length() || x < 0) {
                    return false;
                }

                if (wordSearch.get(y).charAt(x) != target[i]) {
                    return false;
                }

                x += xdelta;
                y += ydelta;
            }

            return true;
        }
    }

    private class P2Checker implements XmasChecker {
        private ArrayList<String> wordSearch;

        public P2Checker(ArrayList<String> wordSearch) {
            this.wordSearch = wordSearch;
        }

        public int checkXmas(int x, int y) {
            Set<Character> diagonal = new HashSet<>();

            if (wordSearch.get(y).charAt(x) != 'A'
                || x == 0 || x == wordSearch.get(y).length() - 1
                || y == 0 || y == wordSearch.size() - 1) {

                return 0;
            }

            diagonal.add(wordSearch.get(y - 1).charAt(x - 1));
            diagonal.add(wordSearch.get(y + 1).charAt(x + 1));

            if (!diagonal.contains('M') || !diagonal.contains('S')) {
                return 0;
            }

            diagonal.clear();
            diagonal.add(wordSearch.get(y + 1).charAt(x - 1));
            diagonal.add(wordSearch.get(y - 1).charAt(x + 1));

            if (!diagonal.contains('M') || !diagonal.contains('S')) {
                return 0;
            }

            return 1;
        }
    }
}

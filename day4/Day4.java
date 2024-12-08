package aoc2024.day4;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;

public class Day4 extends AdventOfCodePuzzle {
    public Day4() {
        this.day = 4;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        int qtyXmas = 0;

        for (int x = 0; x < inputLines.get(0).length(); x++) {
            for (int y = 0; y < inputLines.size(); y++) {
                qtyXmas += checkXmas(inputLines, x, y);
            }
        }

        return Integer.toString(qtyXmas);
    }

    // checks coords for xmas and returns quantity found
    private int checkXmas(ArrayList<String> wordSearch, int x, int y) {
        int qtyXmas = 0;

        for (int xdelta = -1; xdelta <= 1; xdelta++) {
            for (int ydelta = -1; ydelta <= 1; ydelta++) {
                qtyXmas += isXmas(wordSearch, x, y, xdelta, ydelta) ? 1 : 0;
            }
        }

        return qtyXmas;
    }

    private boolean isXmas(ArrayList<String> wordSearch, int x, int y, int xdelta, int ydelta) {
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

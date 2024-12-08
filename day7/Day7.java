package aoc2024.day7;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;

public class Day7 extends AdventOfCodePuzzle {
    public Day7() {
        this.day = 7;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        long validEqnAnsSum = 0;

        for (String line : inputLines) {
            CalibrationEquation eqn = new CalibrationEquation(line);
            
            if (eqn.hasSolution()) {
                validEqnAnsSum += eqn.getAns();
            }
        }

        return Long.toString(validEqnAnsSum);
    }

    private class CalibrationEquation {
        private long ans;
        private ArrayList<Long> operands;

        public CalibrationEquation(String eq) {
            String[] nums = eq.split(":? +");
            this.operands = new ArrayList<>();

            this.ans = Long.decode(nums[0]);
            
            for (int i = 1; i < nums.length; i++) {
                operands.add(Long.decode(nums[i]));
            }
        }

        public long getAns() {
            return ans;
        }

        public boolean hasSolution() {
            int totalOperatorCombos = (int) Math.pow(2, operands.size() - 1);

            // operatorCombo determines the combination of + and * to apply
            // each bit represents either add (bit is 0) or multiply (bit is 1)
            for (int operatorCombo = 0; operatorCombo < totalOperatorCombos; operatorCombo++) {
                long result = operands.get(0);

                for (int i = 0; i < operands.size() - 1; i++) {
                    // check if i-th bit of operatorCombo is 0
                    if ((operatorCombo & (long) Math.pow(2, i)) == 0) {
                        result += operands.get(i + 1);
                    } else {
                        result *= operands.get(i + 1);
                    }
                }

                if (result == ans) {
                    return true;
                }
            }

            return false;
        }
    }
}

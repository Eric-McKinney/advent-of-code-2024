package aoc2024.day7;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;

public class Day7 extends AdventOfCodePuzzle {
    public Day7() {
        this.day = 7;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        return runPart(inputLines, true);
    }

    @Override
    public String part2(ArrayList<String> inputLines) {
        return runPart(inputLines, false);
    }

    private String runPart(ArrayList<String> inputLines, boolean part1) {
        long validEqnAnsSum = 0;

        for (String line : inputLines) {
            CalibrationEquation eqn = new CalibrationEquation(line);
            
            if (eqn.hasSolution(part1)) {
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

        public boolean hasSolution(boolean part1) {
            int numOperators = part1 ? 2 : 3;
            int totalOperatorCombos = (int) Math.pow(numOperators, operands.size() - 1);

            // operatorCombo encodes the combination of operators to apply
            // part1: each bit represents either add (bit is 0) or multiply (bit is 1)
            // part2: each trinary (base 3) bit represents either add, mult, or concat (0, 1, 2 respectively)
            for (int operatorCombo = 0; operatorCombo < totalOperatorCombos; operatorCombo++) {
                long result = operands.get(0);

                for (int i = 0; i < operands.size() - 1; i++) {
                    // check i-th bit of operatorCombo for operator to use
                    switch(accessIthBit(numOperators, operatorCombo, i)) {
                        case 0:
                            result += operands.get(i + 1);
                            break;
                        case 1:
                            result *= operands.get(i + 1);
                            break;
                        case 2:
                            result = Long.decode(Long.toString(result) + Long.toString(operands.get(i + 1)));
                            break;
                        default:
                            throw new UnsupportedOperationException(
                                "Number of operators exceeded number of available operators"
                            );
                    }
                }

                if (result == ans) {
                    return true;
                }
            }

            return false;
        }

        // access i-th bit of x in the given base
        private static int accessIthBit(int base, int x, int i) {
            int upperBits = x % (int) Math.pow(base, i + 1);
            int lowerBits = x % (int) Math.pow(base, i);
            int bitToAccess = upperBits - lowerBits;

            return (int) (bitToAccess / Math.pow(base, i));
        }
    }
}

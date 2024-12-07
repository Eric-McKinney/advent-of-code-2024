package aoc2024.day3;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day3 extends AdventOfCodePuzzle {
    public Day3() {
        this.day = 3;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        int sum = 0;

        for (String line : inputLines) {
            ArrayList<String> tokens = tokenize(line);
            ArrayList<MulOp> ops = parse(tokens);
            sum += sumEvalAll(ops);
        }

        return Integer.toString(sum);
    }
    
    /*
    @Override
    public String part2(ArrayList<String> inputLines) {
        int sum = 0;

        for (String line : inputLines) {
            ArrayList<String> tokens = tokenize(line);
        }
    }
    */

    private class MulOp {
        private int arg1, arg2;

        public MulOp(int arg1, int arg2) {
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        public int eval() {
            return arg1 * arg2;
        }
    }

    private ArrayList<String> tokenize(String line) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern op = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher m = op.matcher(line);

        while (m.find()) {
            int matchLength = m.end();
            tokens.add(m.group());
        }

        return tokens;
    }

    private ArrayList<MulOp> parse(ArrayList<String> tokens) {
        ArrayList<MulOp> ops = new ArrayList<>();

        for (String token : tokens) {
            int arg1, arg2, openParenIdx, commaIdx, closeParenIdx;

            if (token.substring(0,3).compareTo("mul") == 0) {
                openParenIdx = token.indexOf("(");
                commaIdx = token.indexOf(",");
                closeParenIdx = token.indexOf(")");

                arg1 = Integer.decode(token.substring(openParenIdx + 1, commaIdx));
                arg2 = Integer.decode(token.substring(commaIdx + 1, closeParenIdx));

                ops.add(new MulOp(arg1, arg2));
            } else {
                throw new UnsupportedOperationException("Unknown operation");
            }
        }

        return ops;
    }

    private int sumEvalAll(ArrayList<MulOp> ops) {
        int sum = 0;

        for (MulOp op : ops) {
            sum += op.eval();
        }

        return sum;
    }
}

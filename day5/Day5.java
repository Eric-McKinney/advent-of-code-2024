package aoc2024.day5;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.HashMap;

public class Day5 extends AdventOfCodePuzzle {
    public Day5() {
        this.day = 5;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        int middlePageNumSum = 0;
        HashMap<Boolean,ArrayList<ArrayList<Integer>>> followsRules = createAndApplyRuleSet(inputLines);

        for (ArrayList<Integer> pageNums : followsRules.get(true)) {
            middlePageNumSum += pageNums.get(pageNums.size() / 2);
        }

        return Integer.toString(middlePageNumSum);
    }

    @Override
    public String part2(ArrayList<String> inputLines) {
        int middlePageNumSum = 0;

        return Integer.toString(middlePageNumSum);
    }

    private HashMap<Boolean,ArrayList<ArrayList<Integer>>> createAndApplyRuleSet(ArrayList<String> inputLines) {
        RuleSet rs = new RuleSet();
        HashMap<Boolean,ArrayList<ArrayList<Integer>>> followsRules = new HashMap<>();

        followsRules.put(true, new ArrayList<ArrayList<Integer>>());
        followsRules.put(false, new ArrayList<ArrayList<Integer>>());

        for (String line : inputLines) {
            if (line.contains("|")) {
                rs.addRule(line);
            } else if (line.contains(",")) {
                String[] nums = line.split(",");
                ArrayList<Integer> pageNums = new ArrayList<>();

                for (String num : nums) {
                    pageNums.add(Integer.decode(num));
                }

                followsRules.get(rs.followsRuleSet(pageNums)).add(pageNums);
            }
        }

        return followsRules;
    }

    private class RuleSet {
        private ArrayList<Rule> rules;

        public RuleSet() {
            rules = new ArrayList<>();
        }

        private class Rule {
            private int first, second;

            public Rule(int first, int second) {
                this.first = first;
                this.second = second;
            }

            public boolean followsRule(ArrayList<Integer> pageNumbers) {
                int firstIdx = pageNumbers.indexOf(first), secondIdx = pageNumbers.indexOf(second);

                if (firstIdx == -1 || secondIdx == -1) {
                    return true;
                }

                return firstIdx < secondIdx;
            }
        }

        public void addRule(String rule) {
            String[] nums = rule.split("\\|");

            rules.add(new Rule(Integer.decode(nums[0]), Integer.decode(nums[1])));
        }

        public boolean followsRuleSet(ArrayList<Integer> pageNumbers) {
            for (Rule r : rules) {
                if (!r.followsRule(pageNumbers)) {
                    return false;
                }
            }

            return true;
        }
    }
}

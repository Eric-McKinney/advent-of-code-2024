package aoc2024.day5;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Day5 extends AdventOfCodePuzzle {
    public Day5() {
        this.day = 5;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        int middlePageNumSum = 0;
        HashMap<Boolean,ArrayList<ArrayList<Integer>>> followsRules = createAndCheckRuleSet(inputLines);

        for (ArrayList<Integer> pageNums : followsRules.get(true)) {
            middlePageNumSum += pageNums.get(pageNums.size() / 2);
        }

        return Integer.toString(middlePageNumSum);
    }

    @Override
    public String part2(ArrayList<String> inputLines) {
        int middlePageNumSum = 0;
        RuleSet rs = new RuleSet();
        HashMap<Boolean,ArrayList<ArrayList<Integer>>> followsRules = createAndCheckRuleSet(inputLines, rs);

        for (ArrayList<Integer> pageNums : followsRules.get(false)) {
            rs.enforceRules(pageNums);
            middlePageNumSum += pageNums.get(pageNums.size() / 2);
        }

        return Integer.toString(middlePageNumSum);
    }

    private HashMap<Boolean,ArrayList<ArrayList<Integer>>> createAndCheckRuleSet(ArrayList<String> inputLines) {
        RuleSet rs = new RuleSet();

        return createAndCheckRuleSet(inputLines, rs);
    }

    private HashMap<Boolean,ArrayList<ArrayList<Integer>>> createAndCheckRuleSet(ArrayList<String> inputLines, RuleSet rs) {
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

    private class RuleSet implements Comparator<Integer> {
        private ArrayList<Rule> rules;

        public RuleSet() {
            rules = new ArrayList<>();
        }

        private class Rule {
            public int first, second;

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

        public void enforceRules(ArrayList<Integer> pageNumbers) {
            pageNumbers.sort(this);
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            for (Rule r : rules) {
                if (r.first == o1 && r.second == o2) {
                    return -1;
                } else if (r.first == o2 && r.second == o1) {
                    return 1;
                }
            }

            return 0;
        }
    }
}

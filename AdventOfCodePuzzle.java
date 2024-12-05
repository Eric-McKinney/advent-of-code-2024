import java.util.ArrayList;

public abstract class AdventOfCodePuzzle {
    private final String input = "input.txt";

    public String part1(ArrayList<String> inputLines) {
        return "";
    }

    public String part2(ArrayList<String> inputLines) {
        return "";
    }

    public static ArrayList<String> readInput() {
        ArrayList<String> lines = new ArrayList<>();

        return lines;
    }

    public static void main(String[] args) {
        AdventOfCodePuzzle puzzle = new AdventOfCodePuzzle();
        ArrayList<String> inputLines = AdventOfCodePuzzle.readInput();
        String ans1, ans2;

        ans1 = puzzle.part1(inputLines);
        ans2 = puzzle.part2(inputLines);

        System.out.println("part 1: " + ans1);
        System.out.println("part 2: " + ans2);
    }
}

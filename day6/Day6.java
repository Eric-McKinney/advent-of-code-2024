package aoc2024.day6;

import aoc2024.AdventOfCodePuzzle;
import java.util.ArrayList;
import java.util.HashSet;

public class Day6 extends AdventOfCodePuzzle {
    public Day6() {
        this.day = 6;
    }

    @Override
    public String part1(ArrayList<String> inputLines) {
        Board board = new Board(inputLines);

        board.simulate();

        return Integer.toString(board.getNumUniquePosnsGuardVisited());
    }

    private class Position {
        public int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (obj == null || this.getClass() != obj.getClass()) {
                return false;
            }

            Position p = (Position) obj;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return (int) ((x + 1) * (y + 1) * (y + 1) - (x + 1) * y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private class Board {
        private ArrayList<String> grid;
        private HashSet<Position> obstructions;
        private HashSet<Position> posnsGuardVisited;
        private Position guardPosition;
        private enum Bearing {
            N,
            E,
            S,
            W
        }
        private Bearing guardBearing;

        public Board(ArrayList<String> inputLines) {
            this.grid = inputLines;
            this.obstructions = new HashSet<>();
            this.posnsGuardVisited = new HashSet<>();
            this.guardBearing = Bearing.N;

            for (int y = 0; y < grid.size(); y++) {
                for (int x = 0; x < grid.get(y).length(); x++) {
                    char c = grid.get(y).charAt(x);

                    if (c == '^') {
                        this.guardPosition = new Position(x, y);
                        posnsGuardVisited.add(guardPosition);
                    } else if (c == '#') {
                        obstructions.add(new Position(x, y));
                    }
                }
            }
        }

        public void simulate() {
            while (guardInBounds()) {
                tick();
            }
        }

        public void tick() {
            int nextX = guardPosition.x, nextY = guardPosition.y;
            Bearing nextBearing = guardBearing;
            Position nextPos;

            switch(guardBearing) {
                case Bearing.N:
                    nextY = guardPosition.y - 1;
                    nextBearing = Bearing.E;
                    break;
                case Bearing.E:
                    nextX = guardPosition.x + 1;
                    nextBearing = Bearing.S;
                    break;
                case Bearing.S:
                    nextY = guardPosition.y + 1;
                    nextBearing = Bearing.W;
                    break;
                case Bearing.W:
                    nextX = guardPosition.x - 1;
                    nextBearing = Bearing.N;
            }

            nextPos = new Position(nextX, nextY);

            if (obstructions.contains(nextPos)) {
                guardBearing = nextBearing;
            } else {
                guardPosition = nextPos;

                if (guardInBounds()) {
                    posnsGuardVisited.add(guardPosition);
                }
            }
        }

        public boolean guardInBounds() {
            int x = guardPosition.x, y = guardPosition.y;
            return (y >= 0 && y < grid.size() && x >= 0 && x < grid.get(y).length());
        }

        public int getNumUniquePosnsGuardVisited() {
            return posnsGuardVisited.size();
        }
    }
}

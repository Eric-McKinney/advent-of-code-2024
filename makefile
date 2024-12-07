.PHONY: all run clean

days=$(wildcard day*/Day*.java)

all: Driver.java AdventOfCodePuzzle.java $(days)
	javac -d . $^

run:
	java aoc2024.Driver

clean:
	rm -rf ./aoc2024

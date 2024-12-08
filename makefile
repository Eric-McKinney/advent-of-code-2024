.PHONY: all compile run clean

days=$(wildcard day*/Day*.java)

all: compile run

compile: Driver.java AdventOfCodePuzzle.java $(days)
	@javac -d . $^

run:
	@java aoc2024.Driver

day%: compile
	@java aoc2024.Driver $*

clean:
	rm -rf ./aoc2024

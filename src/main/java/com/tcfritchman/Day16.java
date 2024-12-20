package com.tcfritchman;

import com.tcfritchman.common.Vec2;

import java.util.Arrays;

public class Day16 {

    static String exampleInput = """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
            """;

    static String realInput = """
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        char[][] grid = parseGrid(input);
        int size = grid.length;

        int[][] scores = new int[size][size];
        for (int[] scoresRow : scores) {
            Arrays.fill(scoresRow, Integer.MAX_VALUE);
        }
        
        var start = findStart(grid);
        return findLowestScore(start, new Vec2(1, 0), grid, scores);
    }

    // TODO: Maybe try working backward instead?
    private static int findLowestScore(Vec2 position, Vec2 direction, char[][] grid, int[][] scores) {
        var row = position.y();
        var col = position.x();
        if (grid[row][col] == 'E') {
            return 0;
        }

        // try straight
        var straight = position.add(direction);
        if (grid[straight.y()][straight.x()] != '#') {
            return 1 + findLowestScore(straight, direction, grid, scores);
        }

        // try left
        var leftDir = direction.rotateCounterClockwise();
        var left = position.add(leftDir);
        if (grid[left.y()][left.x()] != '#') {
            return 1001 + findLowestScore(left, leftDir, grid, scores);
        }

        // try right
        var rightDir = direction.rotateClockwise();
        var right = position.add(rightDir);
        if (grid[right.y()][right.x()] != '#') {
            return 1001 + findLowestScore(right, rightDir, grid, scores);
        }

        return -1;
    }

    private static char[][] parseGrid(String input) {
        var lines = input.split("\n");
        var size = lines.length;
        var grid = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = lines[row].charAt(col);
            }
        }
        return grid;
    }

    private static Vec2 findStart(char[][] grid) {
        var size = grid.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == 'S') {
                    return new Vec2(col, row);
                }
            }
        }
        throw new RuntimeException("Couldn't find the start");
    }

    private static Object part2(String input) {
        return null;
    }
}
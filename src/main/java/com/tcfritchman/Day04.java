package com.tcfritchman;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day04 {

    static String exampleInput = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;

    static String realInput = """
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
//        System.out.println("Part 1 --- real: " + part1(realInput));
//        System.out.println("Part 2 --- example: " + part2(exampleInput));
//        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        char[][] linesE = makeGrid(input);
        char[][] linesS = rotate(linesE);
        char[][] linesW = rotate(linesS);
        char[][] linesN = rotate(linesW);
        char[][] linesSE = diagonalize(linesE);
        char[][] linesSW = diagonalize(linesS);
        char[][] linesNW = diagonalize(linesW);
        char[][] linesNE = diagonalize(linesN);

        List<String> allLines = Stream.of(linesE, linesS, linesW, linesN, linesSE, linesSW, linesNW, linesNE)
                .flatMap(Arrays::stream)
                .map(String::new)
                .toList();

        Pattern pattern = Pattern.compile("XMAS");

        return allLines.stream()
                .mapToInt(line -> {
                    Matcher matcher = pattern.matcher(line);
                    int n = 0;
                    while (matcher.find()) {
                        n++;
                    }
                    return n;
                })
                .sum();
    }

    private static Object part2(String input) {
        return null;
    }


    private static char[][] makeGrid(String input) {
        String[] lines = input.split("\n");
        int size = lines.length;
        char[][] arr = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                arr[row][col] = lines[row].charAt(col);
            }
        }
        return arr;
    }

    private static char[][] rotate(char[][] input) {
        int size = input.length;
        char[][] arr = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                arr[row][col] = input[col][row];
            }
        }
        return arr;
    }

    private static char[][] diagonalize(char[][] input) {
        int size = input.length;
        int rows = (size * 2) - 1;
        char[][] arr = new char[rows][size];

        // center diagonal
        for (int i = 0; i < size; i++) {
            arr[0][i] = input[i][i];
        }

        // upper diagonals
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                arr[i][j] = input[j][j + i];
            }
        }

        // lower diagonals
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                arr[size - 1 + i][j] = input[i + j][j];
            }
        }

        return arr;
    }
}
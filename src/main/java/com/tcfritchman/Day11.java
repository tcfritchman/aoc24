package com.tcfritchman;

import java.math.BigInteger;
import java.util.*;

public class Day11 {

    static String exampleInput = """
            125 17""";

    static String realInput = """
            2 77706 5847 9258441 0 741 883933 12""";

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        List<BigInteger> l = parseInput(input);
        return countStonesIterative(25, l);
    }

    private static Object part2(String input) {
        List<BigInteger> l = parseInput(input);
        return countStonesRecursiveMemoized(75, l);
    }

    private static List<BigInteger> parseInput(String input) {
        return Arrays.stream(input.split(" ")).map(BigInteger::new).toList();
    }

    private static long countStonesRecursiveMemoized(int iterations, List<BigInteger> l) {
        List<Map<BigInteger, Long>> mem = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            // Save a separate mapping at each level
            mem.add(new HashMap<>());
        }
        return l.stream()
                .mapToLong(value -> countStonesRecursiveInner(iterations, value, mem))
                .sum();
    }

    private static long countStonesRecursiveInner(int iterations, BigInteger value, List<Map<BigInteger, Long>> mem) {
        if (iterations == 0) {
            return 1;
        }

        // Return the pre-calculated result if exists
        Map<BigInteger, Long> map = mem.get(iterations - 1);
        if (map.containsKey(value)) {
            return map.get(value);
        }

        long result;
        if (value.equals(BigInteger.ZERO)) {
            result = countStonesRecursiveInner(iterations - 1, BigInteger.ONE, mem);
        } else if (hasEvenDigits(value)) {
            List<BigInteger> split = splitDigits(value);
            result = countStonesRecursiveInner(iterations - 1, split.get(0), mem)
                    + countStonesRecursiveInner(iterations - 1, split.get(1), mem);
        } else {
            result = countStonesRecursiveInner(iterations - 1, value.multiply(BigInteger.valueOf(2024L)), mem);
        }

        // Save the result
        map.put(value, result);

        return result;
    }

    private static long countStonesIterative(int iterations, List<BigInteger> l) {
        for (int i = 0; i < iterations; i++) {
            l = blink(l);
        }
        return l.size();
    }

    private static List<BigInteger> blink(List<BigInteger> l) {
        List<BigInteger> newList = new ArrayList<>();
        for (BigInteger value : l) {
            if (value.equals(BigInteger.ZERO)) {
                newList.add(BigInteger.ONE);
            } else if (hasEvenDigits(value)) {
                newList.addAll(splitDigits(value));
            } else {
                newList.add(value.multiply(BigInteger.valueOf(2024L)));
            }
        }
        return newList;
    }

    private static boolean hasEvenDigits(BigInteger value) {
        return value.toString().length() % 2 == 0;
    }

    private static List<BigInteger> splitDigits(BigInteger value) {
        String str = value.toString();
        int length = str.length();
        String left = str.substring(0, length / 2);
        String right = str.substring(length / 2, length);
        return List.of(new BigInteger(left), new BigInteger(right));
    }
}
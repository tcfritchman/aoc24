package com.tcfritchman;

import java.util.Objects;

public class Day24 {

    static String exampleInput = """
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

//        Graph<String, >

        return null;
    }

    private static Object part2(String input) {
        return null;
    }

    private static class Gate {
        private final String name;
        private final GateType type;

        public Gate(String name, GateType type) {
            this.name = name;
            this.type = type;
        }

        public boolean operate(boolean a, boolean b) {
            switch(type) {
                case AND -> {
                    return a & b;
                }
                case OR -> {
                    return a | b;
                }
                case XOR -> {
                    return a ^ b;
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Gate gate = (Gate) o;
            return Objects.equals(name, gate.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    enum GateType {AND, OR, XOR}
}
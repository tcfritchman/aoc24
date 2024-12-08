package com.tcfritchman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {

    static String exampleInput = """
            3267: 81 40 27
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
        int total = 0;
        for (String line : input.split("\n")) {
            int value = Integer.parseInt(line.split(": ")[0]);
            List<Integer> numbers = Arrays.stream(line.split(": ")[1].split(" ")).map(Integer::parseInt).toList();

            List<Equation> equations = createExpressionTree(numbers)
                    .stream()
                    .map(expression -> new Equation(value, expression))
                    .toList();

            if(equations.stream().anyMatch(Equation::isBalanced)) {
                total += value;
            }
        }

        return total;
    }

    private static List<Expression> createExpressionTree(List<Integer> numbers) {
        int n = numbers.getLast();
        return inner(numbers.subList(0, numbers.size() - 1), new ConstantExpression(n));
    }

    private static List<Expression> inner(List<Integer> numbers, Expression ex) {
        if (numbers.isEmpty()) {
            return List.of(ex);
        }
        int n = numbers.getLast();
        List<Expression> ex1 = inner(numbers.subList(0, numbers.size() - 1), new BinaryExpression(new ConstantExpression(n), ex, Operator.ADD));
        List<Expression> ex2 = inner(numbers.subList(0, numbers.size() - 1), new BinaryExpression(new ConstantExpression(n), ex, Operator.MULTIPLY));
        List<Expression> l = new ArrayList<>();
        l.addAll(ex1);
        l.addAll(ex2);
        return l;
    }

    private static Object part2(String input) {
        return null;
    }

    enum Operator {
        ADD, MULTIPLY
    }

    interface Expression {
        int evaluate();
    }

    static class BinaryExpression implements Expression {
        final Expression left;
        final Expression right;
        final Operator operator;

        public BinaryExpression(Expression left, Expression right, Operator operator) {
            this.left = left;
            this.right = right;
            this.operator = operator;
        }

        @Override
        public int evaluate() {
            if (operator.equals(Operator.MULTIPLY)) {
                return left.evaluate() * right.evaluate();
            } else {
                return left.evaluate() + right.evaluate();
            }
        }
    }

    static class ConstantExpression implements Expression {
        final int val;

        public ConstantExpression(int val) {
            this.val = val;
        }

        @Override
        public int evaluate() {
            return val;
        }
    }


    static class Equation {
        int value;
        Expression expression;

        public Equation(int value, Expression expression) {
            this.value = value;
            this.expression = expression;
        }

        boolean isBalanced() {
            return value == expression.evaluate();
        }
    }
}

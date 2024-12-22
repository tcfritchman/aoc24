package com.tcfritchman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 {

    static String exampleInput = """
            Register A: 729
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
            """;

    static String example2 = """
            Register A: 2024
            Register B: 0
            Register C: 0
            
            Program: 0,3,5,4,3,0
            """;

    static String realInput = """
            Register A: 21539243
            Register B: 0
            Register C: 0
            
            Program: 2,4,1,3,7,5,1,5,0,3,4,1,5,5,3,0
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(example2));
//        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        Computer computer = new Computer(input);

        while(!computer.isHalted()) {
            computer.run();
        }

        return computer.output.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    private static Object part2(String input) {
        List<Integer> expectedOutput = Arrays.stream(input.split("\n")[4].split(": ")[1].split(",")).map(Integer::parseInt).toList();
        List<Integer> actualOutput = List.of();
        int initialRegisterA = -1;
        while (!actualOutput.equals(expectedOutput)) {
            initialRegisterA++;
//            System.out.println(initialRegisterA);
            Computer computer = new Computer(input);
            computer.setRegisterA(initialRegisterA);
            while (!computer.isHalted()) {
                computer.run();
            }
            actualOutput = computer.output;
        }
        return initialRegisterA;
    }

    private static class Computer {
        int instructionPointer = 0;
        int registerA;
        int registerB;
        int registerC;
        int[] memory;
        List<Integer> output;

        public Computer(String input) {
            String[] inputLines = input.split("\n");
            this.registerA = Integer.parseInt(inputLines[0].split(": ")[1]);
            this.registerB = Integer.parseInt(inputLines[1].split(": ")[1]);
            this.registerC = Integer.parseInt(inputLines[2].split(": ")[1]);
            var program = Arrays.stream(inputLines[4].split(": ")[1].split(",")).map(Integer::parseInt).toList();
            this.memory = new int[program.size()];
            for (int i = 0; i < program.size(); i++) {
                memory[i] = program.get(i);
            }
            output = new ArrayList<>();
        }

        public void run() {
            if (isHalted()) return;
            var instruction = parseInstruction(memory[instructionPointer], memory[instructionPointer + 1]);
            instruction.run(this);
        }

        public boolean isHalted() {
            return instructionPointer >= memory.length;
        }

        public void setRegisterA(int registerA) {
            this.registerA = registerA;
        }

        private Instruction parseInstruction(int opcode, int operand) {
            switch (opcode) {
                case 0:
                    return new ADV(operand);
                case 1:
                    return new BXL(operand);
                case 2:
                    return new BST(operand);
                case 3:
                    return new JNZ(operand);
                case 4:
                    return new BXC(operand);
                case 5:
                    return new OUT(operand);
                case 6:
                    return new BDV(operand);
                case 7:
                    return new CDV(operand);
                default:
                    throw new RuntimeException("Illegal opcode");
            }
        }
    }

    private static abstract class Instruction {
        final int operand;

        public Instruction(int operand) {
            this.operand = operand;
        }

        abstract void run(Computer computer);

        static int comboOperand(int operand, Computer computer) {
            if (operand == 4) {
                return computer.registerA;
            } else if (operand == 5) {
                return computer.registerB;
            } else if (operand == 6) {
                return computer.registerC;
            } else {
                return operand;
            }
        }
    }

    private static class ADV extends Instruction {

        public ADV(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerA = computer.registerA / (int)Math.pow(2, comboOperand(operand, computer));
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "ADV combo(" + operand + ")";
        }
    }

    private static class BXL extends Instruction {

        public BXL(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerB = operand ^ computer.registerB;
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "BXL literal(" + operand + ")";
        }
    }

    private static class BST extends Instruction {

        public BST(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerB = comboOperand(operand, computer) % 8;
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "BST combo(" + operand + ")";
        }
    }

    private static class JNZ extends Instruction {

        public JNZ(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            if (computer.registerA != 0) {
                computer.instructionPointer = operand;
            } else {
                computer.instructionPointer += 2;
            }
        }

        @Override
        public String toString() {
            return "JNZ literal(" + operand + ")";
        }
    }

    private static class BXC extends Instruction {

        public BXC(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerB = computer.registerB ^ computer.registerC;
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "BXC";
        }
    }

    private static class OUT extends Instruction {

        public OUT(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.output.add(comboOperand(operand, computer) % 8);
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "OUT combo(" + operand + ")";
        }
    }

    private static class BDV extends Instruction {

        public BDV(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerB = computer.registerA / (int)Math.pow(2, comboOperand(operand, computer));
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "BDV combo(" + operand + ")";
        }
    }

    private static class CDV extends Instruction {

        public CDV(int operand) {
            super(operand);
        }

        @Override
        void run(Computer computer) {
            computer.registerC = computer.registerA / (int)Math.pow(2, comboOperand(operand, computer));
            computer.instructionPointer += 2;
        }

        @Override
        public String toString() {
            return "CDV combo(" + operand + ")";
        }
    }
}
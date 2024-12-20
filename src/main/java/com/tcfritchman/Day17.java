package com.tcfritchman;

import java.util.List;

public class Day17 {

    static String exampleInput = """
            Register A: 729
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
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
        return null;
    }

    private static Object part2(String input) {
        return null;
    }

    private static class Computer {
        int instructionPointer = 0;
        int registerA;
        int registerB;
        int registerC;
        int[] memory;
        List<Integer> output;

        public Computer(int registerA, int registerB, int registerC, int[] memory) {
            this.registerA = registerA;
            this.registerB = registerB;
            this.registerC = registerC;
            this.memory = memory;
        }

        public void run() {
            if (isHalted()) return;
            var instruction = parseInstruction(memory[instructionPointer], memory[instructionPointer + 1]);
            instruction.run(this);
        }

        public boolean isHalted() {
            return instructionPointer >= memory.length;
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
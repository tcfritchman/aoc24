package com.tcfritchman;

import com.tcfritchman.common.Pair;
import com.tcfritchman.common.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 {

    static String exampleInput = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """;

    static String realInput = """
            .....................................O..V.........
            ..................................................
            ................................O.........Z.......
            ....W....................................V....v...
            ........................m................8........
            .....................................n........Z..v
            .............F.....3...n....5m....................
            ................................................V.
            ................3............iv....Z.............V
            ...........................O..n..i........p......H
            ......W..6..............................i.........
            ......................................b...........
            ..................................n........p......
            ........M.......c...........m..5......1...........
            ...M............................L..5..A...........
            ...w...........9.............F5..................q
            .W.....................................q....p.....
            .......W........r.......H.....LA......q...........
            ................4.F....................A..........
            ........3.......a.....F...................A..L....
            ....ME...............................Q..........q.
            .E..................ih...................Z........
            ................E...H...........h.................
            .........m.........X..............................
            ..................0......C.................h......
            .M......l.................Q.h.....................
            ..........C..............0........................
            .............lX............3.c....................
            ......8.X.........c....r..a......H.....9..........
            .................QE.....C.........................
            ..R................a........Q...................7.
            ...........................a......................
            l..........X.R............1..I..........9.........
            .................0R..............b.....z......x...
            .......l.....w....r..........................b....
            .8..........0...................P1z...............
            .............c.........................L..........
            .................C..N............o............9...
            ...........e..f..N................................
            8.............................B...................
            ...........4...............................x......
            ....w....RY..........4.......................P....
            .........yw.....Y.............o2...............7..
            ..6y........4..............fo..............7......
            .........Y..6............o......................x.
            .....Y....e.....y..I.r...........2................
            ....e.............................P.......z.bB....
            .............6.................B........7......x..
            ..y.N........f...........1....I....z....B.........
            .....e....f.............I.................2.......
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        var size = getSize(input);
        var antennaMap = parseInput(input);

        Set<Position> antiNodeLocations = antennaMap.values().stream()
                .flatMap(positions -> getAllPairs(positions).stream())
                .map(pair -> getAntiNodes1(pair, size))
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return antiNodeLocations.size();
    }

    private static Object part2(String input) {
        var size = getSize(input);
        var antennaMap = parseInput(input);

        Set<Position> antiNodeLocations = antennaMap.values().stream()
                .flatMap(positions -> getAllPairs(positions).stream())
                .map(pair -> getAntiNodes2(pair, size))
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        return antiNodeLocations.size();
    }

    private static int getSize(String input) {
        return input.split("\n").length;
    }

    private static Map<Character, List<Position>> parseInput(String input) {
        String[] lines = input.split("\n");
        Map<Character, List<Position>> antennaMap = new HashMap<>();
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '.') {
                    if (!antennaMap.containsKey(c)) {
                        antennaMap.put(c, new ArrayList<>());
                    }
                    antennaMap.get(c).add(new Position(row, col));
                }
            }
        }
        return antennaMap;
    }

    private static List<Pair<Position>> getAllPairs(List<Position> positions) {
        if (positions.size() < 2) {
            return List.of();
        }
        List<Pair<Position>> l = new ArrayList<>();
        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                var pair = new Pair<>(positions.get(i), positions.get(j));
                l.add(pair);
            }
        }
        return l;
    }

    private static List<Position> getAntiNodes1(Pair<Position> positions, int size) {
        Position left = positions.left();
        Position right = positions.right();
        Position leftAntiNode = new Position(left.row() + (left.row() - right.row()), left.col() + (left.col() - right.col()));
        Position rightAntiNode = new Position(right.row() + (right.row() - left.row()), right.col() + (right.col() - left.col()));
        List<Position> antiNodes = new ArrayList<>();
        if (isInBounds(leftAntiNode, size)) antiNodes.add(leftAntiNode);
        if (isInBounds(rightAntiNode, size)) antiNodes.add(rightAntiNode);
        return antiNodes;
    }

    private static List<Position> getAntiNodes2(Pair<Position> positions, int size) {
        Position left = positions.left();
        Position right = positions.right();
        Position delta = left.subtract(right);
        List<Position> antiNodes = new ArrayList<>();

        Position nextLeftAntinode = left;
        while (isInBounds(nextLeftAntinode, size)) {
            antiNodes.add(nextLeftAntinode);
            nextLeftAntinode = nextLeftAntinode.add(delta);
        }

        Position nextRightAntiNode = right;
        while (isInBounds(nextRightAntiNode, size)) {
            antiNodes.add(nextRightAntiNode);
            nextRightAntiNode = nextRightAntiNode.subtract(delta);
        }

        return antiNodes;
    }

    private static boolean isInBounds(Position position, int size) {
        return position.row() >= 0 && position.row() < size && position.col() >= 0 && position.col() < size;
    }
}
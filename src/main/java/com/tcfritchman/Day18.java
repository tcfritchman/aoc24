package com.tcfritchman;

import com.tcfritchman.common.Vec2;

import java.util.*;
import java.util.stream.Collectors;

public class Day18 {

    static String exampleInput = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
            """;

    static String realInput = """
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput, 6));
//        System.out.println("Part 1 --- real: " + part1(realInput, 70));
//        System.out.println("Part 2 --- example: " + part2(exampleInput, 6));
//        System.out.println("Part 2 --- real: " + part2(realInput, 70));
    }

    private static Object part1(String input, int size) {
        Set<Vec2> blocks = parseInput(input);

        List<Vec2> shortestPath = findShortestPathAStar(blocks, size);

        System.out.println(shortestPath);
        return null;
    }

    private static List<Vec2> findShortestPathAStar(Set<Vec2> blocks, int size) {
        PriorityQueue<Thingy> open = new PriorityQueue<>(Comparator.comparingInt(Thingy::getEstimate));
        Map<Vec2, Thingy> thingies = new HashMap<>();

        Thingy start = new Thingy(new Vec2(0, 0), 0, null, size);
        open.add(start);
        thingies.put(new Vec2(0,0), start);

        List<Vec2> directions = List.of(
                new Vec2(1, 0), // right
                new Vec2(0, 1), // down
                new Vec2(-1, 0), // left
                new Vec2(0, -1) // up
        );

        while (!open.isEmpty()) {
            Thingy curr = open.remove();
            System.out.println(curr.location);
            thingies.remove(curr.getLocation());

            if (remainingDistance(curr.getLocation(), size) == 0) {
                return buildPath(curr);
            }

            for (Vec2 direction : directions) {
                Thingy next = new Thingy(curr.getLocation().add(direction), curr.getCost() + 1, curr, size);
                if (!next.getLocation().equals(curr.getLocation())) {
                    if (thingies.containsKey(next.getLocation())) {
                        Thingy prev = thingies.get(next.getLocation());
                        if (prev.getCost() > next.getCost()) {
                            open.removeIf(thingy -> thingy.getLocation().equals(next.getLocation()));
                            thingies.remove(next.getLocation());
                        }
                    }
                    if (!thingies.containsKey(next.getLocation())  && !blocks.contains(next.getLocation()) && isInBounds(next.getLocation(), size)) {
                        open.add(next);
                        thingies.put(next.getLocation(), next);
                    }
                }
            }
        }

        throw new RuntimeException("No path found");
    }

    private static boolean isInBounds(Vec2 location, int size) {
        return location.x() >= 0 && location.y() >= 0 && location.x() < size && location.y() < size;
    }

    private static int remainingDistance(Vec2 location, int size) {
        return (size - location.x()) + (size - location.y());
    }

    private static List<Vec2> buildPath(Thingy thingy) {
        List<Vec2> l = new ArrayList<>();
        while (thingy != null) {
            l.add(thingy.location);
            thingy = thingy.predecessor;
        }
        return l.reversed();
    }

    private static class Thingy {
        Vec2 location;
        int cost;
        Thingy predecessor;
        int size;

        public Thingy(Vec2 location, int cost, Thingy predecessor, int size) {
            this.location = location;
            this.cost = cost;
            this.predecessor = predecessor;
            this.size = size;
        }

        public Vec2 getLocation() {
            return location;
        }

        public int getEstimate() {
            return cost + remainingDistance(location, size);
        }

        public int getCost() {
            return cost;
        }

        public Thingy getPredecessor() {
            return predecessor;
        }
    }

    private static Set<Vec2> parseInput(String input) {
        return input.lines()
                .map(line -> {
                    var split = line.split(",");
                    return new Vec2(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                })
                .collect(Collectors.toSet());
    }

    private static Object part2(String input, int size) {
        return null;
    }
}
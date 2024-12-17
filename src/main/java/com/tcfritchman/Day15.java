package com.tcfritchman;

import com.tcfritchman.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

    static String exampleInput = """
            ##########
            #..O..O.O#
            #......O.#
            #.OO..O.O#
            #..O@..O.#
            #O#..O...#
            #O..O..O.#
            #.OO.O.OO#
            #....O...#
            ##########
            
            <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
            vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
            ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
            <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
            ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
            ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
            >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
            <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
            ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
            v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
            """;

    static String realInput = """
            ##################################################
            #O..O......O....OOO..O.OO.#.O#.OO..O.#.#....#..O.#
            ##.O.OO.#.O...O..O.#.O.O.#O..O..OO#..O#O#O#...O..#
            #...O...OOOO.O#OO.#O#.O.#O...#.#..O.O..O........##
            #O.#.O..O.....#O.#.#.#O.....O.....O....O....O..O##
            #O#...O...O#..#.O##O#.O....#OOO..O..O...O...#....#
            #.O....O...###.O..O.O#....O#..O..O...O.OO.OO#...O#
            #O..##...O.......O.O.#.O..O.OOOO...#..OOO.OO.O...#
            #...O....O.O..O....O.......#....#..O.OOO....#O..O#
            ##O....OO.....OOO.#O.#O.#OO#O...........O......#.#
            #O#.......#........#OO.....O.#..#..O...OO.....O.O#
            #O##...OO..#.O...OOOO..O..O.#.O.OO#...O.O.#O...O.#
            #O#O.#O..O.....O#.##.O......O..#...O....OO....O#O#
            #O...O....#.O..##....O.#.....#...##O##...#.O.....#
            #OO...OO#...O#.#.O.O..O..OO..OO.O..O.....#..#.OOO#
            #..#O.#O.#..O.OO.....O#....O.OO...#..O.O.#OO.##..#
            #O..O.##...O....O..#OO..O..O..O...O..O...........#
            ##O....O....O..O...O#..O....O.OO......O.O..O....O#
            #OOOOO.#....O.O#.OO..O...O.......O.#OO...#O..O...#
            #O....O.#.O..O##...OO..##O#....O.........O#.O....#
            #...........O...O.OO.O...O#......O...#OO.O.O.....#
            #..OOO.....O#......O...#..O..O.#..O..OO#O..O.#.O.#
            #.OO...O..O.O.O##OO.O.OO#.................#..O.#.#
            #.#..#O#.O.........O.O...O.#......O.O..O...O.O..O#
            #..O.OOO......#...O.O...@.O..O...O.OO..#...O..O#O#
            #...#O...OO.O...O..OO..###O..O....O......##....O.#
            #....O.#.##...O#.#O.OOO...#.O.......#O.#.##......#
            ##.O..O.OO..O.#.....O..OO..O#....#..O#...#..O....#
            #OOO.O.OOO.O..O..O..O..#OOO....#O..O.......OO..#.#
            #..O......O.O...O....O.OOO#......O.#....O.....OO.#
            #....#..O#......OOOO.O......O.OOOO...O..O.#.....O#
            ##...OO....#.OO....OOO..O.#.OO.#O..O##..O..O.....#
            #..OOO...O....O.....O#O...O...O.....OOOO...O..#.O#
            #...#....#.OO..#.......OO..OOO.O.#.....O....O.O..#
            #O.O.........OO.O..OO##O..OO.O.........OO.O..OO..#
            #...O...#OO#O..O..#..O#OO.....O..#O.....OO.....O.#
            #O..O##O...OOO#OO.O....O#.O.O#..O..OOO..O#.O..##.#
            #.O..O...OO..O#O.#.O.OO.#OO...O..OO..O.O.O..O..OO#
            ##.O.......O.OO..O#.##O.....O....#.....OO.O.#.#OO#
            #O#..O.....OOO#.....O.......O....O...#..O..O##...#
            #.#..O.OO..#.O.OOOO.O..............O...O....O#...#
            #....O..O..#..##..OOO...........O........O....OO##
            #OOO.#...O....O.....#.......OO#.O..OOOO..OO......#
            #......O...........OOOO#OO.......O........O.OOO.O#
            #........O.O.#....#...O.#O..O.#....O.O#..#..#O#..#
            ##................O...OO..O.#O.OO#.O.....O..O..OO#
            #.O...OO.........#OO......O.O.OOO......O#........#
            #............O.#.........O.....O.O...#.O......OO.#
            #O..O......#O...OOO......#..#.#.....O#O.O.....O#.#
            ##################################################
            
            v>v^<v<^^<v<<>>>>vv<>vvv^><>>vvv><v<v^>><vvv>vv^>vvv^vvv<>><vv^>v>v>>^><>v<<<<vv^>^<^^^<^v>v<v^^v<^<^><v<<>v>^v^<v<^v>v<^><^v<>>v>v<>v^<<^^v^^<vvv<^><<v^v^v><^^v<>><>^<v^>>v<v^v^^v<v<^^v^^^^>^v><>^<v^>^<>><v^>vv>><^vv^v^^v<vvv<>><v^><>>><<^>^<<v>^<^>><^>>v^vv>>>v<^>^<>^<^v>^v<>v<>vv<v>vv^v<v>v<^^>vv>^<^<<^^v><>>^^<>^>>>v^v<v>v<>vvvvvv^<>^><<v<<v<v<>v^<v^^^v><^<v>>vv>v>>v^v^^v<<>v<>v>v>^v^vv^^<v<<v<<<^v><>><>v>>v<^<<^><^^v^<v<>><^>^^<^^>><<vvvv<>vvv<>>>>^<v<v>^^v><v^^>vv><<<v<>^<^^v^>v<v><>>^>><^<<>^<>^^^^vvvv^^>^v<<>^vvv>^<<^><v<v<<>>v<^v<>^>v^<><^><<<>^<vv^>><><<vvv>^<>vv><>>v<^^>>v^^<^^>vv<v><<^<^^vv<^^v>^^>>>>v^v>>vv^>v<>^v>v<<<^<>>vv^>^^<^<<vvvv^<<><<^^>^<v^<<^v<>>^>>^>><vv<^vv<<<<>v><><^<<<><^<v^v<^<vv>v^>>^v<>v<><<>v><^^^v>>^<<>vv<>^^><<^>><v<>^>^><v^<><^<>^<^^^^<^>v<^^<vvv^<^<><v^>>>>>><><>^<>^^>v>>^^<<<>>^v<^>v>^<vv>^><^>v<<><^<v>>^<^>^<v^<v<>><<v<<^vv^vv>v<<^vv^^>><v^^^^<<><>v<^<^^^><<v^>>>>^v^^<v<<<^vv<<vv>>>><>v^vv>v^><>^><>^<^<v<<>v<v^vv>v<<v^^<^<^v^<><^v<^>>>^^<>^vv^vv^<>^
            ><<^v<^v<<>><>^^v>>v^^<v<^>^v^>v<^<^>v^v<<<<<<>vv>^>v<vv^v<><<>>>>>^^vv<^v^v^><>^^v<^^vv>vv^^vv<v<<<^^^^<><><v><><>>v><^^>^^><^>v^><^^<>^<^vv><>>>^^<^>vv>v<v^>v^v<><^<>^^v<>>^v>><v><v^<><v^>vv>^<^>^v<^v<^<^v<>vv<v<>vv<^<<^><v^v^>v^>v<vvvvv><<><>><^<<<vv<^v<^^v<^v^<<>v<><^^>><v<^^^vv<<>^v^v<>^^><<<v^<v>v^vv^^^vv<<<v<v^^>>^v<^^^^^^>v<<v<<>^>><vv>^v>^><^<<^v>^<^>>>^v>>v><<>vv>>v^>^v<<^<v<v<vv<>^><vv<vvvvv<>vvv^<>><><^v^<<>^vv^<>><>v>vvv^<v<>v>^vv<>v^vv><<vv^v^v>^^>^><>v<<<^<^^v^vv><<^v>v^>><^<v<>^<>v^>v>^<^v^<><^>><>^>^v<<v>>^<<v<^<^v^^>>^v<<vvv^v<v<^>^vv<v><v^><v^<>>><<v<^<^>^^>>vv^^v>>v^^v^^v<^vv<>><^>^^^>vv<><^>>><v>^>v^vvv<v><>^>vv>v^v<>>>^>^>v^v>vv<v><><^<<^^v<>>^^<><>><^>v<v<<v^><v^vv>v><<v>v^^<v^^<<v^^<^^<<>v<vv><<^<<v><>^<><v>>^^vv^<>^>^vvv<>^^<>>^><<^^<^v<^^v<^^^^v<<^vv^>>>vv^v>^<>^^<><^^><>^<>v<^>v^<<>^<v>v>v<v>>v^v^v>>^v^^^^>^<>vv^>v^^>><<<>v><^<v<v<^<v>>^vv^<^>>>^<v^><><^<<<^>vvvv<v<v<^^>^v>v>^v^v^>^<^v^>><^<v^vv<>^^<^^v><<^>vv^>vv><<>>><v>^<>vv<^v^>>^><^^v<vv^^>vvv>^^>v>>v<>v
            <<^vv<vv><<<<><<v^vv>>^v>>>v<<><v<v<vv<><<>^v^v<^v^v>^vv^>>>v^v^vv<<v^<^>>>^v><><<<v>v<>>>>^<vv^^vvvv^<>v>v><v<>^vv>v<<<>v<^vv<>^^v>>>v^<<<^>v<v><v><>v^<>>v^><vv<>>^v>>>^<<>^^<v<<^^^<>v>^><>v^>v<v>^<><v<^^vv>^>>^vvv>v^>>^<><v^>v<<^^>>>>^><<v^<^^v>>^><vv^^<v<>v^>v>vv<><^v<v<^>v<<vv<vv>v<>>^>>^><v^^<^<v<v^><><<^v<v>vv^<vv^^>>v^>v^^^>vv>v^<vvvv<<>v>v>^>vv><<>><v^v^^<v>>^^v>>vv^^v>^v>^<vv>^^^<^vvvv><<^^vv<<><^^>><vvv><>><<^v>>^^<v^vvv^vv>v<<><<>^><^v<>^<<v^>>>^v^^v<>v^><^^v^><<>v>^vv<v^^v>v^v^^^><v<<<^>><^v>>><v^>>><v^>^>v<<^vv>>><<<vvv>^<^vvv^>><>>>^<v<>^v<<^v^v<^v^<<<^v>v>v>^v^^<><^<^>^>^v><^>v^^^<<vv<^<v<><^>v<^v^^><v<^><>^v^vv^v<><<<><<^vv^>><>>^^<v<v^v>v<^>^<>vv<>><<<^>^<vv>^>^v<v<<<v<><>>^>><^v><<vv>v<>v>>>><^^v^v<^v<>v^<<^v<v^<>v<v^^<^>^<^>v<^>vv<v<>v><<^v>vv>^^v<^^>^vv^>v^>vv<^^v^^v<^>><v<>^<<<>vv^v^v<>v^^<^>v^>>^>>><>>>^v<^v^^^v^^^v>v>v<^<^vvv^vv<^<<<vvvv^^>v>v^vv^>vv>v<>><^><v<<^><v^<^v>>v^><v>v>>>v><>^^v<><vv^v>^<>>^><^^>^<<<v>^<v^v^>^v^^v^<^^vv<^^v^^<>v^^^<v<<v^v<>><>^v<^^vv>>^
            <^v^<^>v^^<>v<v^>v^><^>^>^><^v^^<^<^>><^>>>><>><v^v^><<<^>^v<<v<><>v>vvvv<<<^^>v>^>>^<<<>^^vvv<>^v^^<<<>v<>>v><>vv^>vvv>v>>>>^<v>>^<v^>v>^><<vv^^v^<><<v>^<<vv^<>^^><^>><^vvv>>v^v<>^<>^v<<<^>v^>^<^v>v^<^<^^>v^v<>v^v>^<<v<^v<v>v><>^>v<^<^>^<vv>v^vvv><v<vv<><^^^v<>v^<<^>^vv>^^<^>>>vvv<^><v<>>^^<^>vvv^<^v^^<>><<v^^>v<v^^<>^<<^<>^vv^v>^v<>>^<^<<<>v^v<v<<>vv<>^v<^<<>^<>^<<^>v<>>><v>>v<<<<^<v>><^^>v<^v>^v<^v^v<v>>><v^>v><<<>^vvv^^v^><^vv<>>^>^^<<v>^^>><v<<<v^<<v>>^^><^vv^<<>v<^<v^>v<>>^^>^vv><<^v<vvv^><v><>^>^<v^^<><v<v><<<<vv>^^<<v<>>^>^>>v^vv<^v><^<v>^v><vvvv>v<<><><>v^<>v>vv<^^>><>v<<>><><^^<><>>^<><<^vv><>^^v>>^>>>v<v>>^^<^v><v>>v^^vv<>><<<vvv^^^^>>^>v<<^>v^^<>>>vv>^^^^vv^^<^v^>^<<<^v<>v><^vvv^>>^<v<>vv>v<vv>>v<<<><<<<>vv>v<<><>>vv^^<><>>v^<<>>^>^>>v<<<vv^v^<<>>>^^<v<><>>><<<>v^<v>^^><v><>><v^>>v>><^>v^>v<v^<>^>^^^v^vv>>><^^v<><^^^v^<v^v^>v^^^vv><^^<^^>v<>v^^><>v>>^v<v<v^^><<>>v>^v^<^<>^vvv>>v<^vvvvvvvvv^^vv<^v<^<<>^<>^>^vv^<<v^>v<v<><><><<<>^^>>>v<>>><^<<>^<><^<>^<>>>^v>vv^^<>^^<>^>^v><v
            ><>v<^v^>>^^^vvv^^^v>^<<v><>>>>>^>>v^<v<^>^<^^<>v>>><vvv>><v<<><>^v<v<v^>>^v>v^>>^<^><<<v^>^<>v^v<<<^v<v<^>>^^^v>>><<><><>vv^>>^v>>^^vv^<><v^^<^v>v^>>vv><v<v>>^><^<<<^^><v^><>^<<<<^><v^<><<<v>^v^><^^^>^vv>v<<v><<^><^<^><<v^<<^^^v^vvv>v>v><><><v<vvv<><vv<v>v^<<^v^v^v>><><v^^^>v>>>^v<>^^v>^>><vv<><><v<>v^<vvv^>v^^<<vv<<v><v<><<>><^<<v<>^vvv^>vv^^v<vv^v^^vvvvv<<v<v<^^^^^<<v>v^v^>>>><v><><v><>^>v>^>>v>vv^<<^v>^>vv<<>>^v>v><>^><^v^>^vv>^>vvv<^<<^>^^<<v>>^vvv<<vvv>^^>v><^<><><v<^><<^v^>>v<>^^>v^v<^v<vv>>vv^v^<vv>^>v<v<vvv<<>>v^>v^><><<<<>>>>^<v<^<^>v>^>^^^^v>^^>^^^<v>^vv^>>>><>><>^>^>v>v^>>>^^^>vv>^v<>v><><^v>>^<^>>>>><>><v^<><<>v^v>v<v^v<>^>v^>>>^>><^>vv>>>><v><^>^v>>^><^>^>vv<v<>>vvv<^<><^<>>^>^^v<vv><>^^<>><>^v>>^v^><<v<<<><^>v>>><v<^^^>>v>>^^^>vv>>v^>>v<<^^>v>vv^>^v<^><<v><^v<^v>v<>>v^<>>^<v<>^>v<v^<>v>^>>v^v><<<><v<>><v^v>>vv<>>v>v^^<>v>^^>^^^<v<^v<<<^v^>>v<^^<<^<<v^^>>^<vvvv><>^v^^<<>>v><>^v<vv<<<v>^<v<<>><v^>v>>^>>>v>^v>^<>^^v><^v<v>v^><<<<^><><>vv<v^>><v<^<<>>^v>><>v<<v>>><<vv><<>><<
            ^<vv<^><>>v^vv<^v<v^^>>>^><v^<v<<<><<><<vvv<<><<<<<v>>><<^>>v^<><>>^<v<>^<vvv<^<v^^<<<v^>>vv^v><>v^^<v<^^^^^><v><>^>>v>v^^><<v<v<<>>vv^<>vvv>^v><>^v><<v^<v^>><>^v><^><<>v^>^v>v>>><>^v<>v<<<<<v<^><v><^><v^<v<<>>v>v><><^v^vv<<>>><v^<v<^>^v>^^>><>^>^^>^^^<^v<v>^<vvv^^^^<v^>v<<>vv<<>^<^>>^v<^<v<<<>^>v^>^<v^<<v<^<v><^^<<>vvv<<>v><>>^^><<^>v^<>vvv<^<^^>>^^<>>^^<^>^^v>^vvv>><^><^vvvv<v>v^v^<v^^>v>>v>^v><<>v>v<<<^>v<><^<<^<>v^><>vvvvvv>vv<><^v>v^<><v<>^><<<<>>^^vv<<^>>v^>>>^^<^<<v^<<^<<<v<v<^vv^v>vv<<^<<<>><<>^v^v<^>>>><><^><<><v<<v^><^<v^<v^>^v<vv>>vvv^>^<^v<<<^^>v<<>><>vv>^<<<<<><^<^^v>^><><v>^^v>vvvv>^^>^>>>vv^^>v><vv^><>v<v<<^><v^v><^v<^<v><>>v><^v>^v^>><vv<>><><>><v>v<vvv>v^<>>^>^>v^>>>>^v<^^<>><v>><<v<v><<vvv<vv<<<<<<^<<>v<>>^vv^><v^<<>^><v^v>><>>vv<<^v>^^^v^><vv<v>>^^>>>v>>^v^v<>>^<<^>^><^<>>vvvvvv>^^<>v>^>v^<<<v<><v<>>^>>^v<^v^<^<><<^<><^^^vvv^^^v^><><^^^<<<>>v<^^vv>vv>^<v^<>v>v^><>>v^^^^<^^>>^<v^<>v>>><v<<<<vv<^v>>v^>vv<>vv>^>vv>><^<>^<vvv>>v<>>v<v<<v>^<v<^<>^^^^^v<<>>^><^v^<>v>v<^>vv
            <>><>^<^v><>^>><^^^>^^^^v<>^<<vv^^><^^^>><><^^^><<<>v^>><<v><v<^v^<<>>>^v<^v<<<^^vv><vv><vv>^v<^>v^<v^v>>>^^v<<^^>^<<><v^><>v><^<>>><>^v^v^v<v>>v><<v>><><>^<v<^><^<vv^^>^<^<vv^v>^^^<<<vv<<^v<v^<vv>>v^^>^v>vvv>v><^^v^v<<<>^><v<v<<^v>>v>vv><><vv<^^^v^<>>^^^>^<v^v>vvv<<><>^>>>>>>vv<vvv<v<>>v><<>v>^<><>^v<v<v<vvv^^^>^^><^<^<>v^<>^vv<^^^<^<^>>vv^v^<^><<^v^v>v<><<>>v<vv><v>v<vv<><^>><>^^vvv<^^^^^^^v>>^><><>v>><vvv<vv^^^^<v<<<^v^v^^v^^vv>vv>^<^>v<<^>^<^vvv<vv^v^v<>>vvvv<<^>vv>vvv<v<v<>^vvvv>v>^^^<<^<<v<^vv<v>^<^<vv><vv<v<v^^^^<^^^<v^^^^<v^<v>v>><^^v>v<v><^vv^>v^v<^<v><vv<v<<<vv<vv^<^<vv<><<v>v<v<>vv^^<>vv^<v^v<^^v^^^^<vv^vv>><<v>v>^v>^><>>>><><<^^><^>>^<^<<>>^v<^v><<<<<^<^><v^v^^>^<<>v^^>^vv^<<v<v>^><^>><>v>^<<v><^v^>^v<<><<<^^<<^><v<^><<v<<^^>><v<^^<v<>v^v<^^>v>><<<v><v>^^>^><<^v^v^>vv<vv>>v<<<<^><v>v<v<^vv^>^<><^v<>^>v^^<^^v^^vv^>>^<^>v><><^<v^>v>^^>>><^<vv>v>>^v<^v>>^>^>^>>^vv><>vv><>v><>vv>><^>vv>v^>vv^>^^v^>v>>^<vv><>^^><^<<^>><<v^^>><<<^^<<^v>v<<^>>>^^<^v<>>v>><v>v<^>v<^^>><<<^^>>^v<^>^
            <><^^><<>^^v>^<^><<^><>^^v^<^><<<<^<v><>v^^>^<<^<v^<v^v>v<<<<>^><<vv><<^vv>v<>>^>^>^vv>>^>^^vv<^><^>>>><^^^>>v>^v<^v>>>^v>><^<^>^^<<vv<><^^vv><>>vv<^<^v^<^>v^<<^v^vvv^v>><v^v<>v><vvv^^^<^>^v^<>v<v^<^<v<<^<^<>vv^^<vv^>>^vv>>v<><vv<><<^^<<>v>^v>^^v<<<>>>>v>v>v^v<<<><<^v^>><>><v<>v>v>>>v<v><vv>^<<>>>^<^^v<^<v><v<^v>^vv>><<v<^vv>^<>vvv><><>><<<<>>^^v><v>^<>><<><^<>>v^vvv<><>v>^>v^vv^<v>vv^v^v<v<v>v^<<^<><v>>^v^^vvv^<v^>vvv^vvv<v>^>><^<<<vvv><<v<^>v^v>>v><v^v>^v^^>^<>v^^v^><<<<v^vv^^v<v<>v>^<^v>^v><>vv<v^^>vv<^>>>^<>>>v^<v><v>vv^<<<><><^>^><>>v^vv^>^<><<>>>v^><v^vv<v<><v<><^<vv<><<^v><v><<>>^vvv>>v^<^>v^><>^vv^><v^><<<v<vv>><^vv^><^^v><>vv^<^v>^^<^<v^<<<>v>>>>><^v>^><>>vvv><><v<>>v><>^<>^^>><v^<<><<vv<>^><v^^v<^^>><v>v<v^^<^<<<^>vv>>v^>v^v^^^^vvvvv<^^v<>>>^v^v>>^<v^<<^>v<^>>^^<<^^^>>><vv>v<<><v><v<<v<vv^^^v<<<>>^>^^<v^^><>^^^>^>>^<v<<v<>^v<<^^^v>><<vv^vv<v<v^^<<v>v><<v>>>v<^<<>v<><>v>^v<>^v>^>^^<^>v^v^>v<^>^>>v^<^^vv<v><v<^^>>>v>>v^v><^<^vvvv>v><^vv^<vv<>><vvv^vv<>v^<<v>>v<^v>^>v>^v^^v>v^v^
            >><>>><>^>^^<>^v>^v>>^vvvvv^vv<><^v^>>><^^<v<<v>>v<<v<>v>>^<<<v>v>>><^<vvv>v^v<^<<<^^^v>^>v>v<>^<v^<v^v<><<v<vv<>>v<v>^<v>^<<><<^>v^^><<>>>v^>v^<^v>v>>v<><>^><<<><^>^>^>>>^><vv>^<v>>>>^<><<<>^^>^<^v>^^<vv<^v^^^vv^<v<v>^v<^v<><>>v^>>>><>^^<^^v^v^<v>>>v<^v^>^>^^^v<^v><<v<^>>^^<^>vv<<<>>^v>^^<v<^v<vv<v<<^v<>vv><v^^<^v><^>^v>><<<<vv^><>^<>>^>vv^v^>v<^><v<<v^>>^>^v^>v^>^<<^^>^><>^^v^<>vv<><><v<<v<v<<<^<^<<><vvv^>>v<<^v>>>>><><v^<v<v<>^^v<>>v>^v^<>^<^v<>^v<<<<>^v<v^v>>>^v<v<^>vv<^><><v^^<^><^>>^^v>^^>^><^>>>v>^^v<^>v>^v>>vv<v^>v<^>vv<<^^v^vv>v^<<v<^>v^vvv>^^><v<<^<^><>^>^^<<<v>^^^^<><>><v<v<<^><^<^v>v^<^v>><^<<>>^<<>>^>><>^><^^vvv^v>>^>vv^>>v<>^^<><>vv>v<>v^<<v^v<v>^<><<v>>v>vv><^^<^>>vv^^^>vvv<^>^^v^v<vv<^^>v^<<><vv<v<vv<<^>^<^<>^>>v<><^<<<>^>>^^^<>^^^<v>^^^^^>^<^vvvv^^v><<^vv<>><^v^v^>v^vv^^>^v^v>>v>>^<>vv<^vv^<^^>v^>^<v^^v<>v<v^<<<>^^>><>^^v<>^<<<>>><>^^<v<<>v>><<^v^<><>>^<vv<^^<><v^><<>>>^v^^v^<^v^v<<v^><v^<^<>>^<v^<<<v><<^><<vvv<^>^>v^<v>^v>v<^<v^vv>^^^<><^v^<>v>><v>v^v^vv><^<>^^>^>^<v^
            ^>^vv<<^^^v^>^<v^v<<^<>v^^><><v^v<v<v<v<^<>>v<<vv^><<<<><>v<>^^^^v^^^vv^v^<^vv>>>><<v<v>^>><^^^><<>v>v^>v>>^>><^^v<<<^<<>v><^>vv^vvvv^>v<^^>><>^><<>>>^^v><<^<^v<><>^<>v<><v<v<v><v<^vv><>v>v<v>v><<^>>v<^^>^>v<vv><^^^<><>v><>>><<v<><^<>vvvv<vv<><^>^><>>^<>^^>^<^v^v^^^^<^><vv<<vv>>v^<<<<><>><>vv^<v^>vv^><^^v^>v^<<>vvv>v<^>><v^^v^>^^<^^v>v^^v>^v<^>v<<^v>^<vv>^<<^<^<v>v<^>^<>>>>vvvv^^^>^^>^>^><^^>>>^<>>v^<<^>^<v^^^^^>><^^<vv<>v>^<v><^>^<^<^<v><v<><><^^>>><<^>^>^^v^<^>^>^vv^v^vvv^><<vv^^<^^v<v^<<^^^v<^<v<^>^^^vvv^^vv<^v><<^^<^<vv>^<<<v<vv^<><vvvv<<^<vv<<<^^><^>>>^v<v^>^<^>^><>vv^^>v><^vv<^<v<^<^><^^<<<>><<v<v><<v^v<><v>vv<v^^>v^<v<^><^>v^^v^^<<vvvv<v^^<><^<>v>v<>>^><^v>vv^^v>>vv<v^>^<>^<^<<v^^vv^vv>^<<<^v>><><^v>><<<v<<^v<<<v<<v>^vv><><><^>v^<vvv^^>^^vvv^v<v<v^<^v<vvv<^v<<vv>^<<^v<>^>vvvv<<^v^>^v<<<<<><^<^^^<vvv<<v^v>^v><>>v<v^v<<<>>>v^<>^>vvv<^<>>^^<><v^>>>>^^v<>^vvv>>^^v^^^<v>^v^^^<<^>>^>^<<v>>>><<>^v^>v^v>>v>v^v>^^^v^><^^<<v>^^<^<<<v^>^v>^^v^>^^^^<v<<>^v^^>^^>^><v>^^>>v>v>><>^^<^^v>>vv><<
            ^v>v>v^>v><><v<^vv^vv>>>^>^>><vv^v><^^>^vv>>^vvvvv<^<v<><<<<<>^<><^<<<^<v^v>><><^^><^v^v<<>>>^<^<<^>v><>v>><<v^v>v><<<v>^v>^^<<^v>^>v>vv>v><^^><v<<v^<v<>v<vv<v^^^v<v^><v>>v^<v^v^<v^v^^v<>vvvv>^^^^>vv><>vv^v<^v>v^v>v<<<<>^^<<^^<><v<v^<v>>vv^<vv^<^>><<v<<v<v^^^<^<^^vv<<^v<<v<v>^^v>^^<^^>>>><<>>vv^<vv^vv^^<<v^<>^><^^<v>vvvv<><>^^v<>v<^><<><v>^^^v<v^>v>>^^v^vv><v>^vv>v>vvv^^v>vv<vv>>>^^^vv^v^<<v^>>><vvv<^v^>^>^v<><>^vv><><<<v><^v>v>^<vv<vv^^<vv>>>^<<v^<^<^^^>>^v^v^<<>v<<v^^<^v^>>^^<>vvvvvv^^<v<v<v>>^v>>vvvv<<^^<v<><^^v<v<<v>v<<>^><v^vv^>vvv>v<<v^<<<><<vv<<v>>>^vvv>^^^>v<><<v<v>vv>^<<vv<^^><vv^>v<<><>><>>>>>><><^<>vv^v>v^v>v^<v>^<<v>v<^^^^v<v<>>v>v<<<^^<<^^<<<<v<^>v<^^^^vv^^>^>>>><<v<>^><>>^<v<<v<>v^>^^^^^<^<^vv^v^^>v>>v>>>>vvv><^^<<v^^v^>^<^<><vv<^^^<^<^<v>v^<<<^v<v^v<^vvv^<v>v>^<^v>^><<^^v>><v<>v<<<^v><^>v>v^v>^>vv^^<>>^>^v^<^^>^^>v>>^v^>>^>^>^>>^^>>>^v<v^v>v^>^v><^>^<v^<<^^>^^<<>>>v^^<><^v<>><<<<v>^vv^<<v^><vv><v><^<<^v^v^^<v<v<v<^<>v^v<^<^>><<v<<>>^><^^>vv>>vvvv>vv>vv<>^v>v>>><<<v<v<>^<
            ^^v^^^^^><v><^v^v^vv^v<^v^^>^vvvv>^^^^^^<v><v<><>v^v<^<>^>^^<<<v^<v<^>^v><^^^><v><^^^<v^vv<^<vv<<^><>><<v^><^v><>v^<^^>^v>^v>><<vv>^^^<^^>^^v<><<<v^v<^^^^^vv>^>>^^>><<<v^<<v^><<^vv^^vvv^<>>v^^v>^<<v<^>^>v^>><>>><<^>><<v>^>v<^<>vv<vv>><^v^<><<><vvvv><v^>^vvvv^^>^>^<><>v^>>^v><>^v<^<<^<><^v^^>><<<><><<<v<^<^v^^vv<^v>v>^<<^^^<>v<>^v^<^vv<v^<^>^v>^^^>^>v^v^^^v<<v>v>v^<v^v<<>v<^>^v><<v<>v^v><<v<^>><><^v<v<vvv<><v<^^^<^<<^^^<vv^<^>v^v><<^v>^^<>^<<v<^v><vv^>v^><v<<<^<^v<^^^^vv^><v><<<^>>^<<^^<<<><><>>vv<vvv^<^<>vv><<v^^><^>>><^v^^>>>^<^^>>^^^<v><<^<^^vv^^<v<v><^<v^<>v>>>^<>vv>^v<^^<^>>v>>><<v^^<<>>>>>v<<^^<v>>^^^vv<^v<<^<v>>><^^><>v^v<>v^<<<>^>>^^^v^vv><^vv<>>^>^>>><v^<<v^v^<>vv^>v<v><^^<>^<^v<>v^><^<<^>v>>^<^^v>^<^>^vvv<><^^^<>><><<><v<>vvvv>^<vv^><<<v<v^<<<>^^<<v<^<v^v^v^v>^v>^<^v^vv>^>><<>>>^vv>^<v>^<v<^vv><v<><>>vvv<^vv<<<>v^>^v>vvv^>^v^v>vv^<v<v^>>><>^^^>^<<>^><>^<<>v<>^^<><v<^<v^<^^v>vv^<>v<v>^^<v>><<><<vvv^<v>^>^^<<<<>>^>vv>^><><^^<>v^v>v<<><>^^>>>^>^>vv>^v^v<v<^^><v^<^<v<<v><<<<v>>^<^
            v^vv><^<^^<>v^<vv<>^v<>>>v><<<<^^<v<<<>>^>vv<^<><<<>^v<<^^^v><>><v<<><<^>^<><vv^^^>>^<>^^v>v>^v<<<>^<><vvv<>>^v>^>v<>>^vvvv^<><>v>vv^<<^>>v>^><<>v<<^v^<<>v^>^>v<>^vv>vv<v<>^v<v^vv>vv^<<>^<^><v<^^v<v^><^^^<^><v<^<v<<>>vvvv><^v^v<v><<<^^^v>^>^^<v^<<vv><>v<<<><vv^>><<v>vv>vv>>vv^v^<><^^v>><<>^v<vv^^vv<<vvv<<v<>v^>^^v>>vv<^<<<<^<>^^><>>>><>vv<<^<>^^vv><v><^v<^>v>>v><^^^><><<v<v<^^<>^v>vvvv><<^v^<>vv^^>^v^v<v<>^>><<>>v<^^><v>>v<^^><^vvv^v<<v>vv^>v<><v^<v^>v^v>vv^<<^^<<<><><^>vv<><^v>v^^>^<>><^<v<^>v<v^^^^vv^>^><>v><<v^^v^>^>^<vv>>^><>v<^v>><>>>^><^<v^^<v^^><^<^>>^<v^<<>vvv<v^vv^v>v>>v>^>^>>><<><vv<>^^v>v^^v>v><^<>^<v>v<><<>>v>^<^<<v<^>v<^^>v^<<v^^<v>^v>>vv^>^>^v>^^<>v<<<><^v^v<v<>>vv>^>^<>>><^^v^<<v^<>^<>>><<^^v<v<vvv>v>v^^<<^>>v^v^>^<v><vv^<^<>vv^<^^v>vv^v>><><^^<v^vv^<<^^<<^vv<>>v<v>>^vv^v<^^v>><<>v<^^>^<<v>>>v>>v<v<>^v^<>v<>^<v^^<^<<v<<^<<^v<^><<<^><<^>^>^v>v^<v<vv>v>^<<^><^^^vv^><v^v>^v<<^v><^^vv<vv^<<><<v<>^>v<v^v>>v^<<><<v><vvv^v>^>v<vv>>>v>><^v<^<>^v^v^<><<vv>v<>v<v^vvvvv^v>v><<^<v<>
            vv>><^<><v^v^>vv^><v^<>>^>>v<^v^^<v^v^>^v<^^><vv^v^^^<v>>><><<v^>v<v^>>v^vv<<^v^<<<<<^v>>v>v>^<<<>^>v^>>><v<^>v<<v>>v^><>^><^^^^^v<v<>^<^v<v^>^v<<>><>vvv<^^>>^<>>^v<>vv<v^v^>><^<vv^v>^<>>^^^^^^><^<v^>>v>v>v^<^^<v^v^><^^><>v>^<^>>vvvv>>>vv<>>><v<^v>v^v^>^>>>>>>>v<^>^<vvv>^^v>v>v<vvv>v<v^^>v^^^v<v^<^^vv<^<v><<^><v^^<<>>vv<>v<^^^^><<v^><<v^<vvv<v^^<vvvv^v^v<^<<>>>^>>^>^>^v^^<^>>^v^^<vv<^>v^vvv>^><^^<v<v>^v^<>^>>^>^^>v<^^v>>^^v>^vv>^v>v>vv^><>^>>>^>^v<^<>v^>>v<v<v^>>^><><<<v^vv^>v>v><<<>vv>v^<v><^^v^>><^v^>v>v^vv^^vv><>vv<v>>><^v>^^^<^^^>>^^^^v>><><<v^<^<vvv^^<vv^<^>v>>^<<^>^vv^<v>>^v^v<><^><<v>>^>^>^<<^vvv>v>>^>^>v<<^v^^^<<^^^<^vv^^^<<^v<^>>^<^<>>^<><>^<><^<^v<^v^<^<^^v<><><<>vv^v>^<>><^<^vvv>>^v>^^^^<^^<>vv<>^v>><<^v^v<^v>^v^^><v<^<<<>><v>v^^>><^<>^>vv^v^^vv<>v<>^v<<>>^><^^>vv^>><^<>vv<><<<>>>><^>><v^><^^<v>^<v><v<>><^^v<^^v>>><<<<>^v><v>^^vv^>>>>^<<vvv^>>v<<>vv><>v><<^<v^>>>v>v^<><>v>>><<v>vv>^^v>>^^^v^v<<^v^^^v>><<^<><>^<vv>vv^<v>^v<v^<vv><v^><v><^v<v><v><vv<<>^<v^<>^>v^<^<v<<>v<<^<>^v
            >^v<v>^^<><<><^v<^<v^<>^^^<>>v><<<>v><>^v>^^^<v<^>^<vvv<^>^v>v<^v^><<>vv<>^v><>^vv<<v^>^<v<<v^v<<<<<<<<v^v>v<^vv^><>^<v^><<>v^>>vv<^vvv>v>^^^vv<v<vv<v^>v^^<<v^^^v<<^<<^^<v>v^^^^<<v>>^^v<<<><>vv<^v^<<^^^>v<vv^<v^>vv^<<v>^<v>^<><vv^^^^<><^^<>>>^<>^<>^^v>>^^vvv<v^>>v<^<vvv><<<>^<<^v^><^>>^>>v^<^vvv>vv>^><^>><<^>^><^>^v<><^v^^v^<>v^v>v<<<<><^<v><^>>v<>v^<v><<>v^^^>>^>v<>^v>^v><^v>^<<^<vv<v^<v<>v><><vv>vv<><><vvv^<>^^v>>^^>v<<<<vv><<vv<^v<v^><<>^<v>><^^vv>vv><v>>v^>>><v<^<>v>^v>v>v>^vv^<v^^><^>v<v<v^^>vv>^<^<>><v<<v<>^<v^^^>^v>v<>^>>^vv<<>v^^v^^^^^^^<v^v><v><>v^^><v>^^<>>>^^<<^^><^<vv^v>v^><v<v^>v<v>v^^>^v><<><>^<>^^<^><>v^><><<><^>>^^>>vvv>>>vvv><>>><^^v^>v<<^v>v^<><v><<^>>v^><>v^^><^^<^>^<v<>vv^>>^^><<^v^<>^>vvv><<^^><>^^v<>^v^^^^^>v<vv>>^>vv<><<<>>vvvv^^>^^v>>>v>vvv<v<^^vv>v>^>><v><^v>^<v><^>^^<^<^<v<^v<v>^^<^v>v^<<><vvv>v^><>v>>v<<<>v^^^><>>><v>^<<v^v^><>v^<>>^v>>v^v^vvv^<><vv>v><<vvv>^v<<^<v<>^^^v^^<<^v^><vv>v^v<v>^<>v<vv<<^v>^<<>v>v<^>^v<>v>><v^><>v^<>>^v^<<<v^>vv<><v<^<<><^^>><v^^>^>
            ^<^v^<v>v^^>v>vv><<<<<^>v>>vvv<<^v<><>^vv>v<>^>vv<>^<^>^v>^>^>v^v<<^v>vv<^^>^^^v<v>>^<^<v^<^<^v<<<>v>v<>vv><>>v><v>vv>v^^<^^<v^v>>v>>>vv>^v^>^^<>v<v^^vv^^<v<<^^v^vv<v>>vv^v^<^vv>v>^>>>><^<^vvvvv<<>>v>>v<v>>><^^<v>^v>v<><v<vv<^<><^^v^>^<^<^<^v>><v>v>^<^^^v><<>v^>v>>^^>^v><^<>^^v<<<v>><><v>vv^^<v>>^>><v<v<^^^^^^<>>v^<^<>v^<>v><^>^^>^>>^>><v^<^><v>v<^>^<<<>>^^<<<>>^^v>^<><^<v^^<v<<^<>v<^<^<>><v^<>>v^^>>v^^<<><^vv^<>^^<v^<v>^>v^><vv>vvv^^^<<v^<<^<vv<<>><>^>v>vv^<>vvv^v>><><<><v^^v^<>v<<v^vv<^^vvv><vv^<<^<^<>>>>>vv<<<vv<v<<>vv<^v<<v<^>^<<>v>v<>^v^v<v<><<<<vv^^<^>^^>v^v<<<<vv>v<>>v>vv^v<<<>v>^^v>^><>^^<>v^<<<v<>v^v<vv>><vv<^vv<<^<<^^>>v^>v><<v^^^^>><^^v^vvv<^^>vv^<>><<<<^>vvv>^>vv<>v^>>^^><>>^<>><><^>>>>>^<>v^>^vv^>>>vv<v>^vvv>>v^<v><^><^^v><><<<<<<vv<><v><v>><>v>><^v<<>^>>>^<v^<<<><>>^vv>><v>^v<<>v>>^v^v<^>^^v<<><^^><>><^v<>>>>^^v<vv<>vv<<>^v<>^<^<>v>v<v^^<^vv^>>><^v^v<^>>>^<<v<><>v><v<vv<>>><>v^><^<^><<>v>>>^<><>v^^^vvv^><<<<v^v^v<^^^<^^<v<^v>v>^^<^><<v<v><>>^>v>v<^<vvv<<<>>^vvv>>v<v<v^>v<
            <>>^<^vv>>><><^v^>^>v<vv><v^v<>><<^^vv<>^<>vvvvv<v><vv^^^<^<<^<v<<v>v^^^^v>v<v^>^<^<<^^>^<v^v<<>^>v^^><v>>><<^v<^<^<<v><><v<^<>>vv>vv<v>^><v^^v^>^vv><^>^^v><^^^<<vv^^^^v>v>^<^><^v<^^v>>v^v>^>^><<<><>^^>v^v<><v<>>^<<v^^v>^>v>v>v^<>>>v^><><>vv<v>^^<>^<v><>v<<vv>>^^<^<<>>v^>^v<v><<<v^><>^<^v><>>^^^v^^v^^<^^<^><^v<>>vv<^^><v^>^<<<v>^>^^>v>v>^><^>>^><<^v^v^<<>v><^>v<>^^^<>v<^>^<v<>^^v^<^<v>>^><^vv<<^^><<<vv<<<>^vvvv>^^^^<vv^<>^>vv<^<^>><<><>v<><v<<<><^v<>vvv<>v<<>>v<^^<v>^>v<<>>^v>^>^vv<>v^>^v^v<^^><<><<<<><<><<<<>v>v<><^>^^><>v^^v<v^vv>^<<><v<v^<v<v<<>^vvvvv^><^>v<v^v><<<vvv<vvv><^<^v^vv^v>>vvv>v^>^>vvv<<>v<^<v^><^v<^>v<^>v^>^>^v<vv<<v<v>v<v><v<<>^<>^<v>^^^<>>>>v<v<>^v<<<<^v<<><<^v<^>^vvv<vv^v<v<<vv><v<^><><<^^><<^>v<^<^^^v^>>^^>vv^v>^>>><vvv<^>>^>><v<<<>>><>>^<v^vv<>v^^^><<v><^vvv>>v^^^<v<^><v>v<>><>v^<^>>>>v^^v^v>^^^v>v<v>^v>><v<^<v<>>^><<^^>v<>^>^^<>>v>vv<v<^<v<>^<>vv<>>v^><^^<<v<^><vv><^v<v>>>v>>^>>^^^v<<<^^><<>vv^<v<^>^>^v>>><^vv<><><>^<v<^^>>>>>v^^<>^v<vvv>>v<><^>^^^v^><<>v<vv>vv<<<v
            ^<v^<>>v^>>><>><>v^<><>>>^vvv^^<<^v^v>^><^>v^^<>><^v>^>^v<v<<vv^<<^^><^^>vv<v>><<^^^v>^v>v<<^^v^^<vvv^v>^^>^^<<^>^v>>><^v^v^v<<v^><v>>^>^v<<v>>v><>><v<^v^^><<^>^<v^<vv>^v>^^<>>v><<^^v<<>>^vvv<^<^^<>^v^<^^>vv<><^^<>v<vvv^>v><<v<<v<<>^v<<>v>><^><<^vv^v><v><vv>^^<<<vvvv<<vv>><>>v<^vv<^<^v<v>>>><>^<vv^><<<v^><^v<<v^^<>^vv<>>vv>>>vv<<^<<>vv><>v>>>v<>>vv^^^^vv<^<^v<>>v>>v^v^^<<^^<<^v>v<^>v^^<><<>>v<v<^v^v^v>><v<<>vv^<>v<vvvv>><vv<>vv>>^>>v<^v>v>><<^>^<>v^^<>v<>>v<<v^vv<^^^^vv^>v>v^>><<^>^^v<v>^v<>^^vv^>^<^>^v^^<<v^>^^<><^<>^<>^^v<<^<<vv<^vv^v^>^<vv<>^v>v>vv<^><<^^^<>v<<v<v><v>v>^>^^^<<>v<^^<^^^<^><^^>vv^<<>v>vv^<>>^^v<>v<^<<^^vvvv<>><v<^vv>^>^^v^>v^v^v>v^>v^>vv>v><v><<v^v^^^<^v>><^v<>v><>>v><v><v<<^>v^^^^>><^^v^^>^vv>>vv>>>>v<v^>><<v>>v^<v^<^<^v<<<v>vv<<^^<><><v><v^v<<^v<<vv<^^<>><<><><<^<<<<v><>>^>v^v>v^^^<>^v>v^>^><v^<^<v^^vv<^><><^^v>v>^^v^>>^<><<^<<v>><v>>^v<><<>>>^v<vvv^v<^><>vv>^^^<<>>>>^><<^<<><<<>>>v>>^>v><^v^<vv>>vv^<v>vv^^<>v>>>v>^>>^v><>>v<vvv<<^<v^>>^><^<^<>v>vv<v^^>v^<>>><^^<<v<
            ><^>v^>>>>^v^>v^>vvvv>><vv^<><v<vv><<<>><v<^<^<<>>v^><<<>v<^<>v<<><^vvv^^>v^<<<><<<><^>v<<<>>>^<^<<<vvv^v>v<^v<<^v^<><^vv^v^>^<<vv^v>vv><>>>>^<>>v><<<<^^^>^<v<vv>>^^><v^<<>><vv<><v<>^v<^^vv<><^>v<>>>>v<>>^^^v<<^><>v<>v><>^><^v>v>>^vv^^vvvv^<<<<<v<<>^>v>^vv<>v^^v>><^v><v>^v><>^v<^v><v^<<vvv^<>^v^^>^^v^^^<vv^v^<^<^v>^<><>>^>><^^>^v<^vv><^^v^<<vv<>^^<v>><^><><<>^^^v>^v>vv^>>>v^v<<<^^^>v><v<v<v><^^vvv<>>vv^<<^v^><<vv<<^v^>vv>^>^><vvv>v^^^vv<v<^v^v^^^<>>^v^v<<v>^^v<<<<v<>^<>>>^^>>v^<<^v>^<^v>>v<^>>vvv<<^><^>>v<>v<><^^^vvv<>^<v^^v>v^>v><<>^<v^^>>v<<<^^<>>^v<><<v><^<>>><v<v^><<v^<<>vv^<^<^>^>v^^<^<vvv^>v>v>^^>>v><^^<><<<v>^<>v>^v><^^<<<>^>>><<<^^<<^^<vv<>^<><vv>>>>v>^>>>^^>><v>vv^><>><v>^^v<vv><<>><v^<>^<>>><><>^^^v<>><>^<<v>v>^><^><v>>vv^^<v>v^v^^<^>v>v^^<><>>^>v>vv>vv>vv^>^>^vvv<v>^>>>>^vvv^<^>v<v<<v<<v<v><vv<>v<>v>>>^vv<<<^><<<vv<<v<^^^>v^>>v<<>^<>>>v^v>v^^<^^^^><v>^^>v<>vv<>^<v^>>>^^<<v>v<^>v^<v<^^<^>>><^<^v<v>v>>^>>^v^^>>vvvv^>v>>v<v<<<vvv<<vv^v<^vv^^^<<^^<^^<>v^^<v<^^v^><>>vvv<^^^^vv>>>
            v<><<^<v^>v^><>v<<><v<>vvvv>>>vv<vv^^<<vv>vv<v^<>v<^v^v<vv^<><<<v>v^>v^^>^vv<<vv^<><<<<>v>v^>v<>^v^><<<>v^vvv><<<vv<>>v>v><<<>^<^>v<<>v><v>^>>v<>>^^<>v><<vv^v>^v>^<<^^<<v<v><v>v^^^<^^<><>v>v>v><<<^><v<>v>^vv><^^^^vv^<v>^vv^><>>v<^<><^<<^>^v>^v>vv>v>><>^v<<>v><>v^<^^>>vv^^vvv<><v^^<^<^>^v<^>>vv^^><v^<<v^>vv^v<<><v^vv<><<vv><v>^<^>>v>^v><<><^<>^>^vv^v><>>>^v^<<v^<^v^>v^<vv^><vv<>^^<^<<^>vvv^v<<^^vv<^<v>>v>v>^^vv><>v^^<v<<<^<v>^v><<>^v><<v>>v^>><v>^v^<v<<>>^^>v^v<^^v<>><v^^v^>v<>>^^>^><<<^v>>^vv<^>^vv^^>^<>^<>v^>>^v^><<^<><>><><^<<v<<v>^<<><v<<>^^^v<<<>v>v<^v>>v<^>^<<>^v^<<>vv<v><^^v>v<<><><<<vv<v<v><v>vvv^<^vv<v<<^v^v^v<^>^^v^^^^v>>vv^vvv><v^>^<^^>v^><><>^^<^^v<<vv^>v>^<>v>vvv<<v>>vv<>^<v<<<^>v<>vv>>><>^<^v<><<>v^<>>v^^^^v^v<v^^^><<><>^>v^>v^^^v>>^>>^<<<<^v^v<>v<<>><<v>>v>v<>>^^>v>^<^^^v^^<<v<>^>>>>><>^<><^v><vv<>vv^<><vv<>><v>v>>v>^>^v<<<>^<>^<^^<^<>v><^^v<><^^<<vv<v><^v><vv^^>>v^^>^<v^>>vvvvv<^^>^v^><<v>><<<^<v<<>>v>v^v<^<<><><>^<v^^><^<v^^>>v>^<v<>v^v<>>v>v^v^><>v^v>v<<><^^<v<^v>><<>v
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        Simulation simulation = new Simulation(input, Version.V1);
        simulation.run();
        return simulation.getBoxes().stream()
                .mapToInt(Box::getGpsCoordinate)
                .sum();
    }

    private static Object part2(String input) {
        Simulation simulation = new Simulation(input, Version.V2);
        simulation.run();
        return simulation.getBoxes().stream()
                .mapToInt(Box::getGpsCoordinate)
                .sum();
    }

    private static class Simulation {
        private Entity[][] grid;
        private List<Vec2> moves;
        private Robot robot;

        private List<Box> boxes;

        public Simulation(String input, Version version) {
            String[] inputParts = input.split("\n\n");
            if (version == Version.V2) {
                parseGrid2(inputParts[0]);
            } else {
                parseGrid(inputParts[0]);
            }
            parseMoves(inputParts[1]);
        }

        public void run() {
            moves.forEach(move -> {
                if (robot.canMove(move)) robot.move(move);
            });
        }

        public List<Box> getBoxes() {
            return boxes;
        }

        private void parseGrid(String input) {
            String[] lines = input.split("\n");
            int size = lines.length;
            grid = new Entity[size][size];
            boxes = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    char c = lines[row].charAt(col);
                    Entity entity;
                    if (c == '@') {
                        robot = new Robot(new Vec2(col, row), grid);
                        entity = robot;
                    } else if (c == 'O') {
                        Box box = new Box(new Vec2(col, row), grid);
                        entity = box;
                        boxes.add(box);
                    } else if (c == '#') {
                        entity = new Wall(new Vec2(col, row), grid);
                    } else {
                        entity = null;
                    }
                    grid[row][col] = entity;
                }
            }
        }

        private void parseGrid2(String input) {
            String[] lines = input.split("\n");
            int size = lines.length;
            grid = new Entity[size][size * 2];
            boxes = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    char c = lines[row].charAt(col);
                    Entity entity1;
                    Entity entity2;
                    if (c == '@') {
                        robot = new Robot(new Vec2(2 * col, row), grid);
                        entity1 = robot;
                        entity2 = null;
                    } else if (c == 'O') {
                        DoubleSidedBox doubleSidedBoxL = new DoubleSidedBox(new Vec2(2 * col, row), grid);
                        DoubleSidedBox doubleSidedBoxR = new DoubleSidedBox(new Vec2(2 * col + 1, row), grid);
                        doubleSidedBoxR.setPartner(doubleSidedBoxL);
                        doubleSidedBoxL.setPartner(doubleSidedBoxR);
                        entity1 = doubleSidedBoxL;
                        entity2 = doubleSidedBoxR;
                        boxes.add(doubleSidedBoxL);
                    } else if (c == '#') {
                        entity1 = new Wall(new Vec2(2 * col, row), grid);
                        entity2 = new Wall(new Vec2(2 * col + 1, row), grid);
                    } else {
                        entity1 = null;
                        entity2 = null;
                    }
                    grid[row][col * 2] = entity1;
                    grid[row][col * 2 + 1] = entity2;
                }
            }

        }

        private void parseMoves(String input) {
            moves = new ArrayList<>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '>') {
                    moves.add(new Vec2(1, 0));
                } else if (c == 'v') {
                    moves.add(new Vec2(0, 1));
                } else if (c == '<') {
                    moves.add(new Vec2(-1, 0));
                } else if (c == '^') {
                    moves.add(new Vec2(0, -1));
                }
            }
        }

        private void print(Vec2 direction) {
            if (direction.equals(new Vec2(1, 0))) {
                System.out.println('>');
            }
            if (direction.equals(new Vec2(-1, 0))) {
                System.out.println('<');
            }
            if (direction.equals(new Vec2(0, 1))) {
                System.out.println('v');
            }
            if (direction.equals(new Vec2(0, -1))) {
                System.out.println('^');
            }
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    Entity entity = grid[row][col];
                    if (entity instanceof Wall) {
                        System.out.print('#');
                    } else if (entity instanceof Robot) {
                        System.out.print('@');
                    } else if (entity instanceof DoubleSidedBox) {
                        System.out.print('D');
                    } else if (entity instanceof Box) {
                        System.out.print('O');
                    } else {
                        System.out.print('.');
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static abstract class Entity {
        Vec2 position;
        final Entity[][] grid;

        public Entity(Vec2 position, Entity[][] grid) {
            this.position = position;
            this.grid = grid;
        }

        abstract boolean canMove(Vec2 direction);

        public void move(Vec2 direction) {
            Vec2 currPosition = this.getPosition();
            Vec2 nextPosition = currPosition.add(direction);
            Entity next = grid[nextPosition.y()][nextPosition.x()];
            if (next != null) {
                next.move(direction);
            }
            grid[currPosition.y()][currPosition.x()] = null;
            grid[nextPosition.y()][nextPosition.x()] = this;
            setPosition(nextPosition);
        }

        public Vec2 getPosition() {
            return position;
        }

        public void setPosition(Vec2 position) {
            this.position = position;
        }
    }

    private static class Robot extends Box {
        public Robot(Vec2 position, Entity[][] grid) {
            super(position, grid);
        }
    }

    private static class Box extends Entity {

        public Box(Vec2 position, Entity[][] grid) {
            super(position, grid);
        }

        boolean canMove(Vec2 direction) {
            Vec2 currPosition = this.getPosition();
            Vec2 nextPosition = currPosition.add(direction);
            Entity next = grid[nextPosition.y()][nextPosition.x()];
            return next == null || next.canMove(direction);
        }

        int getGpsCoordinate() {
            return (100 * position.y()) + position.x();
        }
    }

    private static class DoubleSidedBox extends Box {
        DoubleSidedBox partner;
        boolean visited;

        public DoubleSidedBox(Vec2 position, Entity[][] grid) {
            super(position, grid);
        }

        public void setPartner(DoubleSidedBox partner) {
            this.partner = partner;
        }

        @Override
        boolean canMove(Vec2 direction) {
            if (direction.y() == 0) {
                // horizontal movement
                return super.canMove(direction);
            } else {
                if (visited) {
                    return true;
                } else {
                    visited = true;
                    boolean canMove = super.canMove(direction) && partner.canMove(direction);
                    visited = false;
                    return canMove;
                }
            }
        }

        @Override
        public void move(Vec2 direction) {
            if (direction.y() == 0) {
                // horizontal movement
                super.move(direction);
            } else {
                if (!visited) {
                    visited = true;
                    super.move(direction);
                    partner.move(direction);
                    visited = false;
                }
            }
        }
    }

    private static class Wall extends Entity {

        public Wall(Vec2 position, Entity[][] grid) {
            super(position, grid);
        }

        @Override
        boolean canMove(Vec2 direction) {
            return false; // Walls can't move
        }
    }

    enum Version {V1, V2}
}
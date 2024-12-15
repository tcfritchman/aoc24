package com.tcfritchman;

import com.tcfritchman.common.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    static String exampleInput = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """;

    static String realInput = """
            Button A: X+24, Y+90
            Button B: X+85, Y+62
            Prize: X=6844, Y=6152
            
            Button A: X+35, Y+12
            Button B: X+17, Y+52
            Prize: X=9516, Y=13408
            
            Button A: X+38, Y+79
            Button B: X+28, Y+13
            Prize: X=3648, Y=4148
            
            Button A: X+32, Y+60
            Button B: X+27, Y+14
            Prize: X=11335, Y=18922
            
            Button A: X+29, Y+13
            Button B: X+52, Y+76
            Prize: X=7167, Y=1431
            
            Button A: X+39, Y+11
            Button B: X+18, Y+64
            Prize: X=3723, Y=3407
            
            Button A: X+97, Y+16
            Button B: X+48, Y+64
            Prize: X=8392, Y=6656
            
            Button A: X+49, Y+64
            Button B: X+62, Y+15
            Prize: X=5228, Y=4915
            
            Button A: X+99, Y+80
            Button B: X+19, Y+98
            Prize: X=9267, Y=9472
            
            Button A: X+23, Y+11
            Button B: X+42, Y+68
            Prize: X=15419, Y=9479
            
            Button A: X+26, Y+59
            Button B: X+93, Y+53
            Prize: X=8355, Y=9003
            
            Button A: X+47, Y+27
            Button B: X+29, Y+86
            Prize: X=3760, Y=8678
            
            Button A: X+15, Y+61
            Button B: X+72, Y+29
            Prize: X=2831, Y=15765
            
            Button A: X+45, Y+57
            Button B: X+79, Y+21
            Prize: X=2983, Y=3225
            
            Button A: X+23, Y+73
            Button B: X+64, Y+18
            Prize: X=10496, Y=432
            
            Button A: X+42, Y+18
            Button B: X+15, Y+23
            Prize: X=4412, Y=13708
            
            Button A: X+13, Y+99
            Button B: X+83, Y+87
            Prize: X=2884, Y=9426
            
            Button A: X+38, Y+31
            Button B: X+19, Y+87
            Prize: X=2641, Y=4371
            
            Button A: X+30, Y+18
            Button B: X+38, Y+89
            Prize: X=3844, Y=5815
            
            Button A: X+46, Y+66
            Button B: X+89, Y+40
            Prize: X=5366, Y=4542
            
            Button A: X+23, Y+57
            Button B: X+41, Y+18
            Prize: X=5055, Y=11348
            
            Button A: X+28, Y+63
            Button B: X+33, Y+14
            Prize: X=3782, Y=6461
            
            Button A: X+27, Y+64
            Button B: X+60, Y+12
            Prize: X=6077, Y=11760
            
            Button A: X+12, Y+33
            Button B: X+31, Y+20
            Prize: X=2968, Y=3986
            
            Button A: X+86, Y+43
            Button B: X+26, Y+99
            Prize: X=6996, Y=5304
            
            Button A: X+17, Y+49
            Button B: X+59, Y+16
            Prize: X=18869, Y=6446
            
            Button A: X+14, Y+26
            Button B: X+52, Y+15
            Prize: X=19540, Y=11477
            
            Button A: X+30, Y+11
            Button B: X+16, Y+56
            Prize: X=9718, Y=17799
            
            Button A: X+58, Y+29
            Button B: X+14, Y+30
            Prize: X=5576, Y=16523
            
            Button A: X+76, Y+17
            Button B: X+13, Y+48
            Prize: X=17477, Y=5797
            
            Button A: X+39, Y+23
            Button B: X+14, Y+46
            Prize: X=768, Y=1664
            
            Button A: X+45, Y+24
            Button B: X+20, Y+52
            Prize: X=3365, Y=11324
            
            Button A: X+58, Y+30
            Button B: X+12, Y+29
            Prize: X=8988, Y=17006
            
            Button A: X+20, Y+50
            Button B: X+54, Y+32
            Prize: X=18628, Y=15584
            
            Button A: X+12, Y+51
            Button B: X+23, Y+19
            Prize: X=581, Y=1918
            
            Button A: X+36, Y+23
            Button B: X+12, Y+33
            Prize: X=18212, Y=435
            
            Button A: X+16, Y+91
            Button B: X+82, Y+66
            Prize: X=4870, Y=10482
            
            Button A: X+47, Y+17
            Button B: X+31, Y+65
            Prize: X=2105, Y=2375
            
            Button A: X+38, Y+12
            Button B: X+21, Y+55
            Prize: X=15306, Y=7946
            
            Button A: X+49, Y+12
            Button B: X+15, Y+34
            Prize: X=5845, Y=19480
            
            Button A: X+22, Y+56
            Button B: X+61, Y+19
            Prize: X=19689, Y=4311
            
            Button A: X+35, Y+11
            Button B: X+55, Y+81
            Prize: X=13845, Y=19665
            
            Button A: X+99, Y+56
            Button B: X+35, Y+70
            Prize: X=2477, Y=3108
            
            Button A: X+20, Y+46
            Button B: X+55, Y+13
            Prize: X=14990, Y=9952
            
            Button A: X+16, Y+57
            Button B: X+88, Y+70
            Prize: X=8008, Y=9292
            
            Button A: X+64, Y+35
            Button B: X+25, Y+50
            Prize: X=9072, Y=1630
            
            Button A: X+16, Y+52
            Button B: X+85, Y+69
            Prize: X=9222, Y=10490
            
            Button A: X+13, Y+28
            Button B: X+22, Y+11
            Prize: X=14631, Y=13080
            
            Button A: X+21, Y+57
            Button B: X+56, Y+31
            Prize: X=6202, Y=7154
            
            Button A: X+69, Y+39
            Button B: X+20, Y+46
            Prize: X=16373, Y=5333
            
            Button A: X+28, Y+89
            Button B: X+38, Y+11
            Prize: X=3346, Y=2182
            
            Button A: X+27, Y+13
            Button B: X+12, Y+81
            Prize: X=1839, Y=2766
            
            Button A: X+52, Y+16
            Button B: X+13, Y+28
            Prize: X=5255, Y=7124
            
            Button A: X+97, Y+19
            Button B: X+33, Y+97
            Prize: X=4966, Y=7944
            
            Button A: X+49, Y+16
            Button B: X+37, Y+53
            Prize: X=4406, Y=4794
            
            Button A: X+75, Y+30
            Button B: X+34, Y+82
            Prize: X=5247, Y=4356
            
            Button A: X+11, Y+29
            Button B: X+42, Y+13
            Prize: X=7934, Y=15651
            
            Button A: X+15, Y+84
            Button B: X+94, Y+95
            Prize: X=9602, Y=11494
            
            Button A: X+91, Y+14
            Button B: X+17, Y+25
            Prize: X=7393, Y=1406
            
            Button A: X+14, Y+40
            Button B: X+52, Y+34
            Prize: X=2492, Y=13796
            
            Button A: X+23, Y+82
            Button B: X+63, Y+20
            Prize: X=4609, Y=4974
            
            Button A: X+83, Y+90
            Button B: X+76, Y+13
            Prize: X=1528, Y=1171
            
            Button A: X+21, Y+78
            Button B: X+57, Y+35
            Prize: X=3840, Y=4897
            
            Button A: X+41, Y+64
            Button B: X+46, Y+17
            Prize: X=11922, Y=280
            
            Button A: X+12, Y+41
            Button B: X+79, Y+41
            Prize: X=1561, Y=984
            
            Button A: X+47, Y+14
            Button B: X+18, Y+41
            Prize: X=15870, Y=18265
            
            Button A: X+32, Y+90
            Button B: X+79, Y+57
            Prize: X=7507, Y=5751
            
            Button A: X+53, Y+41
            Button B: X+20, Y+74
            Prize: X=4057, Y=4309
            
            Button A: X+29, Y+55
            Button B: X+36, Y+16
            Prize: X=774, Y=19798
            
            Button A: X+50, Y+77
            Button B: X+61, Y+22
            Prize: X=8740, Y=6985
            
            Button A: X+36, Y+80
            Button B: X+56, Y+17
            Prize: X=13104, Y=8073
            
            Button A: X+43, Y+17
            Button B: X+19, Y+47
            Prize: X=2652, Y=11866
            
            Button A: X+31, Y+35
            Button B: X+91, Y+21
            Prize: X=5751, Y=2079
            
            Button A: X+20, Y+57
            Button B: X+48, Y+19
            Prize: X=9432, Y=5497
            
            Button A: X+55, Y+11
            Button B: X+11, Y+47
            Prize: X=13927, Y=1243
            
            Button A: X+52, Y+31
            Button B: X+19, Y+36
            Prize: X=6279, Y=12982
            
            Button A: X+94, Y+27
            Button B: X+32, Y+59
            Prize: X=7116, Y=4634
            
            Button A: X+80, Y+93
            Button B: X+88, Y+19
            Prize: X=9592, Y=9568
            
            Button A: X+78, Y+66
            Button B: X+86, Y+14
            Prize: X=2110, Y=610
            
            Button A: X+11, Y+34
            Button B: X+37, Y+26
            Prize: X=3050, Y=15020
            
            Button A: X+45, Y+21
            Button B: X+33, Y+65
            Prize: X=18344, Y=10176
            
            Button A: X+95, Y+18
            Button B: X+19, Y+80
            Prize: X=10431, Y=8394
            
            Button A: X+11, Y+56
            Button B: X+23, Y+12
            Prize: X=1687, Y=3544
            
            Button A: X+98, Y+12
            Button B: X+19, Y+22
            Prize: X=6174, Y=2684
            
            Button A: X+44, Y+23
            Button B: X+22, Y+55
            Prize: X=990, Y=2127
            
            Button A: X+98, Y+79
            Button B: X+25, Y+90
            Prize: X=8715, Y=9470
            
            Button A: X+52, Y+78
            Button B: X+93, Y+43
            Prize: X=5880, Y=6118
            
            Button A: X+39, Y+24
            Button B: X+11, Y+35
            Prize: X=695, Y=7532
            
            Button A: X+74, Y+19
            Button B: X+15, Y+73
            Prize: X=2032, Y=18163
            
            Button A: X+48, Y+84
            Button B: X+49, Y+13
            Prize: X=8411, Y=9347
            
            Button A: X+83, Y+13
            Button B: X+15, Y+80
            Prize: X=8921, Y=9801
            
            Button A: X+20, Y+11
            Button B: X+19, Y+46
            Prize: X=6215, Y=10670
            
            Button A: X+66, Y+43
            Button B: X+14, Y+34
            Prize: X=19654, Y=3157
            
            Button A: X+34, Y+18
            Button B: X+16, Y+47
            Prize: X=1392, Y=2124
            
            Button A: X+99, Y+57
            Button B: X+11, Y+73
            Prize: X=7403, Y=10129
            
            Button A: X+67, Y+13
            Button B: X+18, Y+49
            Prize: X=4689, Y=15148
            
            Button A: X+16, Y+89
            Button B: X+69, Y+68
            Prize: X=3203, Y=5500
            
            Button A: X+11, Y+30
            Button B: X+49, Y+17
            Prize: X=2144, Y=11482
            
            Button A: X+41, Y+21
            Button B: X+12, Y+20
            Prize: X=7659, Y=247
            
            Button A: X+12, Y+56
            Button B: X+84, Y+26
            Prize: X=18800, Y=9584
            
            Button A: X+28, Y+22
            Button B: X+28, Y+96
            Prize: X=3192, Y=8206
            
            Button A: X+68, Y+32
            Button B: X+34, Y+87
            Prize: X=2448, Y=2856
            
            Button A: X+71, Y+38
            Button B: X+36, Y+84
            Prize: X=3119, Y=5618
            
            Button A: X+75, Y+37
            Button B: X+13, Y+33
            Prize: X=18380, Y=16766
            
            Button A: X+24, Y+94
            Button B: X+63, Y+16
            Prize: X=4752, Y=1998
            
            Button A: X+23, Y+26
            Button B: X+14, Y+72
            Prize: X=2231, Y=6398
            
            Button A: X+56, Y+23
            Button B: X+16, Y+61
            Prize: X=19656, Y=5328
            
            Button A: X+32, Y+11
            Button B: X+34, Y+73
            Prize: X=15646, Y=10729
            
            Button A: X+89, Y+59
            Button B: X+22, Y+88
            Prize: X=2144, Y=5606
            
            Button A: X+26, Y+54
            Button B: X+69, Y+37
            Prize: X=2969, Y=9101
            
            Button A: X+40, Y+17
            Button B: X+17, Y+40
            Prize: X=7098, Y=16873
            
            Button A: X+39, Y+25
            Button B: X+11, Y+29
            Prize: X=17680, Y=18112
            
            Button A: X+63, Y+18
            Button B: X+47, Y+60
            Prize: X=10214, Y=7296
            
            Button A: X+18, Y+93
            Button B: X+63, Y+32
            Prize: X=2880, Y=5488
            
            Button A: X+59, Y+18
            Button B: X+19, Y+40
            Prize: X=14410, Y=14486
            
            Button A: X+20, Y+11
            Button B: X+37, Y+64
            Prize: X=1673, Y=2186
            
            Button A: X+13, Y+58
            Button B: X+60, Y+26
            Prize: X=19672, Y=5444
            
            Button A: X+49, Y+67
            Button B: X+54, Y+12
            Prize: X=5885, Y=1925
            
            Button A: X+37, Y+11
            Button B: X+38, Y+78
            Prize: X=5683, Y=5625
            
            Button A: X+12, Y+62
            Button B: X+79, Y+14
            Prize: X=17225, Y=2330
            
            Button A: X+43, Y+14
            Button B: X+24, Y+62
            Prize: X=4130, Y=4200
            
            Button A: X+61, Y+20
            Button B: X+28, Y+60
            Prize: X=5033, Y=2660
            
            Button A: X+11, Y+83
            Button B: X+73, Y+37
            Prize: X=945, Y=5589
            
            Button A: X+13, Y+29
            Button B: X+67, Y+29
            Prize: X=1739, Y=16997
            
            Button A: X+54, Y+26
            Button B: X+24, Y+54
            Prize: X=5426, Y=7340
            
            Button A: X+39, Y+75
            Button B: X+61, Y+18
            Prize: X=4003, Y=2832
            
            Button A: X+12, Y+28
            Button B: X+91, Y+54
            Prize: X=8830, Y=5720
            
            Button A: X+91, Y+25
            Button B: X+63, Y+91
            Prize: X=6867, Y=3213
            
            Button A: X+68, Y+47
            Button B: X+12, Y+27
            Prize: X=9304, Y=14668
            
            Button A: X+27, Y+71
            Button B: X+47, Y+12
            Prize: X=16276, Y=10143
            
            Button A: X+61, Y+33
            Button B: X+40, Y+73
            Prize: X=1204, Y=1884
            
            Button A: X+86, Y+16
            Button B: X+81, Y+87
            Prize: X=9166, Y=7172
            
            Button A: X+71, Y+33
            Button B: X+34, Y+51
            Prize: X=5308, Y=3699
            
            Button A: X+43, Y+13
            Button B: X+31, Y+62
            Prize: X=12192, Y=12767
            
            Button A: X+24, Y+72
            Button B: X+50, Y+16
            Prize: X=6290, Y=10064
            
            Button A: X+27, Y+78
            Button B: X+66, Y+16
            Prize: X=18800, Y=19496
            
            Button A: X+78, Y+27
            Button B: X+45, Y+80
            Prize: X=6576, Y=2534
            
            Button A: X+98, Y+77
            Button B: X+20, Y+50
            Prize: X=6014, Y=6131
            
            Button A: X+96, Y+86
            Button B: X+84, Y+13
            Prize: X=9564, Y=4397
            
            Button A: X+82, Y+38
            Button B: X+20, Y+56
            Prize: X=5398, Y=3810
            
            Button A: X+75, Y+13
            Button B: X+41, Y+54
            Prize: X=6566, Y=4702
            
            Button A: X+19, Y+64
            Button B: X+47, Y+16
            Prize: X=7224, Y=18384
            
            Button A: X+11, Y+35
            Button B: X+85, Y+60
            Prize: X=19073, Y=6705
            
            Button A: X+12, Y+75
            Button B: X+73, Y+18
            Prize: X=10383, Y=5657
            
            Button A: X+29, Y+86
            Button B: X+59, Y+36
            Prize: X=8157, Y=10988
            
            Button A: X+14, Y+57
            Button B: X+65, Y+54
            Prize: X=3829, Y=5268
            
            Button A: X+94, Y+50
            Button B: X+33, Y+74
            Prize: X=7069, Y=6526
            
            Button A: X+57, Y+75
            Button B: X+58, Y+12
            Prize: X=9591, Y=7989
            
            Button A: X+61, Y+12
            Button B: X+22, Y+73
            Prize: X=5098, Y=17882
            
            Button A: X+46, Y+17
            Button B: X+28, Y+48
            Prize: X=7156, Y=19724
            
            Button A: X+69, Y+12
            Button B: X+13, Y+78
            Prize: X=4318, Y=19370
            
            Button A: X+73, Y+45
            Button B: X+16, Y+63
            Prize: X=4180, Y=7731
            
            Button A: X+94, Y+63
            Button B: X+46, Y+98
            Prize: X=9390, Y=6965
            
            Button A: X+71, Y+37
            Button B: X+17, Y+41
            Prize: X=3706, Y=4374
            
            Button A: X+16, Y+39
            Button B: X+74, Y+12
            Prize: X=5730, Y=2349
            
            Button A: X+43, Y+19
            Button B: X+18, Y+29
            Prize: X=16545, Y=15945
            
            Button A: X+60, Y+34
            Button B: X+14, Y+35
            Prize: X=17606, Y=1627
            
            Button A: X+61, Y+11
            Button B: X+74, Y+99
            Prize: X=8420, Y=4345
            
            Button A: X+43, Y+14
            Button B: X+21, Y+41
            Prize: X=11041, Y=9308
            
            Button A: X+93, Y+16
            Button B: X+21, Y+76
            Prize: X=7698, Y=5740
            
            Button A: X+15, Y+56
            Button B: X+75, Y+13
            Prize: X=11540, Y=2847
            
            Button A: X+42, Y+74
            Button B: X+32, Y+11
            Prize: X=6202, Y=17775
            
            Button A: X+33, Y+98
            Button B: X+33, Y+25
            Prize: X=4290, Y=5659
            
            Button A: X+43, Y+90
            Button B: X+83, Y+51
            Prize: X=9733, Y=10431
            
            Button A: X+33, Y+12
            Button B: X+17, Y+61
            Prize: X=2419, Y=14298
            
            Button A: X+16, Y+37
            Button B: X+63, Y+43
            Prize: X=13097, Y=1953
            
            Button A: X+23, Y+43
            Button B: X+29, Y+16
            Prize: X=11873, Y=10532
            
            Button A: X+79, Y+25
            Button B: X+14, Y+63
            Prize: X=3288, Y=3559
            
            Button A: X+17, Y+80
            Button B: X+66, Y+14
            Prize: X=12830, Y=16300
            
            Button A: X+64, Y+19
            Button B: X+13, Y+64
            Prize: X=9628, Y=343
            
            Button A: X+50, Y+15
            Button B: X+12, Y+92
            Prize: X=1876, Y=4806
            
            Button A: X+35, Y+14
            Button B: X+25, Y+48
            Prize: X=14915, Y=10180
            
            Button A: X+85, Y+79
            Button B: X+12, Y+68
            Prize: X=2124, Y=7204
            
            Button A: X+51, Y+81
            Button B: X+98, Y+27
            Prize: X=4720, Y=1836
            
            Button A: X+31, Y+44
            Button B: X+64, Y+28
            Prize: X=5380, Y=4180
            
            Button A: X+13, Y+40
            Button B: X+80, Y+39
            Prize: X=15000, Y=17783
            
            Button A: X+35, Y+87
            Button B: X+40, Y+23
            Prize: X=4065, Y=4143
            
            Button A: X+82, Y+11
            Button B: X+11, Y+67
            Prize: X=19056, Y=2707
            
            Button A: X+82, Y+16
            Button B: X+69, Y+80
            Prize: X=3085, Y=1600
            
            Button A: X+32, Y+73
            Button B: X+83, Y+48
            Prize: X=5700, Y=6784
            
            Button A: X+16, Y+52
            Button B: X+54, Y+30
            Prize: X=12928, Y=8512
            
            Button A: X+11, Y+95
            Button B: X+84, Y+82
            Prize: X=4187, Y=7205
            
            Button A: X+70, Y+24
            Button B: X+11, Y+60
            Prize: X=7383, Y=3308
            
            Button A: X+15, Y+70
            Button B: X+78, Y+16
            Prize: X=10688, Y=2316
            
            Button A: X+42, Y+70
            Button B: X+27, Y+14
            Prize: X=2787, Y=3374
            
            Button A: X+28, Y+65
            Button B: X+50, Y+15
            Prize: X=8694, Y=13205
            
            Button A: X+33, Y+66
            Button B: X+52, Y+22
            Prize: X=3329, Y=3542
            
            Button A: X+77, Y+58
            Button B: X+16, Y+56
            Prize: X=2219, Y=2902
            
            Button A: X+73, Y+13
            Button B: X+67, Y+74
            Prize: X=6479, Y=1340
            
            Button A: X+57, Y+21
            Button B: X+27, Y+61
            Prize: X=8618, Y=3574
            
            Button A: X+98, Y+67
            Button B: X+11, Y+58
            Prize: X=9734, Y=11299
            
            Button A: X+35, Y+18
            Button B: X+24, Y+68
            Prize: X=1787, Y=1086
            
            Button A: X+48, Y+12
            Button B: X+12, Y+52
            Prize: X=5256, Y=4548
            
            Button A: X+11, Y+65
            Button B: X+53, Y+35
            Prize: X=4903, Y=5605
            
            Button A: X+72, Y+23
            Button B: X+14, Y+57
            Prize: X=1906, Y=11572
            
            Button A: X+70, Y+11
            Button B: X+23, Y+75
            Prize: X=19700, Y=6986
            
            Button A: X+68, Y+80
            Button B: X+69, Y+24
            Prize: X=9476, Y=5888
            
            Button A: X+65, Y+18
            Button B: X+84, Y+94
            Prize: X=12438, Y=10306
            
            Button A: X+22, Y+54
            Button B: X+68, Y+41
            Prize: X=7188, Y=6941
            
            Button A: X+17, Y+74
            Button B: X+87, Y+55
            Prize: X=1355, Y=3956
            
            Button A: X+42, Y+21
            Button B: X+12, Y+49
            Prize: X=10190, Y=3098
            
            Button A: X+27, Y+42
            Button B: X+77, Y+14
            Prize: X=9691, Y=5026
            
            Button A: X+72, Y+58
            Button B: X+18, Y+52
            Prize: X=900, Y=1250
            
            Button A: X+36, Y+20
            Button B: X+13, Y+33
            Prize: X=14707, Y=8159
            
            Button A: X+17, Y+42
            Button B: X+70, Y+35
            Prize: X=15398, Y=7053
            
            Button A: X+34, Y+92
            Button B: X+80, Y+61
            Prize: X=6518, Y=9397
            
            Button A: X+69, Y+21
            Button B: X+24, Y+74
            Prize: X=2210, Y=5886
            
            Button A: X+26, Y+47
            Button B: X+36, Y+13
            Prize: X=11826, Y=8246
            
            Button A: X+26, Y+62
            Button B: X+53, Y+21
            Prize: X=10452, Y=6784
            
            Button A: X+11, Y+51
            Button B: X+56, Y+26
            Prize: X=1379, Y=2889
            
            Button A: X+43, Y+19
            Button B: X+13, Y+43
            Prize: X=12235, Y=12619
            
            Button A: X+36, Y+17
            Button B: X+39, Y+71
            Prize: X=15980, Y=10671
            
            Button A: X+46, Y+71
            Button B: X+38, Y+17
            Prize: X=12312, Y=13864
            
            Button A: X+67, Y+27
            Button B: X+33, Y+96
            Prize: X=6423, Y=4077
            
            Button A: X+13, Y+74
            Button B: X+56, Y+15
            Prize: X=15247, Y=5930
            
            Button A: X+35, Y+24
            Button B: X+15, Y+40
            Prize: X=1495, Y=15400
            
            Button A: X+23, Y+89
            Button B: X+81, Y+67
            Prize: X=4856, Y=5976
            
            Button A: X+12, Y+29
            Button B: X+55, Y+39
            Prize: X=6293, Y=9855
            
            Button A: X+19, Y+79
            Button B: X+75, Y+16
            Prize: X=2104, Y=13185
            
            Button A: X+57, Y+15
            Button B: X+47, Y+80
            Prize: X=4034, Y=5390
            
            Button A: X+86, Y+18
            Button B: X+13, Y+19
            Prize: X=3073, Y=1799
            
            Button A: X+22, Y+34
            Button B: X+60, Y+14
            Prize: X=4630, Y=2668
            
            Button A: X+37, Y+16
            Button B: X+19, Y+64
            Prize: X=17692, Y=2992
            
            Button A: X+65, Y+57
            Button B: X+20, Y+68
            Prize: X=3040, Y=3776
            
            Button A: X+57, Y+12
            Button B: X+14, Y+41
            Prize: X=5617, Y=2170
            
            Button A: X+80, Y+42
            Button B: X+16, Y+49
            Prize: X=18832, Y=970
            
            Button A: X+61, Y+13
            Button B: X+24, Y+65
            Prize: X=16814, Y=16994
            
            Button A: X+21, Y+11
            Button B: X+38, Y+66
            Prize: X=4440, Y=2696
            
            Button A: X+21, Y+69
            Button B: X+51, Y+17
            Prize: X=4359, Y=4987
            
            Button A: X+13, Y+44
            Button B: X+96, Y+61
            Prize: X=10409, Y=9366
            
            Button A: X+46, Y+84
            Button B: X+89, Y+16
            Prize: X=2678, Y=3132
            
            Button A: X+12, Y+41
            Button B: X+32, Y+11
            Prize: X=16352, Y=14216
            
            Button A: X+26, Y+72
            Button B: X+97, Y+18
            Prize: X=6371, Y=1854
            
            Button A: X+23, Y+52
            Button B: X+47, Y+24
            Prize: X=16247, Y=19584
            
            Button A: X+64, Y+28
            Button B: X+24, Y+64
            Prize: X=17024, Y=3816
            
            Button A: X+14, Y+63
            Button B: X+83, Y+81
            Prize: X=1233, Y=1746
            
            Button A: X+54, Y+13
            Button B: X+31, Y+64
            Prize: X=6604, Y=13773
            
            Button A: X+22, Y+65
            Button B: X+54, Y+21
            Prize: X=3136, Y=4356
            
            Button A: X+95, Y+61
            Button B: X+23, Y+57
            Prize: X=2061, Y=1619
            
            Button A: X+91, Y+92
            Button B: X+86, Y+18
            Prize: X=6816, Y=5512
            
            Button A: X+55, Y+17
            Button B: X+34, Y+69
            Prize: X=14540, Y=6542
            
            Button A: X+79, Y+35
            Button B: X+14, Y+47
            Prize: X=18571, Y=6471
            
            Button A: X+57, Y+26
            Button B: X+40, Y+70
            Prize: X=8564, Y=8202
            
            Button A: X+23, Y+67
            Button B: X+65, Y+16
            Prize: X=3491, Y=12320
            
            Button A: X+11, Y+55
            Button B: X+73, Y+33
            Prize: X=17828, Y=12728
            
            Button A: X+35, Y+81
            Button B: X+96, Y+46
            Prize: X=5039, Y=4791
            
            Button A: X+96, Y+49
            Button B: X+29, Y+62
            Prize: X=5486, Y=6104
            
            Button A: X+46, Y+17
            Button B: X+26, Y+61
            Prize: X=15944, Y=4136
            
            Button A: X+18, Y+38
            Button B: X+19, Y+11
            Prize: X=10523, Y=6611
            
            Button A: X+33, Y+64
            Button B: X+44, Y+19
            Prize: X=12530, Y=11701
            
            Button A: X+16, Y+32
            Button B: X+51, Y+33
            Prize: X=14647, Y=2445
            
            Button A: X+46, Y+65
            Button B: X+83, Y+37
            Prize: X=4994, Y=4006
            
            Button A: X+64, Y+20
            Button B: X+12, Y+51
            Prize: X=13152, Y=2284
            
            Button A: X+51, Y+37
            Button B: X+11, Y+27
            Prize: X=8177, Y=18329
            
            Button A: X+54, Y+97
            Button B: X+40, Y+18
            Prize: X=6680, Y=7368
            
            Button A: X+26, Y+79
            Button B: X+52, Y+33
            Prize: X=4394, Y=7726
            
            Button A: X+30, Y+75
            Button B: X+78, Y+22
            Prize: X=6390, Y=3865
            
            Button A: X+23, Y+62
            Button B: X+90, Y+15
            Prize: X=3347, Y=5153
            
            Button A: X+30, Y+50
            Button B: X+61, Y+28
            Prize: X=6580, Y=5810
            
            Button A: X+73, Y+13
            Button B: X+11, Y+78
            Prize: X=11150, Y=7400
            
            Button A: X+17, Y+89
            Button B: X+72, Y+78
            Prize: X=2965, Y=3565
            
            Button A: X+62, Y+93
            Button B: X+58, Y+13
            Prize: X=4424, Y=1308
            
            Button A: X+15, Y+97
            Button B: X+97, Y+28
            Prize: X=10668, Y=9659
            
            Button A: X+24, Y+77
            Button B: X+64, Y+49
            Prize: X=2800, Y=6482
            
            Button A: X+43, Y+12
            Button B: X+17, Y+32
            Prize: X=3533, Y=9192
            
            Button A: X+56, Y+30
            Button B: X+22, Y+46
            Prize: X=11852, Y=5624
            
            Button A: X+16, Y+27
            Button B: X+44, Y+22
            Prize: X=5416, Y=11356
            
            Button A: X+44, Y+12
            Button B: X+33, Y+78
            Prize: X=2861, Y=11042
            
            Button A: X+72, Y+40
            Button B: X+25, Y+52
            Prize: X=4348, Y=5312
            
            Button A: X+88, Y+23
            Button B: X+87, Y+94
            Prize: X=8018, Y=7654
            
            Button A: X+20, Y+74
            Button B: X+45, Y+11
            Prize: X=19355, Y=18661
            
            Button A: X+69, Y+16
            Button B: X+20, Y+65
            Prize: X=18261, Y=8934
            
            Button A: X+80, Y+32
            Button B: X+19, Y+66
            Prize: X=8136, Y=12400
            
            Button A: X+40, Y+14
            Button B: X+13, Y+53
            Prize: X=7815, Y=11767
            
            Button A: X+55, Y+28
            Button B: X+31, Y+52
            Prize: X=14530, Y=5416
            
            Button A: X+38, Y+20
            Button B: X+26, Y+53
            Prize: X=17104, Y=6610
            
            Button A: X+54, Y+25
            Button B: X+20, Y+47
            Prize: X=10538, Y=5739
            
            Button A: X+43, Y+21
            Button B: X+19, Y+49
            Prize: X=3514, Y=19674
            
            Button A: X+12, Y+27
            Button B: X+72, Y+40
            Prize: X=14996, Y=15251
            
            Button A: X+35, Y+60
            Button B: X+33, Y+11
            Prize: X=18903, Y=10131
            
            Button A: X+44, Y+14
            Button B: X+42, Y+79
            Prize: X=15818, Y=1719
            
            Button A: X+12, Y+81
            Button B: X+97, Y+29
            Prize: X=5661, Y=2544
            
            Button A: X+29, Y+84
            Button B: X+95, Y+30
            Prize: X=7753, Y=9708
            
            Button A: X+66, Y+38
            Button B: X+14, Y+32
            Prize: X=2624, Y=4182
            
            Button A: X+91, Y+48
            Button B: X+29, Y+79
            Prize: X=7537, Y=4740
            
            Button A: X+43, Y+28
            Button B: X+17, Y+60
            Prize: X=3448, Y=6600
            
            Button A: X+52, Y+92
            Button B: X+93, Y+27
            Prize: X=3518, Y=2098
            
            Button A: X+68, Y+49
            Button B: X+16, Y+39
            Prize: X=12960, Y=14984
            
            Button A: X+67, Y+24
            Button B: X+13, Y+53
            Prize: X=13552, Y=14921
            
            Button A: X+89, Y+12
            Button B: X+39, Y+60
            Prize: X=1380, Y=624
            
            Button A: X+94, Y+44
            Button B: X+16, Y+72
            Prize: X=2194, Y=7220
            
            Button A: X+14, Y+57
            Button B: X+61, Y+50
            Prize: X=4637, Y=5986
            
            Button A: X+27, Y+46
            Button B: X+36, Y+20
            Prize: X=2105, Y=5626
            
            Button A: X+15, Y+47
            Button B: X+78, Y+48
            Prize: X=3839, Y=12295
            
            Button A: X+18, Y+29
            Button B: X+43, Y+17
            Prize: X=17455, Y=3821
            
            Button A: X+69, Y+15
            Button B: X+66, Y+74
            Prize: X=7281, Y=2239
            
            Button A: X+33, Y+19
            Button B: X+14, Y+56
            Prize: X=352, Y=730
            
            Button A: X+50, Y+21
            Button B: X+24, Y+65
            Prize: X=5922, Y=11179
            
            Button A: X+39, Y+76
            Button B: X+47, Y+12
            Prize: X=10853, Y=14212
            
            Button A: X+68, Y+18
            Button B: X+22, Y+95
            Prize: X=404, Y=642
            
            Button A: X+34, Y+14
            Button B: X+31, Y+63
            Prize: X=11559, Y=9363
            
            Button A: X+18, Y+47
            Button B: X+57, Y+27
            Prize: X=7778, Y=19391
            
            Button A: X+65, Y+33
            Button B: X+18, Y+52
            Prize: X=17494, Y=5474
            
            Button A: X+17, Y+62
            Button B: X+79, Y+27
            Prize: X=5390, Y=17212
            
            Button A: X+41, Y+92
            Button B: X+75, Y+53
            Prize: X=4438, Y=7422
            
            Button A: X+59, Y+75
            Button B: X+30, Y+12
            Prize: X=18210, Y=1130
            
            Button A: X+27, Y+96
            Button B: X+48, Y+14
            Prize: X=2340, Y=4560
            
            Button A: X+57, Y+30
            Button B: X+61, Y+97
            Prize: X=9785, Y=11315
            
            Button A: X+43, Y+90
            Button B: X+49, Y+15
            Prize: X=2321, Y=4245
            
            Button A: X+22, Y+89
            Button B: X+77, Y+11
            Prize: X=2541, Y=7575
            
            Button A: X+77, Y+52
            Button B: X+16, Y+43
            Prize: X=14313, Y=10777
            
            Button A: X+16, Y+59
            Button B: X+50, Y+14
            Prize: X=1820, Y=2413
            
            Button A: X+68, Y+16
            Button B: X+22, Y+65
            Prize: X=3078, Y=5197
            
            Button A: X+71, Y+28
            Button B: X+22, Y+65
            Prize: X=732, Y=18964
            
            Button A: X+47, Y+78
            Button B: X+39, Y+16
            Prize: X=13668, Y=5742
            
            Button A: X+37, Y+24
            Button B: X+14, Y+47
            Prize: X=3255, Y=3211
            
            Button A: X+51, Y+17
            Button B: X+21, Y+65
            Prize: X=6177, Y=5597
            
            Button A: X+12, Y+73
            Button B: X+86, Y+23
            Prize: X=5142, Y=14930
            
            Button A: X+45, Y+25
            Button B: X+30, Y+51
            Prize: X=4655, Y=8742
            
            Button A: X+29, Y+85
            Button B: X+52, Y+16
            Prize: X=4745, Y=6405
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
//        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(Machine::new)
                .mapToLong(Machine::findLowestCost)
                .sum();
    }

    private static Object part2(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(s -> new Machine(s, 10000000000000L))
                .mapToLong(Machine::findLowestCost)
                .sum();
    }

    private static class Machine {
        Pair<Long> buttonA;
        Pair<Long> buttonB;
        Pair<Long> prize;

        public Machine(String input, long buffer) {
            String[] lines = input.split("\n");
            buttonA = parseInputLine(lines[0]);
            buttonB = parseInputLine(lines[1]);
            Pair<Long> unadjustedPrize = parseInputLine(lines[2]);
            prize = new Pair<>(unadjustedPrize.left() + buffer, unadjustedPrize.right() + buffer);
        }

        public Machine(String input) {
            this(input, 0L);
        }

        public long findLowestCost() {
            Set<Pair<Long>> possiblePressCounts = this.findPossiblePressCounts();
            Optional<Pair<Long>> cheapestPressCount = possiblePressCounts.stream()
                    .min(Comparator.comparingLong(Machine::getCostOfPressCounts));
            return cheapestPressCount.map(Machine::getCostOfPressCounts).orElse(0L);
        }

        private static long getCostOfPressCounts(Pair<Long> pressCountsAB) {
            return (3 * pressCountsAB.left()) + pressCountsAB.right();
        }

        /**
         * E.g. 8400=94x+22y, 5400=34x+67y : x=80, y=40
         */
        private Set<Pair<Long>> findPossiblePressCounts() {
            Set<Pair<Long>> possiblePressCountsX = findPossiblePressCounts(this.prize.left(), this.buttonA.left(), this.buttonB.left());
            Set<Pair<Long>> possiblePressCountsY = findPossiblePressCounts(this.prize.right(), this.buttonA.right(), this.buttonB.right());
            Set<Pair<Long>> possiblePressCountsBoth = new HashSet<>(possiblePressCountsX);
            possiblePressCountsBoth.retainAll(possiblePressCountsY);
            return possiblePressCountsBoth;
        }

        private static Set<Pair<Long>> findPossiblePressCounts(long target, long aValue, long bValue) {
            long aPresses = 0;
            long bPresses = 0;
            Set<Pair<Long>> result = new HashSet<>();
            while (aPresses * aValue <= target) {
                while (bPresses * bValue <= target) {
                    if ((aPresses * aValue) + (bPresses * bValue) == target) {
                        result.add(new Pair<>(aPresses, bPresses));
                    }
                    bPresses++;
                }
                aPresses++;
                bPresses = 0;
            }
            return result;
        }

        private Pair<Long> parseInputLine(String line) {
            Pattern pattern = Pattern.compile(".*: X[+=](\\d+), Y[+=](\\d+)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String g1 = matcher.group(1);
                String g2 = matcher.group(2);
                if (Objects.nonNull(g1) && Objects.nonNull(g2)) {
                    return new Pair<>(Long.parseLong(g1), Long.parseLong(g2));
                }
            }
            throw new RuntimeException("Invalid input");
        }

        @Override
        public String toString() {
            return "Machine{" +
                    "buttonA=" + buttonA +
                    ", buttonB=" + buttonB +
                    ", prize=" + prize +
                    '}';
        }
    }
}
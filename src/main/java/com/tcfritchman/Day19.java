package com.tcfritchman;

import java.util.*;
import java.util.stream.Collectors;

public class Day19 {

    static String exampleInput = """
            r, wr, b, g, bwu, rb, gb, br
            
            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
            """;

    static String realInput = """
            rwu, bubw, wbwb, buwwuw, rrwrw, wbb, bgbb, wugbrr, brrw, rb, rwbw, bwgb, uub, ubrguw, rbwrwbur, rrgbw, urgw, urr, buugww, ggg, guu, rgwuu, rbwuu, ugugbgr, ugr, wbbu, bgg, rgr, rbbubw, bwrbgww, bbgurbr, b, wgubgw, rrw, rww, gbu, wwwbu, uwwg, uuuu, gbb, wrwggb, urgubb, rwr, bbw, urbuw, bg, grguu, guwbw, bwuuw, bu, uwruu, ruwg, grwbg, wbrb, rwgwrgu, wguuu, wuu, rur, br, wwg, ugrubb, gwwubbwb, wwgwuur, rwwrbbb, wgg, bggg, uubuu, guuwur, bbwwuguw, rgwwgw, rgrrrwur, wwgr, wwurgb, uuruw, bwg, bwwwwgw, gwrbww, wgw, uwrw, rubwb, rbb, gubwgb, guwubgg, bugg, gwu, ubgrr, uuwbwu, gg, ggu, rrubw, rbg, rwg, rrwgu, wguwb, brubugg, gurgrr, uuw, guggrg, brr, gww, bbwb, rrwrg, uggbbr, brbwg, wubr, urgg, brw, uwwug, wugbrw, gbgrb, bwwggg, wgubur, guuggw, brwr, urbr, uurrwrw, rrb, wggguw, brugrwg, wubrb, ubb, brggw, uuur, wubrgbr, wbwg, rwugur, gwg, wgb, uubr, ww, rgg, rwuwgur, uru, uwu, gwr, ru, uwrr, ubrwwr, bwgbgg, bwrw, grr, bbb, w, wrbgrg, bbwbgb, rwb, wwgrb, rub, bubr, wb, gwbuwwbr, bwgwrb, guw, wwb, urg, ubgu, rwuuub, rg, wuw, bwr, bw, rwuw, rggr, buwu, uu, uw, bbugb, bubbb, uuu, grrb, wwbw, rbrbu, rwwrb, bgr, brb, wrrrggw, gw, bgb, wguww, rgug, wgu, wbugwbuw, ggb, rbrg, buur, gub, rbu, bgub, ggw, rw, rggbgrbw, grbb, gbbb, ubgbg, gbr, uww, gwurb, rrwg, ubg, uwwrwuub, gbwwg, ruw, wbuwu, bbrg, grrg, gbrrr, wwu, bgwgb, wuwgbuwu, bbu, gwb, rrwrbbrr, wbg, wrww, gru, uuguug, wbubrg, rbggr, bwwb, ruu, wbww, rbrbr, rgwgrwuu, bgur, rgbbuu, ubu, urbwru, urbgb, gbg, rbrb, wu, u, rruwu, rrrrb, ubbru, urwubr, urrb, bug, wwrrrbb, rgw, rwuu, wrbu, brg, rggg, rrggw, rwuuwb, bruww, wrgb, ruruuwwu, bbrugrwb, bur, buw, wrrg, g, wbbbwgb, gur, ggwgg, ugbg, rrr, ug, bub, ugbr, uwwuu, ugwrwr, buu, wugb, rbrr, bbgw, wbgbg, gwuw, uug, ggbr, bggrb, ugru, uwuw, ugw, wgwrrwr, wrgrb, rbrgg, gu, uugwuub, wuwrw, uwg, gwwrbb, gbbrb, wrg, rguuur, gwuwrwgg, ugrwr, ururb, wwgwbg, wgwu, wgbwu, rubwwr, bwrg, wbr, ggr, uwwu, wuuwgrr, ruurrugb, grugrub, rrurb, rgb, ubwwurr, wgbubgw, ugu, bbub, gubu, bugw, ggrww, ugwr, wrw, brgb, rgbrgru, ugguwb, brwrubu, grw, gbrr, bgu, uwr, uuru, wgbr, bruw, gwwr, gbgw, wgbubggg, bgrg, ubwrg, wrb, rwgwg, grgb, ubuuu, wr, ub, bb, bru, bbwrgr, wuuubuw, rgru, rgugguw, gug, wwubwu, wgbbw, ruwwwgw, wgr, brbuuu, rbwrbr, ruuuub, gb, wrbwwbg, bruug, wgug, urb, rru, gr, gbrwgw, wrr, rubug, bgguw, gwgr, wwub, rwrrg, bgubrr, urgwg, wwr, www, brrubg, ugrrg, wurugguw, wur, uwrwrg, rrg, bwgrr, gbggbb, wrrggwr, rr, gguu, wru, wbu, wuuuubw, ugb, rgwuwuuw, bwb, gbrgbg, gwugb, gbggguww, uurrggw, wrwr, bwu, bbr, ggwggwu, bbrubw, ubw, gbw, bbwggbu, rbbbg, ruubw, guwb, rruwbg, rgwr, wrggr, rbr, rgu, gwwbw, wruwg, rugggug, grg, ruuwu, wbub, uwug, rwbgggw, uwbb, urw, rrggrg, urwg, burwbw, rbgb, bbg, ugugurr, wug, guuw, ugg, wbw, gbwwr, uwb, rgbg, gbbu, bww, brugwug, wrwwwrr, rurbgbgb, ugwu, ubwuggu, bgw, urrbb, bgbug, ubr, gurg, wwbwww, buwug, wrrwgbb, wuub, rwru, wggubw, bgrw, grgu, brbrgg
            
            brbgbrurbgbgbrwwggwuuwwbrbwuuwuwwugbubwuwwuruwuburbwuur
            wgbwgwgugwgwgrwbwbrrugubwbrgwuwuuwugrwuur
            uwuggwwrwuwbuuwugurwbwggwurgbbwbgugrrubbgwbuwgbwgwwurgwb
            bwggggwwrwwgbrrurwggwwwwuwggbbrrwrwruruburbrgbr
            rrwububwbugrruurwuwrwbggwbugwwwruuwrbbugbwrrbuwrwrurw
            bbgrbuwwrgwgwuburwgubrwuwrbguggwurgbbgwrwbubggbrwrrbwuuu
            wbwuwbggubbgbbbbubgwgwrgwuubguwubgubwwggguwubuwuugrbggwuw
            bwugbgruurbburuwgggbubbgbbggbguburgwbgwbrgbwugrbwbuugw
            grrwurwwwruwurrgrubbrbwgubwgbrrgburwugwggwwrwb
            uwwbwgbwruwbwgwwggrbugwgbbrbrwruuububwbwrurugwwgwwwwwuur
            gbuwrbwrwwbbuwwurbbgwbwwuwbwwruurbbruwruwbgrwbgrrgwwbbwr
            ugrgbrggrwugugwbrwbbwwbrwrbuuuwubuwrruur
            bwwrruurgubwrgrguuwwbwrbuwggbwgrbrbbugbwugggrwbgur
            wwbggbbwubbbbwbbgrgggrrgrrwbwgwgubwwrguwrwwrbubuuruggw
            uwrbwuggugubbubbrubwgruuugbrbuwgbugwgbbuuggwwwbwwr
            ugwrgwbrrwwbuguubguwwubugwgbrbbwrwbubrgbggbwgwbwb
            ugurbbrwwrwwrbbwugbguwuwgwbuuwguubwwbuwwggbrruwbwugwg
            rbwbbubrurbwrwrwurbbwwrggggrrbbwwrgrggruwbuuwwwuubuu
            bwubuwuwrurbwggbburbrbgwrruruuwbbuuwwrggbgrbwurrb
            wrbuguuwugbrrwbrugbbrgbugrwuugubwugwwbubruwruurruu
            wbugwubuwbwugrrbggbgruuwgrruwgrbguuwrbgrguurw
            wbguwurwrwbbuurubrbgbbrwwburrrbbrggwrrruwbgbbrrgwubuug
            brrrwbwuwgrbrrurwwbugrwwbgguuwubbrwuuruwbgurggbbubbguur
            ububbuwggguwwuguggguwbrurwubuwurwubwwrbubrrwgggwgb
            ggwgbrgbrugrgrrurrrrbgbgbwbbgwuubuurwruwruur
            buurugrwrwgrbuubwubbwbgwwbwwuwuubrrrruuurwuuubgwwgubrrrrwuur
            gugubwbgrbrgwuuuwgrgrgrwwrrbgrgwugwgwbbwuwrwggbwgwg
            rugruugwwgwbwwburuugwwgubwrgrrrwurrgbwuur
            ggbbggbbgubwggrbuwbubrurgwbwgrruwwgwbgubub
            gggwurrgwuwuuwrrrbrwrwwrwbgwwrbgwrubbwugbgwgwggguugggbg
            rwbggrbbrrubrbgggrrbrgrgrwrgburubbuuubruurrrurgwbugrbr
            uugbwwrgwrrbrrugrbbuuwbuuggrrwwrwbgbbrguuruwrbbgruubw
            buwbbbgwwrrrggbrwubrwbbbrwwugbwgggwruuuubwbgbbru
            uwwwgwbgwwwrbguwbgrbwrwguwrrrgwrwrbgrgruurrggrwgbbuw
            uwwbuuwrurrbugugbgruwrggbuububugggurubrwbugrruggurgwruur
            brbuuwwbugubgrbwwwrbgggruurrwurgbwrwwguruwbr
            bbrgrbbruuugwbwuurrrrgubgburwuubgbwgrugbugbgurugwwgrrrwwuur
            burwgrrgwgwwrbbgubwwbwruwgwugbwgugrrurgrbrubwugbu
            ggwwuwgwwggrwwurwrgwwbgurwgbugrrgugbrubrwwuwguugrwwggbgru
            uurrgbbbwrwbwgburgrwwgugbbgruurwbugguguwrurggbuwuwrbw
            uwbuwrruuuwbbguwbgrwwgbuguugubuuwrurwuwrbbr
            rgwruwgubbwrrruburbbgbguubrwbwwgbuwgrgwububrwgrwwrwwbuuuu
            ggrgrwurwuuwbwrbwrugurwbwurrbbwbrbruruur
            ubrggwgruuuurrwrguwggwgbuwggggwwwrgwbgbwbguu
            wrwwrgbbbgruwrbubbbrrbbrbrwrwwubuggrbuuwwuwwrwubbggbbwbwuur
            bwwgugbrgbggrgwguwurrwubuggbguruwwrgrwuwwburrburgrgwb
            gwubugrgurbbgbubgbwgrubbwbgubgrubgrgbruubggwwwgr
            rrggrubrbugubrbuburrgbubuurugbgrrugwgbrrrwubbggww
            rurwbgbgwbwrbburubruubrbwgrrrbbrbrurububwgwwugbwg
            wbgbgrrrgwgrwbrwuwwuuuurubrrbgwwwbrbgbuwwgbbrrbgrrggbb
            wbgwbrgrrbbwrwbrggwwwrbrurbgbuggurgrwrgwrwuwr
            wrrbrgbwrugrgwuubuubguwguubwrgugbggwgurbugbrrubbrrgrbu
            ruubgrgrgrurbrgugugbbwrrwuwbwuwwwwuwbgbrgrrrbrubb
            urwbbwwgbugwgurbrgwruggwugbburbbwrgubugwwrugwubrg
            uwurgbuwuuwgwubwgwuwwgbubgwrgbgrrrbubwgwgbwwgbgb
            bbgwrrugbgrwrrgwggubwrurwwrgubrrbrwubrbwrwwrbrr
            burwbwuurrgbbbrgrwuubuwwwuwgbrgruwgwrgrgbgrwgu
            buwgwrbbrguwrgruwguwrrwugbbwgggggwrbwubrur
            rubgruggggubrbwgbggwugwgggrgrurwgbrgbbrbbbrgub
            ugurruwuggbrrugrbbwgwrgrurbwwgrbrbwurwuurbrubwurug
            wuguurubruguuwwggbgwrrrgrrurwrwgrgugrrbbbubgrwuguub
            ugbrbwrwuugrgwbwubbubuwwgwrgbugbgurbuwrwgwb
            rbrrburrbwwgbrururrggrbubrgburwwwbwrgrwbrrrggwgbguuug
            uugwggrwuwbgwrrgugbwwwbuwrrbgwwugbuwggrugbuuw
            ubgrugbbwuwgbuwugbggubrugwuurugwbgrgugrwrruwguur
            guwbruuuurbgruwbguwwgwbugbwrbrbgbwrguwbwrurwwb
            bwwwrwbbwrwbbuubgrbggguwgwubgwrrwubggrgubwwbwruur
            wwgwubwbbuwgwbgubbwruwrbrbururrbruwugggggbwubrrwwgwuw
            brrwgwrubrugggrbuubgbbbbwuuurrbgwwrgrrbbuugbgwwbgrr
            wubgugguugwgrwbwuwubgbwguwgrgggugburrruwguur
            buuuugwggwgwgbgubbwbrbwrwburuwwuubbwrwgguur
            wwgbggwrrrgbgggbwgbubggggbuurrrruwbwbbbgbbggwwgrugruur
            buguwwwrbruubuubgrgrruguugggrbgwwuwuggwgrubbgwrwbuub
            gbruurruurwgbubgggwggubbwuwugbbgwwubbrrrrbruwwbwwrrwwbggw
            bugrgwuwwbrurggwwubbwbuwwgrrurgbrwbggwugbrruur
            urggwgbrbbuwrrbgruuuugurrurwrgbgwrbgbrgurwwb
            ubrwbgugbuwuwwwwwrwugwbwbbuuuuubwwwbgwggwb
            uwrbbbrgwrugrrgurugwuruwrubgrwuruwuurwubrbg
            uwrbugrrrbugggwurrbgrugrrgbuurguwuwrgbbwbrww
            wrrgwgbgugrruguwrrwbbwugburrwwrwbuggbuuubbrbruuguuggurgwbw
            rrugggbggbuwwgurubwgbbwwuwbuwwrguugbrbbbrugrr
            wuwgguuggwugguwwrrugbrburrgbburuwwuubwggwuwgbuwurrg
            wbwgbbuurgbbrgrrurwwrbrrwgrrrwbbggwbbrgurugwwrbgrbgrgb
            rggbubwrrbrwuruuwwwrgugguwrrrbugwwrgggbbuggwugwbrwruwgw
            ruuwrwburgrwwrrrrwrbbrrwwbgbubuuwguubrgrgbrbwrrruwrbrub
            ruwrubbugrugwggwrbwrrgwuwgubuuruuggrgubwugwugbwbrgbruur
            rrwururuuwwbrbgrururrwrbrgubbrbwwgububgbruubguuurww
            rruubrbrrbggwrruwrgwguwgburbbbggubugwuurrrbbwbwwu
            rugwubgbgurgwbuwbrbubwgbuugwubgbuuggbwuuwubrbrgrwwrgggu
            wrugrwrggbugbbbrbwwubbwuubrgwrwgrrrwbububbw
            brbrbrwwwrbrrgrwwbrrbbubwwwwburugbrgwrwruuubggugr
            rbggwguugubuurugrgbggbggbrbwruwguwurguwuububrrwrguggwrbgg
            rrggrgrugbrbgrbwwwrbrwrgubuwguwrwburbwbgwgr
            ggwrgwruwgruurrugbbgrwwguubrggwwbgrbwwggbbruwugubbgbuw
            wwrwgrbrbgbwgwwguwwwuubwrbuwbrggbrbrwwwwruwuur
            rwggubwgrbrbbbwbrgbbrubruuuwrwuugwwugurbwugrwbgrwuubub
            bwrrwgubgwbwbbugbubrgwgbbrbbbwgugbbugugwgrgubrwr
            bwbgrbbgbbbrwburgrgrrrrubwurbbubbwuwubwugrwwrbguwbwbgguwug
            gwrbbbwbuuggurwuwgwruwbrrwgrrugggrbwgbwrrbgwgbwgwrrruur
            wrubuugugwbgubwggbbwuuguuguuuuuuuguggrbrrwubuwrgbrbu
            wruubbwrggurwwrrwgrbrbbbwrrwbgggrbgguwrwwwwwrguwubr
            grbgrgwgwuruubwgwuwgruubgbgggbuuubgbuubgwrwwruu
            wwwuwuurrburgrbbrgruurrgwwbgwgrgbrggbgbgrrwbr
            ubrbgbggubgbruwbgrbuugggruurubrurggbrbwwwwwbgguggrggb
            guwbwwubbrgurugurbgbubbrgwrugbbgrbbbbwrwbwubrwwggrurbuu
            bubburbwgbwbbwuguubbbubbwbbbbgrrwbwgrwggrbggrrrgbw
            uwbwruguwbrrrrbgggbbgwgwwbbuuguwbuwuurwwgbrwbrrgwwgguwwwu
            wrwwguwubwbbgwugwrwgubbwuwwwbrwrwgrbbwrbwuur
            bwbuuwbwuuwwrwuwubwgbwrguwgubuuwbrrurrbugbrruubbuuwrrgwwg
            wwbgrgwguubrugugbbubrrrgbbuburrguurggrrwrbbrrgbuuugbbrbr
            bwuugwwbbgurwurugbguuubrwrwrgbwbgrwrbwrruwgwg
            rbgggwgrgrbbggrrwbuwwrwwwwuuwgwwbgbwbubgbbwugburwugu
            ubrggwwbrgrwrwbwwwbggwrgurugwrwrrubbwguruurbgu
            gwurruuggubgrwrbbwrgbbuwbgbugwwgbrwuuwrgrbrbbwrrguwurw
            wgubugrrbuwbbwuguwggrurgwwwruuuurrgbuwwbrgbggrgwbuwuwwrr
            wrgguwwwrrgrbggbggbwgwgbbwguugbbuwubbwuggrbuwgrbrrbgbg
            grrbubuwubrurburwbuggrwbrggwgugrgwuuwbbwubrbwrrwuur
            rgrrwwuguuuwgurrgwbggwbgrrwgwwggwuwwurbrbwgugr
            gbgurubrubrbwugugbwbbguwruwrgwgwugbgbrgwbbgwuguw
            ruuwrubrgbggwbrrwubrwgrwgwrbgrwuguuwwwgrwugguuugw
            rubwbbbrgruwwubuwbubrgbrbwuugbuuwurgwurgwugbugbwrgwgbgwgu
            gwwrgrbuwgbrgwgggurgugggrwrbggbgwuuubburgurbwbrrwww
            rwwuwurgrwrbwbugwbuwgrwwwgwwbrrbwbrruugburbub
            wgrurwwrbbubbrbuwruwrubbwububuugrwwwuurggrrbwbbwub
            urwrbwurrgbbrbrgrbbuuuwwubrbrbwubwgubwgbbbrbuurgubgbwgbbur
            rrwwuwggrruuugbrwwugruwuugwbwuugrwwgwubrrubwgugwwrguugug
            rguggrgbwwbugugbgubuggwuwrwgggruwurrgbruubrwggbwbbbuwuur
            rwwwbrurggwguwuwwuwwgbbgwrrwgwrburuwuggwggbrrwg
            uwwruwbubgrwugggrgrubbrrwuuggubrbbggguur
            wrwrwbbbrbbgwguugbbuwuuurgwgbuwwbuubgbubuwbuurubbguwuguwuur
            wgbrbbuururgwrwububwbrbrrbwbguwrruwgbbwwwugrbbrwurgbrg
            brwwuwuwubgugrbgwubrugrwwburbggwuwwbwrgguruur
            ugruburgwwgwguugbbbuwgubguuwuubrgrwurrgbggbu
            ugubuubrrwrrrwrbwwgrguurgguruguwggbuwrwuwrgw
            gggrwbruruugbgruwbrrubgwrgrgugrbubbuugwbbbguw
            gbubbgubuubggguwgggbggwurbgguggwugrubbwwrburrgbruwgugr
            bububbuuubgurggrbguuurggubwrrrrwrwuwbggurgwuruwgww
            wuuggruuwgurugggugubbrwwggwwburuwwbbrbubgg
            rwggwgbbgggrruwwwgwruggurgwwugwbgbbwwuububgbuub
            wwbgwbubgbwburrwggggbwugbbrubrrrwgrwuwwbwrbggwwgg
            ugwgrwrwwgbgburbgrrwrurgguguuburgbruwgbwurwbrggbwu
            gurbrbrrruwrurrubrgurgbugrruggbuwuburrrbbbwwubgbgww
            wuuggubbwwwbbggbgrrbwgrrrbuggrwbrgbrurrwbrurbgwb
            rbggrgrwrurwbwwrgwgbugburbbwbwwggbgrurwgwbggguuurguwr
            wwrgugrubgbuwguwrwwuwrrbgguwgrwwubbbbrubgbrugubrrwrrbgwurg
            rrrguugrwbbuwgurgbwbbgguurggruwgggwgrubrbwwb
            bbuuuguwrwuwbgbrgggrwuwgugwrwuwgrwrrrrurubrwurgrbrbwbrbwgg
            gurwruwurwgguwbggbugbggguwwrbruuuggrbrbwubrugrbggurwwbggruur
            bbwgwgrbgwuugwbrrgwbgwguggrbrrbwurubgurgrug
            gwwrgrbbwuwuggrurrbbgurwguwrruwwgwbwruurrwuur
            ubrgwwuugurgbgbrwugubbrgrwurrbrgugwrrurgwuwubrruu
            ubggrwrgrrwgrwgbrwwggrwurbgrurwugubggugbwuguur
            bgrruguuwrwwuugwuwbwwwbwwruuwrwbbubrruur
            bwgbwbwugguwuugwrbrbgwrggwwgrurguuwbgbbwubguubwuuw
            bgrgrrrwbgggbwgrruwgwwgrrrgbbubrbbwrgruurrw
            gubbwrwuwurwguugugugggrubrgurgguugbgrrugwbrgu
            grgbururuwubbgugubwgbgggrgrwrwbbbrguwggrbugbgrw
            guugwubwbbrgggrggrwbwuruwguugwwrwbwubgwbub
            urguuuwgbwgwggbrwugguuggugugbbuwgwgbbrbggur
            ugugggbubwwwuggwgbbrrwbbbbrrbuwgwrbwrggurwggwwg
            rwwgwgrugubwwgbuwgwuuwburwggbbwuuwrrguuugrwrububrwrwgubwg
            wguuwbbggwubbguwubwbrrbuuwrrgbrrwbgrwrbgbubuwuur
            gggugrggbrwwwwbrgwwwurgugububgrwwugbgrwrwruwwgrr
            gwugruugrwggguguwuubbgbbubbbggwwuggburbwuggwuubbbwwrwgwuur
            rurgwgrwgrgbrrwwwrgbwbwrruwguuwgrburbbuuwwwru
            grgurgggruubbgrbubbwrgrggbgubuugwgurububgwrugggwggugbbruur
            urgrugbwwrrrgbgurggugrrubuuuuwrrrrwwwuwugbruuggwgwuwbbbww
            gguwgruuurwugubrgbrbwgugrurrgwuwrubgrwggbwgggu
            guwbubguguurgruuguuurubrbwwwgbuwugbbbuwgbbbuwubwwwggg
            gbgbgguuwbwbwubwgrbwuugrwuwrrbgwrbguwggbgwbbwwuguwguruur
            rgubbwgrggwwwgbbwgbrrggbgwrrbgbgrbbburbgbbrwuurrgbruur
            wgbwguurruuuburbbuwbbrwwrugbwwwuwgrwrggbubbw
            uubuuurwgbgbrbbgwruuwbuwrubwuguuurwrgbwbrbwuwbubbr
            uwrurrurrrwwwrrrbubwuuuggrbruwwwrbbbwruguugrwrgwuur
            bbrwgbbrrwrgwwrbwbwuggbguuguwrggbruuwurrwggrr
            ggburrgbgurrgwrgbbwguwbwugbgururbubrwwwubrgbwuuggrwbu
            rgbgubuwbbguwuurrrurgrwgrbwwbbrubrbbgwrbgubbrwrrb
            gbuwburgburgwgbubrugugwuwgbbugrwrbugwgrubwrb
            wuubrbgruwubwguuwwrburbgwwwrbbrrbbbwbwbggrgbbuwwrgrrwrruur
            grgurwwbbuububrrrbrgrbruuuwuurggwrburrbuwugwrrgruruur
            uwguurgrrurgwrurgwuwugbgwugbbbrurrbwgugbbubwwrrr
            rrrgrrwbrwbwwgurruwrgrwwwbbwbgbuwrurrggbggwbuwurguggb
            bruwwugwbgburgbruuwbwwwuuwrbbbwrruuuwggrwugruguur
            gbwbbrrubgurbgruuubwwwbwgubrrruubwgwugururgguugrwrrwrwuubu
            bbggbuuuwugwwuugburgugwbgrwrurrurruwgrwrwuwbugbg
            urgubrggwrwubwwgbgwubwwgwbrrgbugwrgbgwuwbb
            gguggururbuugrwbgrbrwrrbuurgwwrwgrbbwwgwwrrbrrgg
            uurbbbruwrrggrgwgrbgwbrwubgbbgrurrgrgrgrwr
            ugwbuubbwbwrwgrwburrrggbrbuwwrwwrwbrrwurrgwwwuwwb
            bgurbuurbbwbwurubrwurggwgruwurgwgrwuuwbwuwwbrgwrr
            brwrbwgbubbwgggbwrrwwgurgggggubrrubbbbrugggrgwuu
            rbruwggbggurbguwbggrburrbbrrbwrrguwgrrburbuug
            ggwwubbwbgbbuwrwugrwubgwgwrrwbugrubuugrrrruubwub
            gbbguwwrwuubgrrggrubbuwrwrwgurbrwbggwbgugr
            gwgwugbrbbubgwwbuggwwrrwwgrrgwwurbuuubwguuw
            wgrrbugrwwwwrurbrwwuuggwguwwgrbwwubwgububr
            uwwgugburwrwuwuuwrwgbwwwwuwwgwgwrugrrrubrbg
            grruwruwwbrugrruggrwwgwwbgbbuguwuwwuguggwurwuugguur
            gguuuggbuwgwuuubbwbgbuurgwbrbgguwgubrrguuggwbggwwuur
            burgggbgbbbgubbwuubgggbuwguugubruwuuggwuur
            gugbbbubuwbgrrrrrbgbgwwwrbgwrbgbgrrgwubuurwbgwr
            ggrwgbburrrubguruwrguguwbuwugugrguwggbrugrbuuwwbggbwwubr
            wuggbrrggrwbgbgrggrburuuwrwgrggruwggbgruuurrguubwwgubugbw
            gwuwbwbwubwbugggugubwgwwrwubwrbbwrubrgwwuubbwggwg
            wbbguuwrbgwwuwurruuwburwgbwubwgbbuugurugrwugbugu
            uwwuugwuwuwguubbbbbuurrrubwgrbbruwrwuurwrbruguggwuwu
            wwuwbrrwbbrrwgrgwrubruwbuuubugrubbuwuwrruwgrwrww
            rrgbuuwgwbbbgbuuuwwwggwrrbgbrgwbuurgbwbbwbbwwugguu
            ubbwgwurwwurrwbbbwubwwbgwwrwrgrubwbrgruwugbwrwbwgwbuw
            rruuwgrwwugrwgwbuwwbruuuggrwrgubbwgwuubrgbrb
            buuuguubgwbuggubwggrbwgguwbgugbuururbbgbwbrwbrrgrrwgrurwgu
            gbwwwwwggrrbuugugbwwrubbrrgggrubwuubgggrgugubbbrwb
            bbgwgwubwrrrrwrrrgruwrugwrbbuwrgruuuwrgwbuwwbrguuuwubw
            wgrwgrwbbwrrrbuwwrwuubrbbgrrububbwrwwwbgurrrwugwubgrguuwuur
            rrwubbuububwrrbgrbruguwgggwubgugwwggwbubbrguruurwgbuggrwg
            wubuwgrrwgwgbwgwugurbruwrwgwgbggurwguwuur
            rwururrbrrrwgwwubbruwbwwrgururbgguuguuuwwrbwrgg
            ugwuuruwbwgurbggurrrwrgurgurbbwbbuurbwgwwbbugubgub
            rgwwubgrbwbgwwbwgburuuwurugguwuugbwguuuwug
            grwububburuwgugbgubbwwgugwrwwuwwwgwurgrurgwwrbwg
            rgurbwrbrbubbwbwuurwrwubwwgurrwbwrbrwgwrwrrbbbb
            wwgwuguuwubbwrrugubugrbwbubwuwbbbbrgwrrbrwuwbwbb
            buubgbbbwbgrwruugurggugwubrrrrbbubbwwuguwruwwub
            wrwbwuubbubwbuwrbwgwububgbbbruwuuuwuubbwrbrrwgrbuubuwbwu
            wugwwbrrrgbrbgbwgbuuwwuggurbwrbgwwbbgwwgbbu
            rgrrwugbgbbgbrwgbrrbuwwrbbuwruurbrbwbbwbururrgbwuruu
            bubugurbbgugwuubuurrwrwubrbuuwrrwrbrwwuuurrwbww
            ugrwubwwrgrrbrgbgrbwbbrgururrrruugububwwwwwbgwwugb
            ruurrrwbbgbbburgbrrrrwuuwwuwbggbrgwbuwwbrbbgbbugwwuur
            wgubgrgguwugwwggwbwuwurugugwgwubrrrwggwuubuubgg
            uwrrruuwrwggrgrrgrruwruuwggrugwrguguuguuwuugb
            wgrbrruugwrbgbwbrbgurgbbugggbbgwuubbgwbuguwwuuwbgub
            ugggruggrwrrubggbrbubwurwwubbuwwbwugrrbwruwwubbuuwgbuu
            uruwrwburwruwwbburrgrggbugbggbbuubwbbggbbbggbgggwr
            uuuuwgrwurugbgubrwgbgbbugwwuwrburrubgurrwrrbbrguugugbggguur
            grurrwwwugbuubgugwrwguubgbwwgggbgurwguur
            rguwwrgrwgwrbrbwggbwuuuuuuurwgguurbuwrwbrwrwbrgbguuwuu
            rubwbburgbrggbgwwrwbbguuggbgubrwbbrgwgwwuuugruwrrgr
            urrrbubrggubwwwubrwgwuuwurubugurwgbguugguwbwrrwwwurgugr
            uuguwuwrbrrggwubwuguuwgbbwbrbggrwrurwubuwbgwubrb
            bubbgwugwgwbbwwuwbwgrguwrguwrbwguwruuwwrbwwgg
            grgubwbrrgrrbbwgwuubuwugrggwrgwururwuuguur
            rrwubbwwwrubwrgugrgurgbbbwuwuggrbuwuwgbrwrbrbrwurubb
            ubrwwgbuwwbgbugugbbbbrbbwbrrwwurrgbrurbuuwgrubbgurwru
            urwbwwburrruurwrwuggubruubbwrwrgrrguguuuuwrbwg
            guuwugwbuuuggrrrbgwuwrggurggbubwwbgugbrwgrbguuuwuur
            bwbrrgrwbwrrguubrbuwurubbgwgrggbwggbbuwgwrguugu
            gurruugurgwgbggwgbgrbgwwgwubwgbbuburgurwrggrwrrwbbuw
            bbgrrbwwbrgbgugrburrgubgubrbwuwuwwwwwrbubuubgbbbgwbrgg
            uwbwrwwuwguuwwggbubbgrrugugubgggbrbggrbwgwuggubu
            gbbwrwrrbwugwgrubbbbrrbururwugguuuwbugubbbwwbgbbwubwwbbuw
            ggwbbgwgwwggruurruwugggbrguwubrurwubuwbrbuwrgrrr
            rubrrurwbrwurguwbrrruubuuruwbrbbbwuuubrbbwbgbgwrwggurbgr
            rgwgrwbwrburwrbgururgbwggbgurbbgrurbuwubbrrrgrrbruuu
            buwbruwbuwrggwbwuwuwbbuwbbbubbwbgrwbruwwuururgbur
            bwgbrbugbgguurwuggggugbggguwwrbwbbgrrguuwuuwrbrbb
            wrwuuwgwrgwugbbrbgbwgubbwwrugrguwuurwbbuugburrbur
            guuugugubwgwgwuugwgugwrgbruuubbubbbgugwwrur
            rwguuubbuwbrubwrwwuurrbgrgubuuurrwubrgwrwrg
            rgbuuuggwrbgbwrububwurubbuugubwggbrrwrgbbuubwwuurbubwr
            wwggwwwuwurwbrbrbbbwbwggrbgubbuwggbbugrwrrrgbg
            gurrugwurbuwurbrwbbbbwugrbwrguwugwugrbrgbgbgwgruguur
            wbwwwwrbgwuwubwrggbuggwbrwrugbgrgrguwuggwggwbgwuwgbruwugruur
            wubrbwurwwbrwggggugbbgrrrgrwguggurbbrugrwbwuwrruur
            ggruwgugruuggbwuugrwwwgrwuugbugwwurwrwrwgggwbuguuwrww
            wrrrrugbbbwggrgbwrrwrrwbgwrugugugurrrbggwugbbgbw
            rggwgbuggruurwwwruburwuwrbrubgguwubgbubruuubuurwgwugubwwu
            ugbguwrggbruuugwrrwgbgugwbwuwbbrggwurbruuu
            urguwuruubgbwgbguwgwubugugurwwrbggugrbgrubwrbgbbbb
            uruubbburbrwwwwrruwrwgrrwrwgwurrbrbgrrbbbruur
            uggrugguwbgbrwbrbwbubburwwwrbbwuwwubwbbbwwbrw
            rubrgugubbwuubbbwgrrbwwgwggguwwrbburggwrurwrgrru
            ubuwrbgubbbwrurbuugwuwwuwugbggugbbbwururrurbbbbrubbbrwu
            ubgrrwggruurggggwubgrgggwgugurrwgbgurgrrubuwgwugruubu
            gbrbbrrbuggwwgwwbrbrbruwugrwbbrgrbwgwbgwrw
            wrrwggburbuwururbbwgbwuwgbuggubwwgubbrubbbgbbguur
            guurwrggbwruubrgbgbuuurwugruwbugwggbgwbbgg
            wugugwrbgbrbwwuwugbwuubuggrrrwgubgwrbbubgwg
            uuwwugbrrwrubbrgrurwrwwrrwwbuwruwrgugrururrwrgwwbwug
            wgrbuwgbrrrgbruwgruuurubbrwguugurugurgwrggbgbur
            rgggbggrrbwwbuwggbuwrubbgwwwbbwburrwuggbgwwwwrwubbgr
            ruuwbrbggbwbuuwuwwrbwbwbrbbbgwubwwgbbugbugugub
            rrurbugbrwuwguwgwbuwbubrruuubbbwwbwbruwbrww
            brwgrruuuwwgggbwwbuguruwrgbugubrrwrbbrrggbwwrruwuur
            uwrwbuwurwbwbrbrubuuwbuggwwgurrwbrrurggwrgbwgruwbg
            ubwwbruwbrubrbggbuugwrugbrbwrggwbwbgbruuggrbbgrwruurwr
            uwbwwrgbuwrbwbbbrbwrguwwgbwwwgbguuwugbuwguwugrrrwrbwurw
            rwrgugbrbuurbrbwurwwrrrrwggrwugburbbwgrurruwwbgrguwrru
            rbbburrwbbrrugwgbuwugwwgwgwrwurugbwgbgbbruwbbbwbbbrgwbr
            rrbgwruwgwbrrrwuururrrwbbgugrwbbwbggwgrburgg
            wrgubrruwwwgbgwwwgwrrgbbrwbbruurubrbugrggwgurwwrru
            ugbrwwwwwgugbugrrbrrruwugbgbgbbrbgrbbwggrgurrbwbbbww
            wuurggrgrbbubuwgwwguwwrubrrrgbrwgrwgrwrrwugbgguugbu
            uuwuwbrwwgbrrrrrwgwbubrrugbguburrggbgburbbwbbgbbbguw
            bgwrgbbwurwubwwgbwuwwrrwwwrwgruuuwbrwwrgbrbgwuuuwrrwgr
            brgrugugwwgrrguggbgbwrbbrgbwwbbgwuwgwgrrubrgwugbbgwgrruur
            gwwgwruwwrubbuwguwrrgwbubugguuwggrbwgbgbrurugbwuruur
            ggwwuwwgrwbrbrbuggrgurrbububwrwrurrruubguur
            bwggrbrbbuwugwbguggrbwbrbwrgubrubwbuwggugbr
            wgwuwruuuuugbwgwbbruugurgbbwbguubwwuwugwubw
            rwwrbwwrruwburwrruwbwugbbrrrburrruurrwgubuggwg
            buruuwwwguubbuwbuuruuwwwrwugwubwbgrwrbwbbrgbrr
            rbrurbgwwrgggrbbrwgwrugwwwrwgwrurbburguwbgrggbbguur
            rugburuubugggrwuugrrbgbguwrbugruwbbwwrurwgrgwgrwuur
            gwruggggrgbgbwbrgbgbgbwbugwbuwbrurbbbwwuur
            ruwbrrbrbrwuguwguggggbgwwbwgrbbubrbwgugrbgruwuwwuruuwu
            wuguwwwguugbwwggggwbubububgrwrurwbgrwuwgurrbbrrbuggwbbbgruur
            guurgbrgurbrbugbwgbgbubgbbgwwwgbrgwrrbbgrgrubbb
            grurwbrwuggrbruwwbbruwurbbrwwbrwbuurguggwwbruggrbwbrwrb
            wwwububuwuggwwrrgrbwubbgwgubugrwugwburbgugwugbgugwggugrgb
            bwggbrrwrbwrwuugubuugwgbwbbbugrururwbwwbrwgwwurbuuwguwwuur
            bgruwrruwrurbrbgbgurggbubwbbwgbwrrwurrbrubbbbuwwgrwwbbu
            ubwrbrrwguruggwguwbgwbgbubgguggrgwwwwbwrrburbbwgwr
            rrrwgrgwbgugwrurwbrrubbuwrggbruguuurggbgwwbwgbwuwgwrrwuw
            grrgwgwrurrgbgggwurugugwggrgwwbrubrgbrguur
            ggugrwbbgbuwwguuwgbrbgrgbwbbwruurggrgwwgwuubrguubgubguur
            urgguwggbbrggbuwuggwuuurrbbgurwuubwwubbbbguwbwgwrwuuugurgg
            gwubwwgrrbwwwugbrbwgwugrgrbuurwwbbuurggurggrb
            rbugbuwuggubgbrbgrbgrrurbugbrrgubggwuwbwbbwrgubb
            rbwruwbgbggurrbubwwbrrwbubuwguuwbuguwbwwubuuwrub
            rrwguubwbgwbbrgubbgbrwgbwwrurgwbwwbwubggrrwwgguwrwwubuwb
            rbrurgbgbrrgwggrbwbubgurbbbruurrgwbugurbwrrbbbbwwgbw
            wugbbbuwrrbwbbggbgwgbwrwgbbubwwwbrruwbbbrbrwb
            uwwwbwbbgwubwuwgbbgrrubwwbrguwwrbruuwuuruww
            gwubbuuubbubbbubuwwbwuggugbwwgwwrbruwggwuubgwuugguurb
            urbwwgbuwubgbwuurgrbwbgwgbbbggbgggwgwbrwrrbrbbgwu
            uwuruwwbggrgubwgruwrubrugwurubgrwwguurwrrrrrurbruwgbg
            uugguugwbbgurubwbubwrwuwwururwubwwbgbugrbrrrbbuwbgbwgug
            wrwrwwgguwggwbguubgbwrgbguggwuwubgrubwubbrgrwbbubrbuwbw
            bbgrwrbbrggwwbbubugwwruuuurwgbrrugwurggbgrbwbrruwuur
            rwwuwurbbbwgrurwwububwrruwbrubwrbwrwbuwgrbggbuubur
            urggbrrrubrwbubwgbgbbgrwggrgrrgwuuwwwwgrrru
            urguuwrruuwrugbbrbuwgbgwgurbwuwgubbgggrwgwrur
            wgbbbgubbgbugrbbgrrbwgwuwbwrbwbuwrbuwbwuwrwrugb
            rugubbugugugwurbwbwwbubwgrwrbwbbrugrwbwwwrbgwwgguwuwgrrgw
            brguwwgbugwwrgbwwggwrwubbbburwuruwrwwrrgrwbrwrurrug
            wwbbbugwgwwgurgbguwbwgrbrgrbwwuwwububbgubruur
            bgbwgrgbwgugrbgbbbgrwgbbbuguuguwwuugrrwwwgggrgubrwgbrwbu
            urbrrggrurbgburrgrgggwuggubwwwwbbgwrgbbbuuuruur
            uwwruubbrbbwugrrgrbugbrggbwurugguwggbuwrwrrrgrwgbugbbwuruur
            grgbwrgwwuubrwrrbugbrbwrwburubrwggwuwrgugw
            rrbwwwgbbruuurwugruwbgrubwbugrbbwgugwgbgbwwub
            urgbuugrururrggwuuggugrbbururwuwguuuurrbuurbbgwuwuur
            gbrwuuwbguuburrwbrrruurgbrwuurgrguugggwubbrrrbuub
            bgubuwwwuururbrbrbruggrugguwwwwbubwrbrbwrbwruuwwbwgwwg
            grruwuruwrbrgbwbuuwrurrbubrgrrgrggruwguwubrrgrgbr
            bbbgrgbuuugrgrwuubwwwrwubgwgururwbggwubgbrww
            rwgwurwrubgrwrgwwuuubbwrgrrrwurwrgbrwwgbbwrgur
            rwurgwgurbggbgbggurwrgwuwurrbuubwubggrrrbrrwgw
            wuwgururrurgwrwgubrugggbrwguwbwwrwgrwugbbrbbguuwbbrubu
            ubgrugguwuugwbrubuuwbbgwgrgrggguwbrgwwwrwuggurwwrgg
            wbuwurgurgwbwrrgbbbrggbgbwbguuuwrbgrbgururrgb
            brwwuugbuuwrurgwbruwgbbwugwubwrbwbubruur
            ggwuurubwgurbbwbbuwuugwbwguwbbguwgbgggggrwrrurgbbgu
            rrgrburubuwgwbuwruuwrrwgbwubbgbbggrwgrwuwgrbuwbwbrwuur
            ruugggubuurrwbuuuubrbugubburubrurwrgurbwrwubbgrgugwgbru
            wwrbwrgguubguububbgbbbbrrbbwrbwwbgbrggbrbgbwgbwrurguuwbr
            bubrgrwrwurubbbgwrbrurbgbgbuwubgrubwwbggrwbw
            ruubbbwbbuguwrrwwbwgbrwbggwbuwbbugburuwwbgbugbbwgbr
            rwrrwwrbrgruuwwwwrggurwwgbrbrbrurgruugwwuwru
            wrgurrggrrgbbgubgruugurggbrwbgrguruwbrgruwrguwr
            wgwwggbwbbrurwwgwubugbuggrbwwgugbbrbrwgugbgugrg
            gwrgrwurgbguugwrrwurbbrgubbwbgrubbwggwgwbuwbuwuguwwbbw
            bgrbbggbgwbwwruwubuwrgguwubgbubbuwrwwuuwrrgwuwrbggbw
            wbwwbwrrbbbubwwuuuurrgubuuwwgbgrugrbbubwggrbrwrrwwwuwuur
            wgurwwwwurrrwuwggrwbrgwbgguwrubrrggwrwgbbrbbbgwwbrgrbrgrr
            wuwwwuuuubbwrbgwgrggrggbrgggbbuwubgugbbuburrgrggwrguwgb
            rgguggrbggbwrwbrubrwgubrwruwrwrbwggububruugubrrruuwbgu
            gwuwwururruwgwbbgubgwuugbuwuuwwgwgugrbwguggbugwwbr
            rbbgwgwggbrguuuugburbuwgwbrrwugrbbggbrgrwwb
            gwrubwwwbruruuwgwgbwbbgrbrbrwwgruugbgbugbggwbbwgruur
            bggrgwurbwwrggwgrgrgwuuuwwruggrgrrrbruuguwrgrwuuwgguur
            uruuubgbbrugubwrgbrbuwgrwwbwruggguugurwubrrwu
            grwwuwuwgguuwuggwrrwwwrrwbgbrrwrwubbuurrrbrbb
            uurggwwgrgrbrwubgrwgubgbwbwurbbuuwwuwgbgrgu
            ggrbgbrgrubwuwrguwbguuwrrgwgbwbbgwwgrgururwggbwbugwbuwuwu
            bubgwruuwbbbruwbrrbbubugwbwwubgrrwrguuwuubrbrbrg
            wurbwguuruwwbwrrguwrguwgguwrguwbubugwggguwwguugubbruuugb
            bwwbuubbrbwgbuwrbruurrugbuuggbwrrbuwgwuwgwgurbbbwuur
            bgurgwwgbbwbuurrwbwrggrgwrbrubbuggwruwbubugbggrrwgburgbwgr
            wwwwwwrgrwggwgwwgwuwgurwbwbwgrrruwrguugwburgbruruuwwuur
            uguwgggbwbgwbwuwrgguubbwgububbbguwgbgrwrgurbrruur
            bbuubrwwugburbruurrwbrbwgwbubwwggbuwwwgbgbrguuwbrrwrg
            wrrwbbwrwwbwrwwbwbbuuubwwbwbwbuurrwbbbwwrgwwbbrgwuuwgwgw
            rggrwwgrburgbbruuuggbgrbrbgwbrwuwgbbgwbbrwgg
            urwrrubrgrurbrrwwuwguuwbwrrrrrruwuwubguur
            rbwrwrwrgwrwuwwrgrbrwwrbuggbugwguwggrwggrrwruur
            bbbrbbrrwubwugwwwruruwruuuwruggrrgggbruuwgbgruwuwuwgu
            rburbgubuguwbwwrbwruwburbwgurgwgwbwbbgbugbugwubuugrggwbgrw
            brubwbugugwbwbuwrbbrgwwubuwbuurwrgburrgbwbwuwbwbbrgwrgrwg
            rwuurggubrrububugggrbuwbbbwbwuuwruggubbwuur
            rrbrwugrgbuggggrggwbwuuwrwruubguurwburgbbgrguwbrugbwruuwuur
            rubwgbgruruuwwubgbugrggwrwbbwggwgbggbbbbwgrgbbuwwruwwu
            urwbwrrgggwwbwwubbubuwubuugwuwrwggwuwubwbwguw
            wgbrubbrubrwgwguuwrwgwwuwguurrwbggrwrwwgbuwwurwrwubrg
            gwubbuwguububgruwwwwbuurwgwgugububguwgbbbrrwurwguurgburb
            bgurburuuggbruwrgrbbwwwwgwugguwburbbrrugbbbwrrbuwugugrbg
            uubbrrbuguuwugbwwuwgrbbbggbrwgwrgrwugggbrubuggrwrbgb
            gwbggurrgbwgrugwuburwbrwuruwguguruggggbugwww
            bgbwrrurubwgrubrrgggggwugubuubrgwrrwwwrwwuwww
            """;

    public static void main(String[] args) {
        System.out.println("Part 1 --- example: " + part1(exampleInput));
        System.out.println("Part 1 --- real: " + part1(realInput));
        System.out.println("Part 2 --- example: " + part2(exampleInput));
        System.out.println("Part 2 --- real: " + part2(realInput));
    }

    private static Object part1(String input) {
        String[] inputParts = input.split("\n\n");
        Set<String> patterns = Arrays.stream(inputParts[0].split(", ")).collect(Collectors.toSet());
        List<String> designs = Arrays.stream(inputParts[1].split("\n")).toList();

        return designs.stream()
                .filter(design -> countDesignsPossible(design, 0, patterns, new HashMap<>()) > 0)
                .count();
    }

    private static Object part2(String input) {
        String[] inputParts = input.split("\n\n");
        Set<String> patterns = Arrays.stream(inputParts[0].split(", ")).collect(Collectors.toSet());
        List<String> designs = Arrays.stream(inputParts[1].split("\n")).toList();

        return designs.stream()
                .mapToLong(design -> countDesignsPossible(design, 0, patterns, new HashMap<>()))
                .sum();
    }

    private static long countDesignsPossible(final String design, final int start, final Set<String> patterns, Map<Integer, Long> mem) {
        if (start == design.length()) return 1;

        if (mem.containsKey(start)) {
            return mem.get(start);
        }

        int end = design.length();

        long possibleDesigns = 0;
        while (end > start) {
            String curr = design.substring(start, end);
            if (patterns.contains(curr)) {
                possibleDesigns += countDesignsPossible(design, end, patterns, mem);
            }
            end--;
        }

        mem.put(start, possibleDesigns);
        return possibleDesigns;
    }
}
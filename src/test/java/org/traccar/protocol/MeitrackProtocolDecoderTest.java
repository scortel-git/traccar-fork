package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;
import org.traccar.model.Position;

public class MeitrackProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        var decoder = inject(new MeitrackProtocolDecoder(null));

        verifyAttribute(decoder, binary(
                "24245b3131342c3836343630363034343939333938372c4343452c0000000001005000130006012305000600070f1b004702060800000900000a00000b0000199d011a00000602d179570103b25ccc0604cf04862b0cc65b01000da4090d001c01000000010e0ccc010000b627be11000000002a41300d0a"),
                Position.KEY_LOCK, true);

        verifyAttribute(decoder, buffer(
                "$$u28,864606044993987,D82,0*D6"),
                Position.KEY_RESULT, "D82,0");

        verifyPositions(decoder, binary(
                "24245B3139312C3836343630363034343939333938372C4343452C010000000200500013000601250500060007111B00470206080000093E000AE7030B0000199E011A850306028D7A570103F35ACC0604F9D06C2B0CB92E00000D3FA40C00250CA2B900010E0CCC010000B6276313000000004B00120006012A0500060007111B00470206080000093E000AE7030B0000199E011A7C0305028D7A570103F35ACC0604F9D06C2B0CB92E00000D3FA40C00010E0CCC010000B6276313000000002A31340D0A"));

        verifyPositions(decoder, binary(
                "24246a3138312c3836343238313034313930383330332c4343452c00000000010093001f000505000600070714001502090800000900000a00000b0000160a001706001904001ad90440230006023279570103305ccc0604f536492b0c510300000d495701001c014000000b0e0ccc010000922781abb90c00002a030034212b03008b082c030053082d03009e082e030034212f030034213003003421310300342149090400000000000000004b07010104574946492a36310d0a"));

        verifyPositions(decoder, binary(
                "2424423233322c3836323039303035303030323831332c4343452c0400000003004400110004050006000700fe6962060800000900000a00000b00001aef044023000602d65fbcfd03173b9c0804cc76ae2a0c14ae1b000d00aa0d001c01000000014b030101003f00100004050006000700fe695f060800000900000a00000b00001aea044016000502d65fbcfd03173b9c0804cf76ae2a0c14ae1b000d03aa0d00014b030101003f00100004050006000700fe695f060800000900000a00000b00001aed044001000502d65fbcfd03173b9c0804d076ae2a0c14ae1b000d04aa0d00014b030101002a30460d0a"));

        verifyAttribute(decoder, buffer(
                "$$F160,861412043027965,AAA,22,45.499458,-82.493581,220718171428,V,0,0,0,0,0.0,0,227940,119812,302|220|D8D6|086E1B2B,0000,0000|0000|0000|0191|0573,,,3,,002134,0,0*FA"),
                Position.KEY_POWER, 13.95);

        verifyPositions(decoder, binary(
                "2424423233392c3836323039303035303030373436352c4343452c0100000003004300130006050006000700140015801b00080800000900000a00000b0000165105198d011a630540160005024c5e910103590bfe0204922153290c6b2501000dd5b50200004300130006050006000700140015011b00080800000900000a00000b0000165005198d011a630540010005024c5e910103590bfe0204932153290c6b2501000dd6b50200004300130006050006000700140015011b00080800000900000a00000b0000165205198d011a630540230005024c5e910103590bfe0204942153290c6b2501000dd7b50200002a43330d0a"));

        verifyPosition(decoder, buffer(
                "$$D149,867047043162018,AAA,35,-1.264865,36.800705,211001105240,A,9,20,41.0,323,1,1697,1,0,000|00||,0000,4.33|12.96|1.92|2.72|2.69,0.000000|0|0.000000,*E1"));

        verifyPositions(decoder, binary(
                "2424413132332c3836313538353034333230303836322c4343452c010000000100590015000305010609071b0b081c000939010a07000b1700199e011a9505921a0099c4089c5500c93e00405a000602a8b114000343f12e0604d18806270c654a2e000da20537009bb8963904010e0c0d020300aa7a0af69e0100002a35340d0a"));

        verifyAttribute(decoder, buffer(
                "$$F153,867144025101013,AAA,35,25.219431,55.279918,200916155923,V,0,25,0,0,0.0,0,249701532,98374503,424|2|101C|A3AE,0800,0000|0000|0000|02D3|0103,00000011,*A0"),
                Position.KEY_INPUT, 8);

        verifyPosition(decoder, buffer(
                "$$O160,863835028611502,AAA,35,7.887840,98.375193,200202020238,A,12,4,0,279,0.6,45,32121,442492,520|3|12DF|015273E2,0000,0000|0000|0000|018D|04F0,00000001,,1,0000*F3"));

        verifyNull(decoder, binary(
                "242441313038362c3836343530373033313231393937342c4430302c3138303232343037323631345f4331453130395f4e31553144312e6a70672c31342c302cffd8ffdb008400140e0f120f0d14121012171514181e32211e1c1c1e3d2c2e243249404c4b47404645505a736250556d5645466488656d777b8182814e608d978c7d96737e817c011517171e1a1e3b21213b7c5346537c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7c7cffc000110801e0028003012100021101031101ffdd0004000affc401a20000010501010101010100000000000000000102030405060708090a0b100002010303020403050504040000017d01020300041105122131410613516107227114328191a1082342b1c11552d1f02433627282090a161718191a25262728292a3435363738393a434445464748494a535455565758595a636465666768696a737475767778797a838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae1e2e3e4e5e6e7e8e9eaf1f2f3f4f5f6f7f8f9fa0100030101010101010101010000000000000102030405060708090a0b1100020102040403040705040400010277000102031104052131061241510761711322328108144291a1b1c109233352f0156272d10a162434e125f11718191a262728292a35363738393a434445464748494a535455565758595a636465666768696a737475767778797a82838485868788898a92939495969798999aa2a3a4a5a6a7a8a9aab2b3b4b5b6b7b8b9bac2c3c4c5c6c7c8c9cad2d3d4d5d6d7d8d9dae2e3e4e5e6e7e8e9eaf2f3f4f5f6f7f8f9faffda000c03010002110311003f00cca69ac8d06e3348569884db4845021b498a60371494008692980119a8ca7a5342101a5cd5221a0ab312ed1ee68b943e80dce2a467ffd0c806a48e592270f13b230e841a0096eeea7bb09e6c85b667033552800069c2980e14f15422418a916ad099228a95455089505584140993a2d5fb598a7cae72bd8fa536ae892e8e69e2b9d971168a459fffd1ece8a0028a006b534f4a68ce5b9130a89ab444919a61a6c634d34d21894952310d25002514084a4a00ffd2d2349564086929082929805250025140094940c4a4a04251400949408292819fffd3cca31591a098a5c62801a45464531098a69a6210d371400629a6980628eb400c64cd3791c1aa16c491479393563343105424fcc4d007ffd4c3463c0a94500381a5e3b8cd000c99e57f2a8f3835402834e0d4d08914d4aa6ac4c954d4aad4c4c955aa647a6496236ab51b552132e412e383d3f955b0722b09ab32a0c5a2a0d0ffd5ece8a0028a0061a6d5193dc8daa36ab422334c34c634"));

        verifyPositions(decoder, binary(
                "24246b3131342c3836353738393032343134303439352c4343452c0000000001005000130006011f05010607071415001b00060800000949010a0c000b9b0119a1011afe010602e934ce0203fc9aeb0004309f13220cafc503000d97741e001c01000000010e0ce8000300092f2e060000b7ff2a33330d0a"));

        verifyPosition(decoder, buffer(
                "$$^182,864507031245110,AAA,109,13.844553,100.644360,171227173141,A,11,19,0,359,0.8,8,15075,934591,520|4|0643|07D20555,8400,0000|0000|0000|018D|04CB,,,108,0000,,6,0,,,,,10|171227173100*7C"));

        verifyPosition(decoder, buffer(
                "$$S214,864507031219974,AAA,109,13.844643,100.644395,171207021520,A,10,28,0,31,0.8,6,390,421327,520|0|0016|000F2DB0,8400,0000|0000|0000|018D|04C6,,,108,0000,,6,0,,,,,11|171207091500|171207091500|78|3500|000000|000003*12"));

        verifyPositions(decoder, binary(
                "24245f3237382c3836353738393032313434373233322c4343452c5b00000003005000130006012305010608070d15001b0006080000091e010a09000b2e0019a1011af90106025c033300039be60c06044f6678210c6f1806000d48db06001c41000000010e0cf60113002005912b830001ff5000130006012305010608070d15001b0006080000091e010a09000b2e0019a0011af90106025c033300039be60c0604506678210c6f1806000d49db06001c41000000010e0cf60113002005912b830001ff5000130006012305010608070d15001b0006080000091e010a09000b2e0019a1011af90106025c033300039be60c0604516678210c6f1806000d4adb06001c41000000010e0cf60113002005912b830001ff2a37460d0a"));

        verifyPosition(decoder, buffer(
                "$$V177,863835026871173,AAA,35,34.516428,10.470160,170915154043,A,9,12,68,74,0.9,9,1988259,525882,605|2|008C|0007B5A6,0200,0003|0000|0000|01A6|0571,00000001,,3,0000,06FB2E,360,511*74"));

        verifyPosition(decoder, buffer(
                "$$V177,863835026871173,AAA,35,34.516428,10.470160,170915154043,A,9,12,68,74,0.9,9,1988259,525882,605|2|008C|0007B5A6,0200,0003|0000|0000|01A6|0571,00000001,,3,0000,010A92,360,511*74"));

        verifyPosition(decoder, buffer(
                "$$B136,011691002364761,AAA,29,47.055220,28.893193,170914144240,V,0,7,0,0,0,132,129754946,129793197,259|2|02F8|413F,0000,000D|000C||028C|,*9E"));

        verifyNotNull(decoder, buffer(
                "$$F153,863835026880190,AAA,29,25.313160,55.422473,170628150902,V,0,0,0,0,0.0,0,6553,6697,0|0|0000|00000000,0000,0002|0000|0000|018B|0000,,,3,0000,,110,386*22"));

        verifyPosition(decoder, buffer(
                "$$T143,869013024733944,AAA,1,18.459575,-69.947161,170220142912,A,5,15,10,300,1.6,115,3989,187884,370|2|5337|2B2C,0100,0000|0000|0000|0964|0B04,,*C2"));

        verifyPosition(decoder, buffer(
                "$$K157,866771027160687,AAA,3,37.040231,10.042391,160412151656,A,10,11,0,48,0.8,21,1035518,774980,605|2|0010|307B,0400,0000|0000|0000|0A47|03E3,,,1,0000,001206*2C"));

        verifyNull(decoder, buffer(
                "$$D28,353358017784062,D03,OK*F3"));

        verifyPosition(decoder, buffer(
                "$$A158,79007001520234,AAA,35,40.996370,-8.575065,150730184834,A,8,24,0,1,1.3,173,32573389,31405012,268|3|2BC0|250B,2000,|||0A2D|0000,00000001,,50,,,,,,,,,,,,,*4A"),
                position("2015-07-30 18:48:34.000", true, 40.99637, -8.57507));

        verifyPosition(decoder, buffer(
                "$$G145,862106024274815,AAA,35,-1.287125,36.906061,150530054639,A,10,13,12,67,0.8,1621,38359791,42330881,639|2|FB2|2F3,0000,3|0|0|A58|432,,,1,0009,*26"));

        verifyPosition(decoder, buffer(
                "$$I152,013949004569813,AAA,37,54.739468,25.273648,150208173414,A,5,24,0,73,1.5,165,74,3381,246|1|0065|118A,0000,0003|0003|0000|08D4|0002,006380DF,,1,0008*7C"));

        verifyPosition(decoder, buffer(
                "$$E141,863071013799553,AAA,35,-1.264521,36.801128,150307132846,A,11,20,0.2,0,5,1767,84045888,36496633,639|02|100E|844,1234,0018|||025D|00CB,*17"));

        verifyPosition(decoder, buffer(
                "$$m140,013777008931857,AAA,1,54.739580,25.273263,141120144603,V,0,25,0,6,50.0,159,19825,13940,246|1|0065|118A,0100,0000|0000|0000|092A|0001,,*1C"));
        
        verifyPosition(decoder, buffer(
                "$$X138,862170010187175,AAA,35,-29.960365,-51.655455,130507201625,A,8,9,0,107,0.9,7,169322,126582,724|6|0547|132B,0000,0009|000A||0278|0000,*BE"));
        
        verifyPosition(decoder, buffer(
                "$$X138,862170010187175,AAA,35,-29.960365,-51.655455,130507201625,A,8,9,0,107,0.9,-7,169322,126582,724|6|0547|132B,0000,0009|000A||0278|0000,*BE"));

        verifyPosition(decoder, buffer(
                "$$]138,012896000475498,AAA,35,-6.138255,106.910545,121205074600,A,5,18,0,0,0,49,3800,24826,510|10|0081|4F4F,0000,0011|0012|0010|0963|0000,,*94"));

        verifyPosition(decoder, buffer(
                "$$d138,012896000475498,AAA,35,-6.138255,106.910545,121205074819,A,7,18,0,0,0,49,3800,24965,510|10|0081|4F4F,0000,000D|0010|0012|0963|0000,,*BF"));

        verifyPosition(decoder, buffer(
                "$$j138,012896000475498,AAA,35,-6.138306,106.910655,121205103708,A,3,11,0,0,1,36,4182,35025,510|10|0081|4F4F,0000,000A|000C|000A|0915|0000,,*BF"));

        verifyPosition(decoder, buffer(
                "$$m139,012896005334567,AAA,35,-33.866423,151.190060,121208020649,A,7,27,0,32,4,13,6150,49517,505|2|0B67|5A6C,0000,0000|0000|0000|0977|0000,,*F1"));

        verifyPosition(decoder, buffer(
                "$$A141,012896005334567,AAA,35,-33.866543,151.190148,121209081758,A,6,27,0,16,1,48,65551,152784,505|2|0B5F|D9D3,0000,0000|0000|0000|0A39|0000,,*5B"));
        
        verifyPosition(decoder, buffer(
                "$$_128,861074020109479,AAA,34,22.512618,114.057065,090215000318,V,0,31,0,0,0,0,0,733,302|720|3EE4|BBB5,0000,0006|0006||028C|0000,*E3"));
        
        verifyPosition(decoder, buffer(
                "$$K146,013227004985762,AAA,35,28.618005,-81.246783,131101213828,A,9,22,0,209,1.1,23,80974,1187923,310|260|2A13|634E,0000,0000|0000|0000|09DA|0B34,,*51"));
        
        verifyPosition(decoder, buffer(
                "$$E150,013777001165479,AAA,35,10.296601,123.872115,140501161505,A,4,22,1,170,1.4,77,39097,393563,515|3|A0CC|ED96,0000,0008|0003|0000|09D5|0000,,,1,0009*1E"));

        verifyPosition(decoder, buffer(
                "$$B140,013777001293701,AAA,35,-7.266760,112.743550,140521095314,A,3,22,0,275,2.7,45,1984,8059,510|1|3504|EBFE,0000,0000|0000|0000|0914|0002,,*F9\r\n"));

        verifyPosition(decoder, buffer(
                "$$J163,123123123123123,AFF,0004,35,58.588926,16.180473,140928192856,A,10,27,0,161,1.2,19,1648894,435695,240|24|88B9|E435,0000,|||0A22|0000,00000001,,50,,,,,,,,,,,,,*70\r\n"));

        verifyPositions(decoder, binary(
                "24245838362c3336393830303031343039303032312c4343432c020134000100000023381f91ffe354b806c5e3121b0009130000000000000000d33801007cbf0200fe0101000435feeb02000500a3010000000000002a62650d0a"),
                position("2014-05-24 04:59:49.000", false, -7.26650, 112.74365));

        verifyPositions(decoder, binary(
                "2424473937302c3336393830303031333436303637342c4343432c020134005b000000010ce304035db9e000ec6f591a000013000000000c001801edb70200c96d0100e60001004838576501000300a101c20400000000010ce304035db9e000ee6f591a000013000000000c001801edb70200ca6d0100e60001004838576501000300a101c20400000000010ce304035db9e000ef6f591a000013000000000c001801edb70200cc6d0100e60001004838576501000300a101c20400000000020ce304035db9e000f76f591a000016000000000c001801edb70200d36d0100e60001004838576502000300a101bf04000000000a0ce304035db9e000f76f591a000016000000000c001801edb70200d46d0100e60001004838576500000300a101bf0400000000020ce304035db9e000fb6f591a000016000000000c001801edb70200d86d0100e60001004838576502000300a101760400000000180ce304035db9e000fc6f591a0000120000000000008c00edb70200d96d0100e60001004838576502000300a10176040000000019b1e2040323b9e0000b70591a0105150600bb0012002901edb70200e76d0100e60001004838576502000300a2017005000000002023e304031fb9e0001070591a010615070027010d001601fcb70200ec6d0100e60001004838576502000300a201800500000000201fe3040302b9e0001170591a010615090019010d001501feb70200ed6d0100e60001004838576502000300a2018005000000002018e30403dcb8e0001270591a0106150b0011010d00150100b80200ee6d0100e60001004838576502000300a2018005000000002036e3040345b8e0001570591a0107150b002d010b0013010ab80200f16d0100e60001004838576502000300a2018005000000002053e3040326b8e0001670591a0107150d0041010b0013010eb80200f26d0100e60001004838576502000300a2018005000000002070e3040310b8e0001770591a0107150e004f010b00130111b80200f36d0100e60001004838576502000300a2018005000000002095e3040306b8e0001870591a0107150d005a010b00140115b80200f46d0100e60001004838576502000300a20180050000000020b3e3040305b8e0001970591a0107150b0060010b00140118b80200f56d0100e60001004838576502000300a20183050000000020cfe3040308b8e0001a70591a0107150b0066010b0014011bb80200f66d0100e60001004838576502000300a20183050000000020eee304030cb8e0001b70591a0106170b0004000d0014011eb80200f76d0100e60001004838576502000300a2018305000000002a62350d0a"));

        verifyNull(decoder, buffer(
                "$$z27,861451040910625,AAC,1*D3"));

    }

}

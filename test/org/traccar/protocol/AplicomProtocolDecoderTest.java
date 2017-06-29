package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class AplicomProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        AplicomFrameDecoder frameDecoder = new AplicomFrameDecoder();
        AplicomProtocolDecoder decoder = new AplicomProtocolDecoder(new AplicomProtocol());

        verifyAttributes(decoder, binary(
                "45c20144f667c07287008c01ffff6d01000059368963d0340a0616207d7f4b10c0c019e6000039d7000039d71f40ffff5001574442393036363035533132333435363700014142432d33343520202020202000011231303331373139343039303030303031000000000000000000000000000000000000000000000000000001011231303331373139343039303030303031000000000000005a"));

        verifyAttributes(decoder, binary(
                "46c30144f667c1711f00340007ff750058b8f77701037c06b8000000330033000000000b760000425e0100640000b3a90185d5823155000131070204000219641004"));

        verifyAttributes(decoder, binary(
                "46c30144f667c1711f00340007ff75005891601401025707b50236003b003b003500000a9300006bd50100640000a5250167d2f9034c01010107020400021a901004"));

        verifyAttributes(decoder, binary(
                "48C1014143B4493145004900203F6D014B5557C20003000015060110FF00C800000000000000003D01141E283C500100260404010200000000000000000000000000C8000000000000010200110019001E0064019003E8"));

        verifyAttributes(decoder, binary(
                "48c10144b9de54e6b2008700205f710a57d23ec957d23b8d00000000300d0106ff00000000000000000000000000000000000000000000000000000000000000010a141e28323c46505a646e7801000f020104ff000000000000000000010102000f020104ff000000000000000000010103000f020104ff000000000000000000010105000f020104ff0000000000000000000101"));

        verifyAttributes(decoder, binary(
                "45c20145931876ffb2007100ffff6d00000057c6dd1970230d087b1f7d7f0000d0c1000000003580000035801f40ffff5001574442393036363035533132333435363700014142432d333435202020202020000000000000000000000000000000000000000000000001123130343632343639373030303030303100000000"));

        verifyAttributes(decoder, binary(
                "45c20145931876ffb2007100ffff6d00000057c6dd9170250d087b1f7d7f0000d0c1000000003580000035801f40ffff5001574442393036363035533132333435363700014142432d333435202020202020000000000000000000000000000000000000000000000001123130343632343639373030303030303100000000"));

        verifyAttributes(decoder, binary(
                "45c20145931876ffb2007100ffff6d00000057c6de0970270d087b1f7d7f0000d0c1000000003580000035801f40ffff5001574442393036363035533132333435363700014142432d333435202020202020000000000000000000000000000000000000000000000001123130343632343639373030303030303100000000"));

        verifyAttributes(decoder, binary(
                "44c3014645e8ecff3c00ea03ffffbc00f457d68a6557d68a6303bb55fa018843da1100009881000000000000000000000000000000000000000000000000000000000000000000000000000000ff0056007600000000000000014542016d0001010095070e14014645e8ecff3c57d68a6403bb55fa018843dac0010d14ff050102030405060708090a0b0c0d0e0f10112a01010730343f3c1ff5cf01020700007d007d23010103022f2e01060c67452301efcdab8967452301010b10000000007d007d007d7dffffffffffff010a2400000000000000010000000000000000ffffffffffffffff00010001ffff00000000ffff010c02fec6"));

        verifyPosition(decoder, binary(
                "44c3014645e8e91b66002300a21f0b01f056d3e62856d3e626031f845f00c6ee440800000000000000000017bd1cb30000"));

        verifyPosition(decoder, binary(
                "44c3014645e8e91b66002300a21f0b00f056d3e64756d3e646031f845f00c6ee440800000000000000000017bd1cb30001"));

        verifyPosition(decoder, binary(
                "44c3014645e8e91b66001f00221f0b01f456ba1e0d56ba1e0b031f842200c6ef550c000000000017bd1cb30004"));

        verifyAttributes(decoder, binary(
                "44c3014645e8e9bada003e03fff7070055a4f24200000081000000000000000000000000000000000000000000000000000000000000000000000000000000ff00000001000000000000000044c3014645e8e9bada003e03fff77bff55a4f24300000081000000000000000000000000000000000000000000000000000000000000000000000000000000ff00300002000000000000000044c3014645e8e9bada003e03fff7690655a4f24500000081000000000000000000000000000000000000000000000000000000000000000000000000000000ff003000030000000000000000"));

        verifyPosition(decoder, binary(
                "44c3014645e8e9d29a002d0022ff6d00f455893b4d55893b4c027a7e1500189d710800009e0000000000000000000000023300000000000000009d"));
        
        verifyPosition(decoder, binary(
                "44C20146B710C158DA002100B09F0700C054CA0EA254CA0E9C03BE0BF6015D7069070000142A600000000000000001"));

        verifyPosition(decoder, binary(
                "44C20143720729D6840043031fff7191C0450ef906450ef90603b20b8003b20b80066465b3870ce30f010ce30ce3003200001520000000030aa200003b13000000320300000bcb17acff0099000186a002"));

        verifyPosition(decoder, binary(
                "440129D684002b0700C0450ef906450ef90603b20b8003b20b80066465b3870ce30f010ce30ce300003b130300000bcb170a"));

        verifyPosition(decoder, binary(
                "44c3014645e8e9152e008900b09f7700f4558c07e8558c07e703be0bd8015d6faf0e0000003240000000000000000f4349460107010007558c07e70000000000000002d209df028f05fffe00000000000000002eff13fe11fe1a00011000000000000010ff11ff3cff11008c00080060f41b0043502015000000000000020d0000030d0000040c0000040d0000050c0000050d0000058c"));

        verifyPosition(decoder, binary(
                "44c20144563508385a009500b09f7700c0555ea99e555ea9b103bb569f01883ff50b00002a30f000000000000013074349460108010007555ea99e000000000000003f0000ae017605b3ff00000000010000006700d900d500000003000000000000006700d900d500000087002500c4ff0000435020150000000040512001000000000000020d0000030d0000040c0000040d0000050c0000050d0000058c0000060c"));

        verifyPosition(decoder, binary(
                "44C20146B710C158DA009500B09F7700C054CA0EA454CA0EA403BE0BF6015D706B070000142A600000000000000002434946010801000754CA0EA4000000000000008400000000000000000000000000000000300000FE00FE0000000000000000000000000000000000000000000000000000000000000000000040502035000000000000020D0000030D0000040C0000040D0000050C0000050D0000058C0000060C"));

        verifyPosition(decoder, frameDecoder.decode(null, null,
                binary("33353733303030373030393233333644C20146B710C158DA009500B09F7700C054CA0EA454CA0EA403BE0BF6015D706B070000142A600000000000000002434946010801000754CA0EA4000000000000008400000000000000000000000000000000300000FE00FE0000000000000000000000000000000000000000000000000000000000000000000040502035000000000000020D0000030D0000040C0000040D0000050C0000050D0000058C0000060C")));

    }

}

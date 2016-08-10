package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class CalAmpProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        CalAmpProtocolDecoder decoder = new CalAmpProtocolDecoder(new CalAmpProtocol());

        verifyPosition(decoder, binary(
                "8305454205067001010102008157a9bddc57a96aaa17cdb98fccc1a457000056ac00000000000007250000ff8f000e00082711570000000000ffff101b00003148000010680000000000000000000000050000000000000000000005b0000000000000289600000000000000000000069b00000000000008e400000000000000000000000000000003000000010000070f"));

        verifyPosition(decoder, binary(
                "83052132052924010101020001575c590300000000000000000000000000000000000000000000002c0000ff8f0000030801010000"));
        
        verifyPosition(decoder, binary(
                "830543321494860101010a0080560b5a5e0eadd0291becf3c500f005090f1f3305000003010040c0a600000000000000008b12a102"));

        verifyPosition(decoder, binary(
                "830543321494860101010a0c215608b6680ead5ada1bed88d300000049801f000500000300003cf33200000000000000008b0ce101"));
        
        verifyNothing(decoder, binary(
                "830545321041830101010300010000333862000023c301000000004532104183ffffff353816051610691f420040163953294fffffffffffffffff8996604211639032949f4f54413a317c303b302c317c343b302c34004f5441535441543a302c302c302c302c302c222200564255533a342c322e302e302c343533323130343138332c5630312e30332e30312e34302c5630312e30332e30312e33312c2c0056494e2d494e464f3a56494e3d31464d5a5537324539355a4137303032362c4445562d5245474e3d55532c535256522d5245474e3d555300"));
        
        verifyPosition(decoder, binary(
                "8308353301059723580f01020102088952d994c352d994c4134fa767c4c482e20000c12700000d29006e1002019affc90f061d00060c0000"));
        
        verifyPosition(decoder, binary(
                "8308355233050116134f01020102445652d993e152d993e1124c728cc68f0647000023c00000000000000e02019affc90f071c0015020000"));
        
        verifyPosition(decoder, binary(
                "830545420185450101010200075517fb335516c5c40fb1aea4cf4cbf250000000000000000008900260015ffb10f001108110a0000"));
        
        verifyPosition(decoder, binary(
                "830543321494750101010A00085492798A0EC4F9E71BDA3B81005600040F1F33050000030000000076000000000000000000000000"));

    }

}

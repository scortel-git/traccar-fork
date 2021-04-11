package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;

public class StartekProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        var decoder = new StartekProtocolDecoder(null);

        verifyPosition(decoder, text(
                "&&A147,021104023195429,000,0,,180106093046,A,22.646430,114.065730,8,0.9,54,86,76,326781,460|0|27B3|0EA7,27,0000000F,02,01,04E2|018C|01C8|0000,1,0104B0,01013D|02813546"));

        verifyPosition(decoder, text(
                "&&y139,860262050009146,000,0,,210323131512,A,22.678655,114.046223,14,1.1,0,231,71,5,460|0|249F|0099C257,28,0000003D,00,00,0493|0199|0000|0000,1,,33"));

    }

}

package org.traccar.protocol;

import java.nio.ByteOrder;
import org.jboss.netty.buffer.ChannelBuffers;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.traccar.helper.ChannelBufferTools;
import static org.traccar.helper.DecoderVerifier.verify;

public class CastelProtocolDecoderTest extends ProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        CastelProtocolDecoder decoder = new CastelProtocolDecoder(new CastelProtocol());

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "4040450004323132474c31313433303035303033000000000040082ca89b55a6a99b555c57000000000000c40200000b0000001400036401111f000302f5533bd653f10d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40404d0004323132474c3131343330303530303300000000004007120000002ca89b55cba99b555c57000000000000c40200000b0000000000036401111f000102000101170000000068850d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "4040420004323132474c31313433303035303033000000000010022ca89b55cca99b555c57000000000000cf0200000b0000000000036401111f0000020013be0d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "4040870004323132474c31313433303035303033000000000040052ca89b55e3a89b555c57000000000000c4020000040000001400036401111f0003000012042105210b210c210d210f211021112113211c2121212321242133213421422146214f212b50663603003ce9030dff060000600dffffc25865ffff9e02b43624000000003cbc0d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "4040d00004323132474c31313433303035303033000000000010013ec09b5596c29b555c57000000000000de0200000f0000000000036401111f000000004944445f3231334730325f532056322e322e36004944445f3231334730325f482056322e322e360032000110021003100410051006100710081009100a100b100c100d100e1011100111021103110411051106110711011202120312041201130213031301160216011701180218011b011c011d011e011f021f031f041f051f061f071f012102210126012701288a690d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40404d0004323132474c3131343330303530303300000000004007050000003ec09b5564c29b555c57000000000000de0200000f0000002000036401111f0000020001010e00000000237e0d0a"))));

        assertNull(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40401F00043130303131313235323939383700000000000000100303320D0A"))));

        verify(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40407F000431303031313132353239393837000000000000001001C1F06952FDF069529C91110000000000698300000C0000000000036401014C00030001190A0D04121A1480D60488C5721800000000AF4944445F3231364730325F532056312E322E31004944445F3231364730325F482056312E322E31000000DF640D0A"))));

        verify(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40405900043130303131313235323939383700000000000000400101C1F06952E7F069529C9111000000000069830000070000000400036401014C00030001190A0D0412041480D60488C57218000000009F01E803ED9A0D0A"))));
        
        verify(decoder.decode(null, null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, ChannelBufferTools.convertHexString(
                "40405900043335343034333035303834343134330000000000400100f61a7355c11b7355710000000b00000000000000000000000400000000240e0200020106060f100b2d5a78a7076ec0fb1d00008c065f010000ac220d0a"))));

    }

}

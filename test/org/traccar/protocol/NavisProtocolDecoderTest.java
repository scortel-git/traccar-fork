package org.traccar.protocol;

import org.traccar.helper.TestDataManager;
import java.nio.ByteOrder;
import org.jboss.netty.buffer.ChannelBuffers;
import static org.traccar.helper.DecoderVerifier.verify;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class NavisProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        NavisProtocolDecoder decoder = new NavisProtocolDecoder(new TestDataManager(), null, null);

        byte[] buf1 = {0x40,0x4E,0x54,0x43,0x01,0x00,0x00,0x00,0x7B,0x00,0x00,0x00,0x13,0x00,0x44,0x34,0x2A,0x3E,0x53,0x3A,0x38,0x36,0x31,0x37,0x38,0x35,0x30,0x30,0x35,0x32,0x30,0x35,0x30,0x37,0x39};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, buf1)));

        byte[] buf2 = {0x40,0x4E,0x54,0x43,0x01,0x00,0x00,0x00,0x7B,0x00,0x00,0x00,0x5A,0x00,0x50,0x69,0x2A,0x3E,0x41,0x01,0x25,(byte)0xDB,0x0E,0x00,0x00,0x00,0x15,0x11,0x07,0x07,0x11,0x0A,0x0C,0x08,(byte)0x80,0x63,0x00,0x00,(byte)0xAA,0x39,(byte)0xA2,0x38,0x16,0x00,0x02,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x0C,0x11,0x07,0x08,0x11,0x0A,0x0C,(byte)0xB3,(byte)0x89,(byte)0x79,0x3F,0x1A,(byte)0xEF,0x26,0x3F,0x00,0x00,0x00,0x00,0x12,0x00,0x34,(byte)0xF5,0x16,0x44,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,(byte)0xFA,(byte)0xFF,0x00,0x00,0x00,(byte)0xFA,(byte)0xFF,0x00,0x00,0x00,(byte)0xFA,(byte)0xFF,(byte)0x80,(byte)0x80,(byte)0x80,(byte)0x80};
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, buf2)));

        byte[] buf3 = {0x40,0x4E,0x54,0x43,0x01,0x00,0x00,0x00,0x7B,0x00,0x00,0x00,0x13,0x00,0x47,0x37,0x2A,0x3E,0x53,0x3A,0x38,0x36,0x31,0x37,0x38,0x35,0x30,0x30,0x35,0x31,0x32,0x36,0x30,0x36,0x39};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ByteOrder.LITTLE_ENDIAN, buf3)));

    }

}

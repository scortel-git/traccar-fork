package org.traccar.protocol;

import org.traccar.helper.TestDataManager;
import org.jboss.netty.buffer.ChannelBuffers;
import static org.traccar.helper.DecoderVerifier.verify;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class Gt06ProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        Gt06ProtocolDecoder decoder = new Gt06ProtocolDecoder(null);
        decoder.setDataManager(new TestDataManager());

        byte[] buf1 = {0x78,0x78,0x11,0x01,0x01,0x23,0x45,0x67,(byte)0x89,0x01,0x23,0x45,0x10,0x0B,0x32,0x01,0x00,0x01,0x71,(byte)0x93,0x0D,0x0A};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf1)));

        byte[] buf2 = {0x78,0x78,0x1F,0x12,0x0B,0x08,0x1D,0x11,0x2E,0x10,(byte)0xCC,0x02,0x7A,(byte)0xC7,(byte)0xEB,0x0C,0x46,0x58,0x49,0x00,0x14,(byte)0x8F,0x01,(byte)0xCC,0x00,0x28,0x7D,0x00,0x1F,(byte)0xB8,0x00,0x03,(byte)0x80,(byte)0x81,0x0D,0x0A};
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf2)));

        byte[] buf3 = {0x78,0x78,0x0D,0x01,0x08,0x64,0x71,0x70,0x03,0x28,0x35,(byte)0x81,0x00,0x09,0x3F,0x04,0x0D,0x0A};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf3)));

        byte[] buf4 = {0x78,0x78,0x0D,0x01,0x01,0x23,0x45,0x67,(byte)0x89,0x01,0x23,0x45,0x00,0x01,(byte)0x8C,(byte)0xDD,0x0D,0x0A};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf4)));

        byte[] buf5 = {0x78,0x78,0x0d,0x01,0x03,0x53,0x41,(byte)0x90,0x36,0x06,0x60,0x61,0x00,0x03,(byte)0xc3,(byte)0xdf,0x0d,0x0a};
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf5)));

        byte[] buf6 = {0x78,0x78,0x19,0x10,0x0B,0x03,0x1A,0x0B,0x1B,0x31,(byte)0xCC,0x02,0x7A,(byte)0xC7,(byte)0xFD,0x0C,0x46,0x57,(byte)0xBF,0x01,0x15,0x21,0x00,0x01,0x00,0x1C,(byte)0xC6,0x07,0x0D,0x0A};
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf6)));

        byte[] buf7 = {78,0x78,0x21,0x12,0x0C,0x01,0x0C,0x0F,0x15,0x1F,(byte)0xCF,0x02,0x7A,(byte)0xC8,(byte)0x84,0x0C,0x46,0x57,(byte)0xEC,0x00,0x14,0x00,0x01,(byte)0xCC,0x00,0x28,0x7D,0x00,0x1F,0x72,0x00,0x01,0x00,0x0F,0x53,(byte)0xA0,0x0D,0x0A};
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf7)));

        byte[] buf8 = {0x78,0x78,0x25,0x16,0x0B,0x05,0x1B,0x09,0x35,0x23,(byte)0xCF,0x02,0x7A,(byte)0xC8,0x36,0x0C,0x46,0x57,(byte)0xB3,0x00,0x14,0x00,0x09,0x01,(byte)0xCC,0x00,0x26,0x6A,0x00,0x1E,0x17,0x40,0x05,0x04,0x00,0x02,0x00,0x08,(byte)0xD7,(byte)0xB1,0x0D,0x0A};
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(buf8)));

    }

}

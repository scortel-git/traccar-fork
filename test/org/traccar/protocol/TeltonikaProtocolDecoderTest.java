package org.traccar.protocol;

import org.traccar.helper.TestDataManager;
import org.jboss.netty.buffer.ChannelBuffers;
import static org.traccar.helper.DecoderVerifier.verify;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.traccar.helper.ChannelBufferTools;

public class TeltonikaProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        TeltonikaProtocolDecoder decoder = new TeltonikaProtocolDecoder(null);
        decoder.setDataManager(new TestDataManager());

        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x0F,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x30,0x31,0x32,0x33,0x34,0x35}))));
        
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xA7,0x08,0x04,0x00,0x00,0x01,0x13,0xfc,0x20,0x8d,0xff,0x00,0x0f,0x14,0xf6,0x50,0x20,0x9c,0xca,0x80,0x00,0x6f,0x00,0xd6,0x04,0x00,0x04,0x00,0x04,0x03,0x01,0x01,0x15,0x03,0x16,0x03,0x00,0x01,0x46,0x00,0x00,0x01,0x5d,0x00,0x00,0x00,0x01,0x13,0xfc,0x17,0x61,0x0b,0x00,0x0f,0x14,0xff,0xe0,0x20,0x9c,0xc5,0x80,0x00,0x6e,0x00,0xc0,0x05,0x00,0x01,0x00,0x04,0x03,0x01,0x01,0x15,0x03,0x16,0x01,0x00,0x01,0x46,0x00,0x00,0x01,0x5e,0x00,0x00,0x00,0x01,0x13,0xfc,0x28,0x49,0x45,0x00,0x0f,0x15,0x0f,0x00,0x20,0x9c,0xd2,0x00,0x00,0x95,0x01,0x08,0x04,0x00,0x00,0x00,0x04,0x03,0x01,0x01,0x15,0x00,0x16,0x03,0x00,0x01,0x46,0x00,0x00,0x01,0x5d,0x00,0x00,0x00,0x01,0x13,0xfc,0x26,0x7c,0x5b,0x00,0x0f,0x15,0x0a,0x50,0x20,0x9c,0xcc,0xc0,0x00,0x93,0x00,0x68,0x04,0x00,0x00,0x00,0x04,0x03,0x01,0x01,0x15,0x00,0x16,0x03,0x00,0x01,0x46,0x00,0x00,0x01,0x5b,0x00,0x04,0x00,0x00}))));
        
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x47,0x08,0x06,0x00,0x00,0x01,0x3e,0x5a,0x60,0xa4,0xcb,0x00,0x3f,0xa7,0xb7,0x80,0xfc,0x42,0x45,0x18,0x00,0x42,0x00,0x00,0x0a,0x00,0x00,0x00,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x00,0x03,0x42,0x68,0xa7,0x46,0x01,0x18,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x3e,0x5d,0xc8,0xba,0x28,0x00,0x3f,0xa7,0xc0,0x80,0xfc,0x42,0x46,0x04,0x00,0x01,0x00,0x00,0x05,0x00,0x00,0x00,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x01,0x03,0x42,0x68,0xb4,0x46,0x00,0xef,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x3e,0x5d,0xc9,0x04,0x55,0x00,0x3f,0xa7,0xb6,0x40,0xfc,0x42,0x43,0x88,0x00,0x3a,0x00,0x00,0x07,0x00,0x00,0xf0,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x00,0x03,0x42,0x68,0xdc,0x46,0x00,0xf7,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x1d,0x00,0x00,0x00,0x01,0x3e,0x5d,0xc9,0xd3,0x68,0x00,0x3f,0xa7,0xb8,0x00,0xfc,0x42,0x44,0x30,0x00,0x49,0x00,0x00,0x04,0x00,0x00,0x00,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x01,0x03,0x42,0x67,0xde,0x46,0x01,0x07,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x3e,0x5d,0xca,0x31,0x1d,0x00,0x3f,0xa7,0xb6,0x80,0xfc,0x42,0x43,0xcc,0x00,0x42,0x00,0x00,0x07,0x00,0x00,0xf0,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x00,0x03,0x42,0x68,0x53,0x46,0x01,0x0b,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x3e,0x5d,0xcf,0xaf,0xe9,0x00,0x3f,0xa7,0xb6,0x00,0xfc,0x42,0x42,0xf0,0x00,0x3d,0x00,0x00,0x08,0x00,0x00,0x00,0x09,0x05,0x01,0x01,0x02,0x00,0xb3,0x00,0xb4,0x00,0xf0,0x00,0x03,0x42,0x68,0x52,0x46,0x01,0x19,0x18,0x00,0x00,0x01,0xc7,0x00,0x00,0x00,0x00,0x00,0x06,0x00,0x00,0x02,0x75}))));

        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x2c,0x08,0x01,0x00,0x00,0x01,0x3e,0xff,0x8d,0x6f,0x98,0x00,0x17,0x32,0x95,0x00,0x21,0x11,0xf4,0x00,0x00,0x81,0x00,0xae,0x0b,0x00,0x00,0x00,0x04,0x01,0x01,0x00,0x03,0x09,0x00,0x16,0x43,0x29,0x80,0x42,0x2f,0x72,0x00,0x00,0x01,0x00,0x00,0x7a,0x5d}))));
        
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0xc7,0x07,0x04,0x41,0xbf,0x9d,0xb0,0x0f,0xff,0x42,0x5a,0xdb,0xd7,0x41,0xca,0x6e,0x1e,0x00,0x9e,0x12,0x05,0x07,0x00,0x01,0x03,0x0b,0x16,0x00,0x00,0x60,0x1a,0x02,0x01,0x5e,0x02,0x00,0x03,0x14,0x00,0x66,0x15,0x00,0x0a,0x16,0x00,0x67,0x01,0x05,0x00,0x00,0x0c,0xe4,0x41,0xbf,0x9d,0x92,0x0f,0xff,0x42,0x5a,0xdb,0xb1,0x41,0xca,0x6f,0xc9,0x00,0xa2,0xb2,0x18,0x07,0x00,0x01,0x03,0x0b,0x16,0x00,0x00,0x60,0x1a,0x02,0x01,0x5e,0x02,0x00,0x03,0x14,0x00,0x66,0x15,0x00,0x0a,0x16,0x00,0x67,0x01,0x05,0x00,0x00,0x0c,0xc6,0x41,0xbf,0x9d,0x74,0x0f,0xff,0x42,0x5a,0xdb,0xee,0x41,0xca,0x73,0x92,0x00,0xb6,0xc9,0x1e,0x07,0x00,0x01,0x03,0x0b,0x1f,0x00,0x00,0x60,0x1a,0x02,0x01,0x5f,0x02,0x00,0x03,0x14,0x00,0x66,0x15,0x00,0x0a,0x16,0x00,0x66,0x01,0x05,0x00,0x00,0x0c,0xa8,0x41,0xbf,0x9c,0xfc,0x0f,0xff,0x42,0x5a,0xdb,0xa0,0x41,0xca,0x70,0xc1,0x00,0xb9,0x38,0x13,0x07,0x00,0x01,0x03,0x0b,0x1f,0x00,0x00,0x60,0x1a,0x02,0x01,0x5f,0x02,0x00,0x03,0x14,0x00,0x23,0x15,0x00,0x0a,0x16,0x00,0x25,0x01,0x05,0x00,0x00,0x0c,0x30,0x04,0x00,0x00,0x00}))));

        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x31,0x07,0x02,0x4c,0x61,0x41,0x0b,0x01,0x3f,0x42,0x31,0xc2,0xc1,0x41,0xd0,0xbe,0xb9,0x00,0x3d,0x00,0x00,0x05,0x00,0x64,0x83,0xff,0x4c,0x61,0x40,0xeb,0x01,0x3f,0x42,0x31,0xc2,0xc1,0x41,0xd0,0xbe,0xb9,0x00,0x3d,0x00,0x00,0x05,0x00,0x64,0x83,0xff,0x02,0x00,0x00,0x41,0xdf}))));

        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x2b,0x08,0x01,0x00,0x00,0x01,0x40,0xd4,0xe3,0xec,0x6e,0x00,0x0c,0xc6,0x61,0xd0,0x16,0x74,0xa5,0xe0,0xff,0xfc,0x00,0x00,0x09,0x00,0x00,0x00,0x04,0x02,0x01,0x00,0xf0,0x00,0x02,0x42,0x32,0x23,0x18,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x7a,0x04}))));
        
        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x2d,0x0c,0x01,0x06,0x00,0x00,0x00,0x25,0x23,0x46,0x4d,0x32,0x3d,0x32,0x36,0x32,0x30,0x33,0x32,0x37,0x36,0x31,0x37,0x32,0x31,0x33,0x39,0x36,0x2c,0x32,0x36,0x32,0x30,0x33,0x2c,0x30,0x37,0x2e,0x30,0x32,0x2e,0x30,0x35,0x0d,0x0a,0x01,0x00,0x00,0x9a,0x2e}))));
        
        verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertArray(
                new int[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x0a,0x0c,0x02,0x06,0x00,0x00,0x00,0x02,0x0d,0x0a,0x02,0x00,0x00,0x6f,0x4e}))));
        
    }

}

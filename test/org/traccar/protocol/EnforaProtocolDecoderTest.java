package org.traccar.protocol;

import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class EnforaProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        EnforaProtocolDecoder decoder = new EnforaProtocolDecoder(new TestDataManager(), 0);
        ChannelBufferFactory factory = new HeapChannelBufferFactory();

        byte[] buf1 = {0x00,0x0A,0x08,0x00,0x20,0x20,0x20,0x20,0x20,0x30,0x31,0x31,0x30,0x37,0x30,0x30,0x30,0x30,0x35,0x37,0x30,0x32,0x36,0x37};
        assertNull(decoder.decode(null, null, factory.getBuffer(buf1, 0, buf1.length)));

        byte[] buf2 = {0x00,0x3B,0x00,0x05,0x02,0x00,0x00,0x00,0x00,0x08,0x20,0x20,0x20,0x20,0x20,0x20,0x30,0x31,0x31,0x30,0x37,0x30,0x30,0x30,0x30,0x35,0x37,0x30,0x32,0x36,0x37,0x20,0x38,0x3A,0x00,0x00,0x00,0x00,0x0D,0x00,0x50,(byte)0x84,0x01,0x35,(byte)0x8E,0x64,0x00,0x32,(byte)0xB3,0x77,0x00,0x00,0x03,0x67,(byte)0xB0,0x00,0x00,(byte)0xA8,0x04};
        assertNull(decoder.decode(null, null, factory.getBuffer(buf2, 0, buf2.length)));

        byte[] buf3 = {0x00,0x71,0x00,0x04,0x02,0x00,0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x38,0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x30,0x31,0x32,0x33,0x34,0x35,0x20,0x31,0x33,0x20,0x24,0x47,0x50,0x52,0x4D,0x43,0x2C,0x32,0x32,0x33,0x31,0x35,0x32,0x2E,0x30,0x30,0x2C,0x41,0x2C,0x33,0x35,0x30,0x39,0x2E,0x38,0x36,0x30,0x35,0x39,0x34,0x2C,0x4E,0x2C,0x30,0x33,0x33,0x32,0x32,0x2E,0x37,0x34,0x33,0x38,0x38,0x37,0x2C,0x45,0x2C,0x30,0x2E,0x30,0x2C,0x30,0x2E,0x30,0x2C,0x30,0x32,0x30,0x36,0x31,0x32,0x2C,0x2C,0x2C,0x41,0x2A,0x35,0x32,0x0D,0x0A};
        assertNotNull(decoder.decode(null, null, factory.getBuffer(buf3, 0, buf3.length)));
    }

}

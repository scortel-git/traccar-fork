package org.traccar.protocol;

import junit.framework.TestCase;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Test;
import org.traccar.helper.ChannelBufferTools;

/**
 * Created by Ankit on 21-10-2015.
 */
public class T800XProtocolDecoderTest extends TestCase {

    @Test
    public void testDecode() throws Exception {

        /*T800XProtocolDecoder decoder = new T800XProtocolDecoder(new TestDataManager(), null, null);

        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertHexString(
                "232301001500020357367031063979150208625010"))));

        assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertHexString(
                "6767030004001A0001"))));*/

       /* assertNull(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertHexString(
                "6767070088001050E2281400FFFFFFFF02334455660333445566043344556605AA00000007334455660A334455660B334455660C4E2000000DAA0000000E334455660F3344556610AAAA000011334455661C334455661F334455662133445566423344556646334455664D334455665C334455665E33445566880000000089000000008A000000008B00000000"))));
*/
       /* verify(decoder.decode(null, null, ChannelBuffers.wrappedBuffer(ChannelBufferTools.convertHexString(
                "676702001b03c5538086df0190c1790b3482df0f0157020800013beb00342401"))));
*/
    }
}
package org.traccar.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Assert;
import org.junit.Test;
import org.traccar.ProtocolDecoderTest;

public class MeitrackFrameDecoderTest extends ProtocolDecoderTest {

    @Test
    public void testDecode() throws Exception {

        MeitrackFrameDecoder decoder = new MeitrackFrameDecoder();

        Assert.assertEquals(
                binary("24244e3132372c3836333037313031333830333036362c4141412c33352c2d312e3330323638302c33362e3835323133352c3135303430393231313032362c412c392c302c302e312c302c352c313635332c343039362c33323634382c3633397c30327c313030347c3930432c303030302c307c307c307c3346467c3330302c2a37430d0a"),
                decoder.decode(null, null, binary("24244e3132372c3836333037313031333830333036362c4141412c33352c2d312e3330323638302c33362e3835323133352c3135303430393231313032362c412c392c302c302e312c302c352c313635332c343039362c33323634382c3633397c30327c313030347c3930432c303030302c307c307c307c3346467c3330302c2a37430d0a")));

    }

}

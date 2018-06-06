/*
 * Copyright 2013 - 2018 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.protocol;

import java.nio.charset.StandardCharsets;

import org.traccar.BaseFrameDecoder;
import org.traccar.NetworkMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class WondexFrameDecoder extends BaseFrameDecoder {

    private static final int KEEP_ALIVE_LENGTH = 8;

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, ByteBuf buf) throws Exception {

        if (buf.readableBytes() < KEEP_ALIVE_LENGTH) {
            return null;
        }

        if (buf.getUnsignedByte(buf.readerIndex()) == 0xD0) {

            // Send response
            ByteBuf frame = buf.readBytes(KEEP_ALIVE_LENGTH);
            if (channel != null) {
                channel.writeAndFlush(new NetworkMessage(frame, channel.remoteAddress()));
            }
            return frame;

        } else {

            ByteBuf delimiter = Unpooled.wrappedBuffer("\r\n".getBytes(StandardCharsets.US_ASCII));
            int index = ByteBufUtil.indexOf(delimiter, buf);
            if (index != -1) {
                ByteBuf frame = buf.readBytes(index - buf.readerIndex());
                buf.skipBytes(2);
                return frame;
            }

        }

        return null;
    }

}

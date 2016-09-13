/*
 * Copyright 2013 - 2016 Anton Tananaev (anton.tananaev@gmail.com)
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

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class H02FrameDecoder extends FrameDecoder {

    private int messageLength;

    public H02FrameDecoder(int messageLength) {
        this.messageLength = messageLength;
    }

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf) throws Exception {

        char marker = (char) buf.getByte(buf.readerIndex());

        if (marker == '*') {

            // Return text message
            int index = buf.indexOf(buf.readerIndex(), buf.writerIndex(), (byte) '#');
            if (index != -1) {
                return buf.readBytes(index + 1 - buf.readerIndex());
            }

        } else if (marker == '$' && buf.readableBytes() >= messageLength) {

            // Return binary message
            return buf.readBytes(messageLength);

        }

        return null;
    }

}

/*
 * Copyright 2017 - 2018 Anton Tananaev (anton@traccar.org)
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

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.traccar.BaseFrameDecoder;
import org.traccar.helper.BufferUtil;

public class AtrackFrameDecoder extends BaseFrameDecoder {

    private static final int KEEPALIVE_LENGTH = 12;

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, ByteBuf buf) throws Exception {

        if (buf.readableBytes() >= 2) {

            if (buf.getUnsignedShort(buf.readerIndex()) == 0xfe02) {

                if (buf.readableBytes() >= KEEPALIVE_LENGTH) {
                    return buf.readRetainedSlice(KEEPALIVE_LENGTH);
                }

            } else if (buf.getUnsignedShort(buf.readerIndex()) == 0x4050 && buf.getByte(buf.readerIndex() + 2) != ',') {

                if (buf.readableBytes() > 6) {
                    int length = buf.getUnsignedShort(buf.readerIndex() + 4) + 4 + 2;
                    if (buf.readableBytes() >= length) {
                        return buf.readRetainedSlice(length);
                    }
                }

            } else {

                int endIndex = BufferUtil.indexOf("\r\n", buf);
                if (endIndex > 0) {
                    return buf.readRetainedSlice(endIndex - buf.readerIndex() + 2);
                }

            }

        }

        return null;
    }

}

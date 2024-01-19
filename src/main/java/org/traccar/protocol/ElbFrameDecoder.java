/*
 * Copyright 2015 - 2023 Anton Tananaev (anton@traccar.org)
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
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import jakarta.xml.bind.DatatypeConverter;
import org.traccar.BaseFrameDecoder;

public class ElbFrameDecoder extends BaseFrameDecoder {

    private static final byte STX = (byte) 0x02;
    private static final byte ETX = (byte) 0x03;
    private static final byte PRE_ESCAPE_CHAR = (byte) 0x10;
    private static final byte PRE_ESCAPE_DELTA = (byte) 0x20;

    public ByteBuf extractPacket(ByteBuf source) {
        ByteBuf result = Unpooled.buffer();

        while (source.isReadable()) {
            byte currentByte = source.readByte();

            if (currentByte == STX) {
                // Започваме копирането след STX.
//                result.clear();
                continue;
            } else if (currentByte == ETX) {
                // Завършваме копирането на данните след ETX и прекъсваме цикъла.
                String d = DatatypeConverter.printHexBinary(result.array());
                break;
            } else {
                // Копираме байта, ако сме между STX и ETX и буфера има свободно място.
                result.writeByte(currentByte);
            }
        }

        int readableBytes = result.readableBytes();
        ByteBuf extracted = Unpooled.buffer(readableBytes);
        result.readBytes(extracted, readableBytes);

        return extracted;
    }

    public ByteBuf removePreEscape(ByteBuf frame) {

        ByteBuf data = extractPacket(frame);
        int n = data.array().length;
        ByteBuf buf = Unpooled.buffer(n + 2);

        try {
            buf.writeByte(STX);
            for (int i = 0, j = 1; i < n; i++, j++) {
                byte currentByte = data.readByte();
                if (currentByte == PRE_ESCAPE_CHAR) {
                    byte nextByte = data.readByte();
                    buf.writeByte((byte) (nextByte - PRE_ESCAPE_DELTA));
                } else {
                    buf.writeByte(currentByte);
                }
            }
            buf.writeByte(ETX);
        }catch (Exception e) {
            int o = 1;
        }


        return buf;
    }
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ByteBuf buf) throws Exception {
        int additionalBufferSize =0;
        if (buf.readableBytes() < 4) {
            return null;
        }
        if (buf.getUnsignedByte(buf.readerIndex()) == 239) {
            buf.readUnsignedByte();
            buf.readUnsignedByte();
        }
        buf = removePreEscape(buf);
        if (buf.readableBytes() > 4) {
            ByteBuf frame = Unpooled.buffer();;

            frame.writeBytes(buf);
            return frame;
        }

        return null;
    }

}

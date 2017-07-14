/*
 * Copyright 2017 Ivan Muratov (binakot@gmail.com)
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

public class Arnavi4FrameDecoder extends FrameDecoder {

    static final int PACKET_MIN_LENGTH = 4;

    static final byte HEADER_START_SIGN = (byte) 0xFF;
    static final byte HEADER_VERSION_1 = 0x22;
    static final byte HEADER_VERSION_2 = 0x23;
    static final int HEADER_LENGTH = 10;

    static final byte PACKAGE_START_SIGN = 0x5B;
    static final byte PACKAGE_END_SIGN = 0x5D;
    static final int PACKAGE_MIN_PARCEL_NUMBER = 0x01;
    static final int PACKAGE_MAX_PARCEL_NUMBER = 0xFB;

    @Override
    protected Object decode(
            ChannelHandlerContext ctx,
            Channel channel,
            ChannelBuffer buf) throws Exception {

        if (buf.readableBytes() < PACKET_MIN_LENGTH) {
            return null;
        }

        if (buf.getByte(0) == HEADER_START_SIGN
                && buf.readableBytes() == HEADER_LENGTH
                && (buf.getByte(1) == HEADER_VERSION_1 || buf.getByte(1) == HEADER_VERSION_2)) {
            return buf.readBytes(HEADER_LENGTH);
        }

        int index = buf.getByte(1) & 0xFF; // parcel number
        if (buf.getByte(0) == PACKAGE_START_SIGN
                && index >= PACKAGE_MIN_PARCEL_NUMBER && index <= PACKAGE_MAX_PARCEL_NUMBER) {

            // package: 1 byte start sign, 1 byte parcel number, packets, 1 byte end sign
            int packetStartIndex = 2;
            while (packetStartIndex + 8 < buf.readableBytes() && buf.getByte(packetStartIndex) != PACKAGE_END_SIGN) {
                // packet: 1 byte type, 2 bytes length, 4 bytes unixtime, length-bytes data, 1 byte crc
                packetStartIndex += buf.getUnsignedShort(packetStartIndex + 1) + 8;
            }

            return buf.readBytes(packetStartIndex + 1);
        }

        return null;
    }

}

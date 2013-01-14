/*
 * Copyright 2012 Anton Tananaev (anton.tananaev@gmail.com)
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

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.traccar.BaseProtocolDecoder;
import org.traccar.ServerManager;
import org.traccar.helper.Log;
import org.traccar.model.Position;

/**
 * TR20 tracker protocol decoder
 */
public class Tr20ProtocolDecoder extends BaseProtocolDecoder {

    /**
     * Initialize
     */
    public Tr20ProtocolDecoder(ServerManager serverManager) {
        super(serverManager);
    }

    static private Pattern patternPing = Pattern.compile(
            "\\%\\%[^,]+,(\\d+)");

    //%%TR-10,A,050916070549,N2240.8887E11359.2994,0,000,NA,D3800000,150,CFG:resend|
    //%%123456789012345,A,120101121800,N6000.0000E13000.0000,0,000,0,01034802,150,[Message]
    static private Pattern patternData = Pattern.compile(
            "\\%\\%" +
            "([^,]+)," +                   // Id
            "([AL])," +                    // Validity
            "(\\d{2})(\\d{2})(\\d{2})" +   // Date (YYMMDD)
            "(\\d{2})(\\d{2})(\\d{2})," +  // Time (HHMMSS)
            "([NS])" +
            "(\\d{2})(\\d{2}\\.\\d+)" +    // Latitude (DDMM.MMMM)
            "([EW])" +
            "(\\d{3})(\\d{2}\\.\\d+)," +   // Longitude (DDDMM.MMMM)
            "(\\d+)," +                    // Speed
            "(\\d+)," +                    // Course
            ".*");

    /**
     * Decode message
     */
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg)
            throws Exception {

        String sentence = (String) msg;

        // Keep alive message
        Matcher parser = patternPing.matcher(sentence);
        if (parser.matches()) {

            // Send response
            if (channel != null) {
                channel.write("&&" + parser.group(1) + "\r\n");
            }
        } else {

            // Data message parse
            parser = patternData.matcher(sentence);

            // Unknown message
            if (!parser.matches()) {
                return null;
            }

            // Create new position
            Position position = new Position();
            StringBuilder extendedInfo = new StringBuilder("<protocol>tr20</protocol>");

            Integer index = 1;

            // Get device by id
            String id = parser.group(index++);
            try {
                position.setDeviceId(getDataManager().getDeviceByImei(id).getId());
            } catch(Exception error) {
                Log.warning("Unknown device - " + id);
                return null;
            }

            // Validity
            position.setValid(parser.group(index++).compareTo("A") == 0 ? true : false);

            // Time
            Calendar time = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            time.clear();
            time.set(Calendar.YEAR, 2000 + Integer.parseInt(parser.group(index++)));
            time.set(Calendar.MONTH, Integer.parseInt(parser.group(index++)) - 1);
            time.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.HOUR, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.MINUTE, Integer.parseInt(parser.group(index++)));
            time.set(Calendar.SECOND, Integer.parseInt(parser.group(index++)));
            position.setTime(time.getTime());

            // Latitude
            int hemisphere = 1;
            if (parser.group(index++).compareTo("S") == 0) hemisphere = -1;
            Double latitude = Double.valueOf(parser.group(index++));
            latitude += Double.valueOf(parser.group(index++)) / 60;
            position.setLatitude(latitude * hemisphere);

            // Longitude
            hemisphere = 1;
            if (parser.group(index++).compareTo("W") == 0) hemisphere = -1;
            Double lonlitude = Double.valueOf(parser.group(index++));
            lonlitude += Double.valueOf(parser.group(index++)) / 60;
            position.setLongitude(lonlitude * hemisphere);

            // Speed
            position.setSpeed(Double.valueOf(parser.group(index++)));

            // Course
            position.setCourse(Double.valueOf(parser.group(index++)));

            // Altitude
            position.setAltitude(0.0);

            // Extended info
            position.setExtendedInfo(extendedInfo.toString());

            return position;
        }

        return null;
    }

}

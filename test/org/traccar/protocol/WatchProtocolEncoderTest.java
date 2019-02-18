package org.traccar.protocol;

import io.netty.buffer.ByteBuf;
import org.junit.Test;
import org.traccar.ProtocolTest;
import org.traccar.model.Command;

import static org.junit.Assert.assertEquals;

public class WatchProtocolEncoderTest extends ProtocolTest {

    @Test
    public void testEncode() throws Exception {

        WatchProtocolEncoder encoder = new WatchProtocolEncoder();
        
        Command command;

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_REBOOT_DEVICE);
        assertEquals("[CS*123456789012345*0005*RESET]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_SOS_NUMBER);
        command.set(Command.KEY_INDEX, 1);
        command.set(Command.KEY_PHONE, "123456789");
        assertEquals("[CS*123456789012345*000e*SOS1,123456789]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_VOICE_MESSAGE);
        command.set(Command.KEY_DATA, "2321414d520a2573");
        assertEquals(buffer("[CS*123456789012345*000b*TK,#!AMR\n%s]"), encoder.encodeCommand(null, command));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_VOICE_MESSAGE);
        command.set(Command.KEY_DATA, "7d5b5d2c2a");
        verifyFrame(concatenateBuffers(buffer("[CS*123456789012345*000d*TK,"), binary("7d017d027d037d047d05"), buffer("]")), encoder.encodeCommand(null, command));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_VOICE_MESSAGE);
        command.set(Command.KEY_DATA, "ff");
        verifyFrame(concatenateBuffers(buffer("[CS*123456789012345*0004*TK,"), binary("ff"), buffer("]")), encoder.encodeCommand(null, command));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_MESSAGE);
        command.set(Command.KEY_MESSAGE, "text");
        assertEquals("[CS*123456789012345*0018*MESSAGE,0074006500780074]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_CUSTOM);
        command.set(Command.KEY_DATA, "WORK,6-9,11-13,13-15,17-19");
        assertEquals("[CS*123456789012345*001a*WORK,6-9,11-13,13-15,17-19]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_SET_TIMEZONE);
        command.set(Command.KEY_TIMEZONE, "Europe/Amsterdam");
        assertEquals("[CS*123456789012345*0006*LZ,,+1]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command.set(Command.KEY_TIMEZONE, "GMT+01:30");
        assertEquals("[CS*123456789012345*0008*LZ,,+1.5]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command.set(Command.KEY_TIMEZONE, "Atlantic/Azores");
        assertEquals("[CS*123456789012345*0006*LZ,,-1]", text((ByteBuf)encoder.encodeCommand(null, command)));

        command.set(Command.KEY_TIMEZONE, "GMT-11:30");
        assertEquals("[CS*123456789012345*0009*LZ,,-11.5]", text((ByteBuf)encoder.encodeCommand(null, command)));

    }

}

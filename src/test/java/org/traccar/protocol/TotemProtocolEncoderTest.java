package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;
import org.traccar.model.Command;

import static org.junit.Assert.assertEquals;

public class TotemProtocolEncoderTest extends ProtocolTest {

    @Test
    public void testEncode() throws Exception {

        var encoder = new TotemProtocolEncoder(null);

        Command command = new Command();
        command.setDeviceId(2);
        command.setType(Command.TYPE_REBOOT_DEVICE);
        command.set(Command.KEY_DEVICE_PASSWORD, "000000");

        assertEquals("$$0020CF000000,0061D", encoder.encodeCommand(command));

    }
    
    @Test
    public void testSmsEncode() throws Exception {

        var encoder = new TotemProtocolSmsEncoder(null);

        Command command = new Command();
        command.setDeviceId(2);
        command.setType(Command.TYPE_REBOOT_DEVICE);
        command.set(Command.KEY_DEVICE_PASSWORD, "000000");

        assertEquals("*000000,006#", encoder.encodeCommand(command));

    }

}

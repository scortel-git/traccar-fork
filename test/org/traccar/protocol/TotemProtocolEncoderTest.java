/*
 * Copyright 2015 alexis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.protocol;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.traccar.model.Command;

/**
 *
 * @author alexis
 */
public class TotemProtocolEncoderTest {
    @Test
    public void testDecode() throws Exception {

        TotemProtocolEncoder encoder = new TotemProtocolEncoder();
        
        Command command = new Command();
        command.setDeviceId(2);
        command.setType(Command.TYPE_IMEI);
        
        Assert.assertEquals("*000000,801#", encoder.encodeCommand(command));

    }
}

package org.traccar.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.traccar.EventHandlerTest;
import org.traccar.model.Event;
import org.traccar.model.Position;

public class IgnitionEventHandlerTest extends EventHandlerTest{
    
    @Test
    public void testIgnitionEventHandler() throws Exception {
        
        IgnitionEventHandler ignitionEventHandler = new IgnitionEventHandler();
        
        Position position = new Position();
        position.set(Position.KEY_IGNITION, true);
        position.setValid(true);
        Collection<Event> events = ignitionEventHandler.analyzePosition(position);
        assertNotNull(events);
        Event event = (Event) events.toArray()[0];
        assertEquals(Event.TYPE_IGNITION_ON, event.getType());
    }

}

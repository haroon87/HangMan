package main;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.event.EventNameConstants;
import com.gamemaker.event.KeyboardPressEvent;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.helper.EventeDeserializer;

public class EventeDeserializerTest {

	String name;
	EventeDeserializer eventeDeserializer;
	@Before
	public void setUp() throws Exception {
		name = EventNameConstants.KEYBOARD_PRESS_EVENT.toString();
		eventeDeserializer = new EventeDeserializer();
	}

	@Test
	public void testGetEventObjectFromString() {
		try {
			if(eventeDeserializer.getEventObjectFromString(name) instanceof KeyboardPressEvent)
				assert(true);
			else
				assert(false);
		} catch (UnsupportedEvent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

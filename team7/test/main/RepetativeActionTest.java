package main;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.RepetativeAction;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Event;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class RepetativeActionTest {

	private EventActionHandler eventActionHandler;
	private Sprite sprite;
	ArrayList<Sprite> spritesData;
	ArrayList<Sprite> dynamicSprites;
	
	
	@Before
	public void setUp() throws Exception {
		spritesData = new ArrayList<Sprite>();
		sprite = new Sprite();

		eventActionHandler = new EventActionHandler(spritesData,dynamicSprites);
		createTestData();
	}
	
	
	private void createTestData() {
		spritesData.clear();
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		ArrayList<Action> actions = new ArrayList<Action>();
		
		
		// 1. Add sprite
		sprite.setName("car");
		sprite.setX(300);
		sprite.setY(100);
		sprite.setInitX(100);
		sprite.setInitY(100);
		sprite.setVx(-1);
		sprite.setVy(0);
		sprite.setHeight(50);
		sprite.setWidth(50);

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		actions.add(new RepetativeAction());
		newEventActionPairs.put(new Auto(), actions);

		// give reflect action
		sprite.setNewEventActionPairs(newEventActionPairs);

		spritesData.add(sprite);
	}
	
	
	@Test
	public void testRepetativeAction() {
		
		eventActionHandler.executeAction(new Auto(), new AutoMoveAction());
		eventActionHandler.executeAction(new Auto(), new RepetativeAction());
		Assert.assertNotSame(sprite.getX(), sprite.getInitX());
	}
	
	
	
}

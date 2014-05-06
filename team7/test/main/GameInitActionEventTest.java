package main;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.SetDirectionToDown;
import com.gamemaker.action.SetDirectionToLeft;
import com.gamemaker.action.SetDirectionToRight;
import com.gamemaker.action.SetDirectionToUp;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Event;
import com.gamemaker.event.GameInitEvent;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class GameInitActionEventTest {

	private EventActionHandler eventActionHandler;
	ArrayList<Sprite> spritesData;
	ArrayList<Sprite> dynamicSprites;

	@Before
	public void setUp() throws Exception {
		spritesData = new ArrayList<Sprite>();
		dynamicSprites = new ArrayList<Sprite>();
		eventActionHandler = new EventActionHandler(spritesData, dynamicSprites);
		createTestData();
	}

	private void createTestData() {
		spritesData.clear();

		// 1. Add ball with left direction
		Sprite sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new SetDirectionToLeft());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);
		spritesData.add(sprite);

		// 2. Add ball with right direction
		sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new SetDirectionToRight());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);
		
		// 3. Add ball with up direction
		sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new SetDirectionToUp());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);
		
		// 4. Add ball with down direction
		sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new SetDirectionToDown());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);
		
		// 5. Add ball with NW direction
		// To achieve northwest direction, we basically given up and left direction
		sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new SetDirectionToLeft());
		actions.add(new SetDirectionToUp());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);
		
		
		// 6. Add ball with SW direction
		sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new SetDirectionToRight());
		actions.add(new SetDirectionToDown());
		newEventActionPairs.put(new GameInitEvent(), actions);

		actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);
	}

	@Test
	public void testSetLeftDirection() {
		Sprite sprite = spritesData.get(0);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertEquals(sprite.getY(), yPos);
	}

	@Test
	public void testSetRightDirection() {
		Sprite sprite = spritesData.get(1);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertEquals(sprite.getY(), yPos);
	}

	@Test
	public void testSetUpDirection() {
		Sprite sprite = spritesData.get(2);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}

	@Test
	public void testSetDownDirection() {
		Sprite sprite = spritesData.get(3);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}
	
	@Test
	public void testSetNWDirection() {
		Sprite sprite = spritesData.get(4);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}

	@Test
	public void testSetSWDirection() {
		Sprite sprite = spritesData.get(5);
		sprite.setVx(0);
		sprite.setVy(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}
	

}

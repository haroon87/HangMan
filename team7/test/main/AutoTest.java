package main;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.RepetativeAction;
import com.gamemaker.event.Event;
import com.gamemaker.event.GameInitEvent;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class AutoTest extends TestCase {

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

		Sprite sprite = new Sprite();
		sprite.setName("frog");

		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new GameInitEvent(), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);
		spritesData.add(sprite);

		Sprite sprite2 = new Sprite();
		sprite2.setName("frog");

		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();

		actions = new ArrayList<Action>();
		actions.add(new RepetativeAction());
		newEventActionPairs.put(new GameInitEvent(), actions);

		sprite2.setNewEventActionPairs(newEventActionPairs);
		sprite2.setInitX(10);
		sprite2.setInitY(10);
		sprite2.setX(-10);
		sprite2.setY(200);
		sprite2.setHeight(10);
		sprite2.setWidth(20);
		spritesData.add(sprite2);

	}

	@Test
	public void testAutoMoveAction() {
		Sprite sprite = spritesData.get(0);
		sprite.setVx(10);
		sprite.setVy(10);

		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		Assert.assertEquals(110, xPos);
		Assert.assertEquals(210, yPos);

	}

	@Test
	public void testRepetativeAction() {
		Sprite sprite = spritesData.get(1);

		int xPos = sprite.getInitX();
		int yPos = sprite.getInitY();
		eventActionHandler.executeEvent(new GameInitEvent());

		eventActionHandler.executeEventActionPairs();

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertEquals(sprite.getY(), yPos);

	}

}

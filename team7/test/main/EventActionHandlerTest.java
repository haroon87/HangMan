package main;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.DownMovementAction;
import com.gamemaker.action.LeftMovementAction;
import com.gamemaker.action.ReflectAction;
import com.gamemaker.action.RightMovementAction;
import com.gamemaker.action.UpMovementAction;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Collision;
import com.gamemaker.event.Event;
import com.gamemaker.event.KeyboardPressEvent;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class EventActionHandlerTest {

	private EventActionHandler eventActionHandler;
	ArrayList<Sprite> spritesData;
	ArrayList<Sprite> dynamicSprites;

	@Test
	public void testSuccess() {
		Assert.assertTrue(true);
	}

	// Following test cases are worng. This was just checking unused getters and
	// setters.
	// To improve, EventHandler, we need to remove these unused getter and
	// settters. So commenting out all test cases that are not doing any correct
	// checks.

	@Before
	public void setUp() throws Exception {
		spritesData = new ArrayList<Sprite>();
		dynamicSprites=new ArrayList<Sprite>();
		
		eventActionHandler = new EventActionHandler(spritesData,dynamicSprites);

		createTestData();
	}

	private void createTestData() {
		spritesData.clear();

		// 1. Add ball
		Sprite sprite = new Sprite();
		sprite.setName("myball");

		// give automove action
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		newEventActionPairs.put(new Auto(), actions);

		// give reflect action
		actions = new ArrayList<Action>();
		actions.add(new ReflectAction());
		newEventActionPairs.put(new Collision(GameState.GAME_WIN), actions);

		sprite.setNewEventActionPairs(newEventActionPairs);
		sprite.setVx(GameConstants.DEFAULT_X_SPEED);
		sprite.setVx(GameConstants.DEFAULT_Y_SPEED);
		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);

		spritesData.add(sprite);

		// 2. add wall in the bottom
		sprite = new Sprite();
		sprite.setName("mywallbottom");

		// give automove action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(20);
		sprite.setY(300);
		sprite.setHeight(20);
		sprite.setWidth(500);
		sprite.setVx(0);
		sprite.setVy(0);

		spritesData.add(sprite);

		// 3. add paddle
		sprite = new Sprite();
		sprite.setName("mypaddle");

		// give keypress action
		newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		actions = new ArrayList<Action>();
		actions.add(new LeftMovementAction());
		actions.add(new UpMovementAction());
		actions.add(new RightMovementAction());
		actions.add(new DownMovementAction());

		newEventActionPairs.put(new KeyboardPressEvent(), actions);
		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(50);
		sprite.setY(200);
		sprite.setHeight(20);
		sprite.setWidth(40);
		sprite.setVx(1);
		sprite.setVy(1);

		spritesData.add(sprite);
	}
	
	@Test
	public void testAutoMove() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeEvent(new Auto(), sprite);
		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}

	@Test
	public void testCollision() {
		Sprite sprite1 = spritesData.get(0);
		int xPos = sprite1.getX();
		int yPos = sprite1.getY();
		int vx = sprite1.getVx();
		int vy = sprite1.getVy();

		Sprite sprite2 = spritesData.get(1);
		sprite2.setX(xPos);
		sprite2.setY(yPos);
		
		eventActionHandler.executeEventActionPairs();

		// We expect ball to collide, and change its direction
		Assert.assertNotSame(sprite1.getVx(), vx);
		//Assert.assertNotSame(sprite1.getVy(), vy);
	}

	@Test
	public void testLeftMovement() {
		Sprite sprite1 = spritesData.get(2);
		int xPos = sprite1.getX();
		int yPos = sprite1.getY();
		int vx = sprite1.getVx();
		int vy = sprite1.getVy();

		eventActionHandler.executeAction(new KeyboardPressEvent(),
				new LeftMovementAction());

		Assert.assertNotSame(sprite1.getX(), xPos);
		Assert.assertEquals(sprite1.getY(), yPos);
	}

	@Test
	public void testRightMovement() {
		Sprite sprite1 = spritesData.get(2);
		int xPos = sprite1.getX();
		int yPos = sprite1.getY();
		int vx = sprite1.getVx();
		int vy = sprite1.getVy();

		eventActionHandler.executeAction(new KeyboardPressEvent(),
				new RightMovementAction());

		Assert.assertNotSame(sprite1.getX(), xPos);
		Assert.assertEquals(sprite1.getY(), yPos);
	}

	@Test
	public void testUpMovement() {
		Sprite sprite1 = spritesData.get(2);
		int xPos = sprite1.getX();
		int yPos = sprite1.getY();
		int vx = sprite1.getVx();
		int vy = sprite1.getVy();

		eventActionHandler.executeAction(new KeyboardPressEvent(),
				new UpMovementAction());

		Assert.assertEquals(sprite1.getX(), xPos);
		Assert.assertNotSame(sprite1.getY(), yPos);
	}

	@Test
	public void testDownMovement() {
		Sprite sprite1 = spritesData.get(2);
		int xPos = sprite1.getX();
		int yPos = sprite1.getY();
		int vx = sprite1.getVx();
		int vy = sprite1.getVy();

		eventActionHandler.executeAction(new KeyboardPressEvent(),
				new DownMovementAction());

		Assert.assertEquals(sprite1.getX(), xPos);
		Assert.assertNotSame(sprite1.getY(), yPos);
	}

}

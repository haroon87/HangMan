package main;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.DownHopAction;
import com.gamemaker.action.DownMovementAction;
import com.gamemaker.action.LeftHopAction;
import com.gamemaker.action.LeftMovementAction;
import com.gamemaker.action.RightHopAction;
import com.gamemaker.action.RightMovementAction;
import com.gamemaker.action.UpHopAction;
import com.gamemaker.action.UpMovementAction;
import com.gamemaker.event.Event;
import com.gamemaker.event.Hop;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class HopEventTest {
	
	
	private EventActionHandler eventActionHandler;
	private Sprite sprite;
	ArrayList<Sprite> spritesData;
	ArrayList<Sprite> dynamicSprites;

	@Before
	public void setUp() throws Exception {
		spritesData = new ArrayList<Sprite>();
		dynamicSprites=new ArrayList<Sprite>();
		sprite = new Sprite();

		eventActionHandler = new EventActionHandler(spritesData,dynamicSprites);
		createTestData();
	}
	
	
	
	private void createTestData() {
		spritesData.clear();

		// 1. Add ball
		Sprite sprite = new Sprite();
		sprite.setName("frog");

		// give automove action
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new LeftHopAction());
		actions.add(new DownHopAction());
		actions.add(new UpHopAction());
		actions.add(new RightHopAction());
		
		newEventActionPairs.put(new Hop(), actions);
		
		sprite.setNewEventActionPairs(newEventActionPairs);

		sprite.setX(100);
		sprite.setY(200);
		sprite.setHeight(10);
		sprite.setWidth(20);
		sprite.setVx(4);
		sprite.setVy(4);

		spritesData.add(sprite);

	}
	
	@Test
	public void testLeftMovement() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeAction(new Hop(),
				new LeftHopAction());

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertEquals(sprite.getY(), yPos);
	}
	
	@Test
	public void testRightMovement() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeAction(new Hop(),
				new RightHopAction());

		Assert.assertNotSame(sprite.getX(), xPos);
		Assert.assertEquals(sprite.getY(), yPos);
	}
	@Test
	public void testUpMovement() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeAction(new Hop(),
				new UpHopAction());

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}

	@Test
	public void testDownMovement() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeAction(new Hop(),
				new DownHopAction());

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
	}
	
	
	
}

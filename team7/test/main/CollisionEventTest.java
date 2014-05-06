package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.action.DownMovementAction;
import com.gamemaker.action.LeftMovementAction;
import com.gamemaker.action.PlayCollisionSoundAction;
import com.gamemaker.action.ReflectAction;
import com.gamemaker.action.RightMovementAction;
import com.gamemaker.action.UpMovementAction;
import com.gamemaker.action.VanishAction;
import com.gamemaker.controllers.GameState;
import com.gamemaker.event.Collision;
import com.gamemaker.event.Event;
import com.gamemaker.event.Hop;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.models.Sprite;

public class CollisionEventTest {

	private EventActionHandler eventActionHandler;
	private Sprite sprite;
	ArrayList<Sprite> spritesData;
	ArrayList<Sprite> dynamicSprites;
	GameState state;

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

		Sprite sprite = new Sprite();
		sprite.setName("frog");

		state = GameState.GAME_CONTINUE;
		
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new ReflectAction(state));
		actions.add(new PlayCollisionSoundAction(state));
		actions.add(new VanishAction(state));
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
	public void testReflectAction() {
		Sprite sprite = spritesData.get(0);
		int xPos = sprite.getX();
		int yPos = sprite.getY();
		eventActionHandler.executeAction(new Collision(),
				new ReflectAction());

		Assert.assertEquals(sprite.getX(), xPos);
		Assert.assertNotSame(sprite.getY(), yPos);
		
		Assert.assertEquals((new ReflectAction()).perform(sprite), state);
	}
	
	@Test
	public void testPlayCollisionSoundAction() {
		Sprite sprite = spritesData.get(0);
		eventActionHandler.executeAction(new Collision(),
				new PlayCollisionSoundAction(state));
		
		Assert.assertEquals((new ReflectAction()).perform(sprite), state);
	}
	
	@Test
	public void testVanishAction() {
		boolean x= sprite.isVanish();
		eventActionHandler.executeAction(new Collision(), new VanishAction());
		Assert.assertSame(x, sprite.isVanish());
	}

}

package main;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.gamemaker.action.Action;
import com.gamemaker.controllers.GameState;
import com.gamemaker.event.Collision;
import com.gamemaker.models.Sprite;

/**
 * 
 * @author Haroon Ashraf
 * 
 *         GameState maintains the state of the game. (win/loose/continue
 *         playing).
 * 
 *         This test case will act as placeholder that all collision events must
 *         support this option. Here we test that if any of the collision
 *         supported action is called with gamewin, then gamewin state must be
 *         returned.
 */
public class GameStateTest {
	Sprite sprite = new Sprite();

	@Before
	public void setUp() throws Exception {
		sprite = new Sprite();
	}

	private void performAction(GameState state) {
		for (Action action : new Collision(state).getAssociatedActions()) {
			Assert.assertEquals(state, action.perform(sprite));
		}
	}

	@Test
	public void testGameWin() {
		performAction(GameState.GAME_WIN);
	}

	@Test
	public void testGameLoose() {
		performAction(GameState.GAME_LOOSE);
	}

	@Test
	public void testGameContinue() {
		performAction(GameState.GAME_CONTINUE);
	}

}

package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class ScoreAction extends Action{

	public ScoreAction() {
		super(ActionNameConstants.SCORE);
	}
	
	public ScoreAction(GameState state) {
		super(ActionNameConstants.SCORE, state);
	}
	
	
	@Override
	public GameState perform(Sprite sprite) {
		sprite.setScore(true);
		return state;
	}
}

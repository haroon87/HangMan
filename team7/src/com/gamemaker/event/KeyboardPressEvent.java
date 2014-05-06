package com.gamemaker.event;

import java.util.ArrayList;
import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.action.DownMovementAction;
import com.gamemaker.action.FireAction;
import com.gamemaker.action.LeftMovementAction;
import com.gamemaker.action.RightMovementAction;
import com.gamemaker.action.UpMovementAction;

public class KeyboardPressEvent extends Event {

	public KeyboardPressEvent() {
		super(EventNameConstants.KEYBOARD_PRESS_EVENT);
	}

	public KeyboardPressEvent(String name) {
		this();
	}

	@Override
	public List<Action> getAssociatedActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new LeftMovementAction());
		actions.add(new UpMovementAction());
		actions.add(new RightMovementAction());
		actions.add(new DownMovementAction());
		actions.add(new FireAction());
		return actions;
	}

}

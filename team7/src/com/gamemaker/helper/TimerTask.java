
package com.gamemaker.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import com.gamemaker.controllers.Controller;
import com.gamemaker.controllers.DisplayController;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.controllers.ScoreCalculation;
import com.gamemaker.event.GameInitEvent;
import com.gamemaker.models.Sprite;

public class TimerTask {

	private final Timer t;
	private final Controller controller;
	private int gametimer;
	

	public TimerTask(Controller controller) {
		this.controller = controller;
		t = new Timer(10, new TimerTaskListener());
		
		
	}

	public void stop() {
		t.stop();
	}
	
	public void run() {
		controller.getEventActionHandler().executeEvent(new GameInitEvent());
		t.start();
		
	}
	
	public boolean isRunning(){
		return t.isRunning();
	}

	/*
	 * Action listener class for timer
	 */
	class TimerTaskListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			notifyObservers();
		}
	}

	public void notifyObservers() {

		// controller.getEventActionHandler().executeEventActionPairs();
	GameState state = controller.getEventActionHandler().executeEventActionPairs();
			
	
	
		handleGameState(state);
		
		
		List<Sprite> tempSpriteList = controller
				.getEventActionSpriteData();
		
		
		
		controller.getView().draw(tempSpriteList);
	}

	private void handleGameState(GameState state) {
		
		
		if(ScoreCalculation.scoreMethod=="Score: ")
		{
			String message = Integer.toString(ScoreCalculation.score);
			controller.getView().setScore(ScoreCalculation.scoreMethod+ "\n" + message);
		}
		
		if(gametimer == 2)
		{	
			
			ScoreCalculation.millseconds=ScoreCalculation.millseconds+1;
			if (ScoreCalculation.millseconds > 15)
			{
				ScoreCalculation.millseconds=0;
				ScoreCalculation.seconds=ScoreCalculation.seconds+1;
			}
			
			if (ScoreCalculation.seconds > 60)
			{	
				ScoreCalculation.millseconds=0;
				ScoreCalculation.seconds=0;
				ScoreCalculation.minutes=ScoreCalculation.minutes+1;
			}
			
			controller.getView().setTime(ScoreCalculation.minutes+":" + ScoreCalculation.seconds +":"+ ScoreCalculation.millseconds);
		}
		
		
		if(state == GameState.GAME_WIN){
			controller.getView().setMessage(GameConstants.GAME_WIN);
			this.stop();
		}
		if(state == GameState.GAME_LOOSE){
			controller.getView().setMessage(GameConstants.GAME_LOOSE);
			this.stop();
		}
	}

	public int getGametimer() {
		return gametimer;
	}

	public void setGametimer(int gametimer) {
		this.gametimer = gametimer;
	}

	

}

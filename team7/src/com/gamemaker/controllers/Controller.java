package com.gamemaker.controllers;

import java.util.ArrayDeque;
import java.util.List;

import org.apache.log4j.Logger;

import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.helper.DatabaseManager;
import com.gamemaker.helper.DirectoryScanForFiles;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.helper.ExportGameJson;
import com.gamemaker.helper.ImportGameJson;
import com.gamemaker.models.GameModel;
import com.gamemaker.models.Sprite;
import com.gamemaker.views.GamePlayView;

public abstract class Controller {
	private final Logger logger = Logger.getLogger(Controller.class);

	protected GameModel gameModel;
	protected final ExportGameJson exportGameJson;
	protected final ImportGameJson importGameJson;
	protected DatabaseManager dataBaseManager =  null;

	
	public abstract GamePlayView getView();

	public abstract EventActionHandler getEventActionHandler();

	public abstract List<Sprite> getEventActionSpriteData();

	public Controller() {
		dataBaseManager =  new DatabaseManager();

		this.gameModel = new GameModel();
		
		exportGameJson = new ExportGameJson(gameModel);
		importGameJson = new ImportGameJson(gameModel);
		
	}

	public GameModel getGameModel() {
		return gameModel;
		
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
		
	}

	public List<Sprite> getSpritesData() {
		return this.gameModel.getSpriteList();
	}
	
//	public boolean returnTimer()
	{
		//return this.gameModel.isTimer();
	}

	public void setSpritesData(List<Sprite> spritesData) {
		this.gameModel.setSpriteList(spritesData);
	}

	protected void loadGame(String gameName) {
		
		String loadGame = dataBaseManager.loadGameFromDatabase(gameName);
		
		
		try {
			importGameJson.importSpriteState(loadGame);
						
			
		} catch (UnsupportedEvent e) {
		
			e.printStackTrace();
		} catch (UnsupportedAction e) {
		
			e.printStackTrace();
		}
	}

	protected void saveGame(String gameName) {
		boolean isUpdated = false;
		if (dataBaseManager != null) {
			String gameJson = exportGameJson.exportSpriteState();	
			String[] games = dataBaseManager.savedGames();
			
			if(games.length == 0){
				dataBaseManager.saveGametoDataBase(gameJson ,gameName, isUpdated);
			}
			else{
			for(int i = 0 ; i< games.length ; i++){
				if(games[i].equalsIgnoreCase(gameName)){
					isUpdated = true;
					break;
				}	
			}
			
			if(isUpdated == true){
				dataBaseManager.saveGametoDataBase(gameJson ,gameName, isUpdated);
			}
			else
				dataBaseManager.saveGametoDataBase(gameJson ,gameName, isUpdated);

		
			}
			
		}

	}
	
}

package com.gamemaker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class has the structure of createdGame table
 * and is responsible for creating and updating the createdGame table
 * This table contains the game details of created games
 *
 */

@Entity
@Table(name = "tbl_createdGame")

public class CreatedGame {
	@Id
	private String gameName;
	private String gameMakerName;
	@Column(columnDefinition = "Text")
	private String gameJson;
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameMakerName() {
		return gameMakerName;
	}
	public void setGameMakerName(String gameMakerName) {
		this.gameMakerName = gameMakerName;
	}
	public String getGameJson() {
		return gameJson;
	}
	public void setGameJson(String gameJson) {
		this.gameJson = gameJson;
	}
}

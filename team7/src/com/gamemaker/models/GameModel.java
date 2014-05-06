package com.gamemaker.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel {

	private String name;;
	private List<Sprite> spriteList;
	private String bkgURL;
	private int timer;

	public GameModel() {
		spriteList = new CopyOnWriteArrayList<Sprite>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sprite> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(List<Sprite> spriteList) {
		this.spriteList = spriteList;
	}

	public String getBkgURL() {
		return bkgURL;
	}

	public void setBkgURL(String bkgURL) {
		this.bkgURL = bkgURL;
	}

	public int isTimer() {
		
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
		
	}

}

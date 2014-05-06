package com.gamemaker.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class has the structure of userDetails table and is responsible for creating and 
 * updating the userDetails table. this table has the details for users. 
 *
 */
@Entity
@Table(name = "tbl_userDetails")
public class UserDetails {
	@Id
	private String userId;
	private String userName;
	private String password;
	private String confirmPassword;
	private String emailID;
	private boolean IsGameMaker;
	private boolean IsGamePlayer;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public boolean isIsGameMaker() {
		return IsGameMaker;
	}

	public void setIsGameMaker(boolean isGameMaker) {
		IsGameMaker = isGameMaker;
	}

	public boolean isIsGamePlayer() {
		return IsGamePlayer;
	}

	public void setIsGamePlayer(boolean isGamePlayer) {
		IsGamePlayer = isGamePlayer;
	}

}

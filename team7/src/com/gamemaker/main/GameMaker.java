package com.gamemaker.main;

import com.gamemaker.controllers.Controller;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.helper.DatabaseReader;
import com.gamemaker.helper.DatabaseWriter;
import com.gamemaker.models.UserDetails;

public class GameMaker {
	public static DatabaseWriter databaseWriter;
	public static DatabaseReader databaseReader;
	public static UserDetails loggedInUser;
	public static void main(String[] args) {
		Controller controller = new MakerController();
		databaseWriter = new DatabaseWriter();
		databaseReader = new DatabaseReader();
		loggedInUser = new UserDetails();
	}
}
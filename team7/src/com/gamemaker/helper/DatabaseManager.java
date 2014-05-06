package com.gamemaker.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * This class contains the code to connect to the database 
 */

public class DatabaseManager {
	
	private final Logger logger = Logger.getLogger(DatabaseManager.class);
	Connection conn = null;
	private final String url = "jdbc:mysql://tintin.cs.indiana.edu:8099/p532_a7t7";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName = "a7t7";
	private final String password = "password";
	
	public void saveGametoDataBase(String gamejson, String gameName, boolean isUpdated){
		
		try {
		  Class.forName(driver).newInstance();
		  conn = DriverManager.getConnection(url,userName,password);
		  Statement st = conn.createStatement();
		  if(isUpdated == false){
			  st.executeUpdate("Insert into tbl_createdGame values('"+gameName+"','"+gamejson+"')");
		  }
		  else if(isUpdated == true)
		  {
			
			  st.executeUpdate("Update tbl_createdGame set gameJson = '"+gamejson +"' where gameName = '"+gameName+"'");
		  }
		  
		  conn.close();
		
		} 
		catch (Exception e) {
		    e.printStackTrace();
		
	}
		
	}
	
public String loadGameFromDatabase(String gameName){
		String result = null;
		try {
		  Class.forName(driver).newInstance();
		  conn = DriverManager.getConnection(url,userName,password);
		  Statement st = conn.createStatement();
		  ResultSet rs = st.executeQuery("select * from tbl_createdGame where gameName = '"+gameName+"'");
		while(rs.next()){
			result = rs.getObject("gameJson").toString();
		}
		
		  conn.close();
		
		} 
		catch (Exception e) {
		    logger.warn("Connection not established ");
		    e.printStackTrace();
		
	}
		return result;
		
	}

	public String[] savedGames(){
		String[] gameList = null;
		try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url,userName,password);
			  Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from tbl_createdGame");
			int rows = 0;
			rs.last();
		    rows = rs.getRow();
		    rs.beforeFirst();
			gameList =  new String[rows];
			int i = 0;
			while(rs.next()){
				gameList[i] = rs.getObject("gameName").toString(); 
				i++;
			}
			  conn.close();
			
			} 
			catch (Exception e) {
			    logger.warn("Connection not established ");
			    e.printStackTrace();
			
		}
		return gameList;
	}

}

package main;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.Test;

public class DatabaseManagerTest {
	Connection conn = null;
	private final String url = "jdbc:mysql://tintin.cs.indiana.edu:8099/p532_a7t7";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String userName = "a7t7";
	private final String password = "password";
	boolean isConnected = false;

	@Test
	public void test() {
		
		
		try {
			Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url,userName,password);
			if(conn != null ){
				isConnected =  true;
			}
			else
				isConnected = false;
			
			Assert.assertEquals(true, isConnected);
			
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void testLoadGameFromDatabase(){
		String result = null;
		String gameName = "FirstGame";
		boolean isExist = false;

		try {
			Class.forName(driver).newInstance();
			 conn = DriverManager.getConnection(url,userName,password);
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery("select * from savedGames where gameName = '"+gameName+"'");
			  	while(rs.next()){
			  		if(gameName.equalsIgnoreCase(rs.getObject("gameName").toString())){
			  			isExist = true;
			  		}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		Assert.assertEquals(true, isExist);
		
		
		
	}
	
	
	

}

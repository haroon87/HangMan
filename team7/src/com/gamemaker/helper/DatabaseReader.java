package com.gamemaker.helper;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gamemaker.models.CreatedGame;
import com.gamemaker.models.UserDetails;

/**
 * This class creates a session using session factory and 
 * reads the data from the database. This class is called
 * whenever database is accessed to read the data
 *  
 */
public class DatabaseReader {

	public UserDetails getRegisteredUserDetails(String userID)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails userDetails = (UserDetails) session.get(UserDetails.class, userID);
		session.close();
		return userDetails;
	}
	
	public int getVersionNo(String userId, String baseGameName)
	{
		int counter = 0;
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from CreatedGame";  
		Query query = session.createQuery(queryString); 
		ArrayList<CreatedGame> createdGames = (ArrayList<CreatedGame>)query.list();
		for(CreatedGame createdGame: createdGames)
		{
			String name = createdGame.getGameName().split("_")[0];
			if(name.equals(baseGameName))
			{
				counter++;
			}
		}
		return counter;
	}
}

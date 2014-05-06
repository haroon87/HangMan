package com.gamemaker.helper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gamemaker.models.CreatedGame;

/**
 * This class creates a session using session factory and 
 * writes the data in the database. This class is called
 * whenever there is a write operation to the database
 *  
 */
public class DatabaseWriter {
	public void write(Object obj)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(Object obj)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "delete from CreatedGame where gameName = :gameName";
        Query query = session.createQuery(hql);
		CreatedGame createdGame = (CreatedGame)obj;
		query.setString("gameName", createdGame.getGameName());
		int rowCount = query.executeUpdate();
		session.save(obj);
		session.getTransaction().commit();
		session.close();
	}
}

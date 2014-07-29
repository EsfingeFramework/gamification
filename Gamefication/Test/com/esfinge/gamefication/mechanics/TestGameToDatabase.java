package com.esfinge.gamefication.mechanics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.achievement.Tropy;


public class TestGameToDatabase {
	
	private Game game;
	private String user;
	private static Connection connection;
	
	@BeforeClass
	public static void initializeDatabase() throws Exception{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		connection = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
	}
	
	@Before
	public void initializeGame() throws Exception{
		game = new GameDatabaseStorage(connection);
		Statement s = connection.createStatement();
		s.execute("truncate table gamefication.points");
	    user = "Jaspion";
	}
	
	@Test
	public void addPoint(){
		Achievement p = new Point(10, "apoint");
		game.addAchievement(user, p);
		assertEquals(p, game.getAchievement(user, "apoint"));
	}
	
	@Test
	public void addTwoPoint() {
		Achievement a1 = new Point(20, "point");
		Achievement a2 = new Point(10, "point");		
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);	
		assertEquals(30,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
		assertEquals(1, game.getAchievements("Spider").size());
	}
	
	@Test
	public void addPointDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Point(10, "point");
		Achievement a2 = new Point(20, "point");
		game.addAchievement("Spider", a1);
		game.addAchievement(user2, a2);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(10,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
		assertEquals(20,((Point) game.getAchievement(user2, "point")).getQuantity().intValue());
	}
	
	@Test
	public void removePoint(){
		Achievement p = new Point(50, "point");
		game.addAchievement("Spider", p);
		game.removeAchievement("Spider", p);	
		assertEquals(0,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
	}
	
	@Test
	public void decreasePoint(){
		Achievement p = new Point(50, "point");
		Achievement r = new Point(30, "point");
		game.addAchievement("Spider", p);
		game.removeAchievement("Spider", r);	
		assertEquals(20,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
	}
	
	@Test
	public void removePointDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Point(10, "point");
		Achievement a2 = new Point(20, "point");
		game.addAchievement("Spider", a1);
		game.addAchievement(user2, a2);
		game.removeAchievement(user2, a1);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(10,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
		assertEquals(10,((Point) game.getAchievement(user2, "point")).getQuantity().intValue());
	}
	
}


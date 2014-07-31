package com.esfinge.gamefication.mechanics;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.mechanics.GameDatabaseStorage;


public class TestGameToDatabase {
	
	private static Game game;
	private String user;
	private static Connection connection;
	
	@BeforeClass
	public static void initializeDatabase() throws Exception{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		connection = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
		game = new GameDatabaseStorage(connection);
	}
	
	@Before
	public void initializeGame() throws Exception{
		
		Statement s = connection.createStatement();
		s.execute("truncate table gamification.users");
		s.execute("truncate table gamification.points");
		s.execute("truncate table gamification.ranking");
		s.execute("truncate table gamification.reward");
		s.execute("truncate table gamification.trophy");
	    user = "Jaspion";
	}
	
	@Test
	public void addRanking(){
		Achievement r = new Ranking("mago", "master");
		game.addAchievement(user, r);
		assertEquals(r, (Ranking) game.getAchievement(user, "mago"));
	}
	
	@Test
	public void addTwoRank() {
		Achievement r1 = new Ranking("mago", "noob");
		Achievement r2 = new Ranking("mago", "master");		
		game.addAchievement("Spider", r1);
		game.addAchievement("Spider", r2);	
		assertEquals(r2, (Ranking) game.getAchievement("Spider", "mago"));
		//assertEquals(1, game.getAchievements("Spider").size());
		
	}
	
	@Test
	public void addRankDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement r1 = new Ranking("mago", "master");
		Achievement r2 = new Ranking("mago2", "noob");
		game.addAchievement("Spider", r1);
		game.addAchievement(user2, r2);
		//assertEquals(1, game.getAchievements("Spider").size());
		//assertEquals(1, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(r2, (Ranking) game.getAchievement(user2, "mago2"));
	}
	
	@Test
	public void removeRank(){
		Achievement r = new Ranking("mago", "master");
		game.addAchievement("Spider", r);
		game.removeAchievement("Spider", r);	
		assertEquals(null, (Ranking) game.getAchievement("Spider", "mago"));
	}
	
	@Test
	public void removeRankDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement r1 = new Ranking("mago", "master");
		Achievement r2 = new Ranking("mago2", "noob");
		game.addAchievement("Spider", r1);
		game.addAchievement(user2, r2);
		game.removeAchievement(user2, r2);
		//assertEquals(1, game.getAchievements("Spider").size());
		//assertEquals(0, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(null, (Ranking) game.getAchievement(user2, "mago2"));
		
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


package net.sf.esfinge.gamification.mechanics.sql;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.mechanics.FakeUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameDatabaseStorage;


public class TestPointToDatabase {
	
	private static Game game;
	private String user;
	private static Connection connection;
	
	@BeforeClass
	public static void initializeDatabase() throws Exception{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		connection = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
		game = new GameDatabaseStorage(connection);
	}
	
	@AfterClass
	public static void closeDatabase() throws Exception {
		connection.close();
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
		assertEquals(null,((Point) game.getAchievement("Spider", "point")));
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


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
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.FakeUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameDatabaseStorage;


public class TestTrophyToDatabase {
	
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
	public void addTrophy(){
		Achievement p = new Trophy("trophy");
		game.addAchievement(user, p);
		assertEquals(p, game.getAchievement(user, "trophy"));
	}
	
	@Test
	public void addTwoTrophy() {
		Achievement a1 = new Trophy("trophy");
		Achievement a2 = new Trophy("trophy");		
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);	
		assertEquals(1, game.getAchievements("Spider").size());
	}
	
	@Test
	public void addTrophyDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Trophy("trophy");
		game.addAchievement("Spider", a1);
		game.addAchievement(user2, a1);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(1, game.getAchievements(user2).size());
	}
	
	@Test
	public void removeTrophy(){
		Achievement p = new Trophy("trophy");
		game.addAchievement("Spider", p);
		game.removeAchievement("Spider", p);	
		assertEquals(null,((Trophy) game.getAchievement("Spider", "trophy")));
	}

	
	@Test
	public void removeTrophyDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Trophy("trophy");
		Achievement a2 = new Trophy("trophy");
		game.addAchievement("Spider", a1);
		game.addAchievement(user2, a2);
		game.removeAchievement(user2, a1);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(0, game.getAchievements(user2).size());
	}
	
	
	
}


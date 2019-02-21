package net.sf.esfinge.gamification.mechanics.sql;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.mechanics.FakeUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameDatabaseStorage;


public class TestRankingToDatabase {
	
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
		assertEquals(1, game.getAchievements("Spider").size());
		
	}
	
	@Test
	public void addRankDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement r1 = new Ranking("mago", "master");
		Achievement r2 = new Ranking("mago2", "noob");
		game.addAchievement("Spider", r1);
		game.addAchievement(user2, r2);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(r2, (Ranking) game.getAchievement(user2, "mago2"));
	}
	
	@AfterClass
	public static void closeDatabase() throws Exception {
		connection.close();
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
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(0, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(null, (Ranking) game.getAchievement(user2, "mago2"));
		
	}
	
}


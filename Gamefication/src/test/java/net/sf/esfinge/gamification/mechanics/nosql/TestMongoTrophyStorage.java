package net.sf.esfinge.gamification.mechanics.nosql;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMongoStorage;


public class TestMongoTrophyStorage {
	
	private MongoClient client;
	private static Game game;
	private String user, user2;
	
	@Before
	public void initializeGame() throws Exception{
		
		client = new MongoClient();
		game = new GameMongoStorage(client.getDatabase("local"));
	    user = "Jiraia";
	    user2 = "Spider";
	}
	
	@Test
	public void addTrophy(){
		Achievement p = new Trophy("trophy");
		game.addAchievement(user, p);
		assertEquals(p, game.getAchievement(user, "trophy"));
		game.deleteAchievement(user, p);
	}
	
	@Test
	public void addTwoTrophy() {
		Achievement a1 = new Trophy("trophy");
		Achievement a2 = new Trophy("trophy");		
		game.addAchievement(user2, a1);
		game.addAchievement(user2, a2);	
		assertEquals(1, game.getAchievements(user2).size());
		game.deleteAchievement(user2, a1);
		game.deleteAchievement(user2, a2);	
		
	}
	
	@Test
	public void addTrophyDifferentUser(){
		Achievement a1 = new Trophy("trophy");
		game.addAchievement(user2, a1);
		game.addAchievement(user2, a1);
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(1, game.getAchievements(user2).size());
		game.deleteAchievement(user2, a1);
		game.deleteAchievement(user2, a1);
	}
	
	@Test
	public void removeTrophy(){
		Achievement p = new Trophy("trophy");
		game.addAchievement(user2, p);
		game.removeAchievement(user2, p);	
		assertEquals(null,((Trophy) game.getAchievement(user2, "trophy")));
	}

	
	@Test
	public void removeTrophyDifferentUser(){
		Achievement a1 = new Trophy("trophy");
		Achievement a2 = new Trophy("trophy");
		game.addAchievement(user2, a1);
		game.addAchievement(user, a2);
		game.removeAchievement(user2, a1);
		assertEquals(1, game.getAchievements(user).size());
		assertEquals(0, game.getAchievements(user2).size());
		game.deleteAchievement(user2, a2);
	}
	
	
	
}


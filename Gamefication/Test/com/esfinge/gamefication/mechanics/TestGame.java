package com.esfinge.gamefication.mechanics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Rank;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.mechanics.GameMemoryStorage;
import com.esfinge.gamification.user.UserStorage;


public class TestGame {
	
	private Game game;
	
	@Before
	public void initializeGame(){
		
		game = new GameMemoryStorage();
	    UserStorage.setUserID("Spider");
	}
	
	@Test
	public void addRank() {
		Achievement a = new Rank("Noob", "Level 1");
		game.addAchievement("Spider", a);
		assertEquals(1, game.getAchievements("Spider").size());		
	}
	
	@Test
	public void removeRank() {
		Achievement a = new Rank("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.removeAchievement("Spider", a);
		assertNull(game.getAchievement("Spider",a.getName()));
	}
	
	@Test
	public void addTwoRank() {
		Achievement a = new Rank("Noob", "Level 1");
		Achievement a1 = new Rank("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.addAchievement("Spider", a1);
		assertEquals(1, game.getAchievements("Spider").size());	
	}
	
	@Test
	public void addRankDifferentUser(){
		UserStorage.setUserID("Duende");
		Achievement a = new Rank("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.addAchievement("Duende", a);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(1, game.getAchievements("Duende").size());	
	}
	
	@Test
	public void addTropy() {
		Achievement a = new Trophy("champion");
		game.addAchievement("Spider", a);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(a, game.getAchievement("Spider", "champion"));
	}
	
	@Test
	public void removeTropy(){
		Achievement a = new Trophy("Golden");
		game.addAchievement("Spider", a);
		game.removeAchievement("Spider", a);
		assertNull(game.getAchievement("Spider", "Golden").getName());	
		//getAchievements continua contendo 1 elemento ???
	}
	
	@Test
	public void addTwoTropy() {
		Achievement a1 = new Trophy("champion");
		Achievement a2 = new Trophy("champion");		
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);		
		assertEquals(1, game.getAchievements("Spider").size());
	}
	
	
	@Test
	public void addTropyDifferentUser(){
		UserStorage.setUserID("Duende");
		Achievement a1 = new Trophy("champion");
		Achievement a2 = new Trophy("champion");
		game.addAchievement("Spider", a1);
		game.addAchievement("Duende", a2);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(1, game.getAchievements("Duende").size());	
	}
	
	@Test
	public void addPoint(){
		Achievement p = new Point(10, "point");
		game.addAchievement("Spider", p);
		assertEquals(p, game.getAchievement("Spider", "point"));
		assertEquals(10,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
	}
	
	@Test
	public void removePoint(){
		Achievement p = new Point(50, "point");
		game.addAchievement("Spider", p);
		game.removeAchievement("Spider", p);	
		assertEquals(0,((Point) game.getAchievement("Spider", "point")).getQuantity().intValue());
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
	public void addReward() {
		Achievement a = new Reward("lunch",false);
		game.addAchievement("Spider", a);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(a, game.getAchievement("Spider", a.getName()));
		assertEquals(false, ((Reward) game.getAchievement("Spider", a.getName())).isUsed()); 
	}
	
	@Test
	public void addTwoReward() {
		Achievement a1 = new Reward("lunch",false);
		Achievement a2 = new Reward("lunch",false);
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);	
		assertEquals(1, game.getAchievements("Spider").size());
	}
	
	@Test
	public void addRewardDifferentUser() {
		UserStorage.setUserID("Duende");
		Achievement r1 = new Reward("worth Buying",false);
		Achievement r2 = new Reward("lunch",false);
		game.addAchievement("Spider", r1);
		game.addAchievement("Duende", r2);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(1, game.getAchievements("Duende").size());
	}
	
	@Test
	public void usingReward() {
		Achievement a = new Reward("lunch", false);
		game.addAchievement("Spider", a);
		game.removeAchievement("Spider", a);
		assertEquals(true, ((Reward) game.getAchievement("Spider", a.getName())).isUsed());
		
	}
	
	@Test
	public void addDifferentAchievement(){
		Achievement a1 = new Point(2, "point");
		Achievement a2 = new Trophy("Golden");
		Achievement a3 = new Reward("lunch",false);
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);
		game.addAchievement("Spider", a3);
		assertEquals(3, game.getAchievements("Spider").size());
	}
	
	@Test
	public void addListener(){
		AchievementListenerMock mock = new AchievementListenerMock();
		Achievement a2 = new Point(10,"point");
		game.addListener(mock);
		game.addAchievement("user",a2);
		mock.verifyReceivedAchievements("user",a2);
	}
	
	@Test
	public void addMoreThanOneListener(){
		Achievement a1 = new Point(20,"point");
		AchievementListenerMock mock1 = new AchievementListenerMock();
		AchievementListenerMock mock2 = new AchievementListenerMock();
		game.addListener(mock1);
		game.addListener(mock2);
		game.addAchievement("user", a1);
		mock1.verifyReceivedAchievements("user", a1);
		mock2.verifyReceivedAchievements("user", a1);	
	}
	
	
}


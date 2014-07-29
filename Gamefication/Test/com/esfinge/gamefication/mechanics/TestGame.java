package com.esfinge.gamefication.mechanics;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.achievement.Tropy;


public class TestGame {
	
	private Game game;
	private FakeUser user;
	
	@Before
	public void initializeGame(){
		
		game = new GameMemoryStorage();
	    user = new FakeUser("Jaspion");
	}
	
	@Test
	public void addTropy() {
		Achievement a = new Tropy("champion");
		game.addAchievement(user, a);
		assertEquals(a, game.getAchievement(user, "champion"));
	}
	
	@Test
	public void removeTropy(){
		Achievement a = new Tropy("Golden");
		game.addAchievement(user, a);
		game.removeAchievement(user, a);
		System.out.println(game.getAchievements(user));
		assertNull((Tropy) game.getAchievement(user, "Golden"));	
	}
	
	@Test
	public void addTwoTropy() {
		Achievement a1 = new Tropy("champion");
		Achievement a2 = new Tropy("champion");		
		game.addAchievement(user, a1);
		game.addAchievement(user, a2);		
		assertEquals(1, game.getAchievements(user).size());
	}
	
	@Test
	public void addTropyDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Tropy("champion");
		Achievement a2 = new Tropy("champion");
		game.addAchievement(user, a1);
		game.addAchievement(user2, a2);
		assertEquals(1, game.getAchievements(user).size());	
	}
	
	@Test
	public void addPoint(){
		Achievement p = new Point(10, "point");
		game.addAchievement(user, p);
		assertEquals(p, game.getAchievement(user, "point"));
		assertEquals(10,((Point) game.getAchievement(user, "point")).getQuantity().intValue());
	}
	
	@Test
	public void removePoint(){
		Achievement p = new Point(50, "point");
		game.addAchievement(user, p);
		game.removeAchievement(user, p);		
		assertEquals(0,((Point) game.getAchievement(user, "point")).getQuantity().intValue());
	}
	
	@Test
	public void addTwoPoint() {
		Achievement a1 = new Point(20, "point");
		Achievement a2 = new Point(10, "point");		
		game.addAchievement(user, a1);
		game.addAchievement(user, a2);		
		assertEquals(30,((Point) game.getAchievement(user, "point")).getQuantity().intValue());
	}
	
	@Test
	public void addPointDifferentUser(){
		FakeUser user2 = new FakeUser("Jiraia");
		Achievement a1 = new Point(10, "point");
		Achievement a2 = new Point(20, "point");
		game.addAchievement(user, a1);
		game.addAchievement(user2, a2);
		assertEquals(1, game.getAchievements(user).size());
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(10,((Point) game.getAchievement(user, "point")).getQuantity().intValue());
		assertEquals(20,((Point) game.getAchievement(user2, "point")).getQuantity().intValue());
	}
	
	@Test
	public void addReward() {
		Achievement a = new Reward("lunch");
		game.addAchievement(user, a);
		assertEquals(a, game.getAchievement(user, "lunch"));
	}
	
	@Test
	public void addTwoReward() {
		Achievement a1 = new Reward("lunch");
		Achievement a2 = new Reward("lunch");
		game.addAchievement(user, a1);
		game.addAchievement(user, a2);		
		assertEquals(1, game.getAchievements(user).size());
	}
	
	@Test
	public void addRewardDifferentUser(){
		FakeUser user2 = new FakeUser("Batman");
		Achievement r1 = new Reward("worth Buying");
		Achievement r2 = new Reward("lunch");
		game.addAchievement(user, r1);
		game.addAchievement(user2, r2);
		assertEquals(1, game.getAchievements(user).size());	
	}
	
	@Test
	public void addDifferentAchievement(){
		Achievement a1 = new Point(2, "point");
		Achievement a2 = new Tropy("Golden");
		Achievement a3 = new Reward("lunch");
		game.addAchievement(user, a1);
		game.addAchievement(user, a2);
		game.addAchievement(user, a3);
		assertEquals(3, game.getAchievements(user).size());
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


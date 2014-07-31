package com.esfinge.gamefication.mechanics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.mechanics.GameFileStorage;
import com.esfinge.gamification.mechanics.GameMemoryStorage;


public class TestGameFileStorage {
	

	private FakeUser user, user2;
	private Game gameFile;
		
	@Before
	public void initializeGame(){
	    user = new FakeUser("Megazord");
	    user2 = new FakeUser("Jiraia");
	    gameFile = new GameFileStorage();
	    
	}

	@Test
	public void addPointProperties(){
		Achievement p = new Point(200, "moedas");
		gameFile.addAchievement(user, p);
		gameFile.addAchievement(user2, p);
		
		Achievement n = new Point(500, "placar");
		gameFile.addAchievement(user, n);
		gameFile.addAchievement(user2, n);
		
		Achievement r = new Ranking("reputacao", "platinum");
		gameFile.addAchievement(user, r);
		gameFile.addAchievement(user2, r);
		
		Achievement rw = new Reward("cupom");
		gameFile.addAchievement(user, rw);
		gameFile.addAchievement(user2, rw);
		
		Achievement tr = new Trophy("Gold");
		gameFile.addAchievement(user, tr);		
		
		Achievement r2 = new Ranking("Gold Trophy", "xxx");
		gameFile.addAchievement(user2, r2);		
	
	}
	
	
	@Test
	public void getAchievementsProperties() {
		gameFile.getAchievements(user2);
		
	}
		
	@Test
	public void getAchievementProperties() {
		gameFile.getAchievement(user2, "placar");
	}
	
	@After
	public void removePointFile(){
		Achievement p = new Point(200, "moedas");
		gameFile.removeAchievement(user, p);
	}
	
	
}


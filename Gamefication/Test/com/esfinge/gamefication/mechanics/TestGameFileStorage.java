package com.esfinge.gamefication.mechanics;

import junit.framework.Assert;

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
	
	private Game game;
	private FakeUser user;
	private Game gameFile;
		
	@Before
	public void initializeGame(){
		
		game = new GameMemoryStorage();
	    user = new FakeUser("Jaspion");
	    gameFile = new GameFileStorage();
	    
	}

	/*@Test
	public void addPointProperties(){
		Achievement p = new Point(200, "moedas");
		gameFile.addAchievement(user, p);
		
		Achievement n = new Point(500, "placar");
		gameFile.addAchievement(user, n);
		
		Achievement r = new Ranking("reputacao", "platinum");
		gameFile.addAchievement(user, r);
		
		Achievement rw = new Reward("cupom");
		gameFile.addAchievement(user, rw);
		
		Achievement tr = new Trophy("Gold");
		gameFile.addAchievement(user, tr);		
	
	}*/
	
	@Test
	public void removePointFile(){
		Achievement p = new Point(200, "moedas");
		gameFile.removeAchievement(user, p);
	}
	
	/*@Test
	public void getAchievements() {
		gameFile.getAchievements(user);
		
	}*/
	
}


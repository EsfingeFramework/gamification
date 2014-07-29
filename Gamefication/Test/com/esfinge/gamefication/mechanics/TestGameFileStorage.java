package com.esfinge.gamefication.mechanics;

import org.junit.Before;
import org.junit.Test;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.achievement.Rank;
import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.achievement.Tropy;


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

	@Test
	public void addPointProperties(){
		Achievement p = new Point(200, "moedas");
		gameFile.addAchievement(user, p);
		
		Achievement n = new Point(500, "placar");
		gameFile.addAchievement(user, n);
		
		Achievement r = new Rank("reputacao", "platinum");
		gameFile.addAchievement(user, r);
		
		Achievement rw = new Reward("cupom");
		gameFile.addAchievement(user, rw);
		
		Achievement tr = new Tropy("Gold");
		gameFile.addAchievement(user, tr);
	}
	
	/*@Test
	public void removePointFile(){
		Achievement p = new Point(200, "moedas");
		gameFile.removeAchievement(user, p);
	}*/
	
	@Test
	public void getAchievements() {
		gameFile.getAchievements(user);
		
	}
	
}


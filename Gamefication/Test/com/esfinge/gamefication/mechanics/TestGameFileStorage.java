package com.esfinge.gamefication.mechanics;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.mechanics.GameFileStorage;


public class TestGameFileStorage {
	

	private FakeUser user;
	private Game gameFile;
		
	@Before
	public void initializeGame() throws IOException{
	    
	    Properties props = new Properties();
		FileInputStream inputFile = new FileInputStream("./properties/achievements.properties");
		props.load(inputFile);
		props.clear();
		FileOutputStream outputFile = new FileOutputStream("./properties/achievements.properties");
		props.store(outputFile, null);
		outputFile.close();
		
		user = new FakeUser("Megazord");
	    gameFile = new GameFileStorage(); 
	}


	@Test
	public void addPointProperties(){
		Achievement p = new Point(200, "moedas");
		gameFile.addAchievement(user, p);
		assertEquals(p.getName(), gameFile.getAchievement(user, p.getName()).getName());
		assertEquals(200, ((Point)gameFile.getAchievement(user, p.getName())).getQuantity().intValue());
	}
	
	@Test
	public void addRankingProperties(){		
		Achievement r = new Ranking("reputacao", "platinum");
		gameFile.addAchievement(user, r);
		assertEquals(r.getName(), gameFile.getAchievement(user, r.getName()).getName());
		assertEquals(((Ranking)r).getLevel(), ((Ranking)gameFile.getAchievement(user, r.getName())).getLevel());
	}
	
	@Test
	public void addRewardProperties(){		
		Achievement rw = new Reward("cupom");
		gameFile.addAchievement(user, rw);
		assertEquals(rw.getName(), gameFile.getAchievement(user, rw.getName()).getName());
		assertEquals(false, ((Reward)gameFile.getAchievement(user, rw.getName())).isUsed());
	}
		
	@Test
	public void addTrophyProperties(){		
		Achievement tr = new Trophy("Gold");
		gameFile.addAchievement(user, tr);	
		assertEquals(tr.getName(), gameFile.getAchievement(user, tr.getName()).getName());
	}
	
	
	@Test
	public void removePointsFile(){
		Achievement p = new Point(200, "moedas");
		Achievement p2 = new Point(100, "moedas");
		gameFile.addAchievement(user, p);
		assertEquals(p.getName(), gameFile.getAchievement(user, p.getName()).getName());
		assertEquals(200, ((Point)gameFile.getAchievement(user, p.getName())).getQuantity().intValue());
		gameFile.removeAchievement(user, p2);
		assertEquals(100, ((Point)gameFile.getAchievement(user, p.getName())).getQuantity().intValue());
	}
	
	@Test
	public void removeAllPointsFile(){
		Achievement p = new Point(200, "moedas");
		gameFile.addAchievement(user, p);
		assertEquals(p.getName(), gameFile.getAchievement(user, p.getName()).getName());
		assertEquals(200, ((Point)gameFile.getAchievement(user, p.getName())).getQuantity().intValue());
		gameFile.removeAchievement(user, p);
		assertNull(gameFile.getAchievement(user, p.getName()));
	}
	
	@Test
	public void removeRankingProperties(){		
		Achievement r = new Ranking("reputacao", "platinum");
		gameFile.addAchievement(user, r);
		assertEquals(r.getName(), gameFile.getAchievement(user, r.getName()).getName());
		assertEquals(((Ranking)r).getLevel(), ((Ranking)gameFile.getAchievement(user, r.getName())).getLevel());
		gameFile.removeAchievement(user, r);
		assertNull(gameFile.getAchievement(user, r.getName()));
		
	}
	
	@Test
	public void removeRewardProperties(){		
		Achievement rw = new Reward("cupom");
		gameFile.addAchievement(user, rw);
		assertEquals(rw.getName(), gameFile.getAchievement(user, rw.getName()).getName());
		assertEquals(false, ((Reward)gameFile.getAchievement(user, rw.getName())).isUsed());
		gameFile.removeAchievement(user, rw);
		assertEquals(true, ((Reward)gameFile.getAchievement(user, rw.getName())).isUsed());
	}
	
	@Test
	public void removeTrophyProperties(){		
		Achievement tr = new Trophy("Gold");
		gameFile.addAchievement(user, tr);	
		assertEquals(tr.getName(), gameFile.getAchievement(user, tr.getName()).getName());
		gameFile.removeAchievement(user, tr);
		assertNull(gameFile.getAchievement(user, tr.getName()));
		
	}
	
	
}


package net.sf.esfinge.gamification.mechanics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.user.UserStorage;


public class TestGame {
	
	private Game game;
	
	@Before
	public void initializeGame(){
		
		game = new GameMemoryStorage();
	    UserStorage.setUserID("Spider");
	}
	
	@Test
	public void addRankinkg() {
		Achievement a = new Ranking("Noob", "Level 1");
		game.addAchievement("Spider", a);
		assertEquals(1, game.getAchievements("Spider").size());		
	}
	
	@Test
	public void removeRankinkg() {
		Achievement a = new Ranking("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.removeAchievement("Spider", a);
		assertNull(((Ranking)game.getAchievement("Spider",a.getName())));
	}
	
	@Test
	public void addTwoRankinkg() {
		Achievement a = new Ranking("Noob", "Level 1");
		Achievement a1 = new Ranking("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.addAchievement("Spider", a1);
		assertEquals(1, game.getAchievements("Spider").size());	
	}
	
	@Test
	public void addRankingDifferentUser(){
		UserStorage.setUserID("Duende");
		Achievement a = new Ranking("Noob", "Level 1");
		game.addAchievement("Spider", a);
		game.addAchievement("Duende", a);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(1, game.getAchievements("Duende").size());	
	}
	
	@Test(expected = RuntimeException.class)
	public void launchExceptionRanking() {
		Achievement a1 = new Ranking("Noob","Level 1");
		Achievement a2 = new Ranking("Master", "Level 999");
		a1.incrementAchievement(a2);
	}
	
	@Test
	public void addTrophy() {
		Achievement a = new Trophy("champion");
		game.addAchievement("Spider", a);
		assertEquals(1, game.getAchievements("Spider").size());	
		assertEquals(a, game.getAchievement("Spider", "champion"));
	}
	
	@Test
	public void removeTrophy(){
		Achievement a = new Trophy("Golden");
		game.addAchievement("Spider", a);
		game.removeAchievement("Spider", a);
		assertNull(game.getAchievement("Spider", "Golden"));	
	}
	
	@Test
	public void addTwoTrophy() {
		Achievement a1 = new Trophy("champion");
		Achievement a2 = new Trophy("champion");		
		game.addAchievement("Spider", a1);
		game.addAchievement("Spider", a2);		
		assertEquals(1, game.getAchievements("Spider").size());
	}
	
	@Test
	public void addTrophyDifferentUser(){
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
		assertNull(game.getAchievement("Spider", "point"));
	}
	
	@Test
	public void updatePoint(){
		Achievement p = new Point(50, "point");
		Achievement p2 = new Point(30, "point");
		game.addAchievement("Spider", p);
		game.removeAchievement("Spider", p2);	
		assertEquals(20, ((Point)game.getAchievement("Spider", "point")).getQuantity().intValue());
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
	
	
	@Test(expected = RuntimeException.class)
	public void launchExceptionPoint() {
		Achievement a1 = new Point(1, "happy");
		Achievement a2 = new Point(1, "hungry");
		a1.incrementAchievement(a2);
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
	
	@Test(expected = RuntimeException.class)
	public void launchExceptionReward() {
		Achievement a1 = new Reward("lunch");
		Achievement a2 = new Reward("coffee");
		a1.incrementAchievement(a2);
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


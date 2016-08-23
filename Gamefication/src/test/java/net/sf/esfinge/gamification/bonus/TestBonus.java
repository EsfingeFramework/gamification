package net.sf.esfinge.gamification.bonus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.proxy.ITestPointAnn;
import net.sf.esfinge.gamification.proxy.TestPointAnnotation;
import net.sf.esfinge.gamification.user.UserStorage;

public class TestBonus {
	
	Game game;
	ITestPointAnn p;
	
	@Before
	public void setupGame(){
		UserStorage.setUserID("Spider");
		p = GameProxy.createProxy(new TestPointAnnotation());
		game = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(game);
		game.addBonus(new Trophy("BONUS"))
			.when((Point a, Object user) -> a.getName().equals("GOLD") && a.getQuantity()>=2000);
	}
	
	@Test
	public void testPointAbove2000winTrophy() {
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
	}

	@Test
	public void testPointBelow2000losesTrophy() {
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		
		p.doRemoveSomething();
		ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(1500), ((Point) ach).getQuantity());
		bonus = game.getAchievement("Spider", "BONUS");
		assertNull(bonus);
	}
	@Test
	public void testPointBelow2000hasNoTrophy() {
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(1000), ((Point) ach).getQuantity());
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNull(bonus);
	}
	
	@Test
	public void testTwoTrophy(){
		game.addBonus(new Trophy("BONUS2"))
		.when((Point a, Object user) -> a.getName().equals("GOLD") && a.getQuantity()>=2000);

		p.doSomething();
		p.doSomething();
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		Achievement bonus2 = game.getAchievement("Spider", "BONUS2");
		assertNotNull(bonus2);
	}
	@Test
	public void testTwoTrophyDifferentThreshould(){
		game.addBonus(new Trophy("BONUS2"))
		.when((Point a, Object user) -> a.getName().equals("GOLD") && a.getQuantity()>=1500);

		p.doSomething();
		p.doSomething();
		p.doRemoveSomething();
		
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNull(bonus);
		Achievement bonus2 = game.getAchievement("Spider", "BONUS2");
		assertNotNull(bonus2);
	}
}

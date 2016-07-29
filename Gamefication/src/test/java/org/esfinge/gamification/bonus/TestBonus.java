package org.esfinge.gamification.bonus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.achievement.Point;
import org.esfinge.gamification.achievement.Trophy;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.mechanics.GameMemoryStorage;
import org.esfinge.gamification.proxy.GameInvoker;
import org.esfinge.gamification.proxy.GameProxy;
import org.esfinge.gamification.proxy.ITestPointAnn;
import org.esfinge.gamification.proxy.TestPointAnnotation;
import org.esfinge.gamification.user.UserStorage;
import org.junit.Before;
import org.junit.Test;

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
			.whenAchievementClassIs(Point.class) 
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

}

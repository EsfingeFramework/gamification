package org.esfinge.gamification.bonus;

import org.esfinge.gamification.achievement.Point;
import org.esfinge.gamification.achievement.Trophy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.mechanics.GameMemoryStorage;
import org.esfinge.gamification.proxy.GameInvoker;
import org.esfinge.gamification.proxy.GameProxy;
import org.esfinge.gamification.proxy.ITestPointAnn;
import org.esfinge.gamification.proxy.ITestRankingAnn;
import org.esfinge.gamification.proxy.ITestRewardAnn;
import org.esfinge.gamification.proxy.ITestTrophiesAnn;
import org.esfinge.gamification.proxy.TestPointAnnotation;
import org.esfinge.gamification.proxy.TestRankingAnnotation;
import org.esfinge.gamification.proxy.TestRewardAnnotation;
import org.esfinge.gamification.proxy.TestTrophiesAnnotation;
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
		game.addBonus(
				new Trophy("BONUS"), 
				Point.class, 
				(Point a, Object user) -> a.getName().equals("GOLD") && a.getQuantity()>=2000
		);
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

}

package event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.achievement.Point;
import org.esfinge.gamification.annotation.TrophiesToUser;
import org.esfinge.gamification.event.GamificationListener;
import org.esfinge.gamification.event.WhenReachPoints;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.mechanics.GameMemoryStorage;
import org.esfinge.gamification.proxy.GameInvoker;
import org.esfinge.gamification.proxy.GameProxy;
import org.esfinge.gamification.proxy.ITestPointAnn;
import org.esfinge.gamification.proxy.TestPointAnnotation;
import org.esfinge.gamification.user.UserStorage;
import org.junit.Before;
import org.junit.Test;

public class TestEvent {
	Game game;
	ITestPointAnn p;

	@GamificationListener
	public class EventBonusConfig {
		
		public boolean executed=false;
		
		@WhenReachPoints(type="GOLD", value=2000)
		@TrophiesToUser(name="BONUS")
		public void winTrophy(){
			executed=true;
		}
	}
	@Before
	public void setupGame(){
		UserStorage.setUserID("Spider");
		p = GameProxy.createProxy(new TestPointAnnotation());
		game = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(game);
	}
	
	@Test
	public void testPointAbove2000winTrophy() {
		EventBonusConfig c = new EventBonusConfig();
		game.addEventListeners(c);
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		assertTrue(c.executed);
	}

	//@Test
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
	//@Test
	public void testPointBelow2000hasNoTrophy() {
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(1000), ((Point) ach).getQuantity());
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNull(bonus);
	}

	
	//Outra classe de config
	@GamificationListener
	public class EventBonusConfig2 {
		
		public boolean executed=false;
		
		@WhenReachPoints(type="GOLD", value=2000)
		@TrophiesToUser(name="BONUS2")
		public void winTrophy(){
			executed=true;
		}
	}
	
	@Test
	public void testTwoTrophy(){
		EventBonusConfig c = new EventBonusConfig();
		game.addEventListeners(c);
		EventBonusConfig2 c2 = new EventBonusConfig2();
		game.addEventListeners(c2);
		
		p.doSomething();
		p.doSomething();
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		Achievement bonus2 = game.getAchievement("Spider", "BONUS2");
		assertNotNull(bonus2);
		assertTrue(c.executed);
		assertTrue(c2.executed);
	}

	//Outra classe de config
	@GamificationListener
	public class EventBonusConfig3 {
		
		public boolean executed=false;
		
		@WhenReachPoints(type="GOLD", value=1500)
		@TrophiesToUser(name="BONUS3")
		public void winTrophy(){
			executed=true;
		}
	}
	
	@Test
	public void testTwoTrophyDifferentThreshould(){
		EventBonusConfig c = new EventBonusConfig();
		game.addEventListeners(c);
		EventBonusConfig3 c3 = new EventBonusConfig3();
		game.addEventListeners(c3);
		
		p.doSomething();
		p.doSomething();
		
		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		Achievement bonus2 = game.getAchievement("Spider", "BONUS3");
		assertNotNull(bonus2);
		assertTrue(c.executed);
		assertTrue(c3.executed);
	}

}

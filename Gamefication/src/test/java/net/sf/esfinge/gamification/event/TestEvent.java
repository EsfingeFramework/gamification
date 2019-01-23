package net.sf.esfinge.gamification.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.TrophiesToUser;
import net.sf.esfinge.gamification.event.annotation.GamificationListener;
import net.sf.esfinge.gamification.event.annotation.WhenReachPoints;
import net.sf.esfinge.gamification.event.annotation.WhenReachRanking;
import net.sf.esfinge.gamification.event.annotation.WhenUseReward;
import net.sf.esfinge.gamification.event.annotation.WhenWinReward;
import net.sf.esfinge.gamification.event.annotation.WhenWinTrophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.proxy.ITestPointAnn;
import net.sf.esfinge.gamification.proxy.ITestRankingAnn;
import net.sf.esfinge.gamification.proxy.ITestRewardAnn;
import net.sf.esfinge.gamification.proxy.ITestTrophiesAnn;
import net.sf.esfinge.gamification.proxy.TestPointAnnotation;
import net.sf.esfinge.gamification.proxy.TestRankingAnnotation;
import net.sf.esfinge.gamification.proxy.TestRewardAnnotation;
import net.sf.esfinge.gamification.proxy.TestTrophiesAnnotation;
import net.sf.esfinge.gamification.user.UserStorage;

public class TestEvent {
	Game game;
	ITestPointAnn p;
	ITestRankingAnn r;
	ITestTrophiesAnn t;
	ITestRewardAnn re;

	@Before
	public void setupGame() {
		UserStorage.setUserID("Spider");
		p = GameProxy.createProxy(new TestPointAnnotation());
		r = GameProxy.createProxy(new TestRankingAnnotation());
		t = GameProxy.createProxy(new TestTrophiesAnnotation());
		re = GameProxy.createProxy(new TestRewardAnnotation());
		game = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(game);
	}

	@GamificationListener
	public class EventBonusConfig {

		public boolean executed = false;

		@WhenReachPoints(name = "GOLD", value = 2000)
		@TrophiesToUser(name = "BONUS")
		public void winTrophy() {
			executed = true;
		}
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

	// Outra classe de config
	@GamificationListener
	public class EventBonusConfig2 {

		public boolean executed = false;

		@WhenReachPoints(name = "GOLD", value = 2000)
		@TrophiesToUser(name = "BONUS2")
		public void winTrophy() {
			executed = true;
		}
	}

	@Test
	public void testTwoTrophy() {
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

	// Outra classe de config
	@GamificationListener
	public class EventBonusConfig3 {

		public boolean executed = false;

		@WhenReachPoints(name = "GOLD", value = 1500)
		@TrophiesToUser(name = "BONUS3")
		public void winTrophy() {
			executed = true;
		}
	}

	@Test
	public void testTwoTrophyDifferentThreshould() {
		EventBonusConfig c = new EventBonusConfig();
		EventBonusConfig3 c3 = new EventBonusConfig3();
		game.addEventListeners(c, c3);

		p.doSomething();
		p.doSomething();

		Achievement bonus = game.getAchievement("Spider", "BONUS");
		assertNotNull(bonus);
		Achievement bonus2 = game.getAchievement("Spider", "BONUS3");
		assertNotNull(bonus2);
		assertTrue(c.executed);
		assertTrue(c3.executed);
	}

	@GamificationListener
	public class EventBonusConfig4 {

		public boolean executed = false;

		@WhenReachPoints(name = "GOLD", value = 2000)
		public void onlyRunsMethod() {
			executed = true;
		}
	}

	@Test
	public void testPointAbove2000onlyRunsMethod() {
		EventBonusConfig4 c = new EventBonusConfig4();
		game.addEventListeners(c);
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		assertTrue(c.executed);
	}

	@GamificationListener
	public class EventBonusConfig5 {

		public boolean executed1 = false;
		public boolean executed2 = false;

		@WhenReachPoints(name = "GOLD", value = 2000)
		public void onlyRunsMethod() {
			executed1 = true;
		}

		@WhenReachPoints(name = "GOLD", value = 2000)
		@TrophiesToUser(name = "BONUS3")
		public void winTrophy() {
			executed2 = true;
		}
	}

	@Test
	public void testPointAbove2000onlyRunsMethodAndWinTrophy() {
		EventBonusConfig5 c = new EventBonusConfig5();
		game.addEventListeners(c);
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		Achievement bonus2 = game.getAchievement("Spider", "BONUS3");
		assertNotNull(bonus2);
		assertTrue(c.executed1);
		assertTrue(c.executed2);
	}

	public class EventBonusConfig6 {

		public boolean executed1 = false;
		public boolean executed2 = false;

		@WhenReachPoints(name = "GOLD", value = 2000)
		public void onlyRunsMethod() {
			executed1 = true;
		}

		@WhenReachPoints(name = "GOLD", value = 2000)
		@TrophiesToUser(name = "BONUS3")
		public void winTrophy() {
			executed2 = true;
		}
	}

	@Test
	public void testPointDontConfigure() {
		EventBonusConfig6 c = new EventBonusConfig6();
		game.addEventListeners(c);
		p.doSomething();
		p.doSomething();

		Achievement ach = game.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(2000), ((Point) ach).getQuantity());
		Achievement bonus2 = game.getAchievement("Spider", "BONUS3");
		assertNull(bonus2);
		assertFalse(c.executed1);
		assertFalse(c.executed2);
	}

	// Ranking
	@GamificationListener
	public class EventBonusConfig7 {

		public boolean executed = false;

		@WhenReachRanking(name = "Noob", value = "level 1")
		public void onlyRunsMethod() {
			executed = true;
		}
	}

	@Test
	public void testRankingMasterRunsMethod() {
		EventBonusConfig7 c = new EventBonusConfig7();
		game.addEventListeners(c);
		r.doSomething();
		r.doSomething();

		Achievement ach = game.getAchievement("Spider", "Noob");
		assertEquals("level 1", ((Ranking) ach).getLevel());
		assertTrue(c.executed);
	}

	@GamificationListener
	public class EventBonusConfig8 {

		public boolean executed = false;

		@WhenWinTrophy("champion")
		public void onlyRunsMethod() {
			executed = true;
		}
	}

	@Test
	public void testTrophyChampionRunsMethod() {
		EventBonusConfig8 c = new EventBonusConfig8();
		game.addEventListeners(c);
		t.doSomething();

		Achievement ach = game.getAchievement("Spider", "champion");
		assertEquals("champion", ((Trophy) ach).getName());
		assertTrue(c.executed);
	}

	@GamificationListener
	public class EventBonusConfig9 {

		public boolean executed = false;

		@WhenWinReward("lunch")
		public void onlyRunsMethod() {
			executed = true;
		}
	}

	@Test
	public void testRewardLunchRunsMethod() {
		EventBonusConfig9 c = new EventBonusConfig9();
		game.addEventListeners(c);
		re.doSomething();

		Achievement ach = game.getAchievement("Spider", "lunch");
		assertEquals("lunch", ((Reward) ach).getName());
		assertTrue(c.executed);
	}

	@GamificationListener
	public class EventRankingConfig {

		public boolean executed;

		@WhenUseReward("lunch")
		public void removeMethod() {
			executed = false;
		}

	}

	@Test
	public void testRewardLunchRemoveMethod() {
		EventRankingConfig eventListener = new EventRankingConfig();
		game.addEventListeners(eventListener);
		re.doSomething();
		re.doRemoveSomething();

		Achievement ach = game.getAchievement("Spider", "lunch");
		assertEquals("lunch", ((Reward) ach).getName());
		assertFalse(eventListener.executed);
	}
}

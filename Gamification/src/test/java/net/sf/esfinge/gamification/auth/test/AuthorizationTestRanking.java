package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.exception.AuthorizationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.auth.GuardedRanking;
import net.sf.esfinge.gamification.auth.GuardedRankingImpl;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTestRanking {

	private Game game;
	private String user;
	private Achievement begginer, advanced, expert;
	private GuardedRanking guarded;

	@Before
	public void setUp() {
		user = "user";
		UserStorage.setUserID(user);
		game = new GameMemoryStorage();
		begginer = new Ranking("experience", "begginer");
		advanced = new Ranking("experience", "advanced");
		expert = new Ranking("experience", "expert");
		GameInvoker.getInstance().setGame(game);
		guarded = GameProxy.createProxy(new GuardedRankingImpl());

	}

	@After
	public void tearDown() {

		game.removeAchievement(user, begginer);
		game.removeAchievement(user, advanced);
		game = null;
		begginer = null;
		advanced = null;
		expert = null;
		guarded = null;
		user = null;

	}

	@Test
	public void authorizedUserAllowRanking() {

		game.addAchievement(user, expert);

		guarded.changeProfilePhoto();
		game.removeAchievement(user, expert);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyRanking() {

		game.addAchievement(user, expert);
		guarded.takePhoto();
		game.removeAchievement(user, expert);

	}

	@Test
	public void authorizedUserAllowLevel() {

		game.addAchievement(user, expert);

		guarded.recordVideo();
		game.removeAchievement(user, expert);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyLevel() {

		game.addAchievement(user, expert);
		guarded.receivePhoto();
		game.removeAchievement(user, expert);

	}

	@Test
	public void authorizedUserAllowRankingAndLevel() {

		game.addAchievement(user, begginer);

		guarded.sendPhoto();
		game.removeAchievement(user, begginer);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyRankingAndLevel() {

		game.addAchievement(user, begginer);
		guarded.screenshot();
		game.removeAchievement(user, begginer);

	}

	@Test
	public void authorizedUserAllowRankingOrLevel() {

		game.addAchievement(user, begginer);

		guarded.shutdown();
		game.removeAchievement(user, begginer);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyRankingOrLevel() {

		game.addAchievement(user, advanced);
		guarded.turnOn();
		game.removeAchievement(user, advanced);

	}

}

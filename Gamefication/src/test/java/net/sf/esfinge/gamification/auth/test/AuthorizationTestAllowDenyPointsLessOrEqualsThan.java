package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.context.AuthorizationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedImpl;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTestAllowDenyPointsLessOrEqualsThan {

	private Game game;
	private String user = "user";
	private Achievement bronze, coins;
	private Guarded guarded;

	@Before
	public void setUp() {

		game = new GameMemoryStorage();
		bronze = new Point(10, "bronze");
		coins = new Point(20, "coins");
		game.addAchievement(user, bronze);
		GameInvoker.getInstance().setGame(game);
		guarded = AuthorizationContext.guardObject(new GuardedImpl());

	}

	@After
	public void tearDown() {

		game.removeAchievement(user, bronze);
		game.removeAchievement(user, coins);
		game = null;
		bronze = null;
		coins = null;
		guarded = null;

	}

	@Test
	public void authorizedUserAllowLessThen() {

		UserStorage.setUserID(user);
		game.addAchievement(user, coins);

		guarded.recordVideo();
		game.removeAchievement(user, coins);
	}

	@Test(expected = UnauthorizedException.class)
	public void unauthorizedUserAllowLessThen() {

		UserStorage.setUserID(user);
		game.addAchievement(user, coins);
		game.addAchievement(user, coins);
		guarded.recordVideo();
		game.removeAchievement(user, coins);
		game.removeAchievement(user, coins);

	}

	@Test
	public void authorizedUserDenyLessThen() {
		user = "new user";
		UserStorage.setUserID(user);
		game.addAchievement(user, bronze);
		game.addAchievement(user, bronze);
		game.addAchievement(user, bronze);

		guarded.receivePhoto();
		game.removeAchievement(user, bronze);
		game.removeAchievement(user, bronze);
		game.removeAchievement(user, bronze);

	}

	@Test(expected = UnauthorizedException.class)
	public void unauthorizedUserDenyLessThen() {

		user = "un";
		game.addAchievement(user, bronze);
		UserStorage.setUserID(user);
		guarded.receivePhoto();
		game.removeAchievement(user, bronze);
	}
}

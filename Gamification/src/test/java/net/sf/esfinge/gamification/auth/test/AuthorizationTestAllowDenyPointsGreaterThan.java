package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.exception.AuthorizationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedPointsImpl;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTestAllowDenyPointsGreaterThan {

	private Game game;
	private String user = "user";
	private Achievement silver, gold;
	private Guarded guarded;

	@Before
	public void setUp() {

		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		gold = new Point(5, "gold");
		game.addAchievement(user, silver);
		GameInvoker.getInstance().setGame(game);
		guarded = GameProxy.createProxy(new GuardedPointsImpl());

	}

	@After
	public void tearDown() {

		game.removeAchievement(user, silver);
		game = null;
		silver = null;
		gold = null;
		guarded = null;

	}

	/**
	 * 
	 * In the interface 20 points of silver are defined of minimum points for access
	 * this resource
	 * 
	 */

	@Test
	public void authorizedUserAllowGreaterThen() {

		UserStorage.setUserID(user);
		game.addAchievement(user, gold);
		game.addAchievement(user, gold);

		guarded.changeProfilePhoto();
		game.removeAchievement(user, gold);
	}

	/**
	 * 
	 * When user has not the minimum points an exception is throw
	 * 
	 */

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserAllowGreaterThen() {
		
		UserStorage.setUserID(user);
		game.addAchievement(user, gold);
		guarded.changeProfilePhoto();

	}

	@Test
	public void authorizedUserDenyGreater() {
		user = "un";
		game.addAchievement(user, silver);
		game.addAchievement(user, silver);
		UserStorage.setUserID(user);
		guarded.takePhoto();
		game.removeAchievement(user, silver);
	
	}
	
	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyGreater() {

		user = "un";
		game.addAchievement(user, silver);
		game.addAchievement(user, silver);
		game.addAchievement(user, silver);
		UserStorage.setUserID(user);
		guarded.takePhoto();
		game.removeAchievement(user, silver);
		game.removeAchievement(user, silver);
	}
}

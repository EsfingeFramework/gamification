package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.context.WrappedObj;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedImpl;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;

public class AuthorizationTest {

	private Game game;
	private String user = "user";
	private Achievement silver, gold;
	private Guarded guarded;
	private WrappedObj<Object> wUser;
	private WrappedObj<Game> wGame;

	@Before
	public void setUp() {

		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		gold = new Point(5, "gold");
		game.addAchievement("user", silver);
		wGame = AuthorizationContext.wrapAsEnvironmentProp("game", game);
		wUser = AuthorizationContext.wrapAsResourceProp("currentUser", user);
		guarded = AuthorizationContext.guardObject(new GuardedImpl(), wGame, wUser);

	}

	@After
	public void tearDown() {

		game.removeAchievement("user", silver);
		game = null;
		silver = null;
		gold = null;
		guarded = null;
		wUser = null;
		wGame = null;

	}

	/**
	 * 
	 * In the interface 20 points of silver are defined of minimum points for access
	 * this resource
	 * 
	 * see @net.sf.esfinge.gamification.auth.Guarded
	 */

	@Test
	public void authorizedUser() {

		game.addAchievement(user, silver);
		game.addAchievement("user", gold);
		game.addAchievement("user", gold);

		Guarded guard = AuthorizationContext.guardObject(new GuardedImpl(), wGame, wUser);

		guard.changeProfilePhoto();

		game.removeAchievement("user", gold);
	}

	/**
	 * 
	 * When the user has not the minimum points an exception is throw
	 * 
	 * see @net.sf.esfinge.gamification.auth.GameAuthorizer
	 * see @net.sf.esfinge.gamification.exception.UnauthorizedException
	 * 
	 */

	@Test(expected = UnauthorizedException.class)
	public void unauthorizedUser() {
		guarded.takePhoto();
	}

	/**
	 * If a achievement is set for GameAuthorizer and not be found in gamification
	 * storage an exception is throw because is a configuration error
	 * 
	 */

	@Test(expected = GamificationConfigurationException.class)
	public void wrongSetUp() {

		guarded.changeProfilePhoto();
	}
}

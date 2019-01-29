package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.context.AuthorizationContext;
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
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTest {

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
		guarded = AuthorizationContext.guardObject(new GuardedImpl());

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
	 * see @net.sf.esfinge.gamification.auth.Guarded
	 */

	@Test
	public void authorizedUser() {

		UserStorage.setUserID(user);
		game.addAchievement(user, silver);
		game.addAchievement(user, gold);
		game.addAchievement(user, gold);

		guarded.changeProfilePhoto();

		game.removeAchievement(user, silver);
		game.removeAchievement(user, silver);

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
		
		user = "un";
		game.addAchievement(user, silver);
		UserStorage.setUserID(user);
		guarded.takePhoto();
		game.removeAchievement(user, silver);
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

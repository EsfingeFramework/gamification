package net.sf.esfinge.gamification.auth.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedImpl;
import net.sf.esfinge.gamification.auth.invoke.AuthorizerHandler;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;

public class AuthorizationTest {

	private Game game;
	private Achievement silver;
	private Guarded guarded;
	private Guarded listenedObject;

	@Before
	public void setUp() {
		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		game.addAchievement("user", silver);
		guarded = new GuardedImpl();

	}

	@After
	public void tearDown() {
		game.removeAchievement("user", silver);
		game.removeAchievement("user", silver);
		game = null;
		silver = null;
		guarded = null;
		listenedObject = null;
	}

	/**
	 * 
	 * In the interface 20 points of silver are defined of minimum points for access this resource
	 * 
	 *  see @net.sf.esfinge.gamification.auth.Guarded
	 */
	
	@Test
	public void authorizedUser() {

		game.addAchievement("user", silver);

		listenedObject = (Guarded) AuthorizerHandler.create(guarded, game, "user");
		listenedObject.takePhoto();
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
		listenedObject = (Guarded) AuthorizerHandler.create(guarded, game, "user");
		listenedObject.takePhoto();
	}
	
	/**
	 * If a achievement is set for GameAuthorizer and not be found in gamification storage a exception is throw
	 * because is a configuration error
	 * 
	 */
	
	@Test(expected = GamificationConfigurationException.class)
	public void wrongSetUp() {
		listenedObject = (Guarded) AuthorizerHandler.create(guarded, game, "user");
		listenedObject.changeProfilePhoto();
	}
}

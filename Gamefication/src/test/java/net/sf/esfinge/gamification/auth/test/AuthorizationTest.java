package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.annotation.context.Environment;
import org.esfinge.guardian.annotation.context.Subject;
import org.esfinge.guardian.context.AuthorizationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedImpl;
import net.sf.esfinge.gamification.auth.invoke.AuthorizerCreator;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.guardian.auth.GamificationAuthorizationPopulator;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;

public class AuthorizationTest {

	@Environment("game")
	private Game game;
	@Subject("user")
	private String user = "user";
	private Achievement silver, gold;
	private Guarded guarded;
	private Guarded listenedObject;

	@Before
	public void setUp() {
		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		gold = new Point(5, "gold");
		game.addAchievement("user", silver);
		guarded = new GuardedImpl();

	}

	@After
	public void tearDown() {
		game.removeAchievement("user", silver);
		game = null;
		silver = null;
		gold = null;
		guarded = null;
		listenedObject = null;
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
		AuthorizationContext context = new AuthorizationContext(guarded);
		guarded.changeProfilePhoto();
		GamificationAuthorizationPopulator auth = new GamificationAuthorizationPopulator();
		auth.setUser(user);
		auth.setGame(game);
		auth.populate(context);
//		listenedObject = (Guarded) AuthorizerCreator.create(guarded, game, "user");
//		listenedObject.takePhoto();
//		listenedObject.changeProfilePhoto();
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
		listenedObject = (Guarded) AuthorizerCreator.create(guarded, game, "user");
		listenedObject.takePhoto();
	}

	/**
	 * If a achievement is set for GameAuthorizer and not be found in gamification
	 * storage an exception is throw because is a configuration error
	 * 
	 */

	@Test(expected = GamificationConfigurationException.class)
	public void wrongSetUp() {
		listenedObject = (Guarded) AuthorizerCreator.create(guarded, game, "user");
		listenedObject.changeProfilePhoto();
	}
}
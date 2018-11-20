package net.sf.esfinge.gamification.auth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTest {

	private Game game;
	private Achievement silver;
	private Achievement gold;

	@Before
	public void setUp() {
		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		gold = new Point(10, "gold");
		game.addAchievement("user", silver);
		game.addAchievement("user", gold);
	}

	@After
	public void tearDown() {
		game.removeAchievement("user", silver);
		game.removeAchievement("user", gold);
		game = null;
		UserStorage.setUserID(null);
	}

	@Test
	public void allowExecute() throws NoSuchMethodException, SecurityException {
		GameAuthorizer gameAuthorizer = new GameAuthorizer();
		Guarded guarded = new GuardedImpl();
		gameAuthorizer.authorize(guarded.getClass().getMethod("changeProfilePhoto"), game, "user");
	}

	@Test(expected = RuntimeException.class)
	public void denyExecute() throws NoSuchMethodException, SecurityException {
		GameAuthorizer gameAuthorizer = new GameAuthorizer();
		Guarded guarded = new GuardedImpl();
		gameAuthorizer.authorize(guarded.getClass().getMethod("takePhoto"), game, "user");
	}

}

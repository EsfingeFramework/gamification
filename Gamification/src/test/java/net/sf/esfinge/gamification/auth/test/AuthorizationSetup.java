package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.context.AuthorizationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.GuardedPointsImpl;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationSetup {

	private Game game;
	private String user = "user";
	private Achievement silver;
	private GuardedPointsImpl guarded;

	@Before
	public void setUp() {

		game = new GameMemoryStorage();
		silver = new Point(10, "silver");
		game.addAchievement(user, silver);
		GameInvoker.getInstance().setGame(game);
		guarded = AuthorizationContext.guardObject(new GuardedPointsImpl());

	}

	@After
	public void tearDown() {

		game.removeAchievement(user, silver);
		game = null;
		silver = null;
		guarded = null;

	}

	@Test(expected = GamificationConfigurationException.class)
	public void wrongGameSetUp() {
		GameInvoker.getInstance().setGame(null);
		guarded.takePhoto();
	}

	@Test(expected = GamificationConfigurationException.class)
	public void wrongUserSetUp() {
		UserStorage.setUserID(null);
		guarded.changeProfilePhoto();
	}
}

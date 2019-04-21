package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.exception.AuthorizationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedRewardImpl;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTestReward {

	private Game game;
	private String user = "user";
	private Achievement gold, silver;
	private Guarded guarded;

	@Before
	public void setUp() {

		UserStorage.setUserID(user);
		game = new GameMemoryStorage();
		gold = new Reward("gold");
		silver = new Reward("silver");
		GameInvoker.getInstance().setGame(game);
		guarded = GameProxy.createProxy(new GuardedRewardImpl());

	}

	@After
	public void tearDown() {

		UserStorage.setUserID(null);
		game = null;
		silver = null;
		gold = null;
		guarded = null;

	}

	@Test
	public void authorizedUserAllowReward() {

		game.addAchievement(user, gold);

		guarded.changeProfilePhoto();
		game.removeAchievement(user, gold);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyReward() {

		game.addAchievement(user, silver);
		guarded.takePhoto();
		game.removeAchievement(user, silver);

	}

}

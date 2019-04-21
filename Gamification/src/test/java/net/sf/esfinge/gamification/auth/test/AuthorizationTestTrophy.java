package net.sf.esfinge.gamification.auth.test;

import org.esfinge.guardian.exception.AuthorizationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.auth.GuardedTrophyImpl;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.user.UserStorage;

public class AuthorizationTestTrophy {

	private Game game;
	private String user = "user";
	private Achievement silver, gold;
	private Guarded guarded;

	@Before
	public void setUp() {

		UserStorage.setUserID(user);
		game = new GameMemoryStorage();
		silver = new Trophy("silver");
		gold = new Trophy("gold");
		GameInvoker.getInstance().setGame(game);
		guarded = GameProxy.createProxy(new GuardedTrophyImpl());

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
	public void authorizedUserAllowTrophy() {

		game.addAchievement(user, silver);

		guarded.changeProfilePhoto();
		game.removeAchievement(user, silver);
	}

	@Test(expected = AuthorizationException.class)
	public void unauthorizedUserDenyTrophy() {

		game.addAchievement(user, gold);
		guarded.takePhoto();
		game.removeAchievement(user, gold);

	}

}

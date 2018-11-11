package net.sf.esfinge.gamification.guardian.points;

import org.esfinge.guardian.context.AuthorizationContext;
import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.annotation.PointsToUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.gamification.user.UserStorage;

public class TestAuthPointGreaterThan {
	
	private Game game;
	private TestGreaterPoints testGreaterPoints;
	public interface TestGreaterPoints {
		@PointsToUser(name="Coins",quantity=50)
		public void won();
	}

	public class TestGreaterPointsImp implements TestGreaterPoints {
		public void won() {
			User user = AuthorizationContext.guardObject(new User());
			user.a();
		}
	}
	
	@Before
	public void setUp() {
		game = new GameMemoryStorage();
		GameInvoker.getInstance().setGame(game);
		testGreaterPoints = (TestGreaterPoints) GameProxy.createProxy(new TestGreaterPointsImp());
		UserStorage.setUserID("AuthUser");
	}

	@AuthPointGreaterThan(quantity=0)
	public void someProcess() {
		
	}
	
	@Test
	public void authUserPoints() {
		testGreaterPoints.won();
		someProcess();
	}
}

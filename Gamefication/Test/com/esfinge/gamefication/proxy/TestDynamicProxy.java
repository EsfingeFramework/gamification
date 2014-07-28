package com.esfinge.gamefication.proxy;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.mechanics.FakeUser;
import com.esfinge.gamefication.mechanics.GameMemoryStorage;
import com.esfinge.gamefication.user.UserStorage;

public class TestDynamicProxy {

	@Test
	public void testAddPoints() {

		FakeUser user = new FakeUser("Spider");
		UserStorage.setUser(user);
		ITestPointAnn p = new TestPointAnnotation();
		p = (ITestPointAnn) GameProxy.createProxy(p);
		GameMemoryStorage gs = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(gs);
		p.doSomething();
		p.doSomething();
		p.doSomething();

		Achievement ach = gs.getAchievement(user, "GOLD");
		assertEquals(new Integer(3000), ((Point) ach).getQuantity());

	}

}
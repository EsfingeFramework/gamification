package com.esfinge.gamefication.proxy;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.mechanics.GameMemoryStorage;
import com.esfinge.gamefication.user.UserStorage;

public class TestDynamicProxy {

	@Test
	public void testAddPoints() {
		UserStorage.setUserID("Spider");
		ITestPointAnn p = new TestPointAnnotation();
		p = (ITestPointAnn) GameProxy.createProxy(p);
		GameMemoryStorage gs = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(gs);
		p.doSomething();
		p.doSomething();
		p.doSomething();

		Achievement ach = gs.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(3000), ((Point) ach).getQuantity());
	}
	
	@Test
	public void testInvocationWithException() {
		UserStorage.setUserID("Spider");
		ITestPointAnn p = new TestPointAnnotation();
		p = (ITestPointAnn) GameProxy.createProxy(p);
		GameMemoryStorage gs = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(gs);
		p.doSomething();
		try {
			p.doWrong();
			fail();
		} catch (Exception e) {
			Achievement ach = gs.getAchievement("Spider", "GOLD");
			assertEquals(new Integer(1000), ((Point) ach).getQuantity());
		}
	}

}
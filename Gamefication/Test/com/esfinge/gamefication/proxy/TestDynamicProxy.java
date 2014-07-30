package com.esfinge.gamefication.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.mechanics.FakeUser;
import com.esfinge.gamefication.mechanics.GameMemoryStorage;
import com.esfinge.gamefication.user.UserStorage;

public class TestDynamicProxy {
	
	GameMemoryStorage gs;
	ITestPointAnn p;
	
	@Before
	public void setupGame(){
		UserStorage.setUserID("Spider");
		p = (ITestPointAnn) GameProxy.createProxy(new TestPointAnnotation());
		gs = new GameMemoryStorage();
		GameInvoker gi = GameInvoker.getInstance();
		gi.setGame(gs);
	}
	

	@Test
	public void testAddPoints() {
		p.doSomething();
		p.doSomething();
		p.doSomething();

		Achievement ach = gs.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(3000), ((Point) ach).getQuantity());
	}
	
	@Test
	public void testInvocationWithException() {
		p.doSomething();
		try {
			p.doWrong();
			fail();
		} catch (Exception e) {
			Achievement ach = gs.getAchievement("Spider", "GOLD");
			assertEquals(new Integer(1000), ((Point) ach).getQuantity());
		}
	}
	
	@Test
	public void testRemovePoints() {
		p.doSomething();
		p.doRemoveSomething();

		Achievement ach = gs.getAchievement("Spider", "GOLD");
		assertEquals(new Integer(500), ((Point) ach).getQuantity());
	}
	
	@Test
	public void addPointsToParam(){
		p.niceComment("OK! Thank you!", "Thor");
		Achievement ach = gs.getAchievement("Thor", "SILVER");
		assertEquals(new Integer(100), ((Point) ach).getQuantity());
	}
	
	@Test
	public void addPointsToParamProperty(){
		p.niceComment(new Comment(new FakeUser("Thor"), "OK! Thank you!"));
		Achievement ach = gs.getAchievement("Thor", "SILVER");
		assertEquals(new Integer(300), ((Point) ach).getQuantity());
	}
	

}
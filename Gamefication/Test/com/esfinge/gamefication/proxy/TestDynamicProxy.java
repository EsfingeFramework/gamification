package com.esfinge.gamefication.proxy;
import java.util.Map;
import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.mechanics.FakeUser;
import com.esfinge.gamefication.mechanics.GameMemoryStorage;
import com.esfinge.gamefication.user.UserStorage;

public class TestDynamicProxy {

	public static void main(String[] args) {
		
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
		Map<String,Achievement> map = gs.getAchievements(user);
		for(Achievement a : map.values()){
			System.out.println(a); //fazer um assert para ver se está aqui
		}
		
		System.out.println(gs.getAchievement(user, "GOLD"));
		
		}	

	}


//ITestPointAnn p = (ITestPointAnn) GameProxy.createProxy(new TestPointAnnotation());
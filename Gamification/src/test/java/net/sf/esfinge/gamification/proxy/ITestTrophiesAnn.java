package net.sf.esfinge.gamification.proxy;

import net.sf.esfinge.gamification.annotation.RemoveTrophy;
import net.sf.esfinge.gamification.annotation.TrophiesToUser;

public interface ITestTrophiesAnn {
	
	@TrophiesToUser(name = "champion")
	public void doSomething();
	
	@RemoveTrophy(name="champion")
	public void doRemoveSomething();
	
	@TrophiesToUser(name="loser")
	public void doSomething2();

}

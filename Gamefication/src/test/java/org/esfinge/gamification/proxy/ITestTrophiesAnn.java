package org.esfinge.gamification.proxy;

import org.esfinge.gamification.annotation.RemoveTrophy;
import org.esfinge.gamification.annotation.TrophiesToUser;

public interface ITestTrophiesAnn {
	
	@TrophiesToUser(name = "champion")
	public void doSomething();
	
	@RemoveTrophy(name="champion")
	public void doRemoveSomething();
	
	@TrophiesToUser(name="loser")
	public void doSomething2();

}

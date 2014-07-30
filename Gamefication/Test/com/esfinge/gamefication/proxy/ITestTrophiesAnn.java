package com.esfinge.gamefication.proxy;

import com.esfinge.gamification.annotation.RemoveTrophy;
import com.esfinge.gamification.annotation.TrophiesToUser;

public interface ITestTrophiesAnn {
	
	@TrophiesToUser(name = "champion")
	public void doSomething();
	
	@RemoveTrophy(name="champion")
	public void doRemoveSomething();
	
	@TrophiesToUser(name="loser")
	public void doSomething2();

}

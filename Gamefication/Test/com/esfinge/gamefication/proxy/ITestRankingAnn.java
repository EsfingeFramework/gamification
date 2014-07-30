package com.esfinge.gamefication.proxy;

import com.esfinge.gamification.annotation.RankingsToUser;
import com.esfinge.gamification.annotation.RemoveRankings;

public interface ITestRankingAnn {
	
	@RankingsToUser(name = "Noob", level = "level 1")
    public void doSomething();
	
	@RemoveRankings(name = "Noob", level ="level 1")
	public void doRemoveSomething();
	
	

}

package net.sf.esfinge.gamification.proxy;

import net.sf.esfinge.gamification.annotation.RankingsToUser;
import net.sf.esfinge.gamification.annotation.RemoveRankings;

public interface ITestRankingAnn {
	
	@RankingsToUser(name = "Noob", level = "level 1")
    public void doSomething();
	
	@RemoveRankings(name = "Noob", level ="level 1")
	public void doRemoveSomething();
	

}

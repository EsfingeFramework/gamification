package com.esfinge.gamefication.proxy;
import com.esfinge.gamification.annotation.RemoveReward;
import com.esfinge.gamification.annotation.RewardsToUser;

public interface ITestRewardAnn {
	
	@RewardsToUser(name = "lunch", used = false)
	public void doSomething();
	
	@RemoveReward(name = "lunch", used = true)
	public void doRemoveSomething();

	@RewardsToUser(name = "coffee", used = false)
	public void doSomething2();	
	
	@RewardsToUser(name = "lunch", used = false)
	@RemoveReward(name = "lunch", used = true)
	public void doSomethingWrong();

}

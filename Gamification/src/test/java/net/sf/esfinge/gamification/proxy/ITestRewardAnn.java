package net.sf.esfinge.gamification.proxy;
import net.sf.esfinge.gamification.annotation.RemoveReward;
import net.sf.esfinge.gamification.annotation.RewardsToUser;

public interface ITestRewardAnn {
	
	@RewardsToUser(name = "lunch", used = false)
	public void doSomething();
	
	@RemoveReward(name = "lunch", used = true)
	public void doRemoveSomething();

	@RewardsToUser(name = "coffee", used = false)
	public void doSomething2();	
	
}

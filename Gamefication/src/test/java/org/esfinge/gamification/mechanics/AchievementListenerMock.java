package org.esfinge.gamification.mechanics;
import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.listener.AchievementListener;
import org.junit.Assert;

public class AchievementListenerMock implements AchievementListener {
	
	private Object user;
	private Achievement achievement;
	private boolean error;
	
	public void happenedError(){
		error = true;
	}

	public void achievementAdded(Game game, Object user, Achievement added) {
		
		if (error){
			throw new RuntimeException();
		}
		this.user = user;
		this.achievement = added; 
	}

	public void verifyReceivedAchievements(Object user, Achievement added) {
		Assert.assertEquals(user,this.user);
		Assert.assertEquals(added, this.achievement);
	}
	

	public void achievementRemoved(Game game, Object user, Achievement removed) {
		 if (error){
			 throw new RuntimeException();
		 }
		 this.user = user;
		 this.achievement = removed;	
	}
	
	public void verifyRemovedAchievements(Object user, Achievement removed){
		Assert.assertEquals(user,this.user);
		Assert.assertEquals(removed, this.achievement);	
	}
	
	

}

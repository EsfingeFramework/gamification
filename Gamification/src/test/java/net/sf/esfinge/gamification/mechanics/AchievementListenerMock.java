package net.sf.esfinge.gamification.mechanics;
import org.junit.Assert;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.listener.AchievementListener;
import net.sf.esfinge.gamification.mechanics.Game;

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

package com.esfinge.gamefication.mechanics;
import org.junit.Assert;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.listener.AchievementListener;

public class AchievementListenerMock implements AchievementListener {
	
	private Object user;
	private Achievement achievement;
	private boolean error;
	
	public void happenedError(){
		error = true;
	}

	public void achievementAdded(Object user, Achievement added) {
		
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
	

	public void achievementRemoved(Object user, Achievement removed) {
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

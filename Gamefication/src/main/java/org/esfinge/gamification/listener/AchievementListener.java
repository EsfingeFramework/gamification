package org.esfinge.gamification.listener;
import org.esfinge.gamification.achievement.Achievement;


public interface AchievementListener{
	
	void achievementAdded(Object user, Achievement added); 
	
	void achievementRemoved(Object user, Achievement removed);

}

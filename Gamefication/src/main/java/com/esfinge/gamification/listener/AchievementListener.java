package com.esfinge.gamification.listener;
import com.esfinge.gamification.achievement.Achievement;


public interface AchievementListener{
	
	void achievementAdded(Object user, Achievement added); 
	
	void achievementRemoved(Object user, Achievement removed);

}

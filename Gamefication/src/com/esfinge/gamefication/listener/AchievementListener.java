package com.esfinge.gamefication.listener;
import com.esfinge.gamefication.achievement.Achievement;


public interface AchievementListener{
	
	void achievementAdded(Object user, Achievement added); 
	
	void achievementRemoved(Object user, Achievement removed);

}

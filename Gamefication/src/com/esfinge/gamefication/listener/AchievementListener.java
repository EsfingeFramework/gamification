package com.esfinge.gamefication.listener;
import com.esfinge.gamefication.achievement.Achievement;


public interface AchievementListener{
	
	void achievementAdded(Object user, Achievement added); 
	
	void achievementRemove(Object user, Achievement removed);

}

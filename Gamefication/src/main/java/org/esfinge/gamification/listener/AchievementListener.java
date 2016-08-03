package org.esfinge.gamification.listener;
import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.mechanics.Game;


public interface AchievementListener{
	
	void achievementAdded(Game game, Object user, Achievement a);

	void achievementRemoved(Game game, Object user, Achievement a);

}

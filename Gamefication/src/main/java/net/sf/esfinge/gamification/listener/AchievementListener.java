package net.sf.esfinge.gamification.listener;
import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.mechanics.Game;


public interface AchievementListener{
	
	void achievementAdded(Game game, Object user, Achievement a);

	void achievementRemoved(Game game, Object user, Achievement a);

}

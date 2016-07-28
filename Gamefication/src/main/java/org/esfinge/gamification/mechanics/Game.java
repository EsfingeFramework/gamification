package org.esfinge.gamification.mechanics;

import java.util.ArrayList;
import java.util.Map;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.listener.AchievementListener;

public abstract class Game {

	 public abstract void insertAchievement(Object user, Achievement a);
	
	 public abstract void deleteAchievement(Object user, Achievement a);
	
	 public abstract void updateAchievement(Object user, Achievement a);
	 
	 public abstract Achievement getAchievement(Object user, String
	 achievName);
	
	 public abstract Map<String, Achievement> getAchievements(Object user);
	
	// /
	private ArrayList<AchievementListener> listeners = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.esfinge.gamification.mechanics.Game#addAchievement(java.lang.Object,
	 * org.esfinge.gamification.achievement.Achievement)
	 */
	public void addAchievement(Object user, Achievement a) {
		Achievement p = this.getAchievement(user, a.getName());
		if (p == null){
			this.insertAchievement(user, a);
		}else{
			p.incrementAchievement(a);
			this.updateAchievement(user, p);
		}
		notifyAdded(user, a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.esfinge.gamification.mechanics.Game#removeAchievement(java.lang.Object
	 * , org.esfinge.gamification.achievement.Achievement)
	 */
	public void removeAchievement(Object user, Achievement a) {
		Achievement p = this.getAchievement(user, a.getName());
		if (p.removeAchievement(a))
			this.deleteAchievement(user, p);
		else
			this.updateAchievement(user, p);
		notifyRemoved(user, a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.esfinge.gamification.mechanics.Game#addListener(org.esfinge.gamification
	 * .listener.AchievementListener)
	 */
	public void addListener(AchievementListener listener) {
		listeners.add(listener);
	}

	private void notifyAdded(Object user, Achievement a) {
		for (AchievementListener l : listeners) {
			l.achievementAdded(user, a);

		}
	}
	
	private void notifyRemoved(Object user, Achievement a) {
		for (AchievementListener l : listeners) {
			l.achievementRemoved(user, a);

		}
	}

}
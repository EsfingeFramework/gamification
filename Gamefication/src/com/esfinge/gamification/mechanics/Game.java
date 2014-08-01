package com.esfinge.gamification.mechanics;

import java.util.ArrayList;
import java.util.Map;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.listener.AchievementListener;

public abstract class Game {

	 public abstract void doAddAchievement(Object user, Achievement a);
	
	 public abstract void doRemoveAchievement(Object user, Achievement a);
	
	 public abstract Achievement getAchievement(Object user, String
	 achievName);
	
	 public abstract Map<String, Achievement> getAchievements(Object user);
	
	// /
	private ArrayList<AchievementListener> listeners = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#addAchievement(java.lang.Object,
	 * com.esfinge.gamefication.achievement.Achievement)
	 */
	public void addAchievement(Object user, Achievement a) {
		doAddAchievement(user, a);
		notifyAdded(user, a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#removeAchievement(java.lang.Object
	 * , com.esfinge.gamefication.achievement.Achievement)
	 */
	public void removeAchievement(Object user, Achievement a) {
		doRemoveAchievement(user, a);
		notifyRemoved(user, a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#addListener(com.esfinge.gamefication
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
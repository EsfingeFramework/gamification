package org.esfinge.gamification.mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.listener.AchievementListener;
import org.esfinge.gamification.listener.EvaluationAchievementListener;

public abstract class Game {

	 public abstract void insertAchievement(Object user, Achievement a);
	
	 public abstract void deleteAchievement(Object user, Achievement a);
	
	 public abstract void updateAchievement(Object user, Achievement a);
	 
	 public abstract Achievement getAchievement(Object user, String
	 achievName);
	
	 public abstract Map<String, Achievement> getAchievements(Object user);
	
	// /
	private List<AchievementListener> listeners = new ArrayList<>();

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
			l.achievementAdded(this, user, a);

		}
	}
	
	private void notifyRemoved(Object user, Achievement a) {
		for (AchievementListener l : listeners) {
			l.achievementRemoved(this, user, a);

		}
	}

	public <T extends Achievement> void  addBonus(Achievement bonus, Class<?> whenAchievementClassIs, BiFunction<T,Object,Boolean> when) {
		this.addListener(new EvaluationAchievementListener<T>(whenAchievementClassIs, when, bonus));
	}

}
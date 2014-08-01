package com.esfinge.gamification.mechanics;

import java.util.HashMap;
import java.util.Map;

import com.esfinge.gamification.achievement.Achievement;

public class GameMemoryStorage extends Game {

	private Map<Object, Map<String, Achievement>> achievments = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#addAchievement(java.lang.Object,
	 * com.esfinge.gamefication.achievement.Achievement)
	 */
	@Override
	public void doAddAchievement(Object user, Achievement a) {
		if (!achievments.containsKey(user)) {
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		if (userAchiev.containsKey(a.getName())) {
			userAchiev.get(a.getName()).incrementAchievement(a);
		} else {
			userAchiev.put(a.getName(), a);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#removeAchievement(java.lang.Object
	 * , com.esfinge.gamefication.achievement.Achievement)
	 */
	@Override
	public void doRemoveAchievement(Object user, Achievement a) {

		if (!achievments.containsKey(user)) {
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		if (userAchiev.containsKey(a.getName())) {
			userAchiev.get(a.getName()).removeAchievement(a);
		} else {
			userAchiev.put(a.getName(), a);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#getAchievement(java.lang.Object,
	 * java.lang.String)
	 */
	@Override
	public Achievement getAchievement(Object user, String achievName) {

		return achievments.get(user).get(achievName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#getAchievements(java.lang.Object)
	 */
	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		return achievments.get(user);
	}

}

package net.sf.esfinge.gamification.mechanics;

import java.util.HashMap;
import java.util.Map;

import net.sf.esfinge.gamification.achievement.Achievement;

public class GameMemoryStorage extends Game {

	private Map<Object, Map<String, Achievement>> achievments = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#addAchievement(java.lang.Object,
	 * net.sf.esfinge.gamification.achievement.Achievement)
	 */
	@Override
	public void insertAchievement(Object user, Achievement a) {
		if (!achievments.containsKey(user)) {
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		userAchiev.put(a.getName(), a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#removeAchievement(java.lang.Object
	 * , net.sf.esfinge.gamification.achievement.Achievement)
	 */
	@Override
	public void deleteAchievement(Object user, Achievement a) {

		if (!achievments.containsKey(user)) {
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		if (userAchiev.containsKey(a.getName())) {
			userAchiev.remove(a.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#getAchievement(java.lang.Object,
	 * java.lang.String)
	 */
	@Override
	public Achievement getAchievement(Object user, String achievName) {
		if (!achievments.containsKey(user)) {
			achievments.put(user, new HashMap<String, Achievement>());
		}
		return achievments.get(user).get(achievName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#getAchievements(java.lang.Object)
	 */
	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		return achievments.get(user);
	}

	@Override
	public void updateAchievement(Object user, Achievement a) {
		insertAchievement(user, a);

	}

	@Override
	public Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type) {

		Map<String, Achievement> map = new HashMap<>();

		for (Map.Entry<Object, Map<String, Achievement>> keys : achievments.entrySet()) {
			String key = (String) keys.getKey();
			Map<String, Achievement> userAchievement = achievments.get(key);
			for (Achievement achievement : userAchievement.values()) {
				if (type.getTypeName() == achievement.getClass().getTypeName())
					map.put(key, achievement);
			}
		}

		return map;
	}

}

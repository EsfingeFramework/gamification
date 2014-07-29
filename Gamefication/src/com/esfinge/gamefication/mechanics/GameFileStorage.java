package com.esfinge.gamefication.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.achievement.Rank;
import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.achievement.Tropy;
import com.esfinge.gamefication.listener.AchievementListener;
import com.esfinge.gamefication.user.UserStorage;

public class GameFileStorage implements Game {

	private String key;
	private Map<Object, Map<String, Achievement>> achievments = new HashMap<>();
	private ArrayList<AchievementListener> ac = new ArrayList<>();
	static File dir = new File(
			"C:/Users/Aluno/Documents/GitHub/gamification/Gamefication/properties/achievements.properties");

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(dir);
		props.load(file);
		file.close();
		return props;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#addAchievement(java.lang.Object,
	 * com.esfinge.gamefication.achievement.Achievement)
	 */
	@Override
	public void addAchievement(Object user, Achievement a) {

		UserStorage.setUserID("Guerra");
		String className = a.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);

		key = UserStorage.getUserID().toString() + "." + className + "."
				+ a.getName();

		try {

			Properties prop = getProp();
			FileOutputStream file = new FileOutputStream(dir);

			if (a instanceof Point) {
				prop.setProperty(key, ((Point) a).getQuantity().toString());
			}
			if (a instanceof Rank) {
				prop.setProperty(key, ((Rank) a).getLevel().toString());
			}
			if (a instanceof Reward) {
				prop.setProperty(key,
						((Boolean) ((Reward) a).isUsed()).toString());
			}
			if (a instanceof Tropy) {
				prop.setProperty(key, "");
			}

			prop.store(file, null);
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void removeAchievement(Object user, Achievement a) {
		
		UserStorage.setUserID("Guerra");
		String className = a.getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);

		key = UserStorage.getUserID().toString() + "." + className + "."
				+ a.getName();

		try {
			Properties prop = getProp();
			prop.remove(key);
			FileOutputStream file = new FileOutputStream(dir);
			prop.store(file, null);
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamefication.mechanics.Game#addListener(com.esfinge.gamefication
	 * .listener.AchievementListener)
	 */
	@Override
	public void addListener(AchievementListener listener) {

		ac.add(listener);
	}

	private void notify(AchievementListener listener) {
		for (AchievementListener a : ac) {
			a.achievementAdded(a, null);

		}
	}
}

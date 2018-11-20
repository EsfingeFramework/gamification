package net.sf.esfinge.gamification.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.factory.AchievementFactory;

public class GameFileStorage extends Game {

	private String key;
	private Properties props;
	private static File dir = null;
	private static String delim = "|";

	public GameFileStorage(String fileName) {
		this.dir = new File(fileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#addAchievement(java.lang.Object,
	 * net.sf.esfinge.gamification.achievement.Achievement)
	 */
	@Override
	public void insertAchievement(Object user, Achievement a) {
		String className = a.getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1);

		key = user.toString() + delim + className + delim + a.getName();

		try {
			props = new Properties();
			FileInputStream inputFile = new FileInputStream(dir);
			props.load(inputFile);

			saveProperty(a);

			FileOutputStream outputFile = new FileOutputStream(dir);
			props.store(outputFile, null);
			outputFile.close();

		} catch (IOException e) {
			throw new RuntimeException("Achievement '" + key + "' could not be written into file properly." + e);
		}
	}

	private void saveProperty(Achievement a) {
		String value = "";
		for (Method m : a.getClass().getDeclaredMethods()) {
			if (!"getName".equals(m.getName()) && (m.getName().startsWith("get") || m.getName().startsWith("is"))
					&& m.getParameterTypes().length == 0) {
				try {
					value = m.invoke(a).toString();
				} catch (Exception e) {
					throw new RuntimeException(
							"Achievement '" + key + "' could not be written into file properly." + e);
				}
			}
		}
		props.setProperty(key, value);
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
		String className = a.getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1);

		key = user.toString() + delim + className + delim

				+ a.getName();

		try {
			props = new Properties();
			FileInputStream inputFile = new FileInputStream(dir);
			props.load(inputFile);

			props.remove(key);

			FileOutputStream outputFile = new FileOutputStream(dir);
			props.store(outputFile, null);
			outputFile.close();

		} catch (IOException e) {
			throw new RuntimeException("Achievement '" + key + "' could not be deleted from file properly." + e);
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
		Properties prop;
		Achievement a = null;
		try {

			prop = new Properties();
			FileInputStream inputFile = new FileInputStream(dir);
			prop.load(inputFile);

			for (String key : prop.stringPropertyNames()) {
				String userName = key.substring(0, key.indexOf(delim));
				String achievementType = key.substring(key.indexOf(delim) + 1, key.lastIndexOf(delim));
				String achievementName = key.substring(key.lastIndexOf(delim) + 1);
				String achievementValue = prop.getProperty(key);

				if (user.toString().equals(userName) && achievementName.equals(achievName)) {

					a = AchievementFactory.createAchievement(achievementType, achievementName, achievementValue);
				}
			}

			FileOutputStream file = new FileOutputStream(dir);
			prop.store(file, null);
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#getAchievements(java.lang.Object)
	 */
	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		Properties prop;
		Map<String, Achievement> achievements = new HashMap<String, Achievement>();
		Achievement a;

		try {
			prop = new Properties();
			FileInputStream inputFile = new FileInputStream(dir);
			prop.load(inputFile);

			for (String key : prop.stringPropertyNames()) {
				String userName = key.substring(0, key.indexOf(delim));
				String achievementType = key.substring(key.indexOf(delim) + 1, key.lastIndexOf(delim));
				String achievementName = key.substring(key.lastIndexOf(delim) + 1);
				String achievementValue = prop.getProperty(key);

				if (user.toString().equals(userName)) {

					a = AchievementFactory.createAchievement(achievementType, achievementName, achievementValue);
					achievements.put(a.getName(), a);
				}
			}

			FileOutputStream file = new FileOutputStream(dir);
			prop.store(file, null);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return achievements;
	}

	@Override
	public void updateAchievement(Object user, Achievement a) {
		this.insertAchievement(user, a);

	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#getAllAchievements
	 */
	@Override
	public Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type) {
		Properties prop;
		Map<String, Achievement> achievements = null;

		try {
			prop = new Properties();
			FileInputStream inputFile = new FileInputStream(dir);
			prop.load(inputFile);
			achievements = new HashMap<>();
			for (String key : prop.stringPropertyNames()) {
				
				String userName = key.substring(0, key.indexOf(delim));
				String achievementType = key.substring(key.indexOf(delim) + 1, key.lastIndexOf(delim));
				String achievementName = key.substring(key.lastIndexOf(delim) + 1);
				String achievementValue = prop.getProperty(key);
				if (achievementType.equals(type.getSimpleName())) {
					Achievement achievement = AchievementFactory.createAchievement(achievementType, achievementName,
							achievementValue);
					achievements.put(userName, achievement);
				}

			}

			FileOutputStream file = new FileOutputStream(dir);
			prop.store(file, null);
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return achievements;
	}
}

package com.esfinge.gamification.factory;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;

public class AchievementFactory {
	public static Achievement createAchievement(String type, String name, String param) {
		Achievement a = null;
		
		if (type.equals(getClassName(Point.class.getName()))) {
			a = new Point(Integer.parseInt(param), name);
		}
		else if (type.equals(getClassName(Ranking.class.getName()))) {
			a = new Ranking(name, param);
		}
		else if (type.equals(getClassName(Reward.class.getName()))) {
			a = new Reward(name, Boolean.parseBoolean(param));
		}
		else if (type.equals(getClassName(Trophy.class.getName()))) {
			a = new Trophy(name);
		}
		else {
			throw new RuntimeException ("This achievement type'"+type+"' does not exist.");
		}
		
		return a;
	}
	
	public static String getClassName(String name) {
		String className = name.substring(name.lastIndexOf(".") + 1);
		
		return className;
	}
	


}

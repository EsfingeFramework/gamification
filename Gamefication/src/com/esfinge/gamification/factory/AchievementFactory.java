package com.esfinge.gamification.factory;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;

public class AchievementFactory {
	public static Achievement createAchievement(Achievement a) {
		if (a instanceof Point) {
			a = new Point(((Point) a).getQuantity(), a.getName());
		}
		if (a instanceof Ranking) {
			a = new Ranking(a.getName(), ((Ranking) a).getLevel());
		}
		if (a instanceof Reward) {
			a = new Reward(a.getName(), ((Reward) a).isUsed());
		}
		if (a instanceof Trophy) {
			a = new Trophy(a.getName());
		}
		
		return a;
	}
}

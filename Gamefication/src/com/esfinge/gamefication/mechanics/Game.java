package com.esfinge.gamefication.mechanics;

import java.util.Map;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.listener.AchievementListener;

public interface Game {

	public abstract void addAchievement(Object user, Achievement a);

	public abstract void removeAchievement(Object user, Achievement a);

	public abstract Achievement getAchievement(Object user, String achievName);

	public abstract Map<String, Achievement> getAchievements(Object user);

	public abstract void addListener(AchievementListener listener);

}
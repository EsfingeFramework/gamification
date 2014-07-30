package com.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.annotation.RewardsToUser;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.user.UserStorage;

public class RewardsToUserProcessor implements AchievementProcessor {
	
	private boolean used;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RewardsToUser rtu = (RewardsToUser) an;
		used = rtu.used();
		name = rtu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.addAchievement(user, r);
	}
	
}

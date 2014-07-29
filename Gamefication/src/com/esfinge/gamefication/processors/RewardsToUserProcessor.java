package com.esfinge.gamefication.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.annotation.RewardsToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;

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

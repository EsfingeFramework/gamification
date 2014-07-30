package com.esfinge.gamification.processors;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.annotation.RemoveReward;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.user.UserStorage;

public class RemoveRewardProcessor implements AchievementProcessor {
	private boolean used;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RemoveReward rtu = (RemoveReward) an;
		used = rtu.used();
		name = rtu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.removeAchievement(user, r);

	}

}

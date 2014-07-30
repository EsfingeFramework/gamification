package com.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.achievement.Trophy;
import com.esfinge.gamification.annotation.TrophiesToUser;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.user.UserStorage;

public class TrophiesToUserProcessor implements AchievementProcessor {

	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		TrophiesToUser ttu = (TrophiesToUser) an;		
		name = ttu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
		Object user = UserStorage.getUserID();
		Trophy t = new Trophy(name);
		game.addAchievement(user, t);
	}
}

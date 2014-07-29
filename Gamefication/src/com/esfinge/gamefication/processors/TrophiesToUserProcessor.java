package com.esfinge.gamefication.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Tropy;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.annotation.TrophiesToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;

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
		Tropy t = new Tropy(name);
		game.addAchievement(user, t);
	}
}

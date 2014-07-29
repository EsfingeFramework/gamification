package com.esfinge.gamefication.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.annotation.RemovePoints;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;

public class RemovePointsProcessor implements AchievementProcessor {
	
	private int quantity;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RemovePoints r = (RemovePoints) an;
		quantity = r.quantity();
		name = r.name();

	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		Object user = UserStorage.getUserID();
		Point p = new Point(quantity, name);
		game.removeAchievement(user, p);

	}

}

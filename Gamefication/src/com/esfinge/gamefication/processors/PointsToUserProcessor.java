package com.esfinge.gamefication.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;

public class PointsToUserProcessor implements AchievementProcessor {
	
	private int quantity;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		PointsToUser ptu = (PointsToUser) an;
		quantity = ptu.quantity();
		name = ptu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
		Object user = UserStorage.getUserID();
		Point p = new Point(quantity, name);
		game.addAchievement(user, p);
	}

}

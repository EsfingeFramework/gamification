package com.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.annotation.PointsToUser;
import com.esfinge.gamification.achievement.Trophy;
import com.esfinge.gamification.annotation.TrophiesToUser;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.user.UserStorage;

public class thousandPointToUser implements AchievementProcessor {
	private int quantityPoint;
	private String namePoint;
	private String nameTrophy;
	
	@Override
	public void receiveAnnotation(Annotation point, Annotation trophy){
		PointsToUser ptu = (PointsToUser) point;
		TrophysToUser ttu = (TrophiesToUser) trophy;
		
		quantityPoint = ptu.quantity();
		namePoint = ptu.name();
		nameTrophy = ttu.name();
	}
	
	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args){
		Object user = UserStorage.getUserID();
		if (quantityPoint == 1000){
			Trophy t = new Trophy(nameThopy);
			game.addAchievement(user, t);
	}
}

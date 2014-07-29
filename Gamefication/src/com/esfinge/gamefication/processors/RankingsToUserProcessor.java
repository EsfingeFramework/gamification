package com.esfinge.gamefication.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Rank;
import com.esfinge.gamefication.annotation.RankingsToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;

public class RankingsToUserProcessor implements AchievementProcessor {

	private String name;
	private String level;
	
	@Override
	public void receiveAnnotation(Annotation an) {
		RankingsToUser rtu = (RankingsToUser) an;
		name = rtu.name();
		level = rtu.level();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {		
		Object user = UserStorage.getUserID();
		Rank r = new Rank(name, level);
		game.addAchievement(user, r);
	}

}

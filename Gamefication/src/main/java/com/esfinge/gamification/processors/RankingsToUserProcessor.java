package com.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.annotation.RankingsToUser;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.user.UserStorage;

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
		Ranking r = new Ranking(name, level);
		game.addAchievement(user, r);
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Ranking r = new Ranking(name, level);
		game.addAchievement(user, r);

		
	}

}

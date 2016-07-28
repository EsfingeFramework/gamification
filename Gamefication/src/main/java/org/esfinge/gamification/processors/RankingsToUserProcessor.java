package org.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.gamification.achievement.Ranking;
import org.esfinge.gamification.annotation.RankingsToUser;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.user.UserStorage;

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

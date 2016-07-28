package org.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.gamification.achievement.Ranking;
import org.esfinge.gamification.annotation.RemoveRankings;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.user.UserStorage;

public class RemoveRankingsProcessor implements AchievementProcessor {
	private String name;
	private String level;
	
	@Override
	public void receiveAnnotation(Annotation an) {
		RemoveRankings r = (RemoveRankings) an;
		name = r.name();
		level = r.level();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		Object user = UserStorage.getUserID();
		Ranking p = new Ranking(name, level);
		game.removeAchievement(user, p);		
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Ranking p = new Ranking(name, level);
		game.removeAchievement(user, p);		

		
	}

}

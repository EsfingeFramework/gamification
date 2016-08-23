package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.RankingsToUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

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

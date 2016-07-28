package org.esfinge.gamification.processors;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.esfinge.gamification.achievement.Trophy;
import org.esfinge.gamification.annotation.RemoveTrophy;
import org.esfinge.gamification.mechanics.Game;
import org.esfinge.gamification.user.UserStorage;

public class RemoveTrophyProcessor implements AchievementProcessor {
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RemoveTrophy ttu = (RemoveTrophy) an;		
		name = ttu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
		Object user = UserStorage.getUserID();
		Trophy t = new Trophy(name);
		game.removeAchievement(user, t);
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Trophy t = new Trophy(name);
		game.removeAchievement(user, t);
	}
}

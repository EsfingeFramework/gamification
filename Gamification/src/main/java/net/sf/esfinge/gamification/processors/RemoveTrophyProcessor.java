package net.sf.esfinge.gamification.processors;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.RemoveTrophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

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

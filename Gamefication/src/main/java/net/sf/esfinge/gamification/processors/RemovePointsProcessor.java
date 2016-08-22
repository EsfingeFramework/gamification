package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.RemovePoints;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

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

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Point p = new Point(quantity, name);
		game.removeAchievement(user, p);

		
	}

}

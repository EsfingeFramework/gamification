package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.PointsToUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

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

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Point p = new Point(quantity, name);
		game.addAchievement(user, p);
		
	}

}

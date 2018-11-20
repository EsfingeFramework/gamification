package net.sf.esfinge.gamification.auth;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.mechanics.Game;

public class GameAuthorizer implements Authorizer<MinPoin> {

	@Override
	public Boolean authorize(Method method, Game game, Object user) {

		MinPoin annotation = method.getAnnotation(MinPoin.class);

		if (annotation == null)
			throw new RuntimeException(MinPoin.class.getName() + " it's necessary to validade this process");

		int quantity = annotation.quantity();
		String achiev = annotation.achievementName();
		Point points;

		try {
			points = (Point) game.getAchievement(user, achiev);
			if (points == null) {
				throw new RuntimeException(
						"Annotation could not be found or is not set in the user");
			}
			if (quantity <= points.getQuantity()) {
				return true;
			}
			throw new RuntimeException("User " + user + " unauthorized to perform this operation");
		} catch (ClassCastException e) {
			Logger.getLogger(this.getClass().getName(), "Could be not be get user achievement: " + e.getMessage());
		}

		throw new RuntimeException("User " + user + " unauthorized to perform this operation");
	}

}

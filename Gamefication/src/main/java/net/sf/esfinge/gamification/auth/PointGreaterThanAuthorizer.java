package net.sf.esfinge.gamification.auth;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.AllowPointGraterThan;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.mechanics.Game;

public class PointGreaterThanAuthorizer implements Authorizer<AllowPointGraterThan> {

	@Override
	public Boolean authorize(Method method, Game game, Object user) {

		AllowPointGraterThan annotation = method.getAnnotation(AllowPointGraterThan.class);

		if (annotation == null)
			throw new GamificationConfigurationException(
					AllowPointGraterThan.class.getName() + " it's necessary to validade this process");

		int quantity = annotation.quantity();
		String achiev = annotation.achievementName();
		Point points;

		try {
			points = (Point) game.getAchievement(user, achiev);
			if (points == null) {
				throw new GamificationConfigurationException("Annotation could not be found or is not set in the user");
			}
			if (quantity <= points.getQuantity()) {
				return true;
			}
			throw new UnauthorizedException("User unauthorized to perform this operation");
		} catch (ClassCastException e) {
			Logger.getLogger(this.getClass().getName(), "Could be not be get user achievement: " + e.getMessage());
		}

		throw new UnauthorizedException("User unauthorized to perform this operation");
	}

}

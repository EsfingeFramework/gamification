package net.sf.esfinge.gamification.guardian.auth;

import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.AllowPointGraterThan;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.mechanics.Game;

public class PointGreaterThanAuthorizer implements Authorizer<AllowPointGraterThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowPointGraterThan securityAnnotation) {
		if (securityAnnotation == null)
			throw new GamificationConfigurationException(
					AllowPointGraterThan.class.getName() + " it's necessary to validade this process");

		int quantity = securityAnnotation.quantity();
		String achiev = securityAnnotation.achievementName();
		Point points;

		try {

			Game game = (Game) context.getEnvironment().get("game");
			Object user = (Object) context.getResource().get("currentUser");
			points = (Point) game.getAchievement(user, achiev);

			if (points == null)
				throw new GamificationConfigurationException("Annotation could not be found or is not set in the user");
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

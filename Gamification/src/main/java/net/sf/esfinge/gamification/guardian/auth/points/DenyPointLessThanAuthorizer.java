package net.sf.esfinge.gamification.guardian.auth.points;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.points.DenyPointLessOrEqualsThan;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyPointLessThanAuthorizer extends AuthorizationProcessor
		implements Authorizer<DenyPointLessOrEqualsThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyPointLessOrEqualsThan securityAnnotation) {

		Point points = (Point) process(context, securityAnnotation);

		if (Objects.nonNull(points) && securityAnnotation.quantity() <= points.getQuantity()) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"Authorized accesss: Required achievement: Point " + securityAnnotation.achievementName()
							+ " Quantity: " + securityAnnotation.quantity() + " User's quantity: "
							+ points.getQuantity());
			return true;
		}

		Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
				"Unauthorized accesss: Required achievement: Point " + securityAnnotation.achievementName()
						+ " Required quantity: " + securityAnnotation.quantity() + " User's quantity: "
						+ points.getQuantity());
		return false;

	}

}
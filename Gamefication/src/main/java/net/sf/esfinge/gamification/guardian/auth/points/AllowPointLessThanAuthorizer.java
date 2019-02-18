package net.sf.esfinge.gamification.guardian.auth.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.points.AllowPointLessOrEqualsThan;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowPointLessThanAuthorizer extends AuthorizationProcessor implements Authorizer<AllowPointLessOrEqualsThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowPointLessOrEqualsThan securityAnnotation) {

		Point points = (Point) process(context,securityAnnotation);

		if (securityAnnotation.quantity() >= points.getQuantity()) {
			return true;
		}

		return false;

	}

}
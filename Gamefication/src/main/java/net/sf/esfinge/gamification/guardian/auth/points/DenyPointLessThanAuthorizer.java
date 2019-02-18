package net.sf.esfinge.gamification.guardian.auth.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.points.DenyPointLessOrEqualsThan;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyPointLessThanAuthorizer extends AuthorizationProcessor implements Authorizer<DenyPointLessOrEqualsThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyPointLessOrEqualsThan securityAnnotation) {

		Point points = (Point) process(context, securityAnnotation);
		
		if (securityAnnotation.quantity() <= points.getQuantity()) {
			return true;
		}

		return false;

	}

}
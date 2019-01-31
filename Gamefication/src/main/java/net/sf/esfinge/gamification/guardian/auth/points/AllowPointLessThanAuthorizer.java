package net.sf.esfinge.gamification.guardian.auth.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.points.AllowPointLessOrEqualsThan;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowPointLessThanAuthorizer implements Authorizer<AllowPointLessOrEqualsThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowPointLessOrEqualsThan securityAnnotation) {

		AuthorizationProcessor processor = new AuthorizationProcessor(context);
		Point points = (Point) processor.process(securityAnnotation);

		if (securityAnnotation.quantity() >= points.getQuantity()) {
			return true;
		}

		throw new UnauthorizedException("User unauthorized to perform this operation");
	}

}
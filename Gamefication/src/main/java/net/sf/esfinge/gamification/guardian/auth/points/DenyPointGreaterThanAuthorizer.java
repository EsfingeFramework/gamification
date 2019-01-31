package net.sf.esfinge.gamification.guardian.auth.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.DenyPointGreaterThan;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.guardian.AuthorizationPointsProcessor;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyPointGreaterThanAuthorizer implements Authorizer<DenyPointGreaterThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyPointGreaterThan securityAnnotation) {
		
		AuthorizationProcessor processor = new AuthorizationPointsProcessor(context);
		Point points = (Point) processor.process(securityAnnotation);

		if (securityAnnotation.quantity() >= points.getQuantity()) {
			return true;
		}

		throw new UnauthorizedException("User unauthorized to perform this operation");
	}

}

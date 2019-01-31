package net.sf.esfinge.gamification.guardian.auth.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.auth.DenyPointLessOrEqualsThan;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.guardian.AuthorizationPointsProcessor;

public class DenyPointLessThanAuthorizer implements Authorizer<DenyPointLessOrEqualsThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyPointLessOrEqualsThan securityAnnotation) {

		AuthorizationPointsProcessor processor = new AuthorizationPointsProcessor(context);
		Point points = processor.process(securityAnnotation);
		
		if (securityAnnotation.quantity() <= points.getQuantity()) {
			return true;
		}

		throw new UnauthorizedException("User unauthorized to perform this operation");
	}

}
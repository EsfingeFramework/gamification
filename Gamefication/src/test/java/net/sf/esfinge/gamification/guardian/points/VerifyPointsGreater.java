package net.sf.esfinge.gamification.guardian.points;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

public class VerifyPointsGreater implements Authorizer<AuthPointGreaterThan> {

	@Override
	public Boolean authorize(AuthorizationContext context, AuthPointGreaterThan securityAnnotation) {
		System.out.println("teste");
		return true;
	}

}

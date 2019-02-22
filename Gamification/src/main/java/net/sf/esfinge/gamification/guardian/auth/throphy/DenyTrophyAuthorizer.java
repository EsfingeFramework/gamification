package net.sf.esfinge.gamification.guardian.auth.throphy;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.auth.trophy.DenyTrophy;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyTrophyAuthorizer extends AuthorizationProcessor implements Authorizer<DenyTrophy> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyTrophy securityAnnotation) {

		Trophy trophy = (Trophy) process(context, securityAnnotation);
		if (trophy.getName().equals(securityAnnotation.achievementName()))
			return false;

		return true;

	}

}
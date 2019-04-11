package net.sf.esfinge.gamification.guardian.auth.throphy;

import java.util.Objects;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.auth.trophy.AllowTrophy;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowTrophyAuthorizer extends AuthorizationProcessor implements Authorizer<AllowTrophy> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowTrophy securityAnnotation) {

		Trophy trophy = (Trophy) process(context, securityAnnotation);

		if (Objects.nonNull(trophy) && trophy.getName().equals(securityAnnotation.achievementName()))
			return true;

		return false;
	}

}
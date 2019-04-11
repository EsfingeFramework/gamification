package net.sf.esfinge.gamification.guardian.auth.ranking;

import java.util.Objects;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowLevel;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowLevelAuthorizer extends AuthorizationProcessor implements Authorizer<AllowLevel> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowLevel securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (Objects.nonNull(ranking) && ranking.getLevel().equals(securityAnnotation.level()))
			return true;

		return false;

	}

}

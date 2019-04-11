package net.sf.esfinge.gamification.guardian.auth.ranking;

import java.util.Objects;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyLevel;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyLevelAuthorizer extends AuthorizationProcessor implements Authorizer<DenyLevel> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyLevel securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (Objects.nonNull(ranking) && ranking.getLevel().equals(securityAnnotation.level()))
			return false;

		return true;

	}

}

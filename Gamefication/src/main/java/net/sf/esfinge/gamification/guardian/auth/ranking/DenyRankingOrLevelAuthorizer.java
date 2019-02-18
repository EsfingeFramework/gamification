package net.sf.esfinge.gamification.guardian.auth.ranking;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRankingOrLevel;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRankingOrLevelAuthorizer extends AuthorizationProcessor implements Authorizer<DenyRankingOrLevel> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyRankingOrLevel securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (ranking.getName().equals(securityAnnotation.achievementName())
				&& ranking.getLevel().equals(securityAnnotation.level()))
			return false;

		return true;

	}

}

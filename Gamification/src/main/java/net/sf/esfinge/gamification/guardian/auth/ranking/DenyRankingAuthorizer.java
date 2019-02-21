package net.sf.esfinge.gamification.guardian.auth.ranking;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRanking;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRankingAuthorizer extends AuthorizationProcessor implements Authorizer<DenyRanking> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyRanking securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (ranking.getName().equals(securityAnnotation.achievementName()))
			return false;

		return true;

	}

}

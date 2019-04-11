package net.sf.esfinge.gamification.guardian.auth.ranking;

import java.util.Objects;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowRanking;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowRankingAuthorizer extends AuthorizationProcessor implements Authorizer<AllowRanking> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowRanking securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (Objects.nonNull(ranking) && ranking.getName().equals(securityAnnotation.achievementName()))
			return true;

		return false;

	}

}

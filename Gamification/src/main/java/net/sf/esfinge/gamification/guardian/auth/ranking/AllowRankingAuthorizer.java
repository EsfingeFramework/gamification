package net.sf.esfinge.gamification.guardian.auth.ranking;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowRanking;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowRankingAuthorizer extends AuthorizationProcessor implements Authorizer<AllowRanking> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowRanking securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (Objects.nonNull(ranking) && ranking.getName().equals(securityAnnotation.achievementName())) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"Authorized accesss: Required achievement: Ranking " + securityAnnotation.achievementName());
			return true;
		}
		Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
				"Unauthorized accesss: Denied achievement: Ranking " + securityAnnotation.achievementName());
		return false;

	}

}

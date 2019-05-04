package net.sf.esfinge.gamification.guardian.auth.ranking;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRankingAndLevel;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRankingAndLevelAuthorizer extends AuthorizationProcessor implements Authorizer<DenyRankingAndLevel> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyRankingAndLevel securityAnnotation) {

		Ranking ranking = (Ranking) process(context, securityAnnotation);

		if (Objects.nonNull(ranking) && ranking.getName().equals(securityAnnotation.achievementName())
				&& ranking.getLevel().equals(securityAnnotation.level())) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Unauthorized accesss: Denied achievement: Ranking " + securityAnnotation.achievementName()
							+ " and Level " + securityAnnotation.level());
			return false;
		}

		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Authorized accesss: Required achievement: Ranking "
				+ securityAnnotation.achievementName() + " and Level " + securityAnnotation.level());
		return true;

	}

}

package net.sf.esfinge.gamification.guardian.auth.reward;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.annotation.auth.reward.DenyReward;
import net.sf.esfinge.gamification.exception.UnauthorizedException;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRewardAuthorizer implements Authorizer<DenyReward> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyReward securityAnnotation) {
		
		AuthorizationProcessor processor = new AuthorizationProcessor(context);
		Ranking ranking = (Ranking) processor.process(securityAnnotation);

		if (!ranking.getName().equals(securityAnnotation.achievementName()))
			return true;

		throw new UnauthorizedException("User unauthorized to perform this operation");
	}

}

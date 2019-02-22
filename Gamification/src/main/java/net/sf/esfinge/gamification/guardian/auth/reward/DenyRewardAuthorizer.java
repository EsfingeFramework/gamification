package net.sf.esfinge.gamification.guardian.auth.reward;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.annotation.auth.reward.DenyReward;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRewardAuthorizer extends AuthorizationProcessor implements Authorizer<DenyReward> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyReward securityAnnotation) {

		Reward reward = (Reward) process(context, securityAnnotation);
		if (!reward.isUsed() && reward.getName().equals(securityAnnotation.achievementName()))
			return false;

		return true;
	}

}

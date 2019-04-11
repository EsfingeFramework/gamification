package net.sf.esfinge.gamification.guardian.auth.reward;

import java.util.Objects;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.annotation.auth.reward.AllowReward;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class AllowRewardAuthorizer extends AuthorizationProcessor implements Authorizer<AllowReward> {

	@Override
	public Boolean authorize(AuthorizationContext context, AllowReward securityAnnotation) {

		Reward reward = (Reward) process(context, securityAnnotation);
		if (Objects.nonNull(reward) && !reward.isUsed()
				&& reward.getName().equals(securityAnnotation.achievementName()))
			return true;

		return false;
	}

}

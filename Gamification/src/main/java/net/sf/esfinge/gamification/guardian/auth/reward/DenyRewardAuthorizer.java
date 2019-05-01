package net.sf.esfinge.gamification.guardian.auth.reward;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.annotation.auth.reward.DenyReward;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyRewardAuthorizer extends AuthorizationProcessor implements Authorizer<DenyReward> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyReward securityAnnotation) {

		Reward reward = (Reward) process(context, securityAnnotation);
		if (Objects.nonNull(reward) && !reward.isUsed()
				&& reward.getName().equals(securityAnnotation.achievementName())) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Unauthorized accesss: Denied achievement: Reward available");
			return false;
		}
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"Authorized accesss: Required achievement: Reward unavailable");
		return true;
	}

}

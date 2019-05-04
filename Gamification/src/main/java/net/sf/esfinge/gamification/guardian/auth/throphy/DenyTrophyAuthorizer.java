package net.sf.esfinge.gamification.guardian.auth.throphy;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.auth.trophy.DenyTrophy;
import net.sf.esfinge.gamification.guardian.AuthorizationProcessor;

public class DenyTrophyAuthorizer extends AuthorizationProcessor implements Authorizer<DenyTrophy> {

	@Override
	public Boolean authorize(AuthorizationContext context, DenyTrophy securityAnnotation) {

		Trophy trophy = (Trophy) process(context, securityAnnotation);
		if (Objects.nonNull(trophy) || trophy.getName().equals(securityAnnotation.achievementName())) {
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
					"Unauthorized accesss: Denied achievement: Trophy " + securityAnnotation.achievementName());
			return false;
		}
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"Authorized accesss: Required achievement: Trophy " + securityAnnotation.achievementName());
		return true;

	}

}
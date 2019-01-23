package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.event.annotation.WhenUseReward;

public class WhenUseRewardEventListener extends AbstractEventListener<Reward> {

	private WhenUseReward whenUseReward;

	@Override
	public void setAnnotation(Annotation an) {
		this.whenUseReward = (WhenUseReward) an;

	}

	@Override
	public Boolean evaluate(Reward achievement, Object user) {
		return achievement.getName().equals(whenUseReward.value()) && achievement.isUsed();
	}

}

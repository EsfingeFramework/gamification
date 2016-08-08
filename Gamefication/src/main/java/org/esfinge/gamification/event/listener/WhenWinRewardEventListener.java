package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import org.esfinge.gamification.achievement.Reward;
import org.esfinge.gamification.event.annotation.WhenWinReward;

public class WhenWinRewardEventListener extends AbstractEventListener<Reward> {

	private WhenWinReward an;
	
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenWinReward) an;
	}

	@Override
	public Boolean evaluate(Reward achievement, Object user) {
		return achievement.getName().equals(an.value())
				&& !achievement.isUsed();
	}

}

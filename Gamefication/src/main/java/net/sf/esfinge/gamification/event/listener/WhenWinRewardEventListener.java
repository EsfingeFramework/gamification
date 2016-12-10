package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.event.annotation.WhenWinReward;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class WhenWinRewardEventListener extends AbstractEventListener<Reward> implements AchievementProcessor {

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
	
	// AchievementProcessor
	@Override
	public void receiveAnnotation(Annotation an) {	
	}		
	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
	}
	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {		
	}

}

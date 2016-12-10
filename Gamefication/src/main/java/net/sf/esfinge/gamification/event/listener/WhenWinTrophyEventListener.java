package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.event.annotation.WhenWinTrophy;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class WhenWinTrophyEventListener extends AbstractEventListener<Trophy> implements AchievementProcessor {

	private WhenWinTrophy an;
	
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenWinTrophy) an;
	}
	@Override
	public Boolean evaluate(Trophy achievement, Object user) {
		return achievement.getName().equals(an.value());
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

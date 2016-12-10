package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.event.annotation.WhenReachRanking;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class WhenReachRankingEventListener extends AbstractEventListener<Ranking> implements AchievementProcessor {

	private WhenReachRanking an;

	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenReachRanking) an; 
	}
	@Override
	public Boolean evaluate(Ranking achievement, Object user) {
		return achievement.getName().equals(an.name()) && achievement.getLevel().equals(an.value());
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

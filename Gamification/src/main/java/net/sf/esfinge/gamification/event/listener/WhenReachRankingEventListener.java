package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.event.annotation.WhenReachRanking;

public class WhenReachRankingEventListener extends AbstractEventListener<Ranking> {

	private WhenReachRanking an;

	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenReachRanking) an; 
	}

	@Override
	public Boolean evaluate(Ranking achievement, Object user) {
		return achievement.getName().equals(an.name()) && achievement.getLevel().equals(an.value());
	}
	


}

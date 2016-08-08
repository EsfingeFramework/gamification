package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import org.esfinge.gamification.achievement.Ranking;
import org.esfinge.gamification.event.annotation.WhenReachRanking;

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

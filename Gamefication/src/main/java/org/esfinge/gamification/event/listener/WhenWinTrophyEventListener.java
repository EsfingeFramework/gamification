package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import org.esfinge.gamification.achievement.Trophy;
import org.esfinge.gamification.event.annotation.WhenWinTrophy;

public class WhenWinTrophyEventListener extends AbstractEventListener<Trophy> {

	private WhenWinTrophy an;
	
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenWinTrophy) an;
	}

	@Override
	public Boolean evaluate(Trophy achievement, Object user) {
		return achievement.getName().equals(an.value());
	}

}

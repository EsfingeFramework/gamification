package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;

import org.esfinge.gamification.achievement.Point;
import org.esfinge.gamification.event.annotation.WhenReachPoints;

public class WhenReachPointsEventListener extends AbstractEventListener<Point>  {

	private WhenReachPoints an;
	
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenReachPoints) an;
	}
	@Override
	public Boolean evaluate(Point achievement, Object user) {
		return achievement.getName().equals(an.name()) && achievement.getQuantity()>=an.value();
	}

}

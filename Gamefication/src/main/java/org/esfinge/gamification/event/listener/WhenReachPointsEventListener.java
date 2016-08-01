package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;

import org.esfinge.gamification.achievement.Point;
import org.esfinge.gamification.event.annotation.WhenReachPoints;

public class WhenReachPointsEventListener extends AbstractEventListener  {

	private WhenReachPoints an;
	
	@Override
	public BiPredicate<Point, Object> getEvaluation() {
		return (Point a, Object user) -> a.getName().equals(an.type()) && a.getQuantity()>=an.value();
	}
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenReachPoints) an;
	}

}

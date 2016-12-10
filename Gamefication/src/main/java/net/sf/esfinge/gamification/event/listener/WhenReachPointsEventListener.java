package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.event.annotation.WhenReachPoints;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class WhenReachPointsEventListener extends AbstractEventListener<Point> implements AchievementProcessor {

	private WhenReachPoints an;
	
	@Override
	public void setAnnotation(Annotation an) {
		this.an = (WhenReachPoints) an;
	}
	@Override
	public Boolean evaluate(Point achievement, Object user) {
		return achievement.getName().equals(an.name()) && achievement.getQuantity()>=an.value();
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

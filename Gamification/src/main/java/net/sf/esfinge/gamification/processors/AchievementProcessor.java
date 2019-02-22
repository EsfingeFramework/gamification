package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.mechanics.Game;

public interface AchievementProcessor {
	
	public void receiveAnnotation(Annotation an);
	
	public void process(Game game, Object encapsulated, Method method, Object[] args);

	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args);

}

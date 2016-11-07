package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.metadata.annotation.container.InitProcessor;

public interface AchievementProcessor {
	
	@InitProcessor
	public void receiveAnnotation(Annotation an);
	
	public void process(Game game, Object encapsulated, Method method, Object[] args);

	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args);

}

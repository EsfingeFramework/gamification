package com.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.mechanics.Game;

public interface AchievementProcessor {
	
	public void receiveAnnotation(Annotation an);
	
	public void process(Game game, Object encapsulated, Method method, Object[] args);

}

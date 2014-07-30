package com.esfinge.gamification.proxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamification.annotation.GamificationProcessor;
import com.esfinge.gamification.mechanics.Game;
import com.esfinge.gamification.processors.AchievementProcessor;


public class GameInvoker {
	
	public static GameInvoker getInstance(){
		if(instance==null){
			instance = new GameInvoker();
		}
		return instance;
	}
	
	private static GameInvoker instance;
	
	private Game game;
	
	private GameInvoker(){
	}
	
	public void setGame(Game g){
		game = g;
	}
	
	public void registerAchievment(Object encapsulated, Method method, Object[] args)
			throws Throwable {
		
		for(Annotation an : method.getAnnotations()){
			Class<? extends Annotation> anType = an.annotationType();
			if(anType.isAnnotationPresent(GamificationProcessor.class)){
				GamificationProcessor gp = anType.getAnnotation(GamificationProcessor.class);
				Class<? extends AchievementProcessor> c = gp.value();
				AchievementProcessor ap = c.newInstance();
				ap.receiveAnnotation(an);
				ap.process(game, encapsulated, method, args);
			}
		}
	
	}
}
	
	


package com.esfinge.gamefication.proxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.annotation.GamificationProcessor;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.processors.AchievementProcessor;
import com.esfinge.gamefication.user.UserStorage;


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
	
	


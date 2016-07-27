package com.esfinge.gamification.proxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
		List<AchievementProcessor> achievementProcessorList = new ArrayList<>();
		
		//get annotations
		getAnnotations(method, achievementProcessorList);
		
		//process
		for (AchievementProcessor ap : achievementProcessorList) {
			ap.process(game, encapsulated, method, args);
		}
		
	}

	private void getAnnotations(Method method, List<AchievementProcessor> apList) throws InstantiationException, IllegalAccessException {
		//method
		for(Annotation an : method.getAnnotations()){
			createAchievementProcessor(apList, an);	
		}
		
		//class
		for(Annotation an : method.getClass().getAnnotations()){
			createAchievementProcessor(apList, an);
		}
		
		//TODO: procurar dentro de outras anotacoes
	}

	private void createAchievementProcessor(List<AchievementProcessor> apList,
			Annotation an) throws InstantiationException,
			IllegalAccessException {
		Class<? extends Annotation> anType = an.annotationType();
		if(anType.isAnnotationPresent(GamificationProcessor.class)){
			GamificationProcessor gp = anType.getAnnotation(GamificationProcessor.class);
			Class<? extends AchievementProcessor> c = gp.value();
			AchievementProcessor ap = c.newInstance();				
			ap.receiveAnnotation(an);
			apList.add(ap);
		}
	}
}
	
	


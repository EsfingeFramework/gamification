package net.sf.esfinge.gamification.proxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.sf.esfinge.gamification.annotation.GamificationProcessor;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;


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
	
	public Game getGame() {
		return game;
	}
	
	public void registerAchievment(Object encapsulated, Method method, Object[] args)
			throws Throwable {
		//process
		for (AchievementProcessor ap : getAnnotations(method)) {
			ap.process(game, encapsulated, method, args);
		}
		
	}

	private List<AchievementProcessor> getAnnotations(Method method) throws InstantiationException, IllegalAccessException {
		List<AchievementProcessor> apList = new ArrayList<>();
		//method
		for(Annotation an : method.getAnnotations()){
			createAchievementProcessor(an).ifPresent(ap -> apList.add(ap));	
		}
		
		//class
		for(Annotation an : method.getClass().getAnnotations()){
			createAchievementProcessor(an).ifPresent(ap -> apList.add(ap));	
		}
		return apList;
		//TODO: procurar dentro de outras anotacoes
	}

	private Optional<AchievementProcessor> createAchievementProcessor(Annotation an) throws InstantiationException,
			IllegalAccessException {
		Class<? extends Annotation> anType = an.annotationType();
		if(anType.isAnnotationPresent(GamificationProcessor.class)){
			GamificationProcessor gp = anType.getAnnotation(GamificationProcessor.class);
			Class<? extends AchievementProcessor> c = gp.value();
			AchievementProcessor ap = c.newInstance();				
			ap.receiveAnnotation(an);
			return Optional.of(ap);
		}
		return Optional.empty();
	}
}
	
	


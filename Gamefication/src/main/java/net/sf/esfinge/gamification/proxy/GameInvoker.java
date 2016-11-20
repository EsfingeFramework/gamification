package net.sf.esfinge.gamification.proxy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.sf.esfinge.gamification.annotation.GamificationProcessor;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;
import net.sf.esfinge.metadata.AnnotationReader;

public class GameInvoker {
	
	public static GameInvoker getInstance(){
		if(instance==null)
			instance = new GameInvoker();
		
		return instance;
	}
	
	private static GameInvoker instance;
	private Game game;
	
	private Map<String, Map<Method, AchievementProcessor>> containerMapDb; 
	
	private GameInvoker(){
		containerMapDb = new HashMap<>();
	}
	
	public void setGame(Game g){
		game = g;
	}
	
	public void registerAchievment(Object encapsulated, Method method, Object[] args)
			throws Throwable {
				
		if (encapsulated != null) {	
			createContainerAndProcess(encapsulated, method, args);
		}else{
			for (AchievementProcessor ap : getAnnotations(method))
				ap.process(game, encapsulated, method, args);	
		}
		
	}

	private void createContainerAndProcess(Object encapsulated, Method method, Object[] args) {
		Map<Method, AchievementProcessor> mapa = null;
		String className = encapsulated.getClass().getName();
		
		if( containerMapDb.containsKey(className) )		
			mapa = containerMapDb.get(className);			
		else
			mapa = readMetadatasFrom(encapsulated);
		
		for (Map.Entry<Method, AchievementProcessor> entry : mapa.entrySet()){			    
		    AchievementProcessor ap = entry.getValue();
		    Method m = entry.getKey();
		 	
		    if(m.equals(method))
		    	ap.process(game, encapsulated, method, args);
		}
	}

	private Map<Method, AchievementProcessor> readMetadatasFrom(Object encapsulated) {
		AnnotationReader reader = new AnnotationReader();
		
		Map<Method, AchievementProcessor> mapa = null;
		ContainerGame container = null;
		
		try {
			container = (ContainerGame) reader.readingAnnotationsTo(encapsulated.getClass(), 
																		ContainerGame.class);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		mapa = container.getMapa();			
		containerMapDb.put(encapsulated.getClass().getName(), mapa);
		
		return mapa;
	}
	
	
	private List<AchievementProcessor> getAnnotations(Method method) throws InstantiationException, IllegalAccessException {
		List<AchievementProcessor> apList = new ArrayList<>();
		
		//method
		for(Annotation an : method.getAnnotations())
			createAchievementProcessor(an).ifPresent(ap -> apList.add(ap));	
		
		//class
		for(Annotation an : method.getClass().getAnnotations())
			createAchievementProcessor(an).ifPresent(ap -> apList.add(ap));	
		
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

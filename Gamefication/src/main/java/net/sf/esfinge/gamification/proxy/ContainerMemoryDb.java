package net.sf.esfinge.gamification.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.esfinge.gamification.processors.AchievementProcessor;
import net.sf.esfinge.metadata.AnnotationReader;

public class ContainerMemoryDb {
	
	private Map<String, Map<Method, AchievementProcessor>> containerMapDb;
	
	public ContainerMemoryDb(){		
		containerMapDb = new HashMap<>();		
	}
	
	public Map<String, Map<Method, AchievementProcessor>> getContainerMapDb(){
		return containerMapDb;			
	} 
	
	public Map<Method, AchievementProcessor> getMetadatasFrom(Object encapsulated){		
		String className = encapsulated.getClass().getName();
		
		if( containerMapDb.containsKey(className) )		
			return containerMapDb.get(className);			
		else
			return readMetadatasFrom(encapsulated);
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

}

package net.sf.esfinge.gamification.proxy;
import java.lang.reflect.Method;
import java.util.Map;

import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class GameInvoker {
	
	public static GameInvoker getInstance(){
		if(instance==null)
			instance = new GameInvoker();
		
		return instance;
	}
	
	private static GameInvoker instance;
	
	private Game game;		
	private ContainerMemoryDb containerMapDb;
	
	private GameInvoker(){
		containerMapDb = new ContainerMemoryDb();
	}
	
	public void setGame(Game g){
		game = g;
	}
	
	public void registerAchievment(Object encapsulated, Method method, Object[] args)
			throws Throwable {	
		
		createContainerAndProcess(encapsulated, method, args);		
	}

	private void createContainerAndProcess(Object encapsulated, Method method, Object[] args) {		
		Map<Method, AchievementProcessor> mapa = containerMapDb.getMetadatasFrom(encapsulated);
		
		for (Map.Entry<Method, AchievementProcessor> entry : mapa.entrySet()){			    
		    AchievementProcessor ap = entry.getValue();
		    Method m = entry.getKey();
		 	
		    if(m.equals(method))
		    	ap.process(game, encapsulated, method, args);
		}
	}
	
}

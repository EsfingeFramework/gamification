package org.esfinge.gamification.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.esfinge.gamification.exception.GamificationConfigurationException;
import org.esfinge.metadata.AnnotationValidationException;
import org.esfinge.metadata.validate.MetadataValidator;


public class GameProxy implements InvocationHandler{
	
	
	private Object encapsulated;
	
	private GameProxy(Object encapsulated) {
		this.encapsulated = encapsulated;
	}

	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			Object returnValue = method.invoke(encapsulated, args);
			GameInvoker gameInvoker = GameInvoker.getInstance();
			gameInvoker.registerAchievment(encapsulated, method, args);
			
			return returnValue;
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} 
	}
	
	public static <T> T createProxy(T encapsulated){
		Object obj =  Proxy.newProxyInstance(encapsulated.getClass().getClassLoader(),
				encapsulated.getClass().getInterfaces(), 
				new GameProxy(encapsulated));
		
		//here is called Esfinge Metadata validator
		try {
			MetadataValidator.validateMetadataOn(encapsulated.getClass());
			for(Class interf : encapsulated.getClass().getInterfaces()){
				MetadataValidator.validateMetadataOn(interf);
			}
		} catch (AnnotationValidationException e){
			throw new GamificationConfigurationException("Invalid annotation configuration", e);
		}
		
		return (T)obj;	
	}	

}

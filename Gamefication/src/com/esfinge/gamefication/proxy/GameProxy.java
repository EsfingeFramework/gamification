package com.esfinge.gamefication.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


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
	
	public static Object createProxy(Object encapsulated){
		return Proxy.newProxyInstance(encapsulated.getClass().getClassLoader(),
				encapsulated.getClass().getInterfaces(),
				new GameProxy(encapsulated));	
	}	

}

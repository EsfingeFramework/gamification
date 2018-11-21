package net.sf.esfinge.gamification.auth.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.esfinge.gamification.mechanics.Game;

public class GenericListener {
	/**
	 * Return a class that implements the interface that contains the declaration
	 * for listenerMethod. In this new class, listenerMethod will call first invoke
	 * method of Authorization Handler to the incoming Event.
	 */
	public static Object create(Method listenerMethod, Game game, Object user) {
		/*
		 * Create an instance of the AuthorizationHandler and override the invoke method
		 * to handle the invoking method on the target.
		 */
		InvocationHandler handler = new AuthorizationHandler();
		
		Class cls = listenerMethod.getDeclaringClass();
		ClassLoader cl = cls.getClassLoader();
		return Proxy.newProxyInstance(cl, new Class[] { cls }, handler);
	}
}

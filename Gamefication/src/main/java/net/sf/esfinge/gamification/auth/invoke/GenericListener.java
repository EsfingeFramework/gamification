package net.sf.esfinge.gamification.auth.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import net.sf.esfinge.gamification.mechanics.Game;

public class GenericListener {
	/**
	 * Return a class that implements the interface that contains the declaration
	 * for listenerObject. In this new class, listenerObject will call first invoke
	 * method of AuthorizationHandler.
	 */
	public static Object create(Object listenerObject, Game game, Object user) {
		/**
		 * Create a instance of the AuthorizationHandler and override the invoke method
		 * to handle the invoking method on the target.
		 */
		InvocationHandler handler = new AuthorizationHandler(game, user);

		return Proxy.newProxyInstance(listenerObject.getClass().getClassLoader(),
				listenerObject.getClass().getInterfaces(), handler);

	}
}

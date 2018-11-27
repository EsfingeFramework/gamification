package net.sf.esfinge.gamification.auth.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.auth.GameAuthorizer;
import net.sf.esfinge.gamification.mechanics.Game;

/**
 * Implementation of the InvocationHandler which handles the basic object
 * methods.
 */
class AuthorizationHandler implements InvocationHandler {

	private Game game;
	private Object user;
	private Object listenedObject;

	public AuthorizationHandler(Game game, Object listenedObject, Object user) {
		super();
		this.game = game;
		this.user = user;
		this.listenedObject = listenedObject;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object invokedObject = method.invoke(listenedObject, args);
		GameAuthorizer gameAuthorizer = new GameAuthorizer();
		gameAuthorizer.authorize(method, game, user);
		
		return invokedObject;
	}
}
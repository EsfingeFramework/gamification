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

	public AuthorizationHandler(Game game, Object user) {
		super();
		this.game = game;
		this.user = user;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		GameAuthorizer gameAuthorizer = new GameAuthorizer();
		gameAuthorizer.authorize(method, game, user);
		
		return proxy;
	}
}
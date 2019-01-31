package net.sf.esfinge.gamification.guardian;

import java.lang.annotation.Annotation;

import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.mechanics.Game;

public abstract class AuthorizationProcessor {

	public abstract Achievement process(Annotation securityAnnotation);

	private Game game;
	private Object user;

	public AuthorizationProcessor(AuthorizationContext context) {

		this.game = (Game) context.getEnvironment().get("game");
		this.user = (Object) context.getResource().get("currentUser");

	}

	public Game getGame() {
		return game;
	}

	public Object getUser() {
		return user;
	}

}

package net.sf.esfinge.gamification.guardian.auth;

import org.esfinge.guardian.annotation.config.PopulatorClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;

import net.sf.esfinge.gamification.mechanics.Game;

//@PopulatorClass(GamificationAuthorizationPopulator.class)
public class GamificationAuthorizationPopulator {//implements Populator {

	private Game game;
	private Object user;

//	@Override
	public void populate(AuthorizationContext context) {
		context.getResource().put("currentUser", getUser());
		context.getEnvironment().put("game", getGame());
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

}

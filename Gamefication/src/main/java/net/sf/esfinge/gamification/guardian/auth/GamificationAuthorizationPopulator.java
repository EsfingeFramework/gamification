package net.sf.esfinge.gamification.guardian.auth;

import org.esfinge.guardian.annotation.config.PopulatorClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;

import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

@PopulatorClass(GamificationAuthorizationPopulator.class)
public class GamificationAuthorizationPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {
		context.getResource().put("currentUser", getUser());
		context.getEnvironment().put("game", getGame());
	}

	public Game getGame() {
		return GameInvoker.getInstance().getGame();
	}

	public Object getUser() {
		return UserStorage.getUserID();
	}

}

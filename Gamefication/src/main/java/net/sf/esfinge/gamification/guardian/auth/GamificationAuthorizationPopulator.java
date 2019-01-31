package net.sf.esfinge.gamification.guardian.auth;

import java.util.Optional;

import org.esfinge.guardian.annotation.config.PopulatorClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.populator.Populator;

import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

@PopulatorClass(GamificationAuthorizationPopulator.class)
public class GamificationAuthorizationPopulator implements Populator {

	@Override
	public void populate(AuthorizationContext context) {

		Optional<Game> optionalGame = Optional.ofNullable(GameInvoker.getInstance().getGame());
		Optional<Object> optionalUser = Optional.ofNullable(UserStorage.getUserID());

		if (optionalGame.isPresent())
			context.getEnvironment().put("game", optionalGame.get());
		else
			throw new GamificationConfigurationException(
					"Game cannot be found, use setGame method of GameInvoker class");

		if (optionalUser.isPresent())
			context.getResource().put("currentUser", optionalUser.get());
		else
			throw new GamificationConfigurationException(
					"Current user cannot be found, use setUser method of UserStorage class");

	}

}

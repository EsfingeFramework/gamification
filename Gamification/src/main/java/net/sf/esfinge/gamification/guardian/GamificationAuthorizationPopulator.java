package net.sf.esfinge.gamification.guardian;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.esfinge.guardian.annotation.config.AuthorizerClass;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.init.ClasspathAnnotations;
import org.esfinge.guardian.init.Repository;
import org.esfinge.guardian.populator.Populator;
import org.reflections.Reflections;

import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.proxy.GameInvoker;
import net.sf.esfinge.gamification.user.UserStorage;

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

		try {
			Reflections reflections = new Reflections("net.sf.esfinge.gamification.annotation.auth");
			Set<?> secureGamificationAnnotations = reflections.getTypesAnnotatedWith(AuthorizerClass.class);
			Repository repository = context.getRepository();

			Set<String> annotationsNames = new HashSet<String>();
			secureGamificationAnnotations.forEach(annotation -> {
				annotationsNames.add(annotation.toString().split(" ")[1]);
			});

			if (Objects.isNull(repository.getCachedClasspathAnnotations()))
				repository.setCachedClasspathAnnotations(ClasspathAnnotations.getInstance(context));
			
			ClasspathAnnotations classpathAnnotations = repository.getCachedClasspathAnnotations();
			classpathAnnotations.getClasspathAnnotations().put(AuthorizerClass.class.getName(), annotationsNames);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

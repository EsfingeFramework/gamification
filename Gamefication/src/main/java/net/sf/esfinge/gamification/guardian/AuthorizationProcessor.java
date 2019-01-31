package net.sf.esfinge.gamification.guardian;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import org.esfinge.guardian.context.AuthorizationContext;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.mechanics.Game;

public class AuthorizationProcessor {

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

	public Achievement process(Annotation securityAnnotation) {
		if (Objects.isNull(securityAnnotation))
			throw new GamificationConfigurationException(
					"One security annotation it's necessary to validade this process");

		Class<? extends Annotation> annotationType = securityAnnotation.annotationType();
		String achiev = "";

		try {
			Method achievGetter = annotationType.getMethod("achievementName");
			achiev = (String) achievGetter.invoke(securityAnnotation);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException invokeException) {
			throw new GamificationConfigurationException(
					"Achievement name property could not be found in annotation " + annotationType.getName(),
					invokeException);
		}

		Achievement points = game.getAchievement(user, achiev);

		if (Objects.isNull(points))
			throw new GamificationConfigurationException(
					"Annotation could not be found or current user is not set correctly");

		return points;
	}

}

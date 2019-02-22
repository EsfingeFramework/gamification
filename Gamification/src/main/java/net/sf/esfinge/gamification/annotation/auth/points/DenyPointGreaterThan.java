package net.sf.esfinge.gamification.annotation.auth.points;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.guardian.auth.points.DenyPointGreaterThanAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;


/**
 * 
 * Annotation for validate a maximum quantity of {@link Point}
 * 
 * @param quantity        minimum quantity for user access resources
 * @param achievementName achievement to listen
 *
 */

@Retention(RUNTIME)
@Target(METHOD)
@AuthorizerClass(DenyPointGreaterThanAuthorizer.class)
public @interface DenyPointGreaterThan {

	@NotNull int quantity();
	@NotNull String achievementName();

}

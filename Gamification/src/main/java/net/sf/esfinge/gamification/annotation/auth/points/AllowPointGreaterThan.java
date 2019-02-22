package net.sf.esfinge.gamification.annotation.auth.points;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.guardian.auth.points.AllowPointGreaterThanAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;

/**
 * 
 * Annotation for validate a minimum quantity of {@link Point}
 * 
 * @param quantity        minimum quantity for user access resources
 * @param achievementName achievement to listen
 *
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@AuthorizerClass(AllowPointGreaterThanAuthorizer.class)
public @interface AllowPointGreaterThan {
	
	@NotNull int quantity();
	@NotNull String achievementName();
}

package net.sf.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.guardian.auth.PointGreaterThanAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;

/**
 * 
 * Annotation for validate a minimum quantity of points
 * 
 * @param quantity        minimum quantity for user access resources
 * @param achievementName achievement to listen
 *
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@AuthorizerClass(PointGreaterThanAuthorizer.class)
public @interface AllowPointGraterThan {
	
	@NotNull int quantity();
	@NotNull String achievementName();
}

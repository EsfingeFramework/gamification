package net.sf.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.esfinge.metadata.annotation.validator.NotNull;

/**
 * 
 * Interface for validate a minimum quantity to achievements
 * @param quantity 			minimum quantity for user access resources
 * @param achievementName	achievement to listen
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinPoin {
	
	@NotNull int quantity();
	@NotNull String achievementName();
}

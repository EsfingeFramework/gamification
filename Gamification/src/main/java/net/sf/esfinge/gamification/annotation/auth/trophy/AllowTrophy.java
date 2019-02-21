package net.sf.esfinge.gamification.annotation.auth.trophy;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.guardian.auth.throphy.AllowTrophyAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;

/***
 * 
 * Allow users execution with a specific {@link Trophy} 
 *
 */

@Retention(RUNTIME)
@Target(METHOD)
@AuthorizerClass(AllowTrophyAuthorizer.class)
public @interface AllowTrophy {

	@NotNull String achievementName();
	
}

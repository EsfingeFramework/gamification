package net.sf.esfinge.gamification.annotation.auth.reward;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.guardian.auth.reward.DenyRewardAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;

/***
 * 
 * Deny users execution with a specific {@link Reward} 
 *
 */

@Retention(RUNTIME)
@Target(METHOD)
@AuthorizerClass(DenyRewardAuthorizer.class)
public @interface DenyReward {
	
	@NotNull String achievementName();
	
}

package net.sf.esfinge.gamification.annotation.auth.reward;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.esfinge.guardian.annotation.config.AuthorizerClass;

import net.sf.esfinge.gamification.guardian.auth.reward.AllowRewardAuthorizer;
import net.sf.esfinge.metadata.annotation.validator.NotNull;

/***
 * 
 * Allow users execution with a specific {@link Reward} 
 *
 */

@Retention(RUNTIME)
@Target(METHOD)
@AuthorizerClass(AllowRewardAuthorizer.class)
public @interface AllowReward {
	
	@NotNull String achievementName();

}

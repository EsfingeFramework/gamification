package net.sf.esfinge.gamification.event.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.event.listener.WhenWinRewardEventListener;

@Retention(RUNTIME)
@Target(METHOD)
@EventListenerImplementation(WhenWinRewardEventListener.class)
public @interface WhenWinReward {
	String value();
}

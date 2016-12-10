package net.sf.esfinge.gamification.event.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.annotation.GamificationProcessor;
import net.sf.esfinge.gamification.event.listener.WhenReachPointsEventListener;
import net.sf.esfinge.gamification.event.listener.WhenWinRewardEventListener;

@Retention(RUNTIME)
@Target(METHOD)
@EventListenerImplementation(WhenWinRewardEventListener.class)
@GamificationProcessor(WhenWinRewardEventListener.class)
public @interface WhenWinReward {
	String value();
}

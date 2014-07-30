package com.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.esfinge.gamification.processors.RemoveRewardProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(RemoveRewardProcessor.class)
public @interface RemoveReward {
	boolean used();
	String name();
}

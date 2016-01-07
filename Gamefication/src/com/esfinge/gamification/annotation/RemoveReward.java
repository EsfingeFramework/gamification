package com.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.RemoveRewardProcessor;
import com.esfinge.gamification.validate.annotation.ProhibitsGamification;
@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@ProhibitsGamification(RewardsToUser.class)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@GamificationProcessor(RemoveRewardProcessor.class)
public @interface RemoveReward {
	boolean used();
	String name();
}

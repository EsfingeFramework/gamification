package com.esfinge.gamefication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamefication.processors.RewardsToUserProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(RewardsToUserProcessor.class)
public @interface RewardsToUser {
	boolean used();
	String name();
}

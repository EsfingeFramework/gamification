package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamification.processors.TrophiesToUserProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(TrophiesToUserProcessor.class)
public @interface TrophiesToUser {
	String name();
}

package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamification.processors.AchievementProcessor;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GamificationProcessor {
	Class<? extends AchievementProcessor> value();
}

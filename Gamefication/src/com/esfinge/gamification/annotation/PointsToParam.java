package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamification.processors.PointsToParameterProcessor;
import com.esfinge.gamification.processors.PointsToUserProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(PointsToParameterProcessor.class)
public @interface PointsToParam {
	int quantity();
	String name();
	String param();
	String prop() default "";
}

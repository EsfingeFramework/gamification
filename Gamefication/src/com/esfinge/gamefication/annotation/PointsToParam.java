package com.esfinge.gamefication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamefication.processors.PointsToParameterProcessor;
import com.esfinge.gamefication.processors.PointsToUserProcessor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(PointsToParameterProcessor.class)
public @interface PointsToParam {
	int quantity();
	String name();
	String param();
	String prop() default "";
}

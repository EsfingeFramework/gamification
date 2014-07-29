package com.esfinge.gamefication.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.esfinge.gamefication.processors.PointsToUserProcessor;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(PointsToUserProcessor.class)
public @interface PointsToUser {
	int quantity();
	String name();
}

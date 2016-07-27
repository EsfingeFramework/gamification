package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.validator.MinValue;
import org.esfinge.metadata.annotation.validator.NotNull;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.PointsToParameterProcessor;
import com.esfinge.gamification.validate.annotation.ProhibitsGamification;
@SearchOnEnclosingElements
@SearchInsideAnnotations
@ProhibitsGamification(value = RemovePointsToParam.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@GamificationProcessor(PointsToParameterProcessor.class)
public @interface PointsToParam {
	@MinValue(value=0)
	int quantity();
	@NotNull
	String name();
	String param();
	String prop() default "";
}

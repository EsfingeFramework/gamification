package net.sf.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.annotation.*;
import net.sf.esfinge.gamification.processors.RemovePointsProcessor;
import net.sf.esfinge.gamification.validate.annotation.ProhibitsGamification;
import net.sf.esfinge.metadata.annotation.validator.MinValue;
import net.sf.esfinge.metadata.annotation.validator.NotNull;
import net.sf.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import net.sf.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@ProhibitsGamification(PointsToUser.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@GamificationProcessor(RemovePointsProcessor.class)
public @interface RemovePoints {
	@MinValue(value=0)
	int quantity();
	@NotNull
	String name();
}

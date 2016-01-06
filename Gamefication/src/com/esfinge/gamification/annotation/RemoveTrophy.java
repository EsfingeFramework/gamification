package com.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.RemoveTrophyProcessor;
import com.esfinge.gamification.validate.annotation.ProhibitsGamification;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@ProhibitsGamification(value = TrophiesToUser.class)
@GamificationProcessor(RemoveTrophyProcessor.class)
public @interface RemoveTrophy {
	String name();
}

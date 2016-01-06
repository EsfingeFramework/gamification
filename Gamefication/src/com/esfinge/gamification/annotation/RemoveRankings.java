package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.RemoveRankingsProcessor;
import com.esfinge.gamification.validate.annotation.ProhibitsGamification;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@ProhibitsGamification(RankingsToUser.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@GamificationProcessor(RemoveRankingsProcessor.class)
public @interface RemoveRankings {
	String name();
	String level();
}

package com.esfinge.gamification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.NotNull;
import org.esfinge.metadata.annotation.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.RankingsToUserProcessor;
import com.esfinge.gamification.validate.annotation.ProhibitsGamification;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@ProhibitsGamification(RemoveRankings.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@GamificationProcessor(RankingsToUserProcessor.class)
public @interface RankingsToUser {
	@NotNull
	String name();
	@NotNull
	String level();
}

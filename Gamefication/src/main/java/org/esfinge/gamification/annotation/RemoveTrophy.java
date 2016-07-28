package org.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.gamification.processors.RemoveTrophyProcessor;
import org.esfinge.gamification.validate.annotation.ProhibitsGamification;
import org.esfinge.metadata.annotation.validator.NotNull;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@ProhibitsGamification(value = TrophiesToUser.class)
@GamificationProcessor(RemoveTrophyProcessor.class)
public @interface RemoveTrophy {
	@NotNull
	String name();
}
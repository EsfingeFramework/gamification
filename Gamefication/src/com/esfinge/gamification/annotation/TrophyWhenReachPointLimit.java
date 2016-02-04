package com.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.MinValue;
import org.esfinge.metadata.annotation.NeedsToHave;
import org.esfinge.metadata.annotation.NotNull;
import org.esfinge.metadata.annotation.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.SearchOnEnclosingElements;

import com.esfinge.gamification.processors.TrophyWhenReachPointLimitProcessor;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@NeedsToHave(PointsToUser.class)
@GamificationProcessor(TrophyWhenReachPointLimitProcessor.class)
public @interface TrophyWhenReachPointLimit {
	@MinValue(value=1000)
	int quantityPoint();
	@NotNull
	String namePoint();
    @NotNull
    String nameTrophy();
}

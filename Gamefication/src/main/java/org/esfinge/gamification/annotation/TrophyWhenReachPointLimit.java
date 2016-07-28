package org.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.gamification.processors.TrophyWhenReachPointLimitProcessor;
import org.esfinge.metadata.annotation.validator.MinValue;
import org.esfinge.metadata.annotation.validator.NeedsToHave;
import org.esfinge.metadata.annotation.validator.NotNull;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

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

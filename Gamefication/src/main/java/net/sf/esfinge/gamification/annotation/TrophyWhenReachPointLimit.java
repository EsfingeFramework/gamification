package net.sf.esfinge.gamification.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.annotation.*;
import net.sf.esfinge.gamification.processors.TrophyWhenReachPointLimitProcessor;
import net.sf.esfinge.metadata.annotation.validator.MinValue;
import net.sf.esfinge.metadata.annotation.validator.NeedsToHave;
import net.sf.esfinge.metadata.annotation.validator.NotNull;
import net.sf.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import net.sf.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

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

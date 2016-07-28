package org.esfinge.gamification.casestudy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.esfinge.gamification.annotation.PointsToUser;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@PointsToUser(name = "PontoA", quantity = 20)
public @interface ExtraPoints {

}
package GamificationCaseStudy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

import com.esfinge.gamification.annotation.PointsToUser;;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@PointsToUser(name = "PontoA", quantity = 20)
public @interface ExtraPoints {

}
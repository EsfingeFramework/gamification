package GamificationCaseStudy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

import com.esfinge.gamification.annotation.RemoveRankings;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@RemoveRankings(level="lendario", name="noobA")
public @interface Admin {

}
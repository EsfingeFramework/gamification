package org.esfinge.gamification.casestudy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.esfinge.gamification.annotation.RemoveRankings;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

@SearchOnEnclosingElements
@SearchInsideAnnotations
@Retention(RetentionPolicy.RUNTIME)
@RemoveRankings(level="lendario", name="noobA")
public @interface Admin {

}
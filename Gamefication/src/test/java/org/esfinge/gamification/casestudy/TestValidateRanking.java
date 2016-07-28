package org.esfinge.gamification.casestudy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.gamification.annotation.RankingsToUser;
import org.esfinge.gamification.annotation.RemoveRankings;
import org.esfinge.gamification.annotation.TrophiesToUser;
import org.esfinge.gamification.exception.GamificationConfigurationException;
import org.esfinge.gamification.proxy.GameProxy;
import org.esfinge.metadata.AnnotationValidationException;
import org.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import org.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;
import org.junit.Test;

public class TestValidateRanking {

	public class TestRankingAnnotation01 {
		@RankingsToUser(name = "PontoA", level = "1")
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT01() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation01());
	}
	
	@RankingsToUser(name = "PontoA", level = "1")
	public class TestRankingAnnotation02 {
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT02() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation02());
	}
	
	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RankingsToUser(name = "PontoA", level = "1")
	@TrophiesToUser(name = "LevelA")
	public @interface ExtraPoints03 {	
	    
	}
	
	public class TestRankingAnnotation03 {
		@ExtraPoints03
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT03() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation03());
	}
	
	@ExtraPoints03
	public class TestRankingAnnotation04 {
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT04() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation04());
	}
	
	
	public class TestRankingAnnotation05 {
		@RankingsToUser(name = "PontoA", level = "1")
		@RemoveRankings(name = "PontoB", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = AnnotationValidationException.class)
	public void CT05() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation05());
	}
	
	
	public class TestRankingAnnotation07 {
		@ExtraPoints03
		@RemoveRankings(name = "PontoB", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT07() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation07());
	}
	
	@ExtraPoints03
	public class TestRankingAnnotation08 {
		@RemoveRankings(name = "PontoB", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT08() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation08());
	}
	
    public class TestRankingAnnotation09 {
    	@RankingsToUser(name = "PontoB", level = "")
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = AnnotationValidationException.class)
	public void CT09() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation09());
	}
	
	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RankingsToUser(name = "PontoB", level = "")
	@TrophiesToUser(name = "LevelA")
	public @interface ExtraPoints04 {	
	    
	}
    public class TestRankingAnnotation11 {
    	@ExtraPoints04
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = AnnotationValidationException.class)
	public void CT11() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation11());
	}
	
	@ExtraPoints04
    public class TestRankingAnnotation12 {
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = AnnotationValidationException.class)
	public void CT12() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation12());
	}
	
	public class TestRankingAnnotation13 {
		@RankingsToUser(name = "", level = "1")
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = AnnotationValidationException.class)
	public void CT13() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation13());
	}
	
	@RankingsToUser(name = "", level = "1")
	public class TestRankingAnnotation14 {
		@RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = AnnotationValidationException.class)
	public void CT14() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation14());
	}
	
	@ExtraPoints
	public class TestRankingAnnotation16 {
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}
	
	@Test(expected = AnnotationValidationException.class)
	public void CT16() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation16());
	}
	
	@RankingsToUser(name = "PontoA", level = "1")
    public class TestRankingAnnotation06 {
        @RemoveRankings(name = "PontoB", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test
	public void CT06() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation06());
	}
	
	@RankingsToUser(name = "PontoB", level = "1")
    public class TestRankingAnnotation10 {
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test
	public void CT10() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation10());
	}

	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RankingsToUser(name = "", level = "1")
	@TrophiesToUser(name = "LevelA")
	public @interface ExtraPoints {	
	    
	}
	
	public class TestRankingAnnotation15 {
        @ExtraPoints
        @RemoveRankings(name = "PontoA", level = "1")
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test
	public void CT15() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation15());
	}
	
}

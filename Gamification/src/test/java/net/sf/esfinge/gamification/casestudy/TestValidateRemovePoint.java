package net.sf.esfinge.gamification.casestudy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

import net.sf.esfinge.gamification.annotation.PointsToUser;
import net.sf.esfinge.gamification.annotation.RankingsToUser;
import net.sf.esfinge.gamification.annotation.RemovePoints;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.metadata.AnnotationValidationException;
import net.sf.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import net.sf.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;

public class TestValidateRemovePoint {

	public class TestPointAnnotation01 {
		@RemovePoints(name = "PontoA", quantity = 1)
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT01() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation01());
	}
	
	@RemovePoints(name = "PontoA", quantity = 1)
	public class TestPointAnnotation02 {
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT02() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation02());
	}
	
	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RemovePoints(name = "PontoA", quantity = 1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints03 {	
	    
	}
	
	public class TestPointAnnotation03 {
		@ExtraPoints03
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT03() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation03());
	}
	
	@ExtraPoints03
	public class TestPointAnnotation04 {
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT04() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation04());
	}
	
	
	public class TestPointAnnotation05 {
		@RemovePoints(name = "PontoA", quantity = 1)
		@PointsToUser(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test
	public void CT05() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation05());
	}
	
	
	public class TestPointAnnotation07 {
		@ExtraPoints03
		@PointsToUser(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test
	public void CT07() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation07());
	}
	
	@ExtraPoints03
	public class TestPointAnnotation08 {
		@PointsToUser(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test
	public void CT08() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation08());
	}
	
    public class TestPointAnnotation09 {
    	@RemovePoints(name = "PontoB", quantity = -1)
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT09() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation09());
	}
	
	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RemovePoints(name = "PontoB", quantity = -1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints04 {	
	    
	}
    public class TestPointAnnotation11 {
    	@ExtraPoints04
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT11() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation11());
	}
	
	@ExtraPoints04
    public class TestPointAnnotation12 {
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT12() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation12());
	}
	
	public class TestPointAnnotation13 {
		@RemovePoints(name = "", quantity = 1)
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT13() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation13());
	}
	
	@RemovePoints(name = "", quantity = 1)
	public class TestPointAnnotation14 {
		@PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT14() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation14());
	}
	
	@ExtraPoints
	public class TestPointAnnotation16 {
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT16() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation16());
	}
	
	@RemovePoints(name = "PontoA", quantity = 1)
    public class TestPointAnnotation06 {
        @PointsToUser(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test
	public void CT06() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation06());
	}
	
	@RemovePoints(name = "PontoB", quantity = -1)
    public class TestPointAnnotation10 {
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT10() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation10());
	}

	@SearchOnEnclosingElements
	@SearchInsideAnnotations
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@RemovePoints(name = "", quantity = 1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints {	
	    
	}
	
	public class TestPointAnnotation15 {
        @ExtraPoints
        @PointsToUser(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT15() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation15());
	}

}

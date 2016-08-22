package net.sf.esfinge.gamification.casestudy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.annotation.PointsToUser;
import net.sf.esfinge.gamification.annotation.RankingsToUser;
import net.sf.esfinge.gamification.annotation.RemovePoints;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.metadata.AnnotationValidationException;
import net.sf.esfinge.metadata.annotation.validator.SearchInsideAnnotations;
import net.sf.esfinge.metadata.annotation.validator.SearchOnEnclosingElements;
import org.junit.Test;

public class TestValidatePoint {

	public class TestPointAnnotation01 {
		@PointsToUser(name = "PontoA", quantity = 1)
		@RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT01() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation01());
	}
	
	@PointsToUser(name = "PontoA", quantity = 1)
	public class TestPointAnnotation02 {
		@RemovePoints(name = "PontoA", quantity = 1)
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
	@PointsToUser(name = "PontoA", quantity = 1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints03 {	
	    
	}
	
	public class TestPointAnnotation03 {
		@ExtraPoints03
		@RemovePoints(name = "PontoA", quantity = 1)
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
		@RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT04() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation04());
	}
	
	
	public class TestPointAnnotation05 {
		@PointsToUser(name = "PontoA", quantity = 1)
		@RemovePoints(name = "PontoB", quantity = 1)
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
		@RemovePoints(name = "PontoB", quantity = 1)
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
		@RemovePoints(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	
	@Test
	public void CT08() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation08());
	}
	
    public class TestPointAnnotation09 {
    	@PointsToUser(name = "PontoB", quantity = -1)
        @RemovePoints(name = "PontoA", quantity = 1)
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
	@PointsToUser(name = "PontoB", quantity = -1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints04 {	
	    
	}
    public class TestPointAnnotation11 {
    	@ExtraPoints04
        @RemovePoints(name = "PontoA", quantity = 1)
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
        @RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT12() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation12());
	}
	
	public class TestPointAnnotation13 {
		@PointsToUser(name = "", quantity = 1)
		@RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}
	}
	@Test(expected = GamificationConfigurationException.class)
	public void CT13() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation13());
	}
	
	@PointsToUser(name = "", quantity = 1)
	public class TestPointAnnotation14 {
		@RemovePoints(name = "PontoA", quantity = 1)
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
        @RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}
	
	@Test(expected = GamificationConfigurationException.class)
	public void CT16() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation16());
	}
	
	@PointsToUser(name = "PontoA", quantity = 1)
    public class TestPointAnnotation06 {
        @RemovePoints(name = "PontoB", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test
	public void CT06() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation06());
	}
	
	@PointsToUser(name = "PontoB", quantity = -1)
    public class TestPointAnnotation10 {
        @RemovePoints(name = "PontoA", quantity = 1)
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
	@PointsToUser(name = "", quantity = 1)
	@RankingsToUser(name = "LevelA", level = "1")
	public @interface ExtraPoints {	
	    
	}
	
	public class TestPointAnnotation15 {
        @ExtraPoints
        @RemovePoints(name = "PontoA", quantity = 1)
		public void doSomethingWrong(){
			//method implementation
		}

	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT15() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation15());
	}
	
}

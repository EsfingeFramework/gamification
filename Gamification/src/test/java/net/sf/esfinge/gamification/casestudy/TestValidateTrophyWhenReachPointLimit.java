package net.sf.esfinge.gamification.casestudy;

import net.sf.esfinge.gamification.annotation.PointsToUser;
import net.sf.esfinge.gamification.annotation.TrophyWhenReachPointLimit;
import net.sf.esfinge.gamification.exception.GamificationConfigurationException;
import net.sf.esfinge.gamification.proxy.GameProxy;
import net.sf.esfinge.metadata.AnnotationValidationException;
import org.junit.Test;

public class TestValidateTrophyWhenReachPointLimit {

	// CT01
	public class TestValidateTrophyWhenReachPointLimit01 {
		@TrophyWhenReachPointLimit(namePoint = "PontoA", nameTrophy = "TrofeuA", quantityPoint = 10)
		public void doSomethingWrong() {
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT01() throws AnnotationValidationException {
		GameProxy.createProxy(new TestValidateTrophyWhenReachPointLimit01());
	}

	// CT02
	public class TestValidateTrophyWhenReachPointLimit02 {
		@TrophyWhenReachPointLimit(namePoint = "PontoA", nameTrophy = "TrofeuA", quantityPoint = 10)
		@PointsToUser(name = "PontoA", quantity = 20)
		public void doSomethingWrong() {
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT02() throws AnnotationValidationException {
		GameProxy.createProxy(new TestValidateTrophyWhenReachPointLimit02());
	}

	// CT03
	@PointsToUser(name = "PontoA", quantity = 20)
	public class TestValidateTrophyWhenReachPointLimit03 {
		@TrophyWhenReachPointLimit(namePoint = "PontoA", nameTrophy = "TrofeuA", quantityPoint = 10)
		public void doSomethingWrong() {
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT03() throws AnnotationValidationException {
		GameProxy.createProxy(new TestValidateTrophyWhenReachPointLimit03());
	}

	// CT04
	public class TestValidateTrophyWhenReachPointLimit04 {
		@TrophyWhenReachPointLimit(namePoint = "PontoA", nameTrophy = "TrofeuA", quantityPoint = 10)
		@ExtraPoints
		public void doSomethingWrong() {
		}
	}

	@Test(expected = GamificationConfigurationException.class)
	public void CT05() throws AnnotationValidationException {
		GameProxy.createProxy(new TestValidateTrophyWhenReachPointLimit04());
	}

	// CT05
	@ExtraPoints
	public class TestValidateTrophyWhenReachPointLimit05 {
		@TrophyWhenReachPointLimit(namePoint = "PontoA", nameTrophy = "TrofeuA", quantityPoint = 10)
		public void doSomethingWrong() {
		}
	}

	@Test(expected = GamificationConfigurationException.class)
		public void CT06() throws AnnotationValidationException {
		GameProxy.createProxy(new TestValidateTrophyWhenReachPointLimit05());
	}
}

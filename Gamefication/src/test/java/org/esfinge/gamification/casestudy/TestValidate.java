package org.esfinge.gamification.casestudy;

import org.esfinge.gamification.annotation.RankingsToUser;
import org.esfinge.gamification.annotation.RemoveRankings;
import org.esfinge.gamification.annotation.RemoveTrophy;
import org.esfinge.gamification.annotation.TrophiesToUser;
import org.esfinge.gamification.exception.GamificationConfigurationException;
import org.esfinge.gamification.proxy.GameProxy;
import org.esfinge.gamification.proxy.TestPointAnnotation;
import org.esfinge.gamification.proxy.TestRankingAnnotation;
import org.esfinge.gamification.proxy.TestRewardAnnotation;
import org.esfinge.metadata.AnnotationValidationException;
import org.esfinge.metadata.validate.MetadataValidator;
import org.junit.Test;

public class TestValidate {

	// @PointsToUser(name = "GOLD", quantity = -1)
	// Resultado esperado: erro - quantity nao poder ser menor que MinValue(0)
	@Test (expected = GamificationConfigurationException.class)
	public void testPointAnnotation() throws AnnotationValidationException {
		GameProxy.createProxy(new TestPointAnnotation());		
	}

	// @RankingsToUser(name = "Noob", level = "")
	// Resultado esperado: erro - level nao pode ser nulo
	@Test (expected = GamificationConfigurationException.class)
	public void testRankingAnnotation() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRankingAnnotation());
	}
	
	// @RewardsToUser(name = "lunch", used = false)
	// @RemoveReward(name = "lunch", used = true)
	// Resultado esperado: erro - as anotacoes nao podem estar juntas
	@Test (expected = GamificationConfigurationException.class)
	public void testRewardAnnotation() throws AnnotationValidationException {
		GameProxy.createProxy(new TestRewardAnnotation());
	}
	
	// CT06: @RankingsToUser na classe, name: noobA, com @SearchOnEnclosingElements
	//       @RemoveRankings no metodo, name: noobA
	// Resultado esperado: falha
	@RankingsToUser(level="profissional", name="noobA")
	public class OrderProcessing04 {
		@RemoveRankings(level="lendario", name="noobA")
		public void registerPurchase() {
		}
	}

	@Test(expected = AnnotationValidationException.class)
	public void CT06() throws AnnotationValidationException {
		MetadataValidator.validateMetadataOn(OrderProcessing04.class);
	}
	
	
	// CT07: @RankingsToUser na classe, name: noobA, com @SearchOnEnclosingElements
	//       @RemoveRankings no metodo, name: noobB
	// Resultado esperado: ok
	@RankingsToUser(level="profissional", name="noobA")
	public class OrderProcessing05 {
		@RemoveRankings(level="lendario", name="noobB")
		public void registerPurchase() {
		}
	}

	@Test
	public void CT07() throws AnnotationValidationException {
		MetadataValidator.validateMetadataOn(OrderProcessing05.class);
	}

	
	// CT08: @RemoveRankings dentro de @Admin(na classe), name: noobB, com @SearchOnEnclosingElements e @SearchInsideAnnotations
	//       @RankingsToUser no metodo, name: noobA, com @SearchOnEnclosingElements
	// Resultado esperado: falha
	@Admin
	public class OrderProcessing06 {
		@RankingsToUser(level="lendario", name="noobA")
		public void registerPurchase() {
		}
	}

	@Test(expected = AnnotationValidationException.class)
	public void CT08() throws AnnotationValidationException {
		MetadataValidator.validateMetadataOn(OrderProcessing06.class);
	}
	
	// CT09: @TrophiesToUser na classe, name: master, com @SearchInsideAnnotations e SearchOnEnclosingElements
	//       @RemoveTrophy no metodo, name: master, com @SearchOnEnclosingElements e SearchInsideAnnotations
	// Resultado esperado: ok
	@TrophiesToUser(name="master")
	public class OrderProcessing07 {
		@RemoveTrophy(name="master")
		public void registerPurchase() {
		}
	}

	@Test(expected = AnnotationValidationException.class)
	public void CT09() throws AnnotationValidationException {
		MetadataValidator.validateMetadataOn(OrderProcessing07.class);
	}			

	// CT10: @TrophiesToUser no metodo, name: master, com @SearchInsideAnnotations
	//       @RemoveTrophy na classe, name: master, com @SearchOnEnclosingElements
	// Resultado esperado: falha
	@RemoveTrophy(name="master")
	public class OrderProcessing08 {
		@TrophiesToUser(name="master")
		public void registerPurchase() {
		}
	}

	@Test(expected = AnnotationValidationException.class)
	public void CT10() throws AnnotationValidationException {
		MetadataValidator.validateMetadataOn(OrderProcessing08.class);
	}			
}

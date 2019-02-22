package net.sf.esfinge.gamification.casestudy;

import net.sf.esfinge.gamification.annotation.RankingsToUser;
import net.sf.esfinge.gamification.annotation.RemoveRankings;
import net.sf.esfinge.gamification.annotation.RemoveTrophy;
import net.sf.esfinge.gamification.annotation.TrophiesToUser;
import net.sf.esfinge.metadata.AnnotationValidationException;
import net.sf.esfinge.metadata.validate.MetadataValidator;
import org.junit.Test;

public class TestValidate {
	
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

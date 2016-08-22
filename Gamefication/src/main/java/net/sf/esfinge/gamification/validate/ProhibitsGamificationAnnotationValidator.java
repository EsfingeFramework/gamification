package net.sf.esfinge.gamification.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.esfinge.gamification.validate.annotation.ProhibitsGamification;
import net.sf.esfinge.metadata.AnnotationFinder;
import net.sf.esfinge.metadata.AnnotationValidationException;
import net.sf.esfinge.metadata.validate.ProhibitsAnnotationValidator;

public class ProhibitsGamificationAnnotationValidator extends ProhibitsAnnotationValidator {
	
	@Override
	public void initialize(Annotation self) {
		ProhibitsGamification ntp = (ProhibitsGamification) self;
		whatItNeedsToProhibits = ntp.value();
	}

	@Override
	public void validate(Annotation toValidate, AnnotatedElement annotated) throws AnnotationValidationException {		
		List<Annotation> annToProhibits = AnnotationFinder.findAnnotation(annotated, whatItNeedsToProhibits); 
		
		// property checking
		for (Annotation annotation : annToProhibits) {
			String nameAttributeOfProhibits = getAttributeOfAnnotation(annotation);
			String nameAttributeToValidate = getAttributeOfAnnotation(toValidate);
			
				// attribute checking
			if(nameAttributeOfProhibits.equals(nameAttributeToValidate)){			
				throw new AnnotationValidationException("The annotation it needs to prohibits was found: @" + whatItNeedsToProhibits.getSimpleName()+", attribute: "+nameAttributeOfProhibits);
			}
		}
	}

	private String getAttributeOfAnnotation(Annotation ann) {
		for (Method m : ann.annotationType().getDeclaredMethods()) {			
			try {
				Object o = m.invoke(ann);					
				if(m.getName().equals("name")) return o.toString();					
			} catch (Exception e) {
				e.getMessage(); 
			}			
		}
		return "";
	}
}

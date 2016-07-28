package org.esfinge.gamification.validate.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.gamification.validate.ProhibitsGamificationAnnotationValidator;
import org.esfinge.metadata.annotation.validator.ToValidate;

@ToValidate(validationClass = ProhibitsGamificationAnnotationValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ProhibitsGamification {
	public Class<? extends Annotation> value();
}

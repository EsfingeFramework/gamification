package com.esfinge.gamification.validate.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.metadata.annotation.ToValidate;

import com.esfinge.gamification.validate.ProhibitsGamificationAnnotationValidator;

@ToValidate(validationClass = ProhibitsGamificationAnnotationValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ProhibitsGamification {
	public Class<? extends Annotation> value();
}

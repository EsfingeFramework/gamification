package net.sf.esfinge.gamification.guardian.points;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.esfinge.guardian.abac.annotation.authorization.Rule;
import org.esfinge.guardian.annotation.AuthorizerClass;

import net.sf.esfinge.metadata.annotation.validator.MinValue;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
@AuthorizerClass(VerifyPointsGreater.class)

public @interface AuthPointGreaterThan {
	@MinValue(value = 0)
	int quantity();
}

package org.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;

import org.esfinge.gamification.achievement.Achievement;

public interface EventListener<T extends Achievement> {

	void setAnnotation(Annotation an);
	BiPredicate<T,Object> getEvaluation();
	Method getMethod();
	void setMethod(Method method);
	void setConfigurationObject(Object configurationObject);
	Object getConfigurationObject();
}

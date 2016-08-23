package net.sf.esfinge.gamification.event.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;

import net.sf.esfinge.gamification.achievement.Achievement;

public interface EventListener<T extends Achievement> {

	void setAnnotation(Annotation an);
	Boolean evaluate(T achievement,Object user);
	Method getMethod();
	void setMethod(Method method);
	void setConfigurationObject(Object configurationObject);
	Object getConfigurationObject();
}

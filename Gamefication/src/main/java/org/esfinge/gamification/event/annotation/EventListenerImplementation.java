package org.esfinge.gamification.event.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.event.listener.EventListener;

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface EventListenerImplementation {
	Class<? extends EventListener<? extends Achievement>> value();
}

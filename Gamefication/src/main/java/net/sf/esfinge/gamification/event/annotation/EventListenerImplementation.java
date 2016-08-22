package net.sf.esfinge.gamification.event.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.event.listener.EventListener;

@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface EventListenerImplementation {
	Class<? extends EventListener<? extends Achievement>> value();
}

package net.sf.esfinge.gamification.auth;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.mechanics.Game;

public interface Authorizer<E extends Annotation> {
	Boolean authorize(Method method, Game game, Object user);
}

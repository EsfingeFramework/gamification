package net.sf.esfinge.gamification.auth;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.mechanics.Game;

/***
 * 
 * This interface receive a security annotation for process the authorization
 *
 * @param <E> annotation
 * 
 *        see net.sf.esfinge.gamification.auth.GameAuthorizer
 */

public interface Authorizer<E extends Annotation> {

	Boolean authorize(Method method, Game game, Object user);

}

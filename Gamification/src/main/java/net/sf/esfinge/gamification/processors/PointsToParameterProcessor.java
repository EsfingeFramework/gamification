package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.PointsToParam;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.utils.ReflectionUtils;

public class PointsToParameterProcessor implements AchievementProcessor{
	
	private int quantity;
	private String name;
	private String parameterName;
	private String propertyName;

	@Override
	public void receiveAnnotation(Annotation an) {
		PointsToParam ptu = (PointsToParam) an;
		quantity = ptu.quantity();
		name = ptu.name();
		parameterName = ptu.param();
		propertyName = ptu.prop();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		int index = ReflectionUtils.findParameterNamed(method, parameterName);
		Object target = args[index];
		if(!propertyName.equals("")){
			try {
				target = PropertyUtils.getProperty(target, propertyName);
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				throw new RuntimeException("Cannot retrieve property '"+propertyName+"' from class "+target.getClass(), e);
			}
		}
		Point p = new Point(quantity, name);
		game.addAchievement(target, p);
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		// TODO Auto-generated method stub
		
	}
	
	

}

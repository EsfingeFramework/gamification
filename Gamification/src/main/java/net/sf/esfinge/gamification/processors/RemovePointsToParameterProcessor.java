package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.annotation.RemovePointsToParam;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.utils.ReflectionUtils;

public class RemovePointsToParameterProcessor implements AchievementProcessor {

	private int quantity;
	private String name;
	private String parameterName;
	private String propertyName;
	
	@Override
	public void receiveAnnotation(Annotation an) {
		RemovePointsToParam rptu = (RemovePointsToParam) an;
		quantity = rptu.quantity();
		name = rptu.name();
		parameterName = rptu.param();
		propertyName = rptu.prop();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		int index = ReflectionUtils.findParameterNamed(method, parameterName);
		Object target = args[index];
		if(!propertyName.equals("")){
			try{
				target = PropertyUtils.getProperty(target, propertyName);
			} catch (IllegalAccessException | InvocationTargetException
					 | NoSuchMethodException e){
				throw new RuntimeException("Cannot retrieve property '"+propertyName+"' from class "+target.getClass(), e);
			}
		}
		Point p = new Point(quantity, name);
		game.removeAchievement(target, p);
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		// TODO Auto-generated method stub
		
	}

}

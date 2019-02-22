package net.sf.esfinge.gamification.event.listener;

import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Achievement;

public abstract class AbstractEventListener<T extends Achievement> implements EventListener<T>{

	Method method;
	Object configurationObject;

	public AbstractEventListener() {
		super();
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public void setMethod(Method method) {
		this.method = method;
	}

	@Override
	public void setConfigurationObject(Object configurationObject) {
		this.configurationObject = configurationObject;
	}

	@Override
	public Object getConfigurationObject() {
		return configurationObject;
	}

}
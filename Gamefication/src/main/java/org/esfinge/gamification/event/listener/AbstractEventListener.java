package org.esfinge.gamification.event.listener;

import java.lang.reflect.Method;

import org.esfinge.gamification.achievement.Point;

public abstract class AbstractEventListener implements EventListener<Point>{

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
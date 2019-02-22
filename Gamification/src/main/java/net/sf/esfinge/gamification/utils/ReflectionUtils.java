package net.sf.esfinge.gamification.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.annotation.Named;

public class ReflectionUtils {
	
	public static int findParameterNamed(Method method, String name){
		Annotation[][] ans = method.getParameterAnnotations();
		for(int i = 0; i < ans.length; i++){
			Annotation[] paramAnots = ans[i];
			for(int j =0; j < paramAnots.length; j++){
				if(paramAnots[j].annotationType() == Named.class){
					String foundName = ((Named) paramAnots[j]).value();
					if(foundName.equals(name)){
						return i;
					}
				}
			}
		}
		throw new RuntimeException("A parameter named '" +name+ "' is not found on method "+ method.getName());
	}

}

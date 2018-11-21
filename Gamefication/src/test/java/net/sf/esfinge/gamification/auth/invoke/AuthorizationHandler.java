package net.sf.esfinge.gamification.auth.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Implementation of the InvocationHandler which handles the basic object
 * methods.
 */
class AuthorizationHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if (method.getDeclaringClass() == Object.class) {
			String methodName = method.getName();
			if (methodName.equals("hashCode")) {
				return proxyHashCode(proxy);
			} else if (methodName.equals("equals")) {
				return proxyEquals(proxy, args[0]);
			} else if (methodName.equals("toString")) {
				return proxyToString(proxy);
			}
		}

		// Although listener methods are supposed to be void, we
		// allow for any return type here and produce null/0/false
		// as appropriate.
		return nullValueOf(method.getReturnType());
	}

	private Object nullValueOf(Class<?> returnType) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object proxyToString(Object proxy) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object proxyEquals(Object proxy, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object proxyHashCode(Object proxy) {
		// TODO Auto-generated method stub
		return null;
	}
}
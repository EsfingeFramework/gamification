package com.esfinge.gamefication.user;

public class UserStorage {
	
	private static ThreadLocal<Object> user = new ThreadLocal<Object>();
	
	public static Object getUser(){
		return user.get();
	}
	
	public static void setUser(Object u){
		user.set(u);
	}
}

package net.sf.esfinge.gamification.user;

public class UserStorage {
	
	private static ThreadLocal<Object> user = new ThreadLocal<Object>();
	
	public static Object getUserID(){
		return user.get();
	}
	
	public static void setUserID(Object u){
		user.set(u);
	}
}

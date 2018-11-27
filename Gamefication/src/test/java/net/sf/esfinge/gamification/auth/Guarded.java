package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.MinPoin;

public interface Guarded {

	/**
	 * Setting 10 points of gold for this resource
	 * 
	 */
	
	@MinPoin(quantity = 10, achievementName = "gold")
	void changeProfilePhoto();

	/**
	 * Setting 20 points of gold for this resource
	 * 
	 */
	@MinPoin(quantity = 20, achievementName = "silver")
	public void takePhoto();

}

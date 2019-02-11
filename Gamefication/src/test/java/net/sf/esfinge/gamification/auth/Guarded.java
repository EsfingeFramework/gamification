package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.AllowPointGraterThan;

public interface Guarded {

	/**
	 * Setting 10 points of gold for this resource
	 * 
	 */
	
	@AllowPointGraterThan(quantity = 10, achievementName = "gold")
	void changeProfilePhoto();

	/**
	 * Setting 20 points of silver for this resource
	 * 
	 */
	@AllowPointGraterThan(quantity = 20, achievementName = "silver")
	public void takePhoto();

}

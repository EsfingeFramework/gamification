package net.sf.esfinge.gamification.auth;

public class GuardedImpl implements Guarded {

	/**
	 * Example method with security annotation
	 */
	@MinPoin(quantity = 10, achievementName = "silver")
	public void changeProfilePhoto() {
	}

	@MinPoin(quantity = 20, achievementName = "gold")
	public void takePhoto() {
	}
}

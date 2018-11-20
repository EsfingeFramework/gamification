package net.sf.esfinge.gamification.auth;

public interface Guarded {
	
	@MinPoin(quantity = 10, achievementName = "silver")
	void changeProfilePhoto();

	@MinPoin(quantity = 20, achievementName = "gold")
	public void takePhoto();

}

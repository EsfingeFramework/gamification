package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.AllowPointGraterThan;

public class GuardedImpl implements Guarded {

	@AllowPointGraterThan(quantity = 10, achievementName = "gold")
	public void changeProfilePhoto() {
		
		System.out.println("profile photo changed");
	}

	@AllowPointGraterThan(quantity = 20, achievementName = "silver")
	public void takePhoto() {
		
		System.out.println("photo taked");
		
	}
}

package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.auth.AllowPointGreaterThan;
import net.sf.esfinge.gamification.annotation.auth.AllowPointLessOrEqualsThan;
import net.sf.esfinge.gamification.annotation.auth.DenyPointGreaterThan;
import net.sf.esfinge.gamification.annotation.auth.DenyPointLessOrEqualsThan;

public class GuardedImpl implements Guarded {

	@AllowPointGreaterThan(quantity = 10, achievementName = "gold")
	public void changeProfilePhoto() {

		System.out.println("profile photo changed");
	}

	@DenyPointGreaterThan(quantity = 20, achievementName = "silver")
	public void takePhoto() {

		System.out.println("photo taked");

	}

	@AllowPointLessOrEqualsThan(quantity = 20, achievementName = "coins")
	public void recordVideo() {

		System.out.println("video recorded");

	}

	@DenyPointLessOrEqualsThan(quantity = 20, achievementName = "bronze")
	public void receivePhoto() {

		System.out.println("photo received");

	}

}

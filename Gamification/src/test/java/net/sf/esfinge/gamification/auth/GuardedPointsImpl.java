package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.auth.points.AllowPointGreaterThan;
import net.sf.esfinge.gamification.annotation.auth.points.AllowPointLessOrEqualsThan;
import net.sf.esfinge.gamification.annotation.auth.points.DenyPointGreaterThan;
import net.sf.esfinge.gamification.annotation.auth.points.DenyPointLessOrEqualsThan;

public class GuardedPointsImpl implements Guarded {

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

package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.auth.reward.AllowReward;
import net.sf.esfinge.gamification.annotation.auth.reward.DenyReward;

public class GuardedRewardImpl implements Guarded {

	@AllowReward(achievementName = "gold")
	public void changeProfilePhoto() {

		System.out.println("profile photo changed");
	}

	@DenyReward(achievementName = "silver")
	public void takePhoto() {

		System.out.println("photo taked");

	}

	public void recordVideo() {

		System.out.println("video recorded");

	}

	public void receivePhoto() {

		System.out.println("photo received");

	}

}

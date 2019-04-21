package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.auth.trophy.AllowTrophy;
import net.sf.esfinge.gamification.annotation.auth.trophy.DenyTrophy;

public class GuardedTrophyImpl implements Guarded {

	@AllowTrophy(achievementName = "silver")
	public void changeProfilePhoto() {

		System.out.println("profile photo changed");
	}

	@DenyTrophy(achievementName = "gold")
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

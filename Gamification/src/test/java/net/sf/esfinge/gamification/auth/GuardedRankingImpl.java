package net.sf.esfinge.gamification.auth;

import net.sf.esfinge.gamification.annotation.auth.ranking.AllowLevel;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowRanking;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowRankingAndLevel;
import net.sf.esfinge.gamification.annotation.auth.ranking.AllowRankingOrLevel;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyLevel;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRanking;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRankingAndLevel;
import net.sf.esfinge.gamification.annotation.auth.ranking.DenyRankingOrLevel;

public class GuardedRankingImpl implements GuardedRanking {

	@AllowRanking(achievementName = "experience", level = "expert")
	public void changeProfilePhoto() {

		System.out.println("profile photo changed");
	}

	@DenyRanking(achievementName = "experience", level = "expert")
	public void takePhoto() {

		System.out.println("photo taked");

	}

	@AllowLevel(achievementName = "experience", level = "expert")
	public void recordVideo() {

		System.out.println("video recorded");

	}

	@DenyLevel(achievementName = "experience", level = "expert")
	public void receivePhoto() {

		System.out.println("photo received");

	}

	@AllowRankingAndLevel(achievementName = "experience", level = "begginer")
	public void sendPhoto() {

		System.out.println("photo send");

	}

	@DenyRankingAndLevel(achievementName = "experience", level = "begginer")
	public void screenshot() {

		System.out.println("printscreen taked");

	}

	@AllowRankingOrLevel(achievementName = "experience", level = "begginer")
	public void shutdown() {
		System.out.println("turning off");
	}

	@DenyRankingOrLevel(achievementName = "experience", level = "advanced")
	public void turnOn() {

		System.out.println("turning on");

	}

}

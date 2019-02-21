package net.sf.esfinge.gamification.mechanics.nosql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMongoStorage;

public class TestMongoRewardStorage {

	private MongoClient client;
	private static Game game;
	private String user, user2;

	@Before
	public void initializeGame() throws Exception {

		client = new MongoClient();
		game = new GameMongoStorage(client.getDatabase("local"));
		user = "Jaspion";
		user2 = "Spider";
	}

	@Test
	public void addReward() {
		Achievement p = new Reward("reward");
		game.addAchievement(user, p);
		assertEquals(p, game.getAchievement(user, "reward"));
		game.deleteAchievement(user, p);
	}

	@Test
	public void addTwoReward() {
		Achievement a1 = new Reward("reward");
		Achievement a2 = new Reward("reward");
		game.addAchievement(user2, a1);
		game.addAchievement(user2, a2);
		assertEquals(1, game.getAchievements(user2).size());
		assertFalse(((Reward) game.getAchievement(user2, "reward")).isUsed());
		game.deleteAchievement(user2, a1);
		game.deleteAchievement(user2, a2);
	}

	@Test
	public void addRewardDifferentUser() {

		Achievement a1 = new Reward("reward");
		Achievement a2 = new Reward("reward");
		game.addAchievement(user2, a1);
		game.addAchievement(user2, a2);
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(1, game.getAchievements(user2).size());
		assertFalse(((Reward) game.getAchievement(user2, "reward")).isUsed());
		assertFalse(((Reward) game.getAchievement(user2, "reward")).isUsed());
		game.deleteAchievement(user2, a1);
		game.deleteAchievement(user2, a2);

	}

	@Test
	public void removeReward() {
		Achievement p = new Reward("reward");
		game.addAchievement(user2, p);
		game.removeAchievement(user2, p);
		assertTrue(((Reward) game.getAchievement(user2, "reward")).isUsed());
		game.deleteAchievement(user2, p);
	}

	@Test
	public void removeRewardDifferentUser() {
		Achievement a1 = new Reward("reward");
		Achievement a2 = new Reward("reward");
		game.addAchievement(user2, a1);
		game.addAchievement(user, a2);
		game.removeAchievement(user, a1);
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(1, game.getAchievements(user).size());
		assertFalse(((Reward) game.getAchievement(user2, "reward")).isUsed());
		assertTrue(((Reward) game.getAchievement(user, "reward")).isUsed());
		game.deleteAchievement(user2, a1);
		game.deleteAchievement(user, a2);
	}

}

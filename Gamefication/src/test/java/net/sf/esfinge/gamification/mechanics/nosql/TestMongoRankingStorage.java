package net.sf.esfinge.gamification.mechanics.nosql;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMongoStorage;

public class TestMongoRankingStorage {

	private MongoClient client;
	private static Game game;
	private String user;

	@Before
	public void initializeGame() throws Exception {

		client = new MongoClient();
		game = new GameMongoStorage(client.getDatabase("local"));
		user = "Jaspion";
	}

	@Test
	public void addRanking() {
		Achievement r = new Ranking("mago", "master");
		game.addAchievement(user, r);
		assertEquals(r, (Ranking) game.getAchievement(user, "mago"));
		game.deleteAchievement(user, r);
	}

	@Test
	public void addTwoRank() {
		Achievement r1 = new Ranking("mago", "noob");
		Achievement r2 = new Ranking("mago", "master");
		game.addAchievement("Spider", r1);
		game.addAchievement("Spider", r2);
		assertEquals(r2, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(1, game.getAchievements("Spider").size());
		game.deleteAchievement("Spider", r1);
		game.deleteAchievement("Spider", r2);

	}

	@Test
	public void addRankDifferentUser() {
		String user2 = new String("Jiraia");
		Achievement r1 = new Ranking("mago", "master");
		Achievement r2 = new Ranking("mago2", "noob");
		game.addAchievement("Spider", r1);
		game.addAchievement(user2, r2);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(1, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(r2, (Ranking) game.getAchievement(user2, "mago2"));
		game.deleteAchievement("Spider", r1);
		game.deleteAchievement(user2, r2);
	}

	@Test
	public void removeRank() {
		Achievement r = new Ranking("mago", "master");
		game.addAchievement("Spider", r);
		game.removeAchievement("Spider", r);
		assertEquals(null, (Ranking) game.getAchievement("Spider", "mago"));
	}

	@Test
	public void removeRankDifferentUser() {
		String user2 = new String("Jiraia");
		Achievement r1 = new Ranking("mago", "master");
		Achievement r2 = new Ranking("mago2", "noob");
		game.addAchievement("Spider", r1);
		game.addAchievement(user2, r2);
		game.removeAchievement(user2, r2);
		assertEquals(1, game.getAchievements("Spider").size());
		assertEquals(0, game.getAchievements(user2).size());
		assertEquals(r1, (Ranking) game.getAchievement("Spider", "mago"));
		assertEquals(null, (Ranking) game.getAchievement(user2, "mago2"));
		game.deleteAchievement("Spider", r1);
		game.deleteAchievement(user2, r2);
	}

}

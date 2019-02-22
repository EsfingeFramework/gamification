package net.sf.esfinge.gamification.mechanics.nosql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMongoStorage;

public class TestMongoPointStorage {
	private String user, user2;
	private Game gameMongo;
	private MongoClient client;

	@Before
	public void initializeGame() throws IOException {

		user = new String("Spider");
		user2 = new String("Jiraia");
		client = new MongoClient();
		gameMongo = new GameMongoStorage(client.getDatabase("local"));

	}

	@Test
	public void addPoint() {
		
		Achievement p = new Point(200, "moedas");
		gameMongo.addAchievement(user, p);
		assertEquals(p.getName(), gameMongo.getAchievement(user, p.getName()).getName());
		assertEquals(200, ((Point) gameMongo.getAchievement(user, p.getName())).getQuantity().intValue());
		gameMongo.deleteAchievement(user, p);
	}

	@Test
	public void addTwoPoint() {
		Achievement a1 = new Point(20, "point");
		Achievement a2 = new Point(10, "point");
		gameMongo.addAchievement(user, a1);
		gameMongo.addAchievement(user, a2);
		assertEquals(30, ((Point) gameMongo.getAchievement(user, "point")).getQuantity().intValue());
		assertEquals(1, gameMongo.getAchievements(user).size());
		gameMongo.deleteAchievement(user, a1);
		gameMongo.deleteAchievement(user, a2);
	}

	@Test
	public void addPointDifferentUser() {
		Achievement a1 = new Point(10, "point");
		Achievement a2 = new Point(20, "point");
		gameMongo.addAchievement(user, a1);
		gameMongo.addAchievement(user2, a2);
		assertEquals(1, gameMongo.getAchievements(user).size());
		assertEquals(1, gameMongo.getAchievements(user2).size());
		assertEquals(10, ((Point) gameMongo.getAchievement(user, "point")).getQuantity().intValue());
		assertEquals(20, ((Point) gameMongo.getAchievement(user2, "point")).getQuantity().intValue());
		gameMongo.deleteAchievement(user, a1);
		gameMongo.deleteAchievement(user2, a2);
	}

	@Test
	public void removePoint() {
		Achievement p = new Point(50, "point");
		gameMongo.addAchievement(user, p);
		gameMongo.removeAchievement(user, p);
		assertEquals(null, ((Point) gameMongo.getAchievement(user, "point")));
	}

	@Test
	public void decreasePoint() {
		Achievement p = new Point(50, "point");
		Achievement r = new Point(30, "point");
		gameMongo.addAchievement(user, p);
		gameMongo.removeAchievement(user, r);
		assertEquals(20, ((Point) gameMongo.getAchievement(user, "point")).getQuantity().intValue());
		gameMongo.deleteAchievement(user, r);
	}

	@Test
	public void removePointDifferentUser() {
		Achievement a1 = new Point(10, "point");
		Achievement a2 = new Point(20, "point");
		gameMongo.addAchievement(user, a1);
		gameMongo.addAchievement(user2, a2);
		gameMongo.removeAchievement(user2, a1);
		assertEquals(1, gameMongo.getAchievements(user).size());
		assertEquals(1, gameMongo.getAchievements(user2).size());
		assertEquals(10, ((Point) gameMongo.getAchievement(user, "point")).getQuantity().intValue());
		assertEquals(10, ((Point) gameMongo.getAchievement(user2, "point")).getQuantity().intValue());
		gameMongo.deleteAchievement(user, a1);
		gameMongo.deleteAchievement(user2, a1);
	}

}

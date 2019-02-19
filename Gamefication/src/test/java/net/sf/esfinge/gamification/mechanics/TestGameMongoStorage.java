package net.sf.esfinge.gamification.mechanics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;

public class TestGameMongoStorage {
	private Object user;
	private Game gameMongo;

	@Before
	public void initializeGame() throws IOException {

		user = new String("usr");
		Achievement p = new Point(200, "moedas");

		MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
		MongoClient client = new MongoClient(uri);
		gameMongo = new GameMongoStorage(client.getDatabase("local"));
		gameMongo.insertAchievement(user, p);
	}

	@Test
	public void addPointProperties() {
//		Achievement p = new Point(200, "moedas");
//		gameMongo.addAchievement(user, p);
//		assertEquals(p.getName(), gameMongo.getAchievement(user, p.getName()).getName());
//		assertEquals(200, ((Point) gameMongo.getAchievement(user, p.getName())).getQuantity().intValue());
	}

}

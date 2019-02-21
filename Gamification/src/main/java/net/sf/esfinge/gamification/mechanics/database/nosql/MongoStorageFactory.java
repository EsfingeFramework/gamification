package net.sf.esfinge.gamification.mechanics.database.nosql;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class MongoStorageFactory {

	private MongoCollection<Document> collection;

	public MongoStorageFactory(MongoCollection<Document> collection) {
		super();
		this.collection = collection;
	}

	public Storage storageFor(Achievement a) {
		if (a instanceof Point) {
			return new MongoPointStorage(collection);
		} else if (a instanceof Ranking) {
			return new MongoRankingStorage(collection);
		} else if (a instanceof Reward) {
			return new MongoRewardStorage(collection);
		} else if (a instanceof Trophy) {
			return new MongoTrophyStorage(collection);
		}
		throw new RuntimeException("Cannot create Storage for " + a.getClass().getName());
	}

}

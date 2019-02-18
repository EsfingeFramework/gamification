package net.sf.esfinge.gamification.mechanics;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.sf.esfinge.gamification.achievement.Achievement;

public class GameNoSqlStorage extends Game {

	private MongoDatabase mongo;
	private MongoCollection collection;
	private Document document;

	public GameNoSqlStorage(MongoDatabase mongo) {
		this.mongo = mongo;
		this.document = new Document();
		try {
			collection = mongo.getCollection("gamification");
		} catch (IllegalArgumentException e) {
			mongo.createCollection("gamification");
		}
	}

	@Override
	public void insertAchievement(Object user, Achievement a) {

		document.append("user", user).append("achievement", a);
		collection.insertOne(document);
		document.clear();
	}

	@Override
	public void deleteAchievement(Object user, Achievement a) {

		collection.findOneAndDelete(and(eq(user), eq(a)));
	}

	@Override
	public void updateAchievement(Object user, Achievement a) {

		document.append("user", user).append("achievement", a);
		collection.findOneAndUpdate(new Document().append("user", user).append("achievement.name", a.getName()),
				document);
		document.clear();

	}

	@Override
	public Achievement getAchievement(Object user, String achievName) {
		Map<Object, Achievement> firstResult = (Map<Object, Achievement>) collection.find(and(eq(user), eq(achievName)))
				.first();
		return firstResult.get(firstResult.keySet());
	}

	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		Map<String, Achievement> result = (Map<String, Achievement>) collection.find(eq("user", user));
		return result;
	}

	@Override
	public Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type) {
		Map<String, Achievement> result = (Map<String, Achievement>) collection.find(eq("achievement.type", type));
		return result;
	}

}

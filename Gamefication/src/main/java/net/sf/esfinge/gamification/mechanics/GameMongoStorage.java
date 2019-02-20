package net.sf.esfinge.gamification.mechanics;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.bson.Document;
import org.reflections.Reflections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.mechanics.database.Storage;
import net.sf.esfinge.gamification.mechanics.database.nosql.MongoStorageFactory;

public class GameMongoStorage extends Game {

	private MongoDatabase mongo;
	private MongoCollection<Document> collection;
	private Document document;
	private MongoStorageFactory factory;

	public GameMongoStorage(MongoDatabase mongo) {

		this.mongo = mongo;
		this.document = new Document();
		try {
			collection = mongo.getCollection("gamification");
		} catch (IllegalArgumentException e) {
			mongo.createCollection("gamification");
		}
		factory = new MongoStorageFactory(collection);
	}

	@Override
	public void insertAchievement(Object user, Achievement a) {
		Storage storage = factory.storageFor(a);
		try {
			storage.insert(user, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		document.clear();
	}

	@Override
	public void deleteAchievement(Object user, Achievement a) {
		Storage storage = factory.storageFor(a);
		try {
			storage.delete(user, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAchievement(Object user, Achievement a) {

		Storage storage = factory.storageFor(a);
		try {
			storage.update(user, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Achievement getAchievement(Object user, String achievName) {
		Reflections r = new Reflections("net.sf.esfinge.gamification.mechanics.database.nosql");
		for (Class c : r.getSubTypesOf(Storage.class)) {
			Storage s;
			try {
				Constructor m = c.getConstructor(MongoCollection.class);
				s = (Storage) m.newInstance(collection);
			} catch (Exception e) {
				throw new RuntimeException("Error creating an instance of " + c.getName()
						+ ". A constructor receiving a MongoCollection must be available.", e);
			}
			try {
				Achievement a = s.select(user, achievName);
				if (a != null)
					return a;
			} catch (SQLException e) {
				throw new RuntimeException("Database error", e);
			}
		}
		return null;
	}

	@Override
	public Map<String, Achievement> getAchievements(Object user) {

		Map<String, Achievement> achievements = new HashMap<String, Achievement>();
		Reflections r = new Reflections("net.sf.esfinge.gamification.mechanics.database.nosql");
		for (Class c : r.getSubTypesOf(Storage.class)) {
			Storage s;
			try {
				Constructor m = c.getConstructor(MongoCollection.class);
				s = (Storage) m.newInstance(collection);
			} catch (Exception e) {
				throw new RuntimeException("Error creating an instance of " + c.getName()
						+ ". A constructor receiving a MongoCollection must be available.", e);
			}
			try {
				Map<String, Achievement> a = s.select(user);
				MapUtils.putAll(achievements, a.entrySet().toArray());

			} catch (SQLException e) {
				throw new RuntimeException("Database error", e);
			}
		}
		return achievements;
	}

	@Override
	public Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type) {
		Storage storage;
		Map<String, Achievement> result = null;

		try {
			storage = factory.storageFor(type.newInstance());
			result = (Map<String, Achievement>) storage.selectAll();

		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

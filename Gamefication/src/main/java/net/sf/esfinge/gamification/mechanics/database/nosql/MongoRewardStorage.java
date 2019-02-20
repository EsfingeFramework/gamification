package net.sf.esfinge.gamification.mechanics.database.nosql;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class MongoRewardStorage implements Storage {

	private MongoCollection<Document> collection;

	public MongoRewardStorage(MongoCollection<Document> c) {
		collection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Document document = toDocument(user, a);
		collection.insertOne(document);
	}

	public Reward select(Object user, String name) throws SQLException {

		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", name)
				.append("achievement.type", "Reward");
		Optional<Document> achievement = Optional.ofNullable(collection.find(query).first());

		Reward r = null;
		if (achievement.isPresent()) {
			Document achievementProperties = achievement.get().get("achievement", Document.class);

			r = new Reward(name, achievementProperties.getBoolean("isUsed"));
		}
		return r;
	}

	public Map<String, Achievement> select(Object user) throws SQLException {

		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(and(eq("user", user), eq("achievement.type", "Reward")))
				.projection(fields(exclude("achievement.type"), excludeId()));

		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Reward r = new Reward(achievement.getString("name"), achievement.getBoolean("isUsed"));
			achievements.put(r.getName(), r);
		}

		return achievements;
	}

	public void update(Object user, Achievement a) throws SQLException {
		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", a.getName())
				.append("achievement.type", "Reward");
		Document update = new Document("$set", new Document().append("achievement.isUsed", ((Reward) a).isUsed()));
		collection.updateOne(query, update);
	}

	@Override
	public void delete(Object user, Achievement p) throws SQLException {
		collection.deleteOne(
				and(eq("user", user), eq("achievement.name", p.getName()), eq("achievement.type", "Reward")));

	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {
		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(eq("achievement.type", "Reward"));
		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Reward r = new Reward(achievement.getString("name"), achievement.getBoolean("isUsed"));
			achievements.put(r.getName(), r);
		}

		return achievements;
	}

	private Document toDocument(Object user, Achievement a) {
		Reward r = (Reward) a;
		return new Document().append("user", user).append("achievement",
				new BasicDBObject("type", "Reward").append("name", r.getName()).append("isUsed", r.isUsed()));
	}
}

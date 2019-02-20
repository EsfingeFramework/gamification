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
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class MongoRankingStorage implements Storage {

	private MongoCollection<Document> collection;

	public MongoRankingStorage(MongoCollection<Document> c) {
		collection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Document document = toDocument(user, a);
		collection.insertOne(document);
	}

	public Ranking select(Object user, String name) throws SQLException {

		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", name).append("achievement.type", "Ranking");
		Optional<Document> achievement = Optional.ofNullable(collection.find(query).first());

		Ranking r = null;
		if (achievement.isPresent()) {
			Document achievementProperties = achievement.get().get("achievement", Document.class);

			r = new Ranking(name, achievementProperties.getString("level"));
		}
		return r;
	}

	public Map<String, Achievement> select(Object user) throws SQLException {

		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(and(eq("user", user), eq("achievement.type", "Ranking")))
				.projection(fields(exclude("achievement.type"), excludeId()));

		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Ranking r = new Ranking(achievement.getString("name"), achievement.getString("level"));
			achievements.put(r.getName(), r);
		}

		return achievements;

	}

	public void update(Object user, Achievement a) throws SQLException {

		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", a.getName()).append("achievement.type", "Ranking");
		Document update = new Document("$set", new Document().append("achievement.level", ((Ranking) a).getLevel()));
		collection.updateOne(query, update);

	}

	@Override
	public void delete(Object user, Achievement a) throws SQLException {

		collection.deleteOne(and(eq("user", user), eq("achievement.name", a.getName()), eq("achievement.type", "Ranking")));

	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {
		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(eq("achievement.type", "Ranking"));
		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Ranking r = new Ranking(achievement.getString("name"), achievement.getString("level"));
			achievements.put(r.getName(), r);
		}

		return achievements;
	}

	private Document toDocument(Object user, Achievement a) {
		Ranking r = (Ranking) a;
		return new Document().append("user", user).append("achievement",
				new BasicDBObject("type", "Ranking").append("name", r.getName()).append("level", r.getLevel()));
	}
}

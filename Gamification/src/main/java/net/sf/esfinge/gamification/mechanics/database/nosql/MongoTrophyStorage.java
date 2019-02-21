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
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class MongoTrophyStorage implements Storage {

	private MongoCollection<Document> collection;

	public MongoTrophyStorage(MongoCollection<Document> c) {
		collection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Document document = toDocument(user, a);
		collection.insertOne(document);

	}

	public Trophy select(Object user, String name) throws SQLException {
		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", name).append("achievement.type", "Trophy");
		Optional<Document> achievement = Optional.ofNullable(collection.find(query).first());

		Trophy t = null;
		if (achievement.isPresent()) {
			Document achievementProperties = achievement.get().get("achievement", Document.class);

			t = new Trophy(achievementProperties.getString("name"));
		}
		return t;
	}

	public Map<String, Achievement> select(Object user) throws SQLException {
		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(and(eq("user", user), eq("achievement.type", "Trophy")))
				.projection(fields(exclude("achievement.type"), excludeId()));

		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Trophy t = new Trophy(achievement.getString("name"));
			achievements.put(t.getName(), t);
		}

		return achievements;
	}

	@Override
	public void delete(Object user, Achievement p) throws SQLException {
		collection.deleteOne(and(eq("user", user), eq("achievement.name", p.getName()),eq("achievement.type", "Trophy")));

	}

	@Override
	public void update(Object user, Achievement p) throws SQLException {

	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {

		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(eq("achievement.type", "Trophy"));
		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Trophy t = new Trophy(achievement.getString("name"));
			achievements.put(t.getName(), t);
		}

		return achievements;
	}

	private Document toDocument(Object user, Achievement a) {
		Trophy t = (Trophy) a;
		return new Document().append("user", user).append("achievement",
				new BasicDBObject("type", "Trophy").append("name", t.getName()));
	}

}

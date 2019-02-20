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
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class MongoPointStorage implements Storage {
	private MongoCollection<Document> collection;

	public MongoPointStorage(MongoCollection<Document> c) {
		collection = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#insert(java.lang.
	 * Object, net.sf.esfinge.gamification.achievement.Point)
	 */
	@Override
	public void insert(Object user, Achievement a) throws SQLException {
		Document document = toDocument(user, a);
		collection.insertOne(document);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#select(java.lang.
	 * Object, java.lang.String)
	 */
	@Override
	public Achievement select(Object user, String name) throws SQLException {
		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", name);
		Optional<Document> achievement = Optional.ofNullable(collection.find(query).first());

		Point p = null;
		if (achievement.isPresent()) {
			Document achievementProperties = achievement.get().get("achievement", Document.class);

			p = new Point(achievementProperties.getInteger("quantity"), name);
		}
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#select(java.lang.
	 * Object)
	 */
	@Override
	public Map<String, Achievement> select(Object user) throws SQLException {

		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(and(eq("user", user), eq("achievement.type", "Point")))
				.projection(fields(exclude("achievement.type"), excludeId()));

		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Point p = new Point(achievement.getInteger("quantity"), achievement.getString("name"));
			achievements.put(p.getName(), p);
		}

		return achievements;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#update(java.lang.
	 * Object, net.sf.esfinge.gamification.achievement.Point)
	 */
	@Override
	public void update(Object user, Achievement a) throws SQLException {
		BasicDBObject query = new BasicDBObject().append("user", user).append("achievement.name", a.getName());
		Document update = new Document("$set",
				new Document().append("achievement.quantity", ((Point) a).getQuantity()));
		collection.updateOne(query, update);
	}

	@Override
	public void delete(Object user, Achievement a) throws SQLException {
		collection.deleteOne(and(eq("user", user), eq("achievement.name", a.getName())));
	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {

		Map<String, Achievement> achievements = new HashMap<>();
		FindIterable<Document> results = collection.find(eq("achievement.type", "Point"));
		for (Document result : results) {
			Document achievement = result.get("achievement", Document.class);
			Point p = new Point(achievement.getInteger("quantity"), achievement.getString("name"));
			achievements.put(p.getName(), p);
		}

		return achievements;
	}

	private Document toDocument(Object user, Achievement a) {
		Point p = (Point) a;
		return new Document().append("user", user).append("achievement",
				new BasicDBObject("type", "Point").append("name", p.getName()).append("quantity", p.getQuantity()));
	}

}

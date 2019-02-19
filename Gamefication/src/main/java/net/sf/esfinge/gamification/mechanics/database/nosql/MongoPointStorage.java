package net.sf.esfinge.gamification.mechanics.database.nosql;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.swing.SortOrder;

import org.bson.Document;

import com.mongodb.BasicDBObject;
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#select(java.lang.
	 * Object)
	 */
	@Override
	public Map<String, Achievement> select(Object user) throws SQLException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.esfinge.gamification.mechanics.database.Storage#update(java.lang.
	 * Object, net.sf.esfinge.gamification.achievement.Point)
	 */
	@Override
	public void update(Object user, Achievement a) throws SQLException {

	}

	@Override
	public void delete(Object user, Achievement a) throws SQLException {
	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {

		return null;
	}

	// @Override
	public Map<String, Achievement> findAll(SortOrder sortOrder) {
		return null;
	}

	private Document toDocument(Object user, Achievement a) {
		Point p = (Point) a;
		return new Document().append("user", user).append("achievement",
				new BasicDBObject("type", "Point").append("name", p.getName()).append("quantity", p.getQuantity()));
	}

}

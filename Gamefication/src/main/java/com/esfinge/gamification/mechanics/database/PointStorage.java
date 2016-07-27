package com.esfinge.gamification.mechanics.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;

public class PointStorage implements Storage {
	private Connection connection;

	public PointStorage(Connection c) {
		connection = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamification.mechanics.database.Storage#insert(java.lang.
	 * Object, com.esfinge.gamification.achievement.Point)
	 */
	@Override
	public void insert(Object user, Achievement a) throws SQLException {
		Point p = (Point) a;
		PreparedStatement stmt;
		stmt = connection.prepareStatement("insert into gamification.points "
				+ "(userid, name, points) values (?,?,?)");
		stmt.setString(1, user.toString());
		stmt.setString(2, p.getName());
		stmt.setInt(3, p.getQuantity());
		stmt.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamification.mechanics.database.Storage#select(java.lang.
	 * Object, java.lang.String)
	 */
	@Override
	public Achievement select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection.prepareStatement("select * from gamification.points "
				+ "where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int q = rs.getInt("points");
			Point p2 = new Point(q, name);
			return p2;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamification.mechanics.database.Storage#select(java.lang.
	 * Object)
	 */
	@Override
	public Map<String, Achievement> select(Object user) throws SQLException {
		Map<String, Achievement> map = new HashMap<String, Achievement>();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("select * from gamification.points "
				+ "where userid=?");
		stmt.setString(1, user.toString());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			int q = rs.getInt("points");
			Point p = new Point(q, name);
			map.put(p.getName(), p);
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esfinge.gamification.mechanics.database.Storage#update(java.lang.
	 * Object, com.esfinge.gamification.achievement.Point)
	 */
	@Override
	public void update(Object user, Achievement a) throws SQLException {
		Point p = (Point) a;
		PreparedStatement stmt;
		stmt = connection.prepareStatement("update gamification.points "
				+ "set points = ? where userid=? and name=?");
		stmt.setString(2, user.toString());
		stmt.setString(3, p.getName());
		stmt.setInt(1, p.getQuantity());
		stmt.execute();
	}

	@Override
	public void delete(Object user, Achievement a) throws SQLException {
		Point p = (Point) a;

		PreparedStatement stmt;
		stmt = connection.prepareStatement("delete from gamification.points "
				+ "where userid=? and name=?");
		stmt.setString(1, user.toString());
		stmt.setString(2, p.getName());
		stmt.execute();
	}

}

package com.esfinge.gamefication.mechanics.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.achievement.Point;

public class PointStorage {
	private Connection connection;

	public PointStorage(Connection c) {
		connection = c;
	}

	public void insert(Object user, Point p) throws SQLException {

		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("insert into gamefication.points (userid, name, points) values (?,?,?)");
		stmt.setString(1, user.toString());
		stmt.setString(2, p.getName());
		stmt.setInt(3, p.getQuantity());
		stmt.execute();
	}

	public Point select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamefication.points where userid=? and name = ?");
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
	
	public Map<String, Achievement> select(Object user) throws SQLException{
		Map<String, Achievement> map = new HashMap<String, Achievement>();
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamefication.points where userid=?");
		stmt.setString(1, user.toString());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			String name = rs.getString("name");
			int q = rs.getInt("points");
			Point p = new Point(q,name);
			map.put(p.getName(), p);
		}
		
		return map;
	}

	public void update(Object user, Point p) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("update gamefication.points set points = ? where userid=? and name=?");
		stmt.setString(2, user.toString());
		stmt.setString(3, p.getName());
		stmt.setInt(1, p.getQuantity());
		stmt.execute();
	}
}

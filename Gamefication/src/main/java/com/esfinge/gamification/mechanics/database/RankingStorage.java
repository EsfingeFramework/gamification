package com.esfinge.gamification.mechanics.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Ranking;

public class RankingStorage implements Storage {

	private Connection connection;

	public RankingStorage(Connection c) {
		connection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Ranking r = (Ranking) a;
		PreparedStatement stmt;

		stmt = connection.prepareStatement("insert into gamification.ranking"
				+ " (userid, name, level) values (?,?,?)");
		stmt.setString(1, user.toString());
		stmt.setString(2, r.getName());
		stmt.setString(3, r.getLevel());
		stmt.execute();
	}

	public Ranking select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection.prepareStatement("select * from gamification.ranking"
				+ " where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			String level = rs.getString("level");
			Ranking r = new Ranking(name, level);
			return r;
		}
		return null;
	}

	public Map<String, Achievement> select(Object user) throws SQLException {
		Map<String, Achievement> map = new HashMap<String, Achievement>();
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.ranking "
						+ "where userid=?");
		stmt.setString(1, user.toString());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			String level = rs.getString("level");
			Ranking r = new Ranking(name, level);
			map.put(r.getName(), r);
		}

		return map;
	}

	public void update(Object user, Achievement a) throws SQLException {
		Ranking r = (Ranking) a;
		PreparedStatement stmt;
		stmt = connection.prepareStatement("update gamification.ranking "
				+ "set level = ? where userid=? and name=?");
		stmt.setString(2, user.toString());
		stmt.setString(3, r.getName());
		stmt.setString(1, r.getLevel());
		stmt.execute();
	}

	@Override
	public void delete(Object user, Achievement a) throws SQLException {
		PreparedStatement stmt;
		stmt = connection.prepareStatement("delete from gamification.ranking"
				+ " where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, a.getName());
		stmt.execute();

	}
}

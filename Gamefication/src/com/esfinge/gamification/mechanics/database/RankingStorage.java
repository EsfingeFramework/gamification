package com.esfinge.gamification.mechanics.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Rank;

public class RankingStorage {

	private Connection connection;
	
	public RankingStorage(Connection c){
		connection = c;
	}
	
	public void insert (Object user, Rank r) throws SQLException{
		PreparedStatement stmt;
		
		stmt = connection
				.prepareStatement("insert into gamification.ranking"
						+ " (userid, name, level) values (?,?,?)");
		stmt.setString(1,user.toString());
		stmt.setString(2,r.getName());
		stmt.setString(3, r.getLevel());
		stmt.execute();
	}
	
	public Rank select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.ranking"
						+ " where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			String level = rs.getString("level");
			Rank r = new Rank(name, level);
			return r;
		}
		return null;
	}
}

package net.sf.esfinge.gamification.mechanics.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class RewardStorage implements Storage {
	private Connection connection;

	public RewardStorage(Connection c) {
		connection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Reward r = (Reward)a;
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("insert into gamification.reward "
						+ "(userid, name, used) values (?,?,?)");
		stmt.setString(1, user.toString());
		stmt.setString(2, r.getName());
		stmt.setBoolean(3, r.isUsed());
		stmt.execute();
	}

	public Reward select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.reward "
						+ "where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			boolean u = rs.getBoolean("used");
			Reward r = new Reward(name, u);
			return r;
		}
		return null;
	}
	
	public Map<String, Achievement> select(Object user) throws SQLException{
		Map<String, Achievement> map = new HashMap<String, Achievement>();
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.reward "
						+ "where userid=?");
		stmt.setString(1, user.toString());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			String name = rs.getString("name");
			boolean u = rs.getBoolean("used");
			Reward r = new Reward(name,u);
			map.put(r.getName(), r);
		}
		
		return map;
	}

	public void update(Object user, Achievement a) throws SQLException {
		Reward r = (Reward)a;
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("update gamification.reward "
						+ "set used = ? where userid=? and name=?");
		stmt.setString(2, user.toString());
		stmt.setString(3, r.getName());
		stmt.setBoolean(1, r.isUsed());
		stmt.execute();
	}

	@Override
	public void delete(Object user, Achievement p) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("delete from gamification.reward "
						+ "where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, p.getName());
		stmt.execute();
		
	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {
		Map<String, Achievement> map = null;
		PreparedStatement stmt;
		stmt = connection.prepareStatement("select userid, name, used from gamification.reward");
		ResultSet rs = stmt.executeQuery();
		if(rs != null) {
			map = new HashMap<>();
			while(rs.next()) {
				String name = rs.getString("name");
				boolean used = rs.getBoolean("used");
				Reward reward = new Reward(name, used);
				map.put(rs.getString("userid"), reward);
			}
		}
		return map;
	}
}

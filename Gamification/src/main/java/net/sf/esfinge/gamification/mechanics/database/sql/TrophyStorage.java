package net.sf.esfinge.gamification.mechanics.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.database.Storage;

public class TrophyStorage implements Storage{
	private Connection connection;

	public TrophyStorage(Connection c) {
		connection = c;
	}

	public void insert(Object user, Achievement a) throws SQLException {
		Trophy t = (Trophy)a;
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("insert into gamification.trophy "
						+ "(userid, name) values (?,?)");
		stmt.setString(1, user.toString());
		stmt.setString(2, t.getName());
	
		stmt.execute();
	}

	public Trophy select(Object user, String name) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.trophy "
						+ "where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, name);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Trophy t = new Trophy(name);
			return t;
		}
		return null;
	}
	
	public Map<String, Achievement> select(Object user) throws SQLException{
		Map<String, Achievement> map = new HashMap<String, Achievement>();
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("select * from gamification.trophy "
						+ "where userid=?");
		stmt.setString(1, user.toString());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			String name = rs.getString("name");
			Trophy t = new Trophy(name);
			map.put(t.getName(), t);
		}
		
		return map;
	}

	@Override
	public void delete(Object user, Achievement p) throws SQLException {
		PreparedStatement stmt;
		stmt = connection
				.prepareStatement("delete from gamification.trophy "
						+ "where userid=? and name = ?");
		stmt.setString(1, user.toString());
		stmt.setString(2, p.getName());
		stmt.execute();
		
	}

	@Override
	public void update(Object user, Achievement p) throws SQLException {
		
	}

	@Override
	public Map<String, Achievement> selectAll() throws SQLException {
		Map<String, Achievement> map = null;
		PreparedStatement stmt;
		stmt = connection.prepareStatement("select userid, name from gamification.trophy");
		ResultSet rs = stmt.executeQuery();
		if(rs != null) {
			map = new HashMap<>();
			while(rs.next()) {
				String name = rs.getString("name");
				Trophy trophy = new Trophy(name);
				map.put(rs.getString("userid"), trophy);
			}
		}
		return map;
	}

}

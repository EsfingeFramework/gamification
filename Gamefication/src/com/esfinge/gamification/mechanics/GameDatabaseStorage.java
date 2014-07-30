package com.esfinge.gamification.mechanics;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.listener.AchievementListener;
import com.esfinge.gamification.mechanics.database.PointStorage;

public class GameDatabaseStorage extends Game {
	private Connection connection;
	public GameDatabaseStorage(Connection c)  {
		connection = c;
		try {
			
			DatabaseMetaData dbmd = connection.getMetaData();
			ResultSet rs = dbmd.getSchemas(null, "GAMEFICATION");
			boolean found = false;
			while(rs.next()) {
				if (rs.getString(1).compareToIgnoreCase("gamefication") == 0) {
					found = true;
				}
			}
			if (!found) {
				Statement s = connection.createStatement();
				s.execute("create table gamefication.points (userid varchar(255) not null, name varchar(255) not null, 	points integer not null, primary key (userid,name))");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertAchievement(Object user, Achievement a) {
		// TODO Auto-generated method stub
		PointStorage ps = new PointStorage(connection);
		try {
			Point p = (Point)this.getAchievement(user, a.getName());
			if (p == null){
				ps.insert(user, (Point)a);
			}else{
				p.incrementAchievement(a);
				ps.update(user, p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAchievement(Object user, Achievement a) {
		PointStorage ps = new PointStorage(connection);
		Point p = null;
		try {
			p = ps.select(user, a.getName());
			p.removeAchievement(a);
			ps.update(user, p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Achievement getAchievement(Object user, String achievName) {
		
		Point p = null;
		PointStorage ps = new PointStorage(connection);
		try {
			p = ps.select(user, achievName);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		PointStorage ps = new PointStorage(connection);
		try {
			return ps.select(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

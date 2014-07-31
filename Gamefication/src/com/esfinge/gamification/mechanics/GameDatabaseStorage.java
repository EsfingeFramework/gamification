package com.esfinge.gamification.mechanics;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.reflections.Reflections;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.mechanics.database.PointStorage;
import com.esfinge.gamification.mechanics.database.RankingStorage;
import com.esfinge.gamification.mechanics.database.Storage;
import com.esfinge.gamification.mechanics.database.StorageFactory;

public class GameDatabaseStorage extends Game {
	private Connection connection;
	private StorageFactory factory;
	public GameDatabaseStorage(Connection c)  {
		connection = c;
		factory = new StorageFactory(c);
		try {
			
			DatabaseMetaData dbmd = connection.getMetaData();
			ResultSet rs = dbmd.getSchemas(null, "GAMIFICATION");
			boolean found = false;
			while(rs.next()) {
				if (rs.getString(1).compareToIgnoreCase("gamification") == 0) {
					found = true;
				}
			}
			if (!found) {
				Statement s = connection.createStatement();
				s.execute("create table gamification.users"
						+ "(userid varchar(255) not null,"
						+ "primary key (userid))");
				s.execute("create table gamification.points"
						+ "(userid varchar(255) not null,"
						+ " name varchar(255) not null, "
						+ "points integer not null, "
						+ "primary key (userid,name))");
				s.execute("create table gamification.ranking "
						+ "(userid varchar(255) not null,"
						+ " name varchar(255) not null, "
						+ " level varchar(255) not null, "
						+ "primary key (userid,name))");
				s.execute("create table gamification.reward "
						+ "(userid varchar(255) not null,"
						+ " name varchar(255) not null, "
						+ " used boolean not null, "
						+ "primary key (userid,name))");
				s.execute("create table gamification.trophy "
						+ "(userid varchar(255) not null,"
						+ " name varchar(255) not null, "
						+ "primary key (userid,name))");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertAchievement(Object user, Achievement a) {
		Storage ps = factory.storageFor(a);
		try {
			Achievement p = this.getAchievement(user, a.getName());
			if (p == null){
				ps.insert(user, a);
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
		Storage ps = factory.storageFor(a);
		Achievement p = null;
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
		
		Reflections r = new Reflections("com.esfinge.gamification.mechanics.database");
		for(Class c:r.getSubTypesOf(Storage.class)) {
			Storage s;
			try {
				Constructor m = c.getConstructor(Connection.class);
				s = (Storage)m.newInstance(connection);
			} catch (Exception e) {
				throw new RuntimeException("Error creating an instance of " + c.getName() + ". A constructor receiving a Connection must be available.", e);
			}
			try {
				Achievement a = s.select(user, achievName);
				if (a != null)
					return a;
			} catch (SQLException e) {
				throw new RuntimeException("Database error", e);
			}
		}
		
		
		Achievement p = null;
		Storage ps = new PointStorage(connection);
		try {
			p = ps.select(user, achievName);
			if (p == null){
				ps = new RankingStorage(connection);
				p = ps.select(user, achievName);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		Storage ps = new PointStorage(connection);
		try {
			return ps.select(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Reflections r = new Reflections("com.esfinge.gamification.mechanics.database");
		for(Class c:r.getSubTypesOf(Storage.class))
			System.out.println(c.getName());
	}

}

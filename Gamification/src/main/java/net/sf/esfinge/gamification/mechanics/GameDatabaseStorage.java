package net.sf.esfinge.gamification.mechanics;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.reflections.Reflections;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.mechanics.database.Storage;
import net.sf.esfinge.gamification.mechanics.database.sql.StorageFactory;

public class GameDatabaseStorage extends Game {
	private Connection connection;
	private StorageFactory factory;

	public GameDatabaseStorage(Connection c) {
		connection = c;
		factory = new StorageFactory(c);
		try {

			DatabaseMetaData dbmd = connection.getMetaData();
			ResultSet rs = dbmd.getSchemas(null, "GAMIFICATION");
			boolean found = false;
			while (rs.next()) {
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
			ps.insert(user, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAchievement(Object user, Achievement a) {
		Storage ps = factory.storageFor(a);
		try {
				ps.delete(user, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Achievement getAchievement(Object user, String achievName) {

		Reflections r = new Reflections(
				"net.sf.esfinge.gamification.mechanics.database.sql");
		for (Class c : r.getSubTypesOf(Storage.class)) {
			Storage s;
			try {
				Constructor m = c.getConstructor(Connection.class);
				s = (Storage) m.newInstance(connection);
			} catch (Exception e) {
				throw new RuntimeException(
						"Error creating an instance of "
								+ c.getName()
								+ ". A constructor receiving a Connection must be available.",
						e);
			}
			try {
				Achievement a = s.select(user, achievName);
				if (a != null)
					return a;
			} catch (SQLException e) {
				throw new RuntimeException("Database error", e);
			}
		}
		return null;
	}

	@Override
	public Map<String, Achievement> getAchievements(Object user) {
		Map<String, Achievement> achievements = new HashMap<String, Achievement>();
		Reflections r = new Reflections(
				"net.sf.esfinge.gamification.mechanics.database.sql");
		for (Class c : r.getSubTypesOf(Storage.class)) {
			Storage s;
			try {
				Constructor m = c.getConstructor(Connection.class);
				s = (Storage) m.newInstance(connection);
			} catch (Exception e) {
				throw new RuntimeException(
						"Error creating an instance of "
								+ c.getName()
								+ ". A constructor receiving a Connection must be available.",
						e);
			}
			try {
				Map<String, Achievement> a = s.select(user);
				MapUtils.putAll(achievements, a.entrySet().toArray());

			} catch (SQLException e) {
				throw new RuntimeException("Database error", e);
			}
		}
		return achievements;
	}

	@Override
	public void updateAchievement(Object user, Achievement a) {
		Storage ps = factory.storageFor(a);
		try {
			ps.update(user, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type) {
		Storage ps = null;
		Map<String, Achievement> map = null;
		try {
			ps = factory.storageFor(type.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			map = ps.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}

package net.sf.esfinge.gamification.mechanics.database;

import java.sql.SQLException;
import java.util.Map;

import net.sf.esfinge.gamification.achievement.Achievement;

public interface Storage {

	public abstract void insert(Object user, Achievement p) throws SQLException;
	
	public abstract void delete(Object user, Achievement p) throws SQLException;

	public abstract Achievement select(Object user, String name) throws SQLException;

	public abstract Map<String, Achievement> select(Object user)
			throws SQLException;

	public abstract void update(Object user, Achievement p) throws SQLException;

	public abstract Map<String, Achievement> selectAll() throws SQLException;
	
}
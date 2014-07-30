package com.esfinge.gamification.mechanics.database;


import java.sql.Connection;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;

public class StorageFactory {
	
	private Connection connection;
	
	public StorageFactory(Connection connection) {
		super();
		this.connection = connection;
	}



	public Storage storageFor(Achievement a) {
		if (a instanceof Point) {
			return new PointStorage(connection);
		}// else if (a instanceof Ranking) {
//			return new RankingStorage(connection);
		throw new RuntimeException("Cannot create Storage for "+a.getClass().getName());
	}

}

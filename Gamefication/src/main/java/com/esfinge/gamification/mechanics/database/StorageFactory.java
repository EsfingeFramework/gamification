package com.esfinge.gamification.mechanics.database;


import java.sql.Connection;

import com.esfinge.gamification.achievement.Achievement;
import com.esfinge.gamification.achievement.Point;
import com.esfinge.gamification.achievement.Ranking;
import com.esfinge.gamification.achievement.Reward;
import com.esfinge.gamification.achievement.Trophy;

public class StorageFactory {
	
	private Connection connection;
	
	public StorageFactory(Connection connection) {
		super();
		this.connection = connection;
	}



	public Storage storageFor(Achievement a) {
		if (a instanceof Point) {
			return new PointStorage(connection);
		} else if (a instanceof Ranking) {
			return new RankingStorage(connection);
		} else if (a instanceof Reward) {
			return new RewardStorage(connection);
		} else if (a instanceof Trophy) {
			return new TrophyStorage(connection);
		}
		throw new RuntimeException("Cannot create Storage for "+a.getClass().getName());
	}

}

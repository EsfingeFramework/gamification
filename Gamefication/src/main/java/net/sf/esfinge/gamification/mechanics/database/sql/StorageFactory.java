package net.sf.esfinge.gamification.mechanics.database.sql;


import java.sql.Connection;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.achievement.Ranking;
import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.mechanics.database.Storage;

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

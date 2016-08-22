package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.annotation.RewardsToUser;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

public class RewardsToUserProcessor implements AchievementProcessor {
	
	private boolean used;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RewardsToUser rtu = (RewardsToUser) an;
		used = rtu.used();
		name = rtu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method, Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.addAchievement(user, r);
	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.addAchievement(user, r);
		
	}
	
}

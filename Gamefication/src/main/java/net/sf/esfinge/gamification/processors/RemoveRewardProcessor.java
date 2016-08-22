package net.sf.esfinge.gamification.processors;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Reward;
import net.sf.esfinge.gamification.annotation.RemoveReward;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

public class RemoveRewardProcessor implements AchievementProcessor {
	private boolean used;
	private String name;

	@Override
	public void receiveAnnotation(Annotation an) {
		RemoveReward rtu = (RemoveReward) an;
		used = rtu.used();
		name = rtu.name();
	}

	@Override
	public void process(Game game, Object encapsulated, Method method,
			Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.removeAchievement(user, r);

	}

	@Override
	public void process(Game game, Object encapsulated,
			Class<? extends Method> class1, Object[] args) {
		Object user = UserStorage.getUserID();
		Reward r = new Reward(name, used);
		game.removeAchievement(user, r);

		
	}

}

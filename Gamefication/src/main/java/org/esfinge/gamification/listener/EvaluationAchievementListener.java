package org.esfinge.gamification.listener;

import java.util.function.BiFunction;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.mechanics.Game;

public class EvaluationAchievementListener<T extends Achievement> implements AchievementListener {

	private BiFunction<T,Object,Boolean> evaluation;
	private Achievement bonus;
	private Class<?> clazz;
	
	
	public EvaluationAchievementListener(Class<?> clazz, BiFunction<T, Object, Boolean> evaluation, Achievement bonus) {
		super();
		this.clazz = clazz;
		this.evaluation = evaluation;
		this.bonus = bonus;
	}

	@Override
	public void achievementAdded(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		if(updated.getClass().isAssignableFrom(clazz) && evaluation.apply((T)updated, user)){
			game.addAchievement(user, bonus);
		}
	}

	@Override
	public void achievementRemoved(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		if(updated != null
				&& updated.getClass().isAssignableFrom(clazz) 
				&& !evaluation.apply((T)updated, user)){
			game.removeAchievement(user, bonus);
		}
	}

}

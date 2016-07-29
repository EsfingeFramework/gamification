package org.esfinge.gamification.listener;

import java.util.function.BiFunction;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.mechanics.Game;

public class EvaluationAchievementListener<T extends Achievement> implements AchievementListener {

	private BiFunction<T,Object,Boolean> evaluation;
	private Achievement bonus;
	
	
	public EvaluationAchievementListener(BiFunction<T, Object, Boolean> evaluation, Achievement bonus) {
		super();
		this.evaluation = evaluation;
		this.bonus = bonus;
	}

	@Override
	public void achievementAdded(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		try {
			if(evaluation.apply((T)updated, user)){
				game.addAchievement(user, bonus);
			}
		} catch (ClassCastException e) {
			//nao eh o tipo certo
		}
	}

	@Override
	public void achievementRemoved(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		try {
			if(updated != null
					&& !evaluation.apply((T)updated, user)){
				game.removeAchievement(user, bonus);
			}
		} catch (ClassCastException e) {
			//Nao eh o tipo certo
		}
	}

}

package net.sf.esfinge.gamification.listener;

import java.util.function.BiPredicate;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.mechanics.Game;

public class EvaluationAchievementListener<T extends Achievement> implements AchievementListener {

	private BiPredicate<T,Object> evaluation;
	private Achievement bonus;
	
	
	public EvaluationAchievementListener(BiPredicate<T, Object> evaluation, Achievement bonus) {
		super();
		this.evaluation = evaluation;
		this.bonus = bonus;
	}

	@Override
	public void achievementAdded(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		try {
			if(evaluation.test((T)updated, user)){
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
					&& evaluation.negate().test((T)updated, user)){
				game.removeAchievement(user, bonus);
			}
		} catch (ClassCastException e) {
			//Nao eh o tipo certo
		}
	}

}

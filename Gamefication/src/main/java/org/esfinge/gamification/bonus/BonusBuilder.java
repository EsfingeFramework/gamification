package org.esfinge.gamification.bonus;

import java.util.function.BiFunction;

import org.esfinge.gamification.achievement.Achievement;
import org.esfinge.gamification.listener.EvaluationAchievementListener;
import org.esfinge.gamification.mechanics.Game;

public class BonusBuilder {

	Game game;
	Achievement bonus;

	public BonusBuilder(Game game, Achievement bonus) {
		super();
		this.game = game;
		this.bonus = bonus;
	}

	public <T extends Achievement> void when(BiFunction<T, Object, Boolean> when) {
		game.addListener(new EvaluationAchievementListener<T>(when, bonus));
	}
}

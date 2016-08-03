package org.esfinge.gamification.event;

import java.util.function.BiPredicate;

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

	public <T extends Achievement> void when(BiPredicate<T, Object> when) {
		game.addListener(new EvaluationAchievementListener<T>(when, bonus));
	}
}

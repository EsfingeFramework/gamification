package net.sf.esfinge.gamification.event;

import java.util.function.BiPredicate;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.listener.EvaluationAchievementListener;
import net.sf.esfinge.gamification.mechanics.Game;

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

package net.sf.esfinge.gamification.listener;

import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.event.listener.EventListener;
import net.sf.esfinge.gamification.mechanics.Game;

public class EvaluationAchievementProcessorAchievementoListener<T extends Achievement> implements AchievementListener {

	private EventListener<T> eventListener; 
	
	public EvaluationAchievementProcessorAchievementoListener(EventListener<T> eventListener) {
		this.eventListener = eventListener;
	}

	@Override
	public void achievementAdded(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		try {
			if(eventListener.evaluate((T)updated, user)){				
				Method method = eventListener.getMethod();				
				method.invoke(eventListener.getConfigurationObject());
			}
		} catch (Throwable e) {
			//nao eh possível colocar achievements
		}
	}

	@Override
	public void achievementRemoved(Game game, Object user, Achievement a) {
		//Não faz nada ao remover.
	}

}

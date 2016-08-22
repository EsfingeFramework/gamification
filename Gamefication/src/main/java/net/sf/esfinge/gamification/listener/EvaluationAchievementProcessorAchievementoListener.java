package net.sf.esfinge.gamification.listener;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.event.listener.EventListener;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.proxy.GameInvoker;

public class EvaluationAchievementProcessorAchievementoListener<T extends Achievement> implements AchievementListener {

	private GameInvoker gameInvoker = GameInvoker.getInstance();
	private EventListener<T> eventListener; 
	
	public EvaluationAchievementProcessorAchievementoListener(EventListener<T> eventListener) {
		super();
		this.eventListener = eventListener;
	}

	@Override
	public void achievementAdded(Game game, Object user, Achievement a) {
		Achievement updated = game.getAchievement(user, a.getName());
		
		try {
			if(eventListener.evaluate((T)updated, user)){
				eventListener.getMethod().invoke(eventListener.getConfigurationObject());
				gameInvoker.registerAchievment(null, eventListener.getMethod(), null);
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

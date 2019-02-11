package net.sf.esfinge.gamification.mechanics;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.event.BonusBuilder;
import net.sf.esfinge.gamification.event.annotation.EventListenerImplementation;
import net.sf.esfinge.gamification.event.annotation.GamificationListener;
import net.sf.esfinge.gamification.event.listener.EventListener;
import net.sf.esfinge.gamification.listener.AchievementListener;
import net.sf.esfinge.gamification.listener.EvaluationAchievementProcessorAchievementoListener;

public abstract class Game {

	 public abstract void insertAchievement(Object user, Achievement a);
	
	 public abstract void deleteAchievement(Object user, Achievement a);
	
	 public abstract void updateAchievement(Object user, Achievement a);
	 
	 public abstract Achievement getAchievement(Object user, String
	 achievName);
	
	 public abstract Map<String, Achievement> getAchievements(Object user);
	 
	 public abstract Map<String, Achievement> getAllAchievements(Class<? extends Achievement> type);
	
	// /
	private List<AchievementListener> listeners = new ArrayList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#addAchievement(java.lang.Object,
	 * net.sf.esfinge.gamification.achievement.Achievement)
	 */
	public void addAchievement(Object user, Achievement a) {
		Achievement p = this.getAchievement(user, a.getName());
		if (p == null){
			this.insertAchievement(user, a);
		}else{
			p.incrementAchievement(a);
			this.updateAchievement(user, p);
		}
		notifyAdded(user, a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#removeAchievement(java.lang.Object
	 * , net.sf.esfinge.gamification.achievement.Achievement)
	 */
	public void removeAchievement(Object user, Achievement a) {
		Achievement p = this.getAchievement(user, a.getName());
		
		if(p != null) {
			if (p.removeAchievement(a))
				this.deleteAchievement(user, p);
			else
				this.updateAchievement(user, p);
			notifyRemoved(user, a);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.esfinge.gamification.mechanics.Game#addListener(net.sf.esfinge.gamification
	 * .listener.AchievementListener)
	 */
	public void addListener(AchievementListener listener) {
		listeners.add(listener);
	}

	private void notifyAdded(Object user, Achievement a) {
		for (AchievementListener l : listeners) {
			l.achievementAdded(this, user, a);

		}
	}
	
	private void notifyRemoved(Object user, Achievement a) {
		for (AchievementListener l : listeners) {
			l.achievementRemoved(this, user, a);

		}
	}

	public BonusBuilder addBonus(Achievement bonus) {
		return new BonusBuilder(this, bonus);
	}
	
	public void addEventListeners(Object... configurationObjects){
		for (Object configurationObject : configurationObjects) {
			Class<?> configurationObjectClazz = configurationObject.getClass();
			if(configurationObjectClazz.isAnnotationPresent(GamificationListener.class)){
				for(Method m: configurationObjectClazz.getDeclaredMethods()){
					for(Annotation an: m.getAnnotations()){
						if(an.annotationType().isAnnotationPresent(EventListenerImplementation.class)){
							try {
								EventListenerImplementation eventImplementation = an.annotationType().getAnnotation(EventListenerImplementation.class);
								EventListener eventListener = eventImplementation.value().newInstance();
								eventListener.setAnnotation(an);
								eventListener.setMethod(m);
								eventListener.setConfigurationObject(configurationObject);
								this.addListener(new EvaluationAchievementProcessorAchievementoListener(eventListener));
							} catch (Exception e) {
								//adicionando o listener de Eventos com anotações
							}
						}
					}
				}
			}
		}
	}

}
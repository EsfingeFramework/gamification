package com.esfinge.gamefication.mechanics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.esfinge.gamefication.achievement.Achievement;
import com.esfinge.gamefication.listener.AchievementListener;


public class GameMemoryStorage implements Game {

	private Map<Object,Map<String, Achievement>> achievments = new HashMap<>();
	private ArrayList<AchievementListener> ac = new ArrayList<>();

	
	/* (non-Javadoc)
	 * @see com.esfinge.gamefication.mechanics.Game#addAchievement(java.lang.Object, com.esfinge.gamefication.achievement.Achievement)
	 */
	@Override
	public void addAchievement(Object user, Achievement a){
		if(!achievments.containsKey(user)){
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		if(userAchiev.containsKey(a.getName())){
			userAchiev.get(a.getName()).incrementAchievement(a);
		}else{
			userAchiev.put(a.getName(), a);
			for(AchievementListener listener:ac)
				listener.achievementAdded(user, a);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.esfinge.gamefication.mechanics.Game#removeAchievement(java.lang.Object, com.esfinge.gamefication.achievement.Achievement)
	 */
	@Override
	public void removeAchievement(Object user, Achievement a){
		
		if(!achievments.containsKey(user)){
			achievments.put(user, new HashMap<String, Achievement>());
		}
		Map<String, Achievement> userAchiev = achievments.get(user);
		if(userAchiev.containsKey(a.getName())){
			userAchiev.get(a.getName()).removeAchievement(a);
		}else{
			userAchiev.put(a.getName(), a);
			for(AchievementListener listener:ac)
				listener.achievementRemoved(user, a);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.esfinge.gamefication.mechanics.Game#getAchievement(java.lang.Object, java.lang.String)
	 */
	@Override
	public Achievement getAchievement(Object user, String achievName){
		
		return achievments.get(user).get(achievName);
	}
	

	/* (non-Javadoc)
	 * @see com.esfinge.gamefication.mechanics.Game#getAchievements(java.lang.Object)
	 */
	@Override
	public Map<String, Achievement> getAchievements(Object user){
		return achievments.get(user);
	}
	
	
	/* (non-Javadoc)
	 * @see com.esfinge.gamefication.mechanics.Game#addListener(com.esfinge.gamefication.listener.AchievementListener)
	 */
	@Override
	public void addListener(AchievementListener listener){
		ac.add(listener);
	}
	
	private void notify(AchievementListener listener){
		for(AchievementListener a : ac){
		a.achievementAdded(a, null);
		
			}
		}
	}
	


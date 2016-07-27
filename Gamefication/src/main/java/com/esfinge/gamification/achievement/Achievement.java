package com.esfinge.gamification.achievement;


public interface Achievement {
	
	
    public String getName();
	
    
	public void incrementAchievement(Achievement a);

	public boolean removeAchievement(Achievement r);

}
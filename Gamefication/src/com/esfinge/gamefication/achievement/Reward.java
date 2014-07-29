package com.esfinge.gamefication.achievement;

public class Reward implements Achievement{
	
	private String name;
	private boolean used;
	
	public Reward(String name, boolean used) {
		this.name = name;
		this.used = used;
	}

	public String getName() {
		return name;
	}

	public boolean isUsed() {
		return used;
	}

	public void incrementAchievement(Achievement a) {
		
	try{
		if(!a.getName().equals(getName()) || !(a instanceof Reward))
			throw new RuntimeException("The achievement should be of the same type");
	}catch (RuntimeException e) {
			this.name += ((Reward)a).isUsed();
		}

	}
	
	public void removeAchievement(Achievement r) {
		
	}
	
	public String toString() {
		return "Reward Achieved - " + name + ": "+ used;
	}

}


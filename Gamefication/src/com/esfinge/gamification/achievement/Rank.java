package com.esfinge.gamification.achievement;

public class Rank implements Achievement{
	
	private String name;
	private String level;
	
	public Rank(String name, String level) {
		super();
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public String getLevel() {
		return level;
	}

	public void incrementAchievement(Achievement a) {
		try{
			if(!a.getName().equals(getName()) || !(a instanceof Rank))
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.name = ((Rank)a).getName();
				this.level = ((Rank)a).getLevel();
			}		
	}
	
	public void removeAchievement(Achievement r) {
		try{
			if(!r.getName().equals(getName()) || r instanceof Rank)
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.name = null;
				this.level = null;
			}
	}
	
	public String toString() {
		return "Rank Achieved - " + name + ": "+ level;
	}

}

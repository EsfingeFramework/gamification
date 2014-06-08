package com.esfinge.gamefication.achievement;

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
		
		
	}
	
	public void removeAchievement(Achievement r) {
		
	}
	
	public String toString() {
		return "Rank Achieved - " + name + ": "+ level;
	}

}

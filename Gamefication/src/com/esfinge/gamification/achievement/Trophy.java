package com.esfinge.gamification.achievement;

public class Trophy implements Achievement{
	
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public Trophy(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void incrementAchievement(Achievement a) {
	}
	
	public void removeAchievement(Achievement r) {
	}

	public String toString() {
		return "Tropy Achieved - " + name;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Trophy))
			return false;
		Trophy t = (Trophy)o;
		return this.name.equals(t.name);
	}
}

package com.esfinge.gamification.achievement;

public class Ranking implements Achievement{
	
	private String name;
	private String level;
	
	public Ranking(String name, String level) {
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
			if(!a.getName().equals(getName()) || !(a instanceof Ranking))
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.name = ((Ranking)a).getName();
				this.level = ((Ranking)a).getLevel();
			}		
	}
	
	public void removeAchievement(Achievement r) {
		try{
			if(!r.getName().equals(getName()) || r instanceof Ranking)
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.name = null;
				this.level = null;
			}
	}
	
	public String toString() {
		return "Ranking Achieved - " + name + ": "+ level;
	}
	
	@Override
	public boolean equals(Object o) {
		Ranking r = (Ranking)o;
		return this.name == r.name && this.level == r.level;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Ranking))
			return false;
		Ranking r = (Ranking)o;
		return this.name.equals(r.name) && this.level.equals(r.level);
	}
}

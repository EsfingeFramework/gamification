package net.sf.esfinge.gamification.achievement;

public class Ranking implements Achievement{
	
	private String name;
	private String level;
	
	public Ranking() {
	}
	
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
			if(!(a.getName().equals(getName()) && a instanceof Ranking))
				throw new RuntimeException("The achievement should be of the same type");
			this.name = ((Ranking)a).getName();
			this.level = ((Ranking)a).getLevel();
	}
	
	public boolean removeAchievement(Achievement r) {
			if(!(r.getName().equals(getName()) && r instanceof Ranking))
				throw new RuntimeException("The achievement should be of the same type");
			this.level = null;
			return true;
	}
	
	public String toString() {
		return "Ranking Achieved - " + name + ": "+ level;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Ranking))
			return false;
		Ranking r = (Ranking)o;
		return this.name.equals(r.name) && this.level.equals(r.level);
	}
}

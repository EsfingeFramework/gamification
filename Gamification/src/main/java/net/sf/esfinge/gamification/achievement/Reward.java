package net.sf.esfinge.gamification.achievement;

public class Reward implements Achievement{
	
	private String name;
	private boolean used;
	
	public Reward() {
	}
	
	public Reward(String name) {
		this.name = name;
		this.used = false;
	}
	
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
		if(!(a.getName().equals(getName()) && a instanceof Reward))
			throw new RuntimeException("The achievement should be of the same type");
		this.used = ((Reward)a).isUsed();
	}
	
	public boolean removeAchievement(Achievement r) {
			if(!(r.getName().equals(getName()) && r instanceof Reward))
				throw new RuntimeException("The achievement should be of the same type");
			this.used = true;
			return false;
	}
	
	public String toString() {
		return "Reward Achieved - " + name + ": "+ used;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Reward))
			return false;
		Reward r = (Reward)o;
		return this.name.equals(r.name) && this.used == r.used;
	}
	
}


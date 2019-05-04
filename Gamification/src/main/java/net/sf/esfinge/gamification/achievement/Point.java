package net.sf.esfinge.gamification.achievement;

public class Point implements Achievement{
	
	
	private Integer quantity;
	private String name;

	public Point() {
	}
	
	public Point(Integer quantity, String name) {
		super();
		this.quantity = quantity;
		this.name = name;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void incrementAchievement(Achievement a) {
			if(!(a.getName().equals(getName()) && a instanceof Point))
				throw new RuntimeException("The achievement should be of the same type");
			this.quantity += ((Point)a).getQuantity();
	}
	
	public boolean removeAchievement(Achievement r) {
			if(!(r.getName().equals(getName()) && r instanceof Point))
				throw new RuntimeException("The achievement should be of the same type");
			this.quantity -= ((Point)r).getQuantity();
			return this.quantity <=0;
	}
	
	
	public String toString() {
		return "Points Achieved - " + name + ": "+ quantity;
	}
	
	@Override
	public boolean equals(Object o) {
		Point p = (Point)o;
		return this.name == p.name && this.quantity == p.quantity;
	}
	
	
}

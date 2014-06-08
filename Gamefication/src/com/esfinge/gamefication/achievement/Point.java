package com.esfinge.gamefication.achievement;

public class Point implements Achievement{
	
	
	private Integer quantity;
	private String name;
	

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
		try{
			if(!a.getName().equals(getName()) || a instanceof Point)
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.quantity += ((Point)a).getQuantity();
		}
	}
	
	public void removeAchievement(Achievement r) {
		try{
			if(!r.getName().equals(getName()) || r instanceof Point)
				throw new RuntimeException("The achievement should be of the same type");
		}catch (RuntimeException e) {
				this.quantity -= ((Point)r).getQuantity();
	}
		
}
	
	
	public String toString() {
		return "Points Achieved - " + name + ": "+ quantity;
	}
	
}

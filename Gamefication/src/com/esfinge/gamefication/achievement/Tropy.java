package com.esfinge.gamefication.achievement;

public class Tropy implements Achievement{
	
	private String name;
	

	public Tropy(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void incrementAchievement(Achievement a) {
	   try{	
		  if(!a.getName().equals(getName()) || !(a instanceof Tropy))
				throw new RuntimeException("The achievement should be of the same type");
	    } catch(RuntimeException e) {
			this.name += ((Tropy)a).getName();
		}
		
	}
	
	public void removeAchievement(Achievement r) {
		
		  
		
	}
	
	public String toString() {
		return "Tropy Achieved - " + name;
	}


}

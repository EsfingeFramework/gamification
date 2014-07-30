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
	   try{	
		  if(!a.getName().equals(getName()) || !(a instanceof Trophy))
				throw new RuntimeException("The achievement should be of the same type");
	    } catch(RuntimeException e) {
			this.name = ((Trophy)a).getName();
		}
	}
	
	public void removeAchievement(Achievement r) {
		try{	
			  if(!r.getName().equals(getName()) || r instanceof Trophy)
					throw new RuntimeException("The achievement should be of the same type");
		    } catch(RuntimeException e) {
		    	this.name = null;
			}	
	}

	public String toString() {
		return "Tropy Achieved - " + name;
	}


}

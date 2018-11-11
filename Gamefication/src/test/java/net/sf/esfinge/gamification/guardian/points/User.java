package net.sf.esfinge.gamification.guardian.points;

import org.esfinge.guardian.abac.annotation.authorization.Rule;

public class User {
	@AuthPointGreaterThan(quantity = 40)
	public void a() {
		
	}
	
}

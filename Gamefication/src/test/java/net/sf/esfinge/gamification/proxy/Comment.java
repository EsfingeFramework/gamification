package net.sf.esfinge.gamification.proxy;

import net.sf.esfinge.gamification.mechanics.FakeUser;

public class Comment {
	
	private FakeUser user;
	private String text;
	public FakeUser getUser() {
		return user;
	}
	public void setUser(FakeUser user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Comment(FakeUser user, String text) {
		super();
		this.user = user;
		this.text = text;
	}
	

}

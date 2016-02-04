package com.esfinge.gamefication.proxy;

import com.esfinge.gamification.annotation.Named;

public class TestPointAnnotation implements ITestPointAnn{
	
	public void doSomething(){
	}
	
	public void doWrong(){
		throw new RuntimeException();
	}
	
	public void doRemoveSomething(){
	}

	public void niceComment(String comment, @Named("owner") String owner) {
	}

	public void niceComment(@Named("comment") Comment c) {
	}

	public void doRemoveWrong() {
		throw new RuntimeException();
	}

	@Override
	public void doSomethingWrong() {
		// TODO Auto-generated method stub
		
	}

}



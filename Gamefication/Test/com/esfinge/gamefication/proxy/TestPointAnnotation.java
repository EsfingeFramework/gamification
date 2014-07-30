package com.esfinge.gamefication.proxy;

import com.esfinge.gamification.annotation.Named;
import com.esfinge.gamification.annotation.PointsToParam;

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
}



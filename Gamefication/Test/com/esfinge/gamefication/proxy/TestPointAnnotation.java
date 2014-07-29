package com.esfinge.gamefication.proxy;

public class TestPointAnnotation implements ITestPointAnn{
	
	public void doSomething(){
	}
	
	public void doWrong(){
		throw new RuntimeException();
	}
	
	public void doRemoveSomething(){
	}
}



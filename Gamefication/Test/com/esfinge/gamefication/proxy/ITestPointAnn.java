package com.esfinge.gamefication.proxy;
import com.esfinge.gamefication.annotation.PointsToUser;

public interface ITestPointAnn {
	
    @PointsToUser(name = "GOLD", quantity = 1000)
    public void doSomething();

}

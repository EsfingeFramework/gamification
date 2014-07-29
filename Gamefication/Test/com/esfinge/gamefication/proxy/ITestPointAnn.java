package com.esfinge.gamefication.proxy;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.annotation.RemovePoints;

public interface ITestPointAnn {
	
    @PointsToUser(name = "GOLD", quantity = 1000)
    public void doSomething();
    
    @PointsToUser(name = "GOLD", quantity = 500)
    public void doWrong();
    
    @RemovePoints(name = "GOLD", quantity = 500)
    public void doRemoveSomething();

}

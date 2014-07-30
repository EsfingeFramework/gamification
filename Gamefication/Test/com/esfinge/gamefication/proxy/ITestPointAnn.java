package com.esfinge.gamefication.proxy;
import com.esfinge.gamefication.annotation.Named;
import com.esfinge.gamefication.annotation.PointsToParam;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.annotation.RemovePoints;

public interface ITestPointAnn {
	
    @PointsToUser(name = "GOLD", quantity = 1000)
    public void doSomething();
    
    @PointsToUser(name = "GOLD", quantity = 500)
    public void doWrong();
    
    @RemovePoints(name = "GOLD", quantity = 500)
    public void doRemoveSomething();
    
    
    @PointsToParam(name = "SILVER", quantity = 100, param="owner")
    public void niceComment(String comment, @Named("owner") String owner);
    
    @PointsToParam(name = "SILVER", quantity = 300, param="comment", prop = "user.login")
    public void niceComment(@Named("comment") Comment c);

}

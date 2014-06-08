package com.esfinge.gamefication.proxy;
import java.lang.reflect.Method;

import com.esfinge.gamefication.achievement.Point;
import com.esfinge.gamefication.achievement.Rank;
import com.esfinge.gamefication.achievement.Reward;
import com.esfinge.gamefication.annotation.PointsToUser;
import com.esfinge.gamefication.annotation.RanksToUser;
import com.esfinge.gamefication.annotation.RewardsToUser;
import com.esfinge.gamefication.mechanics.Game;
import com.esfinge.gamefication.user.UserStorage;


public class GameInvoker {
	
	public static GameInvoker getInstance(){
		if(instance==null){
			instance = new GameInvoker();
		}
		return instance;
	}
	
	private static GameInvoker instance;
	
	private Game game;
	
	private GameInvoker(){
	}
	
	public void setGame(Game g){
		game = g;
	}
	
	public void registerAchievment(Object encapsulated, Method method, Object[] args)
			throws Throwable {
		
		if(method.isAnnotationPresent(PointsToUser.class)){
			PointsToUser point = method.getAnnotation(PointsToUser.class);	
			Object user = UserStorage.getUser();
			Point p = new Point(point.quantity(), point.name());
			game.addAchievement(user, p);
		}
		
		if(method.isAnnotationPresent(RanksToUser.class)){
			RanksToUser rank = method.getAnnotation(RanksToUser.class);
			Object user = UserStorage.getUser();
			Rank r = new Rank(rank.name(), rank.level());
			game.addAchievement(user, r);
		}
		
		if(method.isAnnotationPresent(RewardsToUser.class)){
			RewardsToUser rewards = method.getAnnotation(RewardsToUser.class);
			Object user = UserStorage.getUser();
			Reward re = new Reward(rewards.name());
			game.addAchievement(user, re);
	}
		
		if(method.isAnnotationPresent(RanksToUser.class)){
			RanksToUser ranks = method.getAnnotation(RanksToUser.class);
			Object user = UserStorage.getUser();
			Rank rank = new Rank(ranks.name(), ranks.level());
			game.addAchievement(user, rank);
	
		}
	
	}
}
	
	


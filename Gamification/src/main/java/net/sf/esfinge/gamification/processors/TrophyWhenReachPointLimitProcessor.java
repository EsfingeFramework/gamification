package net.sf.esfinge.gamification.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.sf.esfinge.gamification.achievement.Trophy;
import net.sf.esfinge.gamification.annotation.TrophyWhenReachPointLimit;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.user.UserStorage;

public class TrophyWhenReachPointLimitProcessor implements AchievementProcessor {
	 private int quantityPoint;
	    private String namePoint;
	    private String nameTrophy;
	    
	    @Override
	    public void receiveAnnotation(Annotation an){
	        TrophyWhenReachPointLimit ptu = (TrophyWhenReachPointLimit) an;
	        
	        quantityPoint = ptu.quantityPoint();
	        namePoint = ptu.namePoint();
	        nameTrophy = ptu.nameTrophy();
	    }
	    
	    @Override
	    public void process(Game game, Object encapsulated, Method method, Object[] args){
	        Object user = UserStorage.getUserID();
	        if (quantityPoint == 1000){
	            Trophy t = new Trophy(nameTrophy);
	            game.addAchievement(user, t);
	        }
	        
	    }

		@Override
		public void process(Game game, Object encapsulated,
				Class<? extends Method> class1, Object[] args) {
			// TODO Auto-generated method stub
			
		}
}

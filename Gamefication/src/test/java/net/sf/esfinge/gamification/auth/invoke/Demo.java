package net.sf.esfinge.gamification.auth.invoke;

import net.sf.esfinge.gamification.achievement.Achievement;
import net.sf.esfinge.gamification.achievement.Point;
import net.sf.esfinge.gamification.auth.Guarded;
import net.sf.esfinge.gamification.mechanics.Game;
import net.sf.esfinge.gamification.mechanics.GameMemoryStorage;

public class Demo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Game game;
		game = new GameMemoryStorage();
		Achievement silver = new Point(10, "silver");
		game.addAchievement("user", silver);

		Guarded listener = (Guarded) GenericListener.create(Guarded.class.getMethod("takePhoto"), game, "user");
		listener.takePhoto();
	}
}

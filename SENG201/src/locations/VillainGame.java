package locations;
import java.util.Random;

import gui.MenuSystem;
import team.Hero;

/**
 * Provides an interface for a game that can be played in a Villains Lair
 * 
 * @author fer25
 * @author par116
 */

public abstract class VillainGame {
	
	MenuSystem m;
	Random r;
	
	/**
	 * store the MenuSystem and Random locally on object construction
	 * @param m MenuSystem containing GUI
	 * @param r pseudo random number generator
	 */
	public VillainGame(MenuSystem m , Random r) {
		this.m = m;
		this.r = r;
	}

	/**
	 * Initiates the game to be played
	 * @return true if villain wins, false otherwise
	 * @param villain_name The name of the villain
	 * @param playing_hero Currunt hero object
	 */
	public abstract boolean play(String villain_name, Hero playing_hero);
	
	/**
	 * @return String defining what type of game the object represents
	 */
	public abstract String gameType();
	
}

/**
 * Provides an interface for a game that can be played in a Villains Lair
 * 
 * @author fer25
 *
 */

public interface VillainGame {

	/**
	 * Initiates the game to be played
	 * @return true if villain wins, false otherwise
	 * @param villain_name The name of the villain
	 */
	public boolean play(String villain_name, Hero playing_hero);
	
	/**
	 * @return String defining what type of game the object represents
	 */
	public String gameType();
	
}

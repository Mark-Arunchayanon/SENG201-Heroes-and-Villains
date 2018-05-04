
public interface VillainGame {

	/**
	 * Initiates the game to be played
	 * @return true if villain wins, false otherwise
	 * @param villain_name The name of the villain
	 */
	public boolean play(String villain_name);
	
	/**
	 * @return String defining what type of game the object represents
	 */
	public String gameType();
	
}

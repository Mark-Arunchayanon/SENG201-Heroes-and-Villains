
public interface HeroStatSelector {

	/**
	 * Provides a descriptor about the type of
	 * hero being created
	 * @return String describing type of hero
	 * to be created
	 */
	public String toString();
	
	/**
	 * Creates a Hero with the correct total
	 * health specified by the stats
	 * @return The created hero
	 */
	public Hero createHero();
	
	/**
	 * @return The amount of cash associated with
	 * that type of hero
	 */
	public int getCash();
	
	/**
	 * @return True if the associated hero type provides
	 * a map. False otherwise
	 */
	public boolean getMap();
	
}

/**
 * Provides an interface to define objects that can create
 * a Hero with particular specifications and can provide information
 * about the Hero object before its creation.
 * 
 * @author fer25
 *
 */
public interface HeroStatSelector {

	/**
	 * Provides a descriptor about the type of
	 * hero being created
	 * @return String describing type of hero
	 * to be created
	 */
	public String toString();
	
	/**
	 * Creates a Hero with stats specified by the
	 * HeroStatSelector
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

/**
 * Provides an interface to define objects that can create
 * a Hero with particular specifications and can provide information
 * about the Hero object before its creation.
 * 
 * @author fer25
 * @author par116
 */
public interface HeroStatSelector extends Selectable {
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

/**
 * Provides an interface to define objects that can create
 * a Hero with particular specifications and can provide information
 * about the Hero object before its creation.
 * 
 * @author fer25
 * @author par116
 */
public abstract class HeroStatSelector implements Selectable {
	
	/**
	 * Creates a Hero with stats specified by the
	 * HeroStatSelector
	 * @return The created hero
	 */
	public Hero createHero() {
		// Creates hero with the defines statistics
		Hero hero = new Hero(getTotalHealth(), getIllusion(), getHealing(), getHaggling());
		
		return hero;
		
	}
	
	/**
	 * @return The amount of cash associated with
	 * that type of hero
	 */
	public abstract int getCash();
	
	/**
	 * @return True if the associated hero type provides
	 * a map. False otherwise
	 */
	public abstract boolean getMap();
	
	/**
	 * @return The haggling stat for the HeroStatSelector
	 */
	protected abstract int getHaggling();

	/**
	 * @return The healing stat for the HeroStatSelector
	 */
	protected abstract int getHealing();

	/**
	 * @return The illusion stat for the HeroStatSelector
	 */
	protected abstract int getIllusion();

	/**
	 * @return The TotalHealth stat for the HeroStatSelector
	 */
	protected abstract int getTotalHealth();
	
	@Override
	public String getDescriptor() {
		String s = "Statistics for a " + getTitle() + " type Hero:\n"
				+ "Cash - Used to purchase things in the store:  " + getCash() + 
				"\nTotal Health - Maximum Health the Hero can have:  " + getTotalHealth() +
				"\nHealing Ability - How effectively the Hero regains Health:  " + getHealing() +
				"\nIllusion Ability - How effectively the Hero tricks Villains:  " + getIllusion() +
				"\nHaggling Ability - How effectively the Hero barters for prices:  " + getHaggling();
		
		if (getMap()) {
			s += "\nThis Hero also provides a Map to every City";
		}
		
		return s;
	}
	
}

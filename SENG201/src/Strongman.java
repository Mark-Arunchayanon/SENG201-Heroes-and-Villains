/**
 * Sets the statistics for the hero Strongman
 * 
 * @author fer25
 * @author par116
 */

public class Strongman implements HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 100;
	private static final int TOTAL_HEALTH = 130;
	private static final int HEALING = 90;
	private static final int ILLUSION = 90;
	private static final int HAGGLING = 90;
	private static final boolean MAP = false;
	
	public String toString() {
		
		// Description of the hero
		String s = "Behold the mighty Strong Man. Towering above all like a pillar of... well... human.\n"
				+ "Despite the less than magnificent appearance, the Strong Man does have a lot of health.\n"
				+ "However, the disadvantage of being strong enough to headbut a tree to the ground is that\n"
				+ "you end up about as smart as the tree. Healing, Illusion and Haggling all take a hit";
		
		return s;
		
	}
	

	@Override
	public int getCash() {
		
		// Returns the amount of cash Strongman has
		return CASH;
	}

	@Override
	public boolean getMap() {
		
		// Return false if the hero does not have a map, returns true if the hero does have a map
		return MAP;
	}

	@Override
	public Hero createHero() {

		// Creates a hero with the defined statistics
		Hero hero = new Hero(TOTAL_HEALTH, ILLUSION, HEALING, HAGGLING);
		
		return hero;
		
	}
	
}

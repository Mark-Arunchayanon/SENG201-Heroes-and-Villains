/**
 * Sets the statistics for the hero Illusionist
 * 
 * @author fer25
 * @author par116
 */

public class Illusionist implements HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 90;
	private static final int TOTAL_HEALTH = 90;
	private static final int HEALING = 90;
	private static final int ILLUSION = 130;
	private static final int HAGGLING = 100;
	private static final boolean MAP = false;
	
	public String toString() {
		
		// Description of the hero
		String s = "If you can put up with the constant vanishing, Illusionists are good to have arround.\n"
				+ "They have an uncanny knack to change the outcome of a game that already seems lost.\n"
				+ "Their Illusion stat gets a bonus. They all seem to take their illusions too seriously\n"
				+ "however and tend to become a menace to themselves and others. Their Health and\n"
				+ "Healing stats take a hit. Because of this they are constantly having to pay to get\n"
				+ "themselves fixed. Their Cash takes a hit";
		
		return s;
		
	}
	

	@Override
	public int getCash() {
		
		// Returns the amount of cash the Illusionist has
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

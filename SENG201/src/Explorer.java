/**
 * Sets the statistics for the hero Explorer
 * 
 * @author fer25
 * @author par116
 */

public class Explorer implements HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 80;
	private static final int TOTAL_HEALTH = 90;
	private static final int HEALING = 100;
	private static final int ILLUSION = 100;
	private static final int HAGGLING = 100;
	private static final boolean MAP = true;
	
	public String toString() {
		
		// Description of the hero
		String s = "If you feel like you are constantly getting lost then an explorer may be a good\n"
				+ "addition to your team. They have been almost everywhere already (as they consistently\n"
				+ "like to remind you). Because of this they can tell you which directions buildings of\n"
				+ "significance are in any city you might find yourself in. All this exploring comes at a\n"
				+ "cost however. They have spent some of their cash already. They also have a lingering\n"
				+ "disease from an earlier adventure. Their health takes a hit.";
		
		return s;
		
	}

	@Override
	public int getCash() {
		
		// Returns the amount of cash Explorer has
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

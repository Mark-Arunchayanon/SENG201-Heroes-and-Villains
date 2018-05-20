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
	
	/**
	 * Returns the description of the Hero
	 * @return The Hero's description
	 */
	public String toString() {
		
		// Description of the hero
		String s = "If you feel like you are constantly getting lost then an explorer may be a good\n"
				+ "addition to your team. They have been almost everywhere already (as they consistently\n"
				+ "like to remind you). Because of this they can tell you which directions buildings of\n"
				+ "significance are in any city you might find yourself in. All this exploring comes at a\n"
				+ "cost however. They have spent some of their cash already. They also have a lingering\n"
				+ "disease from an earlier adventure. Their health takes a hit.\n"
				+ "Amount of cash: $" + CASH
				+ "\nTotal Health: "+ TOTAL_HEALTH
				+ "\nHealing Skill: " + HEALING
				+ "\nIllusion Skill: " + ILLUSION
				+ "\nHaggling Skill: " + HAGGLING
				+ "\nOwns a Map: " + MAP;
		
		return s;
		
	}

	/**
	 * Returns the amount of cash the Hero has
	 * @return The Hero's cash
	 */
	@Override
	public int getCash() {
		return CASH;
	}

	/**
	 * Returns the true if the Hero has a map, otherwise return false
	 * @return true or false
	 */
	@Override
	public boolean getMap() {
		return MAP;
	}

	/**
	 * Creates a Hero object
	 * @param temp_health The maximum health of the Hero.
	 * THe hero's health is also set to this value on creation
	 * @param temp_illusion The Hero's illusion stat
	 * @param temp_healing The Hero's healing stat
	 * @param temp_haggling The Hero's haggling stat
	 */
	@Override
	public Hero createHero() {
		
		Hero hero = new Hero(TOTAL_HEALTH, ILLUSION, HEALING, HAGGLING);
		
		return hero;
		
	}
	
}

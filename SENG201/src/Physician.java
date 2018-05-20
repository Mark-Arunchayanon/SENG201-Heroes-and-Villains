/**
 * Sets the statistics for the hero Physician
 * 
 * @author fer25
 * @author par116
 */

public class Physician implements HeroStatSelector {

	// Defining the statistics for each skill
	private static final int CASH = 100;
	private static final int TOTAL_HEALTH = 80;
	private static final int HEALING = 130;
	private static final int ILLUSION = 90;
	private static final int HAGGLING = 100;
	private static final boolean MAP = false;
	
	/**
	 * Returns the description of the Hero
	 * @return The Hero's description
	 */
	public String toString() {
		
		// Description of the hero
		String s = "Physicians are great to have around after a brutal round of Rock, Paper, Scissors.\n"
				+ "They'll patch the paper cut right up. Their Healing stat gets a bonus. Ironically,\n"
				+ "they never seem to lock after themselves very well. Their Health stat takes a hit.\n"
				+ "In addition they love to know how things work. They're inquisitive like that. That is\n"
				+ "why they taught themselves how animals work by dismantling so many of them. They\n"
				+ "struggle to avoid taking notes as a team member tries some trickery so their\n"
				+ "Illusion stat takes a hit.\n"
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

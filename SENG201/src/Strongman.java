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
	
	/**
	 * Returns the description of the Hero
	 * @return The Hero's description
	 */
	public String toString() {
		
		// Description of the hero
		String s = "Behold the mighty Strong Man. Towering above all like a pillar of... well... human.\n"
				+ "Despite the less than magnificent appearance, the Strong Man does have a lot of health.\n"
				+ "However, the disadvantage of being strong enough to headbut a tree to the ground is that\n"
				+ "you end up about as smart as the tree. Healing, Illusion and Haggling all take a hit\n"
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

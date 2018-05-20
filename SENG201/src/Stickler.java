/**
 * Sets the statistics for the hero Stickler
 * 
 * @author fer25
 * @author par116
 */

public class Stickler implements HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 90;
	private static final int TOTAL_HEALTH = 100;
	private static final int HEALING = 90;
	private static final int ILLUSION = 90;
	private static final int HAGGLING = 130;
	private static final boolean MAP = false;
	
	/**
	 * Returns the description of the Hero
	 * @return The Hero's description
	 */
	public String toString() {
		
		// Description of the hero
		String s = "If you find yourself trying to survive with only $2 fried rice from The Wok, a Stickler\n"
				+ "will definately become your best friend. A Stickler will haggle the price down low enough \n"
				+ "that you can afford it with minimum wage part time job. Being skilled in haggling does not \n"
				+ "mean that Stickler is a millionare. Healing and illusion abilities also take a hit\n"
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

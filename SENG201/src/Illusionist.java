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
	
	@Override
	public String toString() {
		
		// Description of the hero
		String s = "If you can put up with the constant vanishing, Illusionists are good to have arround.\n"
				+ "They have an uncanny knack to change the outcome of a game that already seems lost.\n"
				+ "Their Illusion stat gets a bonus. They all seem to take their illusions too seriously\n"
				+ "however and tend to become a menace to themselves and others. Their Health and\n"
				+ "Healing stats take a hit. Because of this they are constantly having to pay to get\n"
				+ "themselves fixed. Their Cash takes a hit\n"
				+ "Amount of cash: $" + CASH
				+ "\nTotal Health: "+ TOTAL_HEALTH
				+ "\nHealing Skill: " + HEALING
				+ "\nIllusion Skill: " + ILLUSION
				+ "\nHaggling Skill: " + HAGGLING
				+ "\nOwns a Map: " + MAP;
		
		return s;
		
	}
	
	@Override
	public int getCash() {
		return CASH;
	}

	@Override
	public boolean getMap() {
		return MAP;
	}

	@Override
	public Hero createHero() {
		// Creates hero with the defines statistics
		Hero hero = new Hero(TOTAL_HEALTH, ILLUSION, HEALING, HAGGLING);
		
		return hero;
		
	}
	
}

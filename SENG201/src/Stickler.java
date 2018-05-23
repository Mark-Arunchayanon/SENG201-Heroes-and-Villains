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
	
	@Override
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

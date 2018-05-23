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
	
	@Override
	public String getDescriptor() {
		
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

	@Override
	public String getTitle() {
		return "StrongMan";
	}
	
}

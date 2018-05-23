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
	
	@Override
	public String getDescriptor() {
		
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
		return "Physician";
	}
	
}

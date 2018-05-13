
public class Physician implements HeroStatSelector {

	private static final int CASH = 100;
	private static final int TOTAL_HEALTH = 80;
	private static final double HEALING = 1.3;
	private static final double ILLUSION = 0.9;
	private static final double HAGGLING = 1;
	private static final boolean MAP = false;
	
	public String toString() {
		
		String s = "Physicians are great to have around after a brutal round of Rock, Paper, Scissors.\n"
				+ "They'll patch the paper cut right up. Their Healing stat gets a bonus. Ironically,\n"
				+ "they never seem to lock after themselves very well. Their Health stat takes a hit.\n"
				+ "In addition they love to know how things work. They're inquisitive like that. That is\n"
				+ "why they taught themselves how animals work by dismantling so many of them. They\n"
				+ "struggle to avoid taking notes as a team member tries some trickery so their\n"
				+ "Illusion stat takes a hit.";
		
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

		Hero hero = new Hero(TOTAL_HEALTH, ILLUSION, HEALING, HAGGLING);
		
		return hero;
		
	}
	
}

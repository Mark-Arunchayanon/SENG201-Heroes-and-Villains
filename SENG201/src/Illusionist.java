
public class Illusionist implements HeroStatSelector {

	private static final int CASH = 90;
	private static final int TOTAL_HEALTH = 90;
	private static final double HEALING = 0.9;
	private static final double ILLUSION = 1.3;
	private static final double HAGGLING = 1;
	private static final boolean MAP = false;
	
	public String toString() {
		
		String s = "If you can put up with the constant vanishing, Illusionists are good to have arround.\n"
				+ "They have an uncanny knack to change the outcome of a game that already seems lost.\n"
				+ "Their Illusion stat gets a bonus. They all seem to take their illusions too seriously\n"
				+ "however and tend to become a menace to themselves and others. Their Health and\n"
				+ "Healing stats take a hit. Because of this they are constantly having to pay to get\n"
				+ "themselves fixed. Their Cash takes a hit";
		
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

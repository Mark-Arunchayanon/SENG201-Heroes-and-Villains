
public class Dandy implements HeroStatSelector {

	private static final int CASH = 130;
	private static final int TOTAL_HEALTH = 90;
	private static final int HEALING = 100;
	private static final int ILLUSION = 90;
	private static final int HAGGLING = 90;
	private static final boolean MAP = false;
	
	@Override
	public String toString() {
		
		String s = "A Dandy can be a useful person to have on a team as they are always happy to shout.\n"
				+ "With their parents trust fund money burning a hole in their back pocket they cannot\n"
				+ "wait to make some new friends by sloshing it about a bit.\n"
				+ "They've always had everything handed to them on a silver platter, however, so have\n"
				+ "never had to build any sort of strength of their own. Therefore, their Health stat\n"
				+ "takes a hit. They also like to be the centre of attention all the time so getting away\n"
				+ "with slight of hand is very diffucult for them. Their Illusion stat takes a hit.\n"
				+ "And with this much money \"a discount is like an insult\", Haggling takes a hit also";
		
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

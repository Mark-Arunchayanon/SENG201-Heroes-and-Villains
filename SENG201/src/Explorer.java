
public class Explorer implements HeroStatSelector {

	private static final int CASH = 80;
	private static final int TOTAL_HEALTH = 90;
	private static final double HEALING = 1;
	private static final double ILLUSION = 1;
	private static final double HAGGLING = 1;
	private static final boolean MAP = true;
	
	public String toString() {
		
		String s = "If you feel like you are constantly getting lost then an explorer may be a good\n"
				+ "addition to your team. They have been almost everywhere already (as they consistently\n"
				+ "like to remind you). Because of this they can tell you which directions buildings of\n"
				+ "significance are in any city you might find yourself in. All this exploring comes at a\n"
				+ "cost however. They have spent some of their cash already. They also have a lingering\n"
				+ "disease from an earlier adventure. Their health takes a hit.";
		
		return s;
		
	}
	

	@Override
	public int getCash() {
		return CASH;
	}

	@Override
	public double getHealing() {
		return HEALING;
	}

	@Override
	public double getIllusion() {
		return ILLUSION;
	}

	@Override
	public double getHaggling() {
		return HAGGLING;
	}

	@Override
	public boolean getMap() {
		return MAP;
	}

	@Override
	public Hero createHero() {

		Hero hero = new Hero(TOTAL_HEALTH);
		
		return hero;
		
	}
	
}

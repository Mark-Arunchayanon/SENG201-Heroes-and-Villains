/**
 * Sets the statistics for the hero Strongman
 * 
 * @author fer25
 * @author par116
 */

public class Strongman extends HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 100;
	private static final int TOTAL_HEALTH = 130;
	private static final int HEALING = 90;
	private static final int ILLUSION = 90;
	private static final int HAGGLING = 90;
	private static final boolean MAP = false;

	@Override
	public int getCash() {
		return CASH;
	}

	@Override
	public boolean getMap() {
		return MAP;
	}

	@Override
	public String getTitle() {
		return "StrongMan";
	}

	@Override
	protected int getHaggling() {
		return HAGGLING;
	}

	@Override
	protected int getHealing() {
		return HEALING;
	}

	@Override
	protected int getIllusion() {
		return ILLUSION;
	}

	@Override
	protected int getTotalHealth() {
		return TOTAL_HEALTH;
	}
	
}

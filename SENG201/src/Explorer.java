/**
 * Sets the statistics for the hero Explorer
 * 
 * @author fer25
 * @author par116
 */

public class Explorer extends HeroStatSelector {
	
	// Defining the statistics for each skill
	private static final int CASH = 80;
	private static final int TOTAL_HEALTH = 90;
	private static final int HEALING = 100;
	private static final int ILLUSION = 100;
	private static final int HAGGLING = 100;
	private static final boolean MAP = true;

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
		return "Explorer";
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

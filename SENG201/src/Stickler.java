
public class Stickler implements HeroStatSelector {

	private static final int CASH = 90;
	private static final int TOTAL_HEALTH = 100;
	private static final double HEALING = 0.9;
	private static final double ILLUSION = 0.9;
	private static final double HAGGLING = 1.3;
	private static final boolean MAP = false;
	
	public String toString() {
		
		String s = "If you find yourself trying to survive with only $2 fried rice from The Wok, a Stickler\n"
				+ "will definately become your best friend. A Stickler will haggle the price down low enough \n"
				+ "that you can afford it with minimum wage part time job. Being skilled in haggling does not \n"
				+ "mean that Stickler is a millionare. Healing and illusion abilities also take a hit";
		
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

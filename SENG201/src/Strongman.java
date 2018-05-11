
public class Strongman extends Hero {

	private static final int CASH = 100;
	private static final int TOTAL_HEALTH = 130;
	private static final double HEALING = 0.9;
	private static final double ILLUSION = 0.9;
	private static final double HAGGLING = 0.9;
	private static final boolean MAP = false;
	
	public Strongman() {
		
		super(CASH, TOTAL_HEALTH, HEALING, ILLUSION, HAGGLING, MAP);
		
	}
	
	public String toString() {
		
		String s = "Behold the mighty Strong Man. Towering above all like a pillar of... well... human.\n"
				+ "Despite the less than magnificent appearance, the Strong Man does have a lot of health.\n"
				+ "However, the disadvantage of being strong enough to headbut a tree to the ground is that"
				+ "you end up about as smart as the tree. Healing, Illusion and Haggling all take a hit";
		
		return s;
		
	}
	
}

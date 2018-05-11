
public class Hero {
	
	private String name;
	
	private int health;
	
	private static int CASH;
	private static int TOTAL_HEALTH;
	private static double HEALING;
	private static double ILLUSION;
	private static double HAGGLING;
	private static boolean MAP;
	
	public Hero(int cash, int health, double healing, double illusion, double haggling, boolean map) {
		
		CASH = cash;
		TOTAL_HEALTH = health;
		HEALING = healing;
		ILLUSION = illusion;
		HAGGLING = haggling;
		MAP = map;
		
	}

	protected void adjustHealth(int i) {
		
		health += i;
		
		if(health > TOTAL_HEALTH) {
			
			health = TOTAL_HEALTH;
			
		} else if(health <= 0) {
			
			/*
			 * If health is zero the hero will be removed from the
			 * team at the next opportunity
			 */
			health = 0;
			
		}
		
	}
	
	public int getHealth() {
		
		return health;
		
	}
	
	public String toString() {
		
		String s = name;
		
		s += " who has ";
		s += health;
		s += "Health Points";
		
		return s;
		
	}

	public String getName() {

		return name;
		
	}
	
}

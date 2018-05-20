import java.util.ArrayList;

/**
 * Defines Hero objects that are used as the protagonists of the game
 * @author fer25 par116
 *
 */
public class Hero {
	
	private String name = null;
	
	//Define the stats of the Hero
	private int health;
	private static int TOTAL_HEALTH;
	private int illusion;
	private int healing;
	private int haggling;
	
	//Define the power up modifiers for a hero.
	private int pu_illusion = 0;
	private int pu_haggling = 0;
	
	//Create ArrayList to store current heal operations
	private ArrayList<HealingItem> heal_operations = new ArrayList<HealingItem>();
	
	/**
	 * Creates a Hero object
	 * @param temp_health The maximum health of the Hero.
	 * THe heros health is also set to this value on creation
	 * @param temp_illusion The Hero's illusion stat
	 * @param temp_healing The Hero's healing stat
	 * @param temp_haggling The Hero's haggling stat
	 */
	public Hero(int temp_health, int temp_illusion, int temp_healing, int temp_haggling) {
		
		TOTAL_HEALTH = temp_health;
		health = temp_health;
		illusion = temp_illusion;
		healing = temp_healing;
		haggling = temp_haggling;
		
	}

	/**
	 * Adds i to the health of the Hero.
	 * Will not allow health to go below zero or above TOTAL_HEALTH.
	 * Negative values of i supported
	 * @param i The amount of health to be added to the Hero
	 */
	public void adjustHealth(int i) {
		
		health += i;
		
		if(health > TOTAL_HEALTH) {
			
			health = TOTAL_HEALTH;
			
		} else if(health < 0) {
			
			/*
			 * If health is zero the hero will be removed from the
			 * team at the next opportunity
			 */
			health = 0;
			
		}
		
	}
	
	/**
	 * Returns the health of the Hero
	 * @return The Hero's health
	 */
	public int getHealth() {
		
		return health;
		
	}
	
	/**
	 * Returns the TOTAL_HEALTH of the Hero
	 * @return THe Hero's TOTAL_HEALTH
	 */
	public int getTotalHealth() {
		
		return TOTAL_HEALTH;
		
	}
	
	public String toString() {
		
		String s = name;
		
		s += " who has ";
		s += health;
		s += " Health Points";
		
		return s;
		
	}
	
	/**
	 * Sets the name of the Hero
	 * Only works if the Hero's name was previously null
	 * @param temp_name The name to set the Hero's name to
	 */
	public void setName(String temp_name) {
		
		if (name ==  null) {
			
			name = temp_name;
			
		}
		
	}

	/**
	 * Returns the name of the Hero
	 * @return The Hero's name
	 */
	public String getName() {

		return name;
		
	}
	
	/**
	 * Returns the sum of the Hero's illusion and pu_illusion
	 * stats
	 * @return The Hero's illusion
	 */
	public int getIllusion() {
		
		return illusion + pu_illusion;
		
	}
	
	/**
	 * Returns the healing stat of the Hero
	 * @return The Hero's healing stat
	 */
	public int getHealing() {
		
		return healing;
		
	}
	
	/**
	 * Returns the sum of the Hero's haggling and pu_haggling
	 * stats
	 * @return The Hero's haggling
	 */
	public int getHaggling() {
		
		return haggling + pu_haggling;
		
	}
	
	/**
	 * Adds illusion_increase to pu_illusion
	 * @param illusion_increase
	 */
	public void adjustPUIllusion(int illusion_increase) {
		
		pu_illusion += illusion_increase;
		
	}
	
	/**
	 * Adds illusion_increase to pu_illusion
	 * @param illusion_increase
	 */
	public void adjustPUHaggling(int haggling_increase) {
		
		pu_haggling += haggling_increase;
		
	}
	
	/**
	 * Resets both power up stats to zero
	 */
	public void resetPU() {
		
		pu_illusion = 0;
		pu_haggling = 0;
		
	}
	
	/**
	 * Adds a HealingItem to the Hero's internal heal_operations ArrayList to preserve the object
	 * from city to city
	 * @param item The Healing object to be preserved
	 */
	public void addHealOperation(HealingItem item) {
		
		heal_operations.add(item);
		
	}
	
}

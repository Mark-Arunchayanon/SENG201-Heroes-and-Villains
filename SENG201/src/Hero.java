
public class Hero {
	
	private String name = null;
	
	private int health;
	
	private double illusion;
	
	private double healing;
	
	private double haggling;
	
	private static int TOTAL_HEALTH;
	
	
	public Hero(int temp_health, double temp_illusion, double temp_healing, double temp_haggling) {
		
		TOTAL_HEALTH = temp_health;
		
		health = temp_health;
		
		illusion = temp_illusion;
		
		healing = temp_healing;
		
		haggling = temp_haggling;
	}

	public void adjustHealth(int i) {
		
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
	
	public void setName(String temp_name) {
		
		if (name ==  null) {
			
			name = temp_name;
			
		}
		
	}

	public String getName() {

		return name;
		
	}
	
	public double getIllusion() {
		
		return illusion;
	}
	
}

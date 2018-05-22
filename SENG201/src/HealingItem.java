import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Defines a Saleable object that has the ability to restore health to a Hero.
 * 
 * 
 * @author fer25
 *
 */
public class HealingItem implements Saleable, Selectable {

	//Define the healing quantities and duration of the 
	private static final int MAX_HEAL = 20;
	private static final int MIN_HEAL = 8;
	private static final int MAX_HEAL_TIME = 90;
	private static final int MIN_HEAL_TIME = 20;
	//Defines how the healing level is multiplied to give the price
	private static final int PRICE_COEFF = 40;
	//Defines the number of times health is added to the Hero
	private static final int HEAL_DIVISOR = 4;
	//Define number of miliseconds in a second
	private static final int S_TO_MILIS = 1000;
	//Define timer update frequency
	private static final int TIMER_UPDATE_FREQ = 4;
	
	private Random r = new Random();
	
	//Generate the HealingItem's stats
	private int heal = (r.nextInt((MAX_HEAL - MIN_HEAL) / HEAL_DIVISOR) * HEAL_DIVISOR + MIN_HEAL);
	private int time = r.nextInt(MAX_HEAL_TIME - MIN_HEAL_TIME) + MIN_HEAL_TIME;
	private int price = (heal / time) * PRICE_COEFF;
	private int temp_price;
	private int current_haggling;
	
	//Create variables that store information for the timed healing system
	private int elapsed_time = 0; //Milliseconds
	private int elapsed_time_segments = 1;	
	private Hero hero;	
	private Timer timer = new Timer();	
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {

			elapsed_time += S_TO_MILIS / TIMER_UPDATE_FREQ;
			
			//Check if the Hero needs healing
			if (elapsed_time > elapsed_time_segments * (time / HEAL_DIVISOR) * S_TO_MILIS) {
				
				//if (DEBUG) m.displayMessage("Elapsed Segments: " + elapsed_time_segments);
				
				elapsed_time_segments++;
				
				//Heal Hero
				hero.adjustHealth(heal / 4);
				
				//Check to see if healing complete
				if (elapsed_time >= time * S_TO_MILIS) {
					timer.cancel();					
				}
				
			}
			
		}
		
	};

	@Override
	public int getPrice() {
		return temp_price;
	}

	/**
	 * Provides a descriptor of the HealingItem containing all the information
	 * relevant to the application of it
	 * @return The descriptor
	 */
	public String getHealingDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Apply the HealingItem to the Hero. This causes the TimerTask to be initiated and
	 * will continue to run until the Healing is complete
	 * @param selected_hero
	 */
	public void heal(Hero selected_hero) {
		
		hero = selected_hero;
		
		timer.schedule(task, S_TO_MILIS / TIMER_UPDATE_FREQ, S_TO_MILIS / TIMER_UPDATE_FREQ);
		
	}

	@Override
	public String getTitle() {
		return "Healing Item";
	}

	@Override
	public String getDescriptor() {
		//Adjust the price of the item based on the haggling ability of the Hero
		temp_price = (int) Math.round(price * 100 / current_haggling);
		
		String description = "Healing Potion\nHeals a hero over a period of time\nHealth boost: "
		+ heal + "\nHeal time: " + time + "s" + "\nPrice: " + temp_price;
		
		return description;
	}

	@Override
	public void setHaggling(int haggling) {
		current_haggling = haggling;
	}
}

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Defines a Saleable object that has the ability to restore health to a Hero.
 * 
 * @author fer25
 * @author par116
 */
public class HealingItem implements Saleable, Selectable {

	//Define the healing quantities and duration of the 
	private static final int MAX_HEAL = 20;
	private static final int MIN_HEAL = 8;
	private static final int MAX_HEAL_TIME = 90;
	private static final int MIN_HEAL_TIME = 20;
	//Defines how the healing level is multiplied to give the price
	private static final int PRICE_COEFF = 80;
	//Defines the number of times health is added to the Hero
	private static final int HEAL_DIVISOR = 4;
	//Define number of milliseconds in a second
	private static final int S_TO_MILIS = 1000;
	
	//Calculate constants
	private static final int HEAL_COEFFICIENT = (MAX_HEAL - MIN_HEAL)/HEAL_DIVISOR;
	private static final int TIME_COEFFICIENT = MAX_HEAL_TIME - MIN_HEAL_TIME;
	
	//Generate the HealingItem's stats
	private int heal;
	private int time;
	private int price;
	private int temp_price;
	private int current_haggling;
	
	//Create variables that store information for the timed healing system
	private int elapsed_time = 0; //Milliseconds
	private int elapsed_time_segments = 1;	
	private Hero hero;
	private Team team;
	private JLabel label;
	private HealingItem self = this;
	private Timer timer = new Timer();	
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {

			elapsed_time += S_TO_MILIS;
			// Set label to show time remaining
			if(label != null) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						int time_remaining = time - elapsed_time / S_TO_MILIS;
						if (time_remaining < 0) time_remaining = 0;
						label.setText("<html>Healing " + hero.getName() +
								"<br>Time Remaining: " + time_remaining);
					}
				});
			}
			
			//Check if the Hero needs healing
			if (elapsed_time > elapsed_time_segments * (time * S_TO_MILIS/ HEAL_DIVISOR)) {
				
				elapsed_time_segments++;
				
				//Heal Hero
				hero.adjustHealth(heal / HEAL_DIVISOR);
				
				//Check to see if healing complete
				if (elapsed_time >= time * S_TO_MILIS) {
					timer.cancel();
					team.removeHealOperation(self);
				}
				
			}
			
		}
		
	};
	
	@Override
	public void setRandom(Random r) {		
		heal = r.nextInt(HEAL_COEFFICIENT) * HEAL_DIVISOR + MIN_HEAL;
		time = r.nextInt(TIME_COEFFICIENT) + MIN_HEAL_TIME;
		price = heal * PRICE_COEFF / time;
	}

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
	public void heal(Hero selected_hero, Team team) {
		hero = selected_hero;
		this.team = team;
		
		timer.schedule(task, S_TO_MILIS, S_TO_MILIS);
		
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
	
	/**
	 * Sets a label in the Hospital that the Healing item can update its remainimg time on
	 * @param label The label to be updated
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
}

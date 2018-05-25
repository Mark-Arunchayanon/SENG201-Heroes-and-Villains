package saleable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import team.Hero;
import team.Team;

/**
 * Defines a Saleable object that has the ability to restore health to a Hero.
 * 
 * @author fer25
 * @author par116
 */
public class HealingItem extends Saleable {

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
	private static final int HEAL_COEFFICIENT = (MAX_HEAL - MIN_HEAL);
	private static final int TIME_COEFFICIENT = MAX_HEAL_TIME - MIN_HEAL_TIME;
	
	//Generate the HealingItem's stats
	private int heal;
	private int time;
	//Stores the healing stat adjusted for the current Hero's healing ability
	private int adj_heal;
	
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
				
				//Check to see if healing complete
				if (elapsed_time >= time * S_TO_MILIS) {
					timer.cancel();
					team.removeHealOperation(self);
					//Add remaining health to combat integer math
					hero.adjustHealth(adj_heal - (HEAL_DIVISOR - 1) * heal / HEAL_DIVISOR);
				} else {
					//Heal Hero
					hero.adjustHealth(adj_heal / HEAL_DIVISOR);
				}
				
			}
			
		}
		
	};
	
	/**
	 * Provides a descriptor of the HealingItem containing all the information
	 * relevant to the application of it
	 * @return The descriptor
	 */
	public String getHealingDescriptor() {
		String s = "Stats for this Healing Potion:\n"
				+ "Healing Amount:  " + adj_heal + 
				"\nHealing Time: " + time;
		
		return s;
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
	
	/**
	 * Sets a label in the Hospital that the Healing item can update its remainimg time on
	 * @param label The label to be updated
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	/**
	 * @param Sets the healing stat adjusted healing value
	 */
	public void setHealing(int healing) {
		adj_heal = heal * healing / 100;
	}
	
	/**
	 * Provided for JUnit testing
	 * @return The healing value of the item
	 */
	public int getHealing() {
		return adj_heal;
	}
	
	/**
	 * Provided for JUnit testing
	 * @return The amount of time healing will take
	 */
	public int getTime() {
		return time;
	}
	
	@Override
	public void setRandom(Random r) {		
		heal = r.nextInt(HEAL_COEFFICIENT) + MIN_HEAL;
		adj_heal = heal;
		time = r.nextInt(TIME_COEFFICIENT) + MIN_HEAL_TIME;
		price = heal * PRICE_COEFF / time;
	}

	@Override
	public String getTitle() {
		return "Healing Potion";
	}

	@Override
	public String getSaleDescriptor() {
				
		String description = "Healing Potion\nHeals a hero over a period of time\nHealth boost: "
		+ heal + "\nHeal time: " + time + "s" + "\nPrice: $" + temp_price;
		
		return description;
	}
	
	@Override
	public String getDescriptor() {
		if (sold) {
			return getHealingDescriptor();
		} else {
			return getSaleDescriptor();
		}
	}
	
}
	

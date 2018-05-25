package locations;

import java.util.ArrayList;

import gui.InformationPanel;
import gui.ItemSelector;
import gui.MenuSystem;
import gui.Selectable;
import saleable.Map;
import saleable.PowerUp;
import saleable.Saleable;
import team.Hero;
import team.Team;

/**
 * Allows heroes to use haggling booster and illusion booster 
 * 
 * @author fer25
 * @author par116
 */
public class PowerUpDen implements Location {
	
	private MenuSystem m;

	/**
	 * Store the MenuSystem locally on the object construction
	 * @param m MenuSystem containing GUI
	 */
	public PowerUpDen(MenuSystem m) {

		this.m = m;
		
	}

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		ArrayList<Saleable> all_items = team.getTeamItems();
		
		ArrayList<PowerUp> items = new ArrayList<PowerUp>();
		
		for (Saleable item : all_items) {
			
			if (item instanceof PowerUp) {
				
				items.add((PowerUp) item);
				
			}
			
		}
		// If hero is in possession of power ups
		while (!items.isEmpty()) {
			
			Hero selected_hero = selectHero(team);
		
			//User want to return to Home Base
			if (selected_hero == null) {
				return;
			}
			
			String title = "Welcome to the Power Up Den";
			String description = "What Power Up item would you like to use?";
			// GUI layout for selection of power ups
			Selectable[] items_array = new Selectable[1];
			items_array = items.toArray(items_array);
			ItemSelector selector = new ItemSelector(title, description, items_array, true);
			m.updatePanel(selector);
			
			PowerUp selected_item = (PowerUp) selector.getSelectedObject();
			
			if (selected_item == null); //Do nothing, Application cancelled
			else {
				applyItem(selected_hero, team, selected_item, all_items, items);
			}
			
		}
		
		String title = "If you get a Job, maybe you could afford some Power Ups";
		String body = "You do not have any Power Ups to apply";
		InformationPanel info = new InformationPanel(title, body);
		m.updatePanel(info);
		info.blockTillOK();
	}
	
	/**
	 * Allow a hero to be selected through GUI and returns the selected hero
	 * 
	 * @param team
	 * @return Selected hero
	 */
	private Hero selectHero(Team team) {
		String title = "Welcome to the Power Up Den";
		String description = "Which Hero would you like to apply a Power Up to?";
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		ItemSelector selector = new ItemSelector(title, description, heros, true);
		m.updatePanel(selector);
		
		Hero selected_hero = (Hero) selector.getSelectedObject();
		
		return selected_hero;
	}
	
	/**
	 * Applies selected_item to selected_hero and removes selected_item from
	 * all_items and items
	 * @param selected_hero The Hero to have the HealingItem applied to
	 * @param selected_item The HealingItem to apply to the Hero
	 * @param all_items The Team's inventory
	 * @param items The Hospital's inventory of the Team's HealingItems
	 */
	private void applyItem(Hero selected_hero, Team team, PowerUp selected_item,
			ArrayList<Saleable> all_items, ArrayList<PowerUp> items) {
		
		selected_item.applyBonus(team, selected_hero);
		//Remove the item from the Team's inventory
		all_items.remove((Saleable) selected_item);
		items.remove(selected_item);
		
		String title;
		String body;
		//Inform the user that the action has been completed
		if (selected_item instanceof Map) {
			title = "Your team now owns a map of the current City";
			body = "The Map powerup has been applied";
		} else {
			title = "The Power Up has been applied";
			body = "The " + selected_item.getTitle() + " has been applied to "
			+ selected_hero.getName() + ".\n Their new stats are:\n\n" + selected_hero.getStats();
		}
		
		InformationPanel info = new InformationPanel(title, body);
		m.updatePanel(info);
		info.blockTillOK();
		
	}

	@Override
	public String getType() {
		return "Power Up Den";
	}

}

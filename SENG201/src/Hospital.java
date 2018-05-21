import java.util.ArrayList;
import java.util.Arrays;

/**
 * A location that allows HealingItems to be applied to a Hero
 * 
 * @author fer25
 *
 */
public class Hospital implements Location{

	MenuSystem m;
	
	public Hospital(MenuSystem m) {
		this.m = m;
	}

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		//Get all the items the Team owns
		ArrayList<Saleable> all_items = team.getTeamItems();
		
		//Create an array and fill it with all the HealingItems the Team own
		ArrayList<HealingItem> items = new ArrayList<HealingItem>();
		for (Saleable item: all_items) {			
			if (item instanceof HealingItem) {				
				items.add((HealingItem) item);				
			}			
		}
		
		m.displayMessage("Welcome to the Hospital");
		
		//Allow healing items to be applied while the Team has some in their inventory
		while (!items.isEmpty()) {
			
			//Get the Hero the player wants, return if that is null
			Hero selected_hero = selectHero(team);
			if (selected_hero == null) return;
			
			//Get the HealingItem the User wants to apply to selected_hero
			HealingItem selected_item = selectItem(items, selected_hero);
			
			//If a HealingItem was selected apply it
			if (!(selected_item == null)) {
				applyItem(selected_hero, selected_item, all_items, items);			
			}			
		}
		
		m.displayMessage("You do not have any Healing Items to apply");
		
	}
	
	/**
	 * Applies selected_item to selected_hero and removes selected_item from
	 * all_items and items
	 * @param selected_hero The Hero to have the HealingItem applied to
	 * @param selected_item The HealingItem to apply to the Hero
	 * @param all_items The Team's inventory
	 * @param items The Hospital's inventory of the Team's HealingItems
	 */
	private void applyItem(Hero selected_hero, HealingItem selected_item,
			ArrayList<Saleable> all_items, ArrayList<HealingItem> items) {

		selected_item.heal(selected_hero);
		//Remove the item from the Team's inventory
		all_items.remove((Saleable) selected_item);
		items.remove(selected_item);
		selected_hero.addHealOperation(selected_item);
		
	}

	/**
	 * Gets the user to select the Healing Item that they want to apply to
	 * selected_hero
	 * @param items The healing items to choose from
	 * @param selected_hero The Hero that the item will be applied to
	 * @return The HealingItem or null is the user doesn't want to apply a
	 * HealingItem to that Hero
	 */
	private HealingItem selectItem(ArrayList<HealingItem> items, Hero selected_hero) {
		
		//Query the User what HealingItem they would like to apply to the Hero
		String message = "What Healing Item would you like to apply to " + selected_hero.getName();
		String[] options = new String[items.size() + 1];			
		for (int i = 0; i < items.size(); i++) {				
			options[i] = items.get(i).getHealingDescriptor();				
		}
		//Add an escape option
		options[options.length - 1] = "Actually, " + selected_hero.getName() + " doesn't need healing";
		int selected  = m.displayMenu(message, options);
		
		//Check if a HealingItem selected
		if (selected < options.length - 1) {
			return items.get(selected);
		} else {
			return null;//If the User doesn't want to apply any of the HealingItems
		}
		
	}

	/**
	 * Selects the Hero that the User would like to apply a healing item to 
	 * @param team The team containing the Hero the user will select
	 * @return The hero the User selected or null if the player wishes to leave
	 * the hospital
	 */
	private Hero selectHero(Team team) {
		
		//Get the identifiers of all the Heros in the Team
		String[] hero_ids = team.heroIdentifiers();
		
		Hero selected_hero;		
		//Check to see if there is more than one Hero in the Team
		if(hero_ids.length > 1) {
			
			//Query the User on what Hero they would like to heal
			String message = "What Hero would you like to heal?";			
			String [] options = Arrays.copyOf(hero_ids, hero_ids.length + 1);
			//Add an option to leave the Hospital
			options[options.length - 1] = "Leave the Hospital";			
			int selected = m.displayMenu(message, options);			
			if (selected == options.length - 1) {
				return null; //If the player wishes to leave the Hospital
			}
			//Get the Hero the player desires
			selected_hero = team.getHero(selected);			
		} else {
			
			//Only one Hero, get them.
			selected_hero  = team.getHero(0);
			//Check that the player actually wants to heal them
			String message = "Would you like to heal " + selected_hero.getName() + "?";
			String [] options = {"Yes", "No I would like to travel back to my Home Base"};
			int selected = m.displayMenu(message, options);
			if (selected == 1) {
				return null;//If the player wishes to leave the Hospital
			}
			
		}		
		return selected_hero;		
	}

	@Override
	public String getType() {
		
		return "Hospital";
		
	}	
}

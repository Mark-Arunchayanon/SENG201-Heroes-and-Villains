import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Shop implements Location {
	
	private static final int MIN_PU_ITEMS = 5;
	private static final int MAX_PU_ITEMS = 9;
	private static final int MIN_HEALING_ITEMS = 2;
	private static final int MAX_HEALING_ITEMS = 4;
	
	@SuppressWarnings("rawtypes")
	private static final Class[] POWER_UPS = {HagglingBooster.class,
			IllusionBooster.class, Map.class};
	
	private static final int DIFFERENCE_PU_ITEMS = MAX_PU_ITEMS - MIN_PU_ITEMS;
	private static final int DIFFERENCE_HEALING_ITEMS = MAX_HEALING_ITEMS -
			MIN_HEALING_ITEMS;
	
	private MenuSystem m;
	private Random r = new Random();
	
	private static int PU_ITEMS;
	private static int HEALING_ITEMS;

	private ArrayList<Saleable> items = new ArrayList<Saleable>();
	
	public Shop (MenuSystem m) {
		
		this.m = m;
		
		PU_ITEMS = r.nextInt(DIFFERENCE_PU_ITEMS) + MIN_PU_ITEMS;
		HEALING_ITEMS = r.nextInt(DIFFERENCE_HEALING_ITEMS) + MIN_HEALING_ITEMS;
		
		//Add power ups to items
		for (int i = 0; i < PU_ITEMS; i++) {
			
			@SuppressWarnings("rawtypes")
			Class power_up = POWER_UPS[r.nextInt(POWER_UPS.length)];
			
			try {
				items.add((Saleable) power_up.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// Shouldn't throw error
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
		}
		
		//Add healing items to items
		for (int i = 0; i < HEALING_ITEMS; i++) {
			
			items.add((Saleable) new HealingItem());
			
		}
		
		Collections.shuffle(items);
		
	}
	
	@Override
	public void travelTo(Team team, boolean last_city) {
		
		
		while(!items.isEmpty()) {
			
			Hero selected_hero = selectHero(team);
			
			//User has pushed cancel button
			if(selected_hero == null) {
				return;
			}
			
			String title = "Welcome to the Shop";
			String description = "What would " + selected_hero.getName() +
					" like to purchase?.\nYou have " + team.getCash() + "$";
			
			Selectable[] items_array = new Selectable[1];
			items_array = items.toArray(items_array);
			ItemSelector selector = new ItemSelector(title, description, items_array);
			
			m.updatePanel(selector);
			
			Saleable purchased_item = (Saleable) selector.getSelectedObject();
			
			
			if (hero_id.length > 1) {
				
				message = "Which Hero would like to purchase something from the store?";
				
				String[] options = Arrays.copyOf(hero_id, hero_id.length + 1);
				
				options[options.length - 1] = "Leave the Shop";
				
				int selection = m.displayMenu(message, options);
				
				if (selection == options.length - 1) {
					return;
				}
				
				selected_hero = team.getHero(selection);
				
			} else {
				
				selected_hero = team.getHero(0);
				
				message = "Would you like to purchase something?";
				
				String[] options = {"Yes", "No. I wish to travel back to my home base."};
				
				int selection = m.displayMenu(message, options);
				
				if (selection == 1) {
					return;
				}
			}
		
			message =  "What would " + selected_hero.getName() + " like to purchase?\n"
					+ "You have $" + team.getCash();
			String[] options = new String[items.size() + 1];
			
			for (int i = 0; i < items.size(); i++) {
				
				options[i] = items.get(i).getSaleDescriptor(selected_hero.getHaggling());
				
			}
			
			options[options.length - 1] = "Actually, " + selected_hero.getName() + " doesn't want anything";
			
			int selected_item = m.displayMenu(message, options);
			
			if (selected_item != options.length - 1) {
				
				if (team.adjustCash(- items.get(selected_item).getPrice())) {
					
					team.addTeamItem(items.get(selected_item));
					items.remove(selected_item);
					
					m.displayMessage("Thankyou for your purchase.");
					
				} else {
					
					m.displayMessage("You do not have enough cash for that item.");
					
				}
				
			}
			
		}
		
		m.displayMessage("The shop has run out of items to sell");
		
	}

	private Hero selectHero(Team team) {
		String title = "Welcome to the Shop";
		String description = "Which Hero would like to purchase something?";
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		ItemSelector selector = new ItemSelector(title, description, heros);
		
		m.updatePanel(selector);
		
		Hero selected_hero = (Hero) selector.getSelectedObject();
		
		return selected_hero;
	}

	@Override
	public String getType() {
		return "Shop";
	}

}

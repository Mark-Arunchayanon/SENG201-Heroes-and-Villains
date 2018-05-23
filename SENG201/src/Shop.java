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
				e.printStackTrace();
			}
			
		}
		
		//Add healing items to items
		for (int i = 0; i < HEALING_ITEMS; i++) {
			
			items.add((Saleable) new HealingItem());
			
		}
		
		//Provide Saleables a pseudo random number generator to generate prices etc.
		for (Saleable item : items) item.setRandom(r);
		
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
			
			Saleable purchased_item = selectItem(team, selected_hero);
			
			if (purchased_item == null); //Do nothing. Sale has been cancelled
			else if (team.adjustCash(-purchased_item.getPrice())) {
				
				team.addTeamItem(purchased_item);
				items.remove(purchased_item);
				
				String title = "Thankyou for your purchase";
				String body = "Your purchase has been added to your inventory";				
				InformationPanel info = new InformationPanel(title, body);
				m.updatePanel(info);
				
				info.blockTillOK();
				
			} else {
				
				String title = "Declined";
				String body = "You do not have enough cash for that purchase";				
				InformationPanel info = new InformationPanel(title, body);
				m.updatePanel(info);
				
				info.blockTillOK();
			}
			
		}
		
		String title = "The Shop is closing now";
		String body = "We have no more items to sell you. Thankyou for your Patronage";				
		InformationPanel info = new InformationPanel(title, body);
		m.updatePanel(info);
		
		info.blockTillOK();
		
	}

	private Saleable selectItem(Team team, Hero selected_hero) {
		
		//Inform each item of the selected Hero's haggling ability
		for (Saleable item : items) {
			item.setHaggling(selected_hero.getHaggling());
		}
		
		String title = "Welcome to the Shop";
		String description = "What would " + selected_hero.getName() +
				" like to purchase?\nYou have $" + team.getCash();
		
		Selectable[] items_array = new Selectable[1];
		items_array = items.toArray(items_array);
		ItemSelector selector = new ItemSelector(title, description, items_array, true);
		
		m.updatePanel(selector);
		
		Saleable purchased_item = (Saleable) selector.getSelectedObject();
		
		return purchased_item;
	}

	private Hero selectHero(Team team) {
		String title = "Welcome to the Shop";
		String description = "Which Hero would like to purchase something?";
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		ItemSelector selector = new ItemSelector(title, description, heros, true);
		
		m.updatePanel(selector);
		
		Hero selected_hero = (Hero) selector.getSelectedObject();
		
		return selected_hero;
	}

	@Override
	public String getType() {
		return "Shop";
	}

}

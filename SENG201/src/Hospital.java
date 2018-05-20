import java.util.ArrayList;
import java.util.Arrays;

public class Hospital implements Location{

	MenuSystem m = new MenuSystem();
	
	@Override
	public void travelTo(Team team, boolean last_city) {
		
		ArrayList<Saleable> all_items = team.getTeamItems();
		ArrayList<HealingItem> items = new ArrayList<HealingItem>();
		
		for (Saleable item: all_items) {
			
			if (item instanceof HealingItem) {
				
				items.add((HealingItem) item);
				
			}
			
		}
		
		m.displayMessage("Welcome to the Hospital");
		
		String[] hero_ids = team.heroIdentifiers();
		
		while (!items.isEmpty()) {
			
			Hero selected_hero;
		
			if(hero_ids.length > 1) {
				
				String message = "What Hero would you like to heal?";
				
				String [] options = Arrays.copyOf(hero_ids, hero_ids.length + 1);
				
				options[options.length - 1] = "Leave the Hospital";
				
				int selected = m.displayMenu(message, options);
				
				if (selected == options.length - 1) {
					return;
				}
				
				selected_hero = team.getHero(selected);
				
			} else {
				
				String message = "Would you like to heal a Hero?";
				String [] options = {"Yes", "No I would like to travel back to my Home Base"};
				
				int selected = m.displayMenu(message, options);
				
				if (selected == 1) {
					return;
				}
				
				selected_hero  = team.getHero(0);
				
			}
						
			String message = "What Healing Item would you like to apply to " + selected_hero.getName();
			
			String[] options = new String[items.size() + 1];
			
			for (int i = 0; i < items.size(); i++) {
				
				options[i] = items.get(i).getHealingDescriptor();
				
			}
			
			options[options.length - 1] = "Actually, " + selected_hero.getName() + " doesn't need healing";
			
			int selected  = m.displayMenu(message, options);
			
			if (selected < options.length - 1) {
				
				HealingItem item = items.get(selected);
				item.heal(selected_hero);
				all_items.remove((Saleable) item);
				items.remove(selected);
				selected_hero.addHealOperation(item);
				
			}
			
		}
		
		m.displayMessage("You do not have any Healing Items to apply");
		
	}

	@Override
	public String getType() {
		
		return "Hospital";
		
	}

	
	
}

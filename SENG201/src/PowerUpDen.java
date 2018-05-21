import java.util.ArrayList;
import java.util.Arrays;

public class PowerUpDen implements Location {
	
	private MenuSystem m;

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
		
		m.displayMessage("Welcome to the Power Up Den");
		
		String[] hero_ids = team.heroIdentifiers();
		
		while (!items.isEmpty()) {
			
			Hero selected_hero;
		
			if(hero_ids.length > 1) {
				
				String message = "What Hero would you like to apply a Power Up to?";
				
				String [] options = Arrays.copyOf(hero_ids, hero_ids.length + 1);
				
				options[options.length - 1] = "Leave the Power Up Den";
				
				int selected = m.displayMenu(message, options);
				
				if (selected == options.length - 1) {
					return;
				}
				
				selected_hero = team.getHero(selected);
				
			} else {
				
				String message = "Would you like to apply a Power Up?";
				String [] options = {"Yes", "No I would like to travel back to my Home Base"};
				
				int selected = m.displayMenu(message, options);
				
				if (selected == 1) {
					return;
				}
				
				selected_hero  = team.getHero(0);
				
			}
						
			String message = "What Power Up would you like to apply to " + selected_hero.getName();
			
			String[] options = new String[items.size() + 1];
			
			for (int i = 0; i < items.size(); i++) {
				
				options[i] = items.get(i).getApplicationDescriptor();
				
			}
			
			options[options.length - 1] = "Actually, " + selected_hero.getName() + " doesn't need a Power Up";
			
			int selected  = m.displayMenu(message, options);
			
			if (selected < options.length - 1) {
				
				items.get(selected).applyBonus(team, selected_hero);
				all_items.remove((Saleable) items.get(selected));
				items.remove(selected);
				
			}
			
		}
		
		m.displayMessage("You do not have any Power Ups to apply");
		
	}

	@Override
	public String getType() {
		return "Power Up Den";
	}

}

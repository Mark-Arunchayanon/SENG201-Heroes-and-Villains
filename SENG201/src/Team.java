
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Team {
	
	private int cash = 0;
	private 
	
	private static final Hero[] HERO_TYPES = {(Hero) new Dandy(),
			(Hero) new Explorer(), (Hero) new Illusionist(),
			(Hero) new Physician(), (Hero) new Stickler(),
			(Hero) new Strongman()};
	
	ArrayList<Hero> heros = new ArrayList<Hero>();
	
	MenuSystem m = new MenuSystem();	
	
	private int team_size;
	
	public Team() {
		
		String message = "How many Heros would you like in your team?";
		
		String[] options = {"1", "2", "3"};
		
		team_size = m.displayMenu(message, options);
		
		for (int i = 0; i < team_size; i++) {
			
			addHero();
			
		}
		
	}
	
	private void addHero() {
		
		String message = "What type of hero would you like?";
		
		String[] options = heroBlurbs();
		
		int selected = m.displayMenu(message, options);
		
		Hero selected_hero = HERO_TYPES[selected];
		
		message = "What will be the name of your new hero?";
		
		String name = m.displayStringRequest(message);
		
		while (heroNames().contains(name)) {
			
			m.displayMessage("You cannot have multiple heros with the same name");
			
			name = m.displayStringRequest(message);
			
		}
		
		selected_hero.setName(name);
		
		heros.add(selected_hero);
		
	}
	
	private String[] heroBlurbs() {
		
		String[] blurbs = new String[HERO_TYPES.length];
		
		for (int i = 0; i < HERO_TYPES.length; i++) {
			
			blurbs[i] = HERO_TYPES[i].toString();
			
		}
		
		return blurbs;
		
	}
	
	public Set<String> heroNames() {
		
		HashSet<String> names = new HashSet<String>();
		
		for(int i = 0; i < heros.size(); i++) {
			
			names.add(heros.get(i).getName());
			
		}
		
		return names;
		
	}
	
	public String[] heroStrings() {
		
		for (Hero hero: heros) {
			
			
			
		}
		
	}
	
	public Hero getHero(int id) {
		
		//TODO Fix
		
		Hero h = new Hero();
		
		return h;
		
	}

	public void check_health() {
		// TODO Auto-generated method stub
		
	}
	
}


import java.util.ArrayList;

public class Team {
	
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
			
			heros.add(addHero());
			
		}
		
	}
	
	private Hero addHero() {
		
		String message = "What type of hero would you like?";
		
		String[] options = heroBlurbs();
		
		int selected = m.displayMenu(message, options);
		
		Hero selected_hero = HERO_TYPES[selected];
		
		message = "What will be the name of your new hero?";
		
		while
		
		String
	}
	
	private String[] heroBlurbs() {
		
		String[] blurbs = new String[HERO_TYPES.length];
		
		for (int i = 0; i < HERO_TYPES.length; i++) {
			
			blurbs[i] = HERO_TYPES[i].toString();
			
		}
		
		return blurbs;
		
	}
	
	public String[] heroNames {
		
		String[] names = new String[heros.size()];
		
		for(int i = 0; i < heros.size(); i++) {
			
			names[i] = heros
			
		}
		
	}
	
	public String[] heroStrings() {
		
		for (hero: heros) {
			
			
			
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

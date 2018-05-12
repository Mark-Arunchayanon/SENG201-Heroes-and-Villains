
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Creates and manages a Team of Heros.
 */
public class Team {
	
	//Create array containing selectors for all the different hero types
	private static final HeroStatSelector[] HERO_TYPES = {
			(HeroStatSelector) new Dandy(), (HeroStatSelector) new Explorer(),
			(HeroStatSelector) new Illusionist(), (HeroStatSelector) new Physician(),
			(HeroStatSelector) new Stickler(),(HeroStatSelector) new Strongman()};
	
	//Create arrayList to store the Heros
	ArrayList<Hero> heros = new ArrayList<Hero>();
	
	//Create variables to store team stats
	private int team_size;
	private int cash = 0;
	private double healing = 0;
	private double illusion = 0;
	private double haggling = 0;
	private boolean map = false;
	
	//Create arrayList to store objects that the team owns
//	ArrayList<StoreBought> objects = new ArrayList<StoreBought>();
	
	MenuSystem m = new MenuSystem();
	
	public Team() {
		
		//Establish the size of the team
		String message = "How many Heros would you like in your team?";
		
		String[] options = {"1", "2", "3"};
		
		team_size = m.displayMenu(message, options);
		
		//Fill the stats variables and heros arrayList
		for (int i = 0; i < team_size; i++) {
			
			Hero new_hero = createHero();
			
			addHero(new_hero);
			
		}
		
		//Normalise stats to team size
		healing /= team_size;
		illusion /= team_size;
		haggling /= team_size;
		
	}
	
	/**
	 * Queries the user on what type of hero they would like to create
	 * based on the HERO_TYPES array. Creates the desired Hero and
	 * sets their name. Also adds their stats to the Team stats
	 * variables.
	 * @return The created Hero
	 */
	private Hero createHero() {
		
		//Get user to select what type of hero they desire
		String message = "What type of hero would you like?";
		String[] options = heroBlurbs();
		int selected = m.displayMenu(message, options);
		
		//Add the stats of the selected Hero to the Team's stats
		addStats(HERO_TYPES[selected]);
		
		//Add the selected Hero to the Team
		Hero selected_hero = HERO_TYPES[selected].createHero();
		
		//Set the name of the new Hero
		message = "What will be the name of your new hero?";
		String name = m.displayStringRequest(message);
		//Hero's cannot share a name
		while (heroNames().contains(name)) {
			
			m.displayMessage("You cannot have multiple heros with the same name");
			
			name = m.displayStringRequest(message);
			
		}
		
		selected_hero.setName(name);
		
		return selected_hero;
		
	}
	
	/**
	 * Adds the stats of the specified HeroStatSelector to the
	 * Team's stats variables
	 * @param heroType The HeroStatSelector to have its stats
	 * added
	 */
	private void addStats(HeroStatSelector heroType) {
		
		cash += heroType.getCash();		
		healing += heroType.getHealing();		
		illusion += heroType.getIllusion();
		haggling += heroType.getHaggling();
		
		if (heroType.getMap()) {
			map = true;
		}
		
	}
	
	/**
	 * Adds a Hero to the Team's heros ArrayList 
	 * @param hero The hero to be added
	 */
	private void addHero(Hero hero) {		
		
		heros.add(hero);
		
	}
	
	/**
	 * Creates a String array of the returned values from
	 * each HeroStatSelector's toString function. The values
	 * appear in the same index as the HeroStatSelector's
	 * appear in the HERO_TYPES array
	 * @return The created String array
	 */
	private String[] heroBlurbs() {
		
		String[] blurbs = new String[HERO_TYPES.length];
		
		for (int i = 0; i < HERO_TYPES.length; i++) {
			
			blurbs[i] = HERO_TYPES[i].toString();
			
		}
		
		return blurbs;
		
	}
	
	/**
	 * Creates a Set<String> containing the names of all
	 * the Hero's in the Team's heros ArrayList
	 * @return The created Set
	 */
	public Set<String> heroNames() {
		
		HashSet<String> names = new HashSet<String>();
		
		for(int i = 0; i < heros.size(); i++) {
			
			names.add(heros.get(i).getName());
			
		}
		
		return names;
		
	}
	
	/**
	 * Creates a String array containing the returned values
	 * from the toString function of each Hero in the Team's
	 * heros ArrayList. The values appear in the same index as
	 * the Hero's in heros
	 * @return The created String array
	 */
	public String[] heroIdentifiers() {
		
		//TODO everything
		
	}
	
	/**
	 * Fetches the hero at the i index of the Team's
	 * heros ArrayList.
	 * @param i The index of the desired Hero
	 * @return The Hero at index i
	 */
	public Hero getHero(int i) {
		
		//TODO Fix
		
		Hero h = new Hero();
		
		return h;
		
	}

	/**
	 * Expels any Hero's from the team who's health has
	 * reached zero
	 */
	public void check_health() {
		// TODO Auto-generated method stub
		
	}
	
}

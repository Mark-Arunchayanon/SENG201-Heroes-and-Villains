
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Creates and manages a Team of Heros.
 * @author fer25
 * 
 */
public class Team {
	
	//Create array containing selectors for all the different hero types
	private static final HeroStatSelector[] HERO_TYPES = {
			(HeroStatSelector) new Dandy(), (HeroStatSelector) new Explorer(),
			(HeroStatSelector) new Illusionist(), (HeroStatSelector) new Physician(),
			(HeroStatSelector) new Stickler(),(HeroStatSelector) new Strongman()};
	
	//Create arrayList to store the Heros in the Team
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	//Create variables to store team stats
	private int team_size;
	private int cash = 0;
	private boolean map = false;
	private boolean PU_map = false;
	
	//Create arrayList to store objects that the team owns
	private ArrayList<Saleable> team_items = new ArrayList<Saleable>();
	
	//Create ArrayList to store current heal operations
	private ArrayList<HealingItem> heal_operations = new ArrayList<HealingItem>();
	
	private MenuSystem m;
	
	public Team(MenuSystem m) {
		
		this.m = m;
		
		//Establish the size of the team
		String message = "How many Heros would you like in your team?";
		
		String[] options = {"One", "Two", "Three"};
		
		team_size = m.displayMenu(message, options) + 1;
		
		//Fill the stats variables and heros arrayList
		for (int i = 0; i < team_size; i++) {
			
			Hero new_hero = createHero();
			
			addHero(new_hero);
			
		}
		
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
		
		//Create the new Hero object
		Hero selected_hero = HERO_TYPES[selected].createHero();
		
		//Add the Hero's cash to the Team's purse
		cash += HERO_TYPES[selected].getCash();
		//See if the hero provides the Team a map
		if (HERO_TYPES[selected].getMap()) map = true;
		
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
		
		String[] identifiers = new String[team_size];
		
		for(int i = 0; i < team_size; i++) {
			identifiers[i] = heros.get(i).toString();
		}
		
		return identifiers;
		
	}
	
	/**
	 * Fetches the hero at the i index of the Team's
	 * heros ArrayList.
	 * @param i The index of the desired Hero
	 * @return The Hero at index i
	 */
	public Hero getHero(int i) {
		
		return heros.get(i);
		
	}

	/**
	 * Expels any Heros from the team who's health has
	 * reached zero
	 * @return True if team still contains Heros. False
	 * otherwise
	 */
	public boolean checkHealth() {
		// TODO Auto-generated method stub
		
		return true;
		
	}

	/**
	 * Returns true if the Team has a map. False otherwise
	 * @return The Team's map status
	 */
	public boolean getMap() {
		
		return map | PU_map;
		
	}
	
	/**
	 * Enables the PU_map for this city
	 */
	public void setPUMap() {
		
		PU_map = true;
		
	}
	
	/**
	 * Resets the Team's power ups. To be used on clearing a City
	 */
	public void resetPU() {
		
		PU_map = false;
		
		for(Hero hero : heros) {
			
			hero.resetPU();
			
		}
		
	}

	/**
	 * Returns the ArrayList of items that the team owns
	 * @return the team_items
	 */
	public ArrayList<Saleable> getTeamItems() {
		return team_items;
	}

	/**
	 * Adds a Saleable item to the list of items that the team owns
	 * @param item The item to add to team_items
	 */
	public void addTeamItem(Saleable item) {
		team_items.add(item);
	}

	/**
	 * Gets the amount of cash in the Team's purse
	 * @return The amount of cash the team has
	 */
	public int getCash() {
		return cash;
	}
	
	/**
	 * Adds amount to the Team's purse
	 * Negative amounts are supported
	 * The function will not allow the Team's purse to go negative
	 * @param amount The amount to add to the Team's purse
	 * @return True if transaction successful. False otherwise
	 */
	public boolean adjustCash(int amount) {
		if (cash + amount >= 0) {
			cash += amount;
			return true;
		}
		return false;
	}
	
	/**
	 * Provides a string containing the current status of the Team and all its Hero's
	 * @return The Team's status as a String
	 */
	public String getStatus() {
		String stat;
		for (int i = 0; i < team_size; i++) {
			stat = heros.get(i).toString();
			m.displayMessage(stat);
		}
		return "Total cash: $" + cash;
	}
	
	/**
	 * Returns the ArrayList of Heros on the Team
	 * @return The ArrayList of Heros
	 */
	public ArrayList<Hero> getHeros(){
		
		return heros;
		
	}
	
	/**
	 * Adds a HealingItem to the Team's internal heal_operations ArrayList to preserve the object
	 * from city to city
	 * @param item The Healing object to be preserved
	 */
	public void addHealOperation(HealingItem item) {
		
		heal_operations.add(item);
		
	}
	
	/**
	 * Removes a HealingItem from the Team's internal heal_operations ArrayList
	 * @param item The Healing object to be removed
	 */
	public void removeHealOperation(HealingItem item) {
		
		heal_operations.remove(item);
		
	}
	
	/**
	 * @return The array of current healing operations
	 */
	public ArrayList<HealingItem> getHealingOperations() {
		return heal_operations;
	}
}

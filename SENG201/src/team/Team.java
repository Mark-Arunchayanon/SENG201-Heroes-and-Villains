package team;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import gui.ItemSelector;
import gui.MenuSystem;
import gui.StringGetPanel;
import saleable.HealingItem;
import saleable.Saleable;
import saleable.Selectable;

/**
 * Creates and manages a Team of Heroes.
 * @author fer25
 * @author par116
 */
public class Team {
	
	//Create array containing selectors for all the different hero types
	private static final Selectable[] HERO_TYPES = {
			(Selectable) new Dandy(), (Selectable) new Explorer(),
			(Selectable) new Illusionist(), (Selectable) new Physician(),
			(Selectable) new Stickler(),(Selectable) new Strongman()};
	
	//Set the maximum and  minimum amount of Hero's allowed in the Team
	private static final int MAX_HEROS = 3;
	private static final int MIN_HEROS = 1;
	//Set the maximum and minimum length of a Team Name
	private static final int MIN_LENGTH_NAME = 2;
	private static final int MAX_LENGTH_NAME = 10;
	
	//Create arrayList to store the Heros in the Team
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	//Create variables to store team stats
	private String team_name = null;
	private int team_size = 0;
	private int cash = 0;
	private boolean map = false;
	private boolean PU_map = false;
	
	//Create arrayList to store objects that the team owns
	private ArrayList<Saleable> team_items = new ArrayList<Saleable>();
	
	//Create ArrayList to store current heal operations
	private ArrayList<HealingItem> heal_operations = new ArrayList<HealingItem>();
	
	private MenuSystem m;
	
	/**
	 * For testing purposes
	 * @param m The menusystem being used for the GUI
	 * @param heros An array of heros to add to the team
	 * @param map True if the test team has a map, False otherwise
	 */
	public Team(MenuSystem m, Hero[] temp_heros, boolean map) {
		this.m = m;
		this.map = map;
		
		//Add heros to team
		for(Hero hero : temp_heros) {
			heros.add(hero);
		}
		
		//Give the testing team some cash
		cash = 10000;
		
	}
	
	/**
	 * For regular use
	 * @param m The menusystem being used for the GUI
	 */
	public Team(MenuSystem m) {
		
		this.m = m;
		
		getTeamName();
		
		
		body = "How many Heros would you like in your team?\n"
				+ "Please enter an Integer from " + MIN_HEROS + " to " + MAX_HEROS;
		
		Sget.bodyTextSet(body);
		
		while (team_size == 0) {
			try {
				team_size = Integer.parseInt(Sget.getUserString());
				
				if (team_size < MIN_HEROS || team_size > MAX_HEROS) {
					team_size = 0;
					JOptionPane.showMessageDialog(m.getFrame(),
							"Value must be from " + MIN_HEROS + " to " + MAX_HEROS,
							"Invalid Input",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(m.getFrame(),
						"Please enter an integer",
						"Invalid Input",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		createHeros();
		
		setNames();
		
	}
	
	/**
	 * Gets the User to input a team Name
	 */
	private void getTeamName() {
		
		String title = "Setup your Team";
		String body = "What will your Team's name be?\nEnter a String from "
		+ MIN_LENGTH_NAME + " to " + MAX_LENGTH_NAME + " charachters long";
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		
		while (team_name == null) {
			team_name = Sget.getUserString();
			
			if(team_name.length() > MAX_LENGTH_NAME || team_name.length() < MIN_LENGTH_NAME) {
				team_name = null;
				
				JOptionPane.showMessageDialog(m.getFrame(),
						"Name must be from " + MIN_LENGTH_NAME + " to " + MAX_LENGTH_NAME + " charachters long",
						"Invalid Input",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}

	/**
	 *  Takes the name from input and sets the name for each hero in the team
	 *  Two heroes cannot share the same name
	 */
	private void setNames() {
		
		String title = "Setup your Team";
		
		StringGetPanel Sget = new StringGetPanel(title);
		m.updatePanel(Sget);
		for(int i = 1; i <= heros.size(); i++) {
			
			String body = "What would you like the name "
					+ "of your number " + i + " Hero to be?";
			Sget.bodyTextSet(body);
			String name = Sget.getUserString();
			//Hero's cannot share a name
			while (heroNames().contains(name)) {
				
				JOptionPane.showMessageDialog(m.getFrame(),
						"Hero's cannot share a name",
						"Invalid Input",
						JOptionPane.INFORMATION_MESSAGE);
				
				name = Sget.getUserString();
				
			}
			
			heros.get(i-1).setName(name);
			
		}
		
	}

	/**
	 * Queries the user on what type of hero they would like to create
	 * based on the HERO_TYPES array. Creates the desired Hero and
	 * sets their name. Also adds their stats to the Team stats
	 * variables.
	 * @return The created Hero
	 */
	private void createHeros() {
		String title = "Setup your Team";
		ItemSelector selector = new ItemSelector(title, HERO_TYPES, false);
		m.updatePanel(selector);
		for(int i = 1; i <= team_size; i++) {
			String body = "What would you like the type "
					+ "of your number " + i + " Hero to be?";
			
			selector.descriptionTextSet(body);
			HeroStatSelector selected = (HeroStatSelector) selector.getSelectedObject();
			heros.add(selected.createHero());
			
			//Add the Hero's cash to the Team's purse
			cash += selected.getCash();
			//See if the hero provides the Team a map
			if (selected.getMap()) map = true;
			
		}
		
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
		
		//Make a list to store heros that need to be kept
		ArrayList<Hero> heros_to_keep = new ArrayList<Hero>();
		
		for (Hero hero : heros) {
			if(hero.getHealth() > 0) {
				heros_to_keep.add(hero);
			}
		}
		
		heros = heros_to_keep;
		
		return !heros.isEmpty();
		
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

	public String getStatus() {
		String s = "Status of " + team_name + ":\n\n"
				+ "Cash: $" + cash;
		
		if (map) s += "\nOwns a map of this city\n\n";
		else s += "\nDoes not own a map of this city\n\n";
		
		s += "===========Heros===========\n\n";
		
		for (Hero hero : heros) {
			s += hero.getName() + ":\n" +
					hero.getDescriptor() + "\n\n";
		}
		
		if (team_items.size() > 0) s += "========Owned Items========\n\n";
		
		for(Saleable item : team_items) {
			s += item.getTitle() + ":\n" +
					item.getDescriptor() + "\n\n";
		}
		
		return s;
		
	}

	/**
	 * Returns team_size, The initial number of Hero's in the Team
	 * @return The team_size variable
	 */
	public String getTeamSize() {
		// TODO Auto-generated method stub
		return null;
	}
}

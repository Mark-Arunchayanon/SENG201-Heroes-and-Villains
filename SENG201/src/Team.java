
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Creates and manages a Team of Heros.
 * @author fer25
 * 
 */
public class Team {
	
	//Create array containing selectors for all the different hero types
	private static final Selectable[] HERO_TYPES = {
			(Selectable) new Dandy(), (Selectable) new Explorer(),
			(Selectable) new Illusionist(), (Selectable) new Physician(),
			(Selectable) new Stickler(),(Selectable) new Strongman()};
	
	private static final int MAX_HEROS = 3;
	private static final int MIN_HEROS = 1;
	
	private String team_name;
	
	//Create arrayList to store the Heros in the Team
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	//Create variables to store team stats
	private int team_size = 0;
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
		
		String title = "Setup your Team";
		String body = "What will your Team's name be?";
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		team_name = Sget.getUserString();		
		
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

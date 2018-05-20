import java.util.ArrayList;
import java.util.Collections;

/**
 * Defines the Cities that the game is based around. When a City is created, so to is all the 
 * locations and objects within the City.
 * 
 * @author fer25
 *
 */

public class City {
	
	private static final String[] DIRECTION_NAMES = {"North", "East", "South", "West"};

	private ArrayList<Location> locations = new ArrayList<Location>(4);
	//Directions is used to randomise which direction goes to which location.
	private ArrayList<Integer> directions = new ArrayList<Integer>(4);	
	
	private MenuSystem m = new MenuSystem();
	
	public City(Team team, boolean last_city) {
		
		//Create the locations that can be traveled to within the city
		Lair lair = new Lair();
		locations.add((Location) lair);
		locations.add((Location) new Hospital());
		locations.add((Location) new PowerUpDen());
		locations.add((Location) new Shop());
		
		//Add the number of locations directions (4)
		for(int i = 0; i < locations.size(); i++) {
			directions.add(i);			
		}
		
		//Shuffle directions to randomise which direction points to which location
		Collections.shuffle(directions);
		
		//Enter the city's "kernel"
		while(team.checkHealth() && !lair.checkVillainDefeated()) {
			
			m.displayMessage("Welcome to your Home Base");
			
			//See if the player wishes to travel or check the Team's status
			String message  = "Would you like to?";
			String[] options = {"Check your teams status", "Travel"};
			int result = m.displayMenu(message, options);
			
			if(result == 0) {
				
				m.displayMessage(team.getStatus());
				
			} else travel(team, last_city);
			
		}
		
	}
	
	/**
	 * Sends a team to another Location.
	 * @param team The Team to send
	 * @param last_city Whether or not this City is the last city in the game
	 */
	private void travel(Team team, boolean last_city) {
		
		//Query the player as to where they wish to travel to
		String message = "Where do you wish to travel to?";
		String[] options = new String[4];
		//Change how the options display depending on whether or not the team has a map
		if(team.getMap()){
			
			for(int i = 0; i < locations.size(); i++) {
				
				//Team has a map. The direction can have the location appended to it
				options[i] = DIRECTION_NAMES[i] + " to " +
				locations.get(directions.get(i)).getType();
				
			} 
			 
		} else {
			
			options = DIRECTION_NAMES;
		}
		
		int selected_direction = m.displayMenu(message, options);
		
		//Travel to the selected location
		locations.get(directions.get(selected_direction)).travelTo(team, last_city);
		
	}
	
}

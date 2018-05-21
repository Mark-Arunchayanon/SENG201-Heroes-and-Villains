import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Defines the Cities that the game is based around. When a City is created, so to is all the 
 * locations and objects within the City.
 * 
 * @author fer25
 *
 */

public class City extends JPanel{
	
	private static final String[] DIRECTION_NAMES = {"North", "East", "South", "West"};

	private ArrayList<Location> locations = new ArrayList<Location>(4);
	//Directions is used to randomise which direction goes to which location.
	private ArrayList<Integer> directions = new ArrayList<Integer>(4);
	
	private MenuSystem m;
	
	public City(Team team, boolean last_city, MenuSystem m) {
		
		this.m = m;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnCheckTeamStatus = new JButton("Check Team Status");
		GridBagConstraints gbc_btnCheckTeamStatus = new GridBagConstraints();
		gbc_btnCheckTeamStatus.insets = new Insets(0, 0, 5, 5);
		gbc_btnCheckTeamStatus.gridx = 1;
		gbc_btnCheckTeamStatus.gridy = 0;
		add(btnCheckTeamStatus, gbc_btnCheckTeamStatus);
		
		JButton btnTravelNorth = new JButton("Travel North");
		GridBagConstraints gbc_btnTravelNorth = new GridBagConstraints();
		gbc_btnTravelNorth.insets = new Insets(0, 0, 5, 5);
		gbc_btnTravelNorth.gridx = 1;
		gbc_btnTravelNorth.gridy = 1;
		add(btnTravelNorth, gbc_btnTravelNorth);
		
		JButton btnTravelWest = new JButton("Travel West");
		GridBagConstraints gbc_btnTravelWest = new GridBagConstraints();
		gbc_btnTravelWest.insets = new Insets(0, 0, 5, 5);
		gbc_btnTravelWest.gridx = 0;
		gbc_btnTravelWest.gridy = 2;
		add(btnTravelWest, gbc_btnTravelWest);
		
		JButton btnTravelEast = new JButton("Travel East");
		GridBagConstraints gbc_btnTravelEast = new GridBagConstraints();
		gbc_btnTravelEast.insets = new Insets(0, 0, 5, 0);
		gbc_btnTravelEast.gridx = 2;
		gbc_btnTravelEast.gridy = 2;
		add(btnTravelEast, gbc_btnTravelEast);
		
		JButton btnTravelSouth = new JButton("Travel South");
		GridBagConstraints gbc_btnTravelSouth = new GridBagConstraints();
		gbc_btnTravelSouth.insets = new Insets(0, 0, 0, 5);
		gbc_btnTravelSouth.gridx = 1;
		gbc_btnTravelSouth.gridy = 3;
		add(btnTravelSouth, gbc_btnTravelSouth);
		
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

	private setGui() {
		
		
		
	}
	
}

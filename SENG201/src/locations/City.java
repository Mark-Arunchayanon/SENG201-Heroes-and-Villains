package locations;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import gui.InformationPanel;
import gui.MenuSystem;
import saleable.HagglingBooster;
import saleable.HealingItem;
import saleable.IllusionBooster;
import saleable.Map;
import saleable.Saleable;
import team.Team;


/**
 * Defines the Cities that the game is based around. When a City is created, so is all the 
 * locations and objects within the City.
 * 
 * @author fer25
 * @author par116
 */

public class City extends JPanel{
	// Percent chance that the team will have an item stolen or given while travelling
	private static final int PERCENT_CHANCE_CHANGE = 15;
	//Maximum cash bonus for clearing this city
	private static final int MAX_CASH_BONUS = 70;
	//Minimum cash bonus for clearing this city
	private static final int MIN_CASH_BONUS = 40;
	
	//Calculate Constants
	private static final int CASH_DELTA = MAX_CASH_BONUS - MIN_CASH_BONUS;	
	
	@SuppressWarnings("rawtypes")
	private static final Class[] SALEABLES = {HagglingBooster.class,
			IllusionBooster.class, Map.class, HealingItem.class};
	
	private ArrayList<Location> locations = new ArrayList<Location>(4);
	//Directions is used to randomise which direction goes to which location.
	private ArrayList<Integer> directions = new ArrayList<Integer>(4);
	
	private MenuSystem m;
	private Random r = new Random();
	private Team team;
	private boolean last_city;
	private Lair lair;
	
	private Object synchronizer = new Object();
	private int travel_direction;
	
	/**
	 * @param team The Team that is traveling to the City
	 * @param last_city Instructs the city to create a lair with a Super Villain if set to True
	 * @param m The MenuSystem containing the GUI
	 */
	public City(Team team, boolean last_city, MenuSystem m) {
		
		this.m = m;
		this.team = team;
		this.last_city = last_city;
		
		//Create the locations that can be traveled to within the city
		lair = new Lair(m);
		locations.add((Location) lair);
		locations.add((Location) new Hospital(m));
		locations.add((Location) new PowerUpDen(m));
		locations.add((Location) new Shop(m));
		
		//Add the number of locations directions (4)
		for(int i = 0; i < locations.size(); i++) {
			directions.add(i);			
		}
		
		//Shuffle directions to randomize which direction points to which location
		Collections.shuffle(directions);
		
		while (!lair.checkVillainDefeated() && team.checkHealth()) {
		
			//Set up the GUI
			displayOptions();
			
			//Wait till the GUI has been interacted with
			synchronized(synchronizer) {			
				try {
					synchronizer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
			//This indicates the user wishes to see the Team's status
			if(travel_direction == 4) {
				displayTeamStatus();
			} else {			
				travel();
			}
		}
		
		//Complete City complete actions
		if(lair.checkVillainDefeated()) {
			//Reset the Team's PowerUps
			team.resetPU();
			//Give the team bonus cash for defeating the villain
			int cash = r.nextInt(CASH_DELTA) + MIN_CASH_BONUS;
			team.adjustCash(cash);
			//Display a congratulations screen
			String title = "Congratulations!";
			String body = "As a thank you gesture for defeating"
					+ " our Villain, the City would like to gift you $" + cash;
			InformationPanel info = new InformationPanel(title, body);
			m.updatePanel(info);
			info.blockTillOK();
		}
		
	}
	
	private void travel() {
		
		locations.get(travel_direction).travelTo(team, last_city);
		
		int ran_chance = r.nextInt(100);
		String message = "";
		String warning = "Notice";
		ArrayList<Saleable> team_stash = team.getTeamItems();
		
		if (ran_chance < PERCENT_CHANCE_CHANGE) {
			message = "Unfortunately there was a thief on the loose, one of the team's items have been stolen";
			if (team_stash.size() != 0) {
				team_stash.remove(r.nextInt(team_stash.size()));
			} else {
				message = "A thief tried to steal an item from the team's inventory, luckily it was empty";
			}
			InformationPanel info = new InformationPanel(warning, message);
			m.updatePanel(info);		
			info.blockTillOK();
		} else if (ran_chance >= (100 - PERCENT_CHANCE_CHANGE)){
			message = "The team stumbled across an item during their travel";
			@SuppressWarnings("rawtypes")
			Class power_up = SALEABLES[r.nextInt(SALEABLES.length)];
			
			try {
				Saleable gift = (Saleable) power_up.newInstance();
				gift.setRandom(r);
				gift.sold();
				team_stash.add(gift);
			} catch (InstantiationException | IllegalAccessException e) {
				// Shouldn't throw error
				e.printStackTrace();
			}
			InformationPanel info = new InformationPanel(warning, message);
			m.updatePanel(info);		
			info.blockTillOK();			
		}
		
	}
	
	/**
	 * Creates a the layout of city GUI
	 */
	private void displayOptions() {
		
		String[] direction_button_text =  getDirectionButtonText();
		
		removeAll();
		
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 1.0, 2.0};
		gridBagLayout.rowWeights = new double[]{1.0, 2.0, 2.0, 2.0, 2.0};
		setLayout(gridBagLayout);
		
		JLabel txtpnWelcomeToYour = new JLabel();
		txtpnWelcomeToYour.setFocusable(false);
		txtpnWelcomeToYour.setText("<html>Welcome to your Home Base.<BR>What would you like to do?</html>");
		txtpnWelcomeToYour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnWelcomeToYour.setHorizontalAlignment(SwingConstants.CENTER);
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		GridBagConstraints gbc_txtpnWelcomeToYour = new GridBagConstraints();
		gbc_txtpnWelcomeToYour.gridwidth = 3;
		gbc_txtpnWelcomeToYour.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnWelcomeToYour.fill = GridBagConstraints.BOTH;
		gbc_txtpnWelcomeToYour.gridx = 0;
		gbc_txtpnWelcomeToYour.gridy = 0;
		add(txtpnWelcomeToYour, gbc_txtpnWelcomeToYour);
		
		JButton btnCheckTeamStatus = new JButton("Check Team Status");
		ActionListener btnCheckTeamStatusListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				synchronized(synchronizer) {
					travel_direction = 4;
					synchronizer.notify();
				}
			}
		
		};
		btnCheckTeamStatus.addActionListener(btnCheckTeamStatusListner);
		GridBagConstraints gbc_btnCheckTeamStatus = new GridBagConstraints();
		gbc_btnCheckTeamStatus.insets = new Insets(0, 0, 5, 5);
		gbc_btnCheckTeamStatus.gridx = 1;
		gbc_btnCheckTeamStatus.gridy = 1;
		add(btnCheckTeamStatus, gbc_btnCheckTeamStatus);
		
		JButton btnTravelNorth = new JButton(direction_button_text[0]);
		GridBagConstraints gbc_btnTravelNorth = new GridBagConstraints();
		gbc_btnTravelNorth.insets = new Insets(0, 0, 5, 5);
		gbc_btnTravelNorth.gridx = 1;
		gbc_btnTravelNorth.gridy = 2;
		add(btnTravelNorth, gbc_btnTravelNorth);
		ActionListener btnTravelNorthListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				synchronized(synchronizer) {
					travel_direction = directions.get(0);
					synchronizer.notify();
				}				
			}			
		};
		btnTravelNorth.addActionListener(btnTravelNorthListner);
		
		JButton btnTravelWest = new JButton(direction_button_text[3]);
		GridBagConstraints gbc_btnTravelWest = new GridBagConstraints();
		gbc_btnTravelWest.insets = new Insets(0, 0, 5, 5);
		gbc_btnTravelWest.gridx = 0;
		gbc_btnTravelWest.gridy = 3;
		add(btnTravelWest, gbc_btnTravelWest);
		ActionListener btnTravelWestListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				synchronized(synchronizer) {
					travel_direction = directions.get(3);
					synchronizer.notify();
				}	
				
			}			
		};
		btnTravelWest.addActionListener(btnTravelWestListner);
		
		JButton btnTravelEast = new JButton(direction_button_text[1]);
		GridBagConstraints gbc_btnTravelEast = new GridBagConstraints();
		gbc_btnTravelEast.insets = new Insets(0, 0, 5, 0);
		gbc_btnTravelEast.gridx = 2;
		gbc_btnTravelEast.gridy = 3;
		add(btnTravelEast, gbc_btnTravelEast);
		ActionListener btnTravelEastListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				synchronized(synchronizer) {
					travel_direction = directions.get(1);
					synchronizer.notify();
				}	
				
			}			
		};
		btnTravelEast.addActionListener(btnTravelEastListner);
		
		JButton btnTravelSouth = new JButton(direction_button_text[2]);
		GridBagConstraints gbc_btnTravelSouth = new GridBagConstraints();
		gbc_btnTravelSouth.insets = new Insets(0, 0, 0, 5);
		gbc_btnTravelSouth.gridx = 1;
		gbc_btnTravelSouth.gridy = 4;
		add(btnTravelSouth, gbc_btnTravelSouth);
		ActionListener btnTravelSouthListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				synchronized(synchronizer) {
					travel_direction = directions.get(2);
					synchronizer.notify();
				}	
				
			}			
		};
		btnTravelSouth.addActionListener(btnTravelSouthListner);
		
		m.updatePanel(this);
		
	}

	/**
	 * Create text for the direction Buttons
	 * @return The button text
	 */
	private String[] getDirectionButtonText() {
		
		String[] direction_strings = {"North", "East", "South", "West"};
		
		for (int i = 0; i < direction_strings.length; i++) {
			
			direction_strings[i] = "Travel " + direction_strings[i];			
			if (team.getMap()) {
				direction_strings[i] += " to " + locations.get(directions.get(i)).getType();
			}
			
		}
		return direction_strings;
	}
	
	/**
	 * Displays information for the selected hero 
	 */
	private void displayTeamStatus() {
		
		String status = team.getStatus();
		
		InformationPanel display = new InformationPanel("Team Status", status);
		
		m.updatePanel(display);
		
		display.blockTillOK();
		
		displayOptions();
		
	}	
	
}

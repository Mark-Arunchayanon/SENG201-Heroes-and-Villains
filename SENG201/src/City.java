import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.Component;

/**
 * Defines the Cities that the game is based around. When a City is created, so to is all the 
 * locations and objects within the City.
 * 
 * @author fer25
 *
 */

public class City extends JPanel{

	private ArrayList<Location> locations = new ArrayList<Location>(4);
	//Directions is used to randomise which direction goes to which location.
	private ArrayList<Integer> directions = new ArrayList<Integer>(4);
	
	private MenuSystem m;	
	private Team team;
	private boolean last_city;
	
	private Object synchronizer = new Object();
	private int travel_direction;
	
	public City(Team team, boolean last_city, MenuSystem m) {
		
		this.m = m;
		this.team = team;
		this.last_city = last_city;
		
		//Create the locations that can be traveled to within the city
		Lair lair = new Lair(m);
		locations.add((Location) lair);
		locations.add((Location) new Hospital(m));
		locations.add((Location) new PowerUpDen(m));
		locations.add((Location) new Shop(m));
		
		//Add the number of locations directions (4)
		for(int i = 0; i < locations.size(); i++) {
			directions.add(i);			
		}
		
		//Shuffle directions to randomise which direction points to which location
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
			
			locations.get(travel_direction).travelTo(team, last_city);
		}
		
	}
	
	private void displayOptions() {
		
		String[] direction_button_text =  getDirectionButtonText();
		
		removeAll();
		
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 1.0, 2.0};
		gridBagLayout.rowWeights = new double[]{1.0, 2.0, 2.0, 2.0, 2.0};
		setLayout(gridBagLayout);
		
		JTextPane txtpnWelcomeToYour = new JTextPane();
		txtpnWelcomeToYour.setEditable(false);
		txtpnWelcomeToYour.setText("Welcome to your Home Base.\r\nWhat would you like to do?");
		txtpnWelcomeToYour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		StyledDocument txtpnWelcomeToYour_SD = txtpnWelcomeToYour.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		txtpnWelcomeToYour_SD.setParagraphAttributes(0, txtpnWelcomeToYour_SD.getLength(), center, false);
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
				displayTeamStatus();
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
	
	private void displayTeamStatus() {
		
		removeAll();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.20, 1.0, 0.20};
		setLayout(gridBagLayout);
		
		JTextPane txtpnTeamStatus = new JTextPane();
		txtpnTeamStatus.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnTeamStatus.setEditable(false);
		txtpnTeamStatus.setText("Team Status");
		StyledDocument txtpnTeamStatus_SD = txtpnTeamStatus.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		txtpnTeamStatus_SD.setParagraphAttributes(0, txtpnTeamStatus_SD.getLength(), center, false);
		GridBagConstraints gbc_txtpnTeamStatus = new GridBagConstraints();
		gbc_txtpnTeamStatus.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTeamStatus.fill = GridBagConstraints.BOTH;
		gbc_txtpnTeamStatus.gridx = 0;
		gbc_txtpnTeamStatus.gridy = 0;
		add(txtpnTeamStatus, gbc_txtpnTeamStatus);
		
		JTextPane txtpnUpdate = new JTextPane();
		txtpnUpdate.setText("Update");
		GridBagConstraints gbc_txtpnUpdate = new GridBagConstraints();
		gbc_txtpnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnUpdate.fill = GridBagConstraints.BOTH;
		gbc_txtpnUpdate.gridx = 0;
		gbc_txtpnUpdate.gridy = 1;
		add(txtpnUpdate, gbc_txtpnUpdate);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayOptions();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 2;
		add(btnOk, gbc_btnOk);
		
		m.updatePanel(this);
		
	}	
	
}

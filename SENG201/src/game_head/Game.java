package game_head;
import javax.swing.JOptionPane;

import gui.InformationPanel;
import gui.MenuSystem;
import gui.StringGetPanel;
import locations.City;
import team.Dandy;
import team.Explorer;
import team.Hero;
import team.Team;

/**
 * Sets up the game with the desired amount of cities(3-6), also advances through the cities
 * 
 * @author fer25
 * @author par116
 */
public class Game {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	private static final boolean DEBUG = true;
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final MenuSystem m = new MenuSystem();
	
	private static final int MAX_CITIES = 6;
	private static final int MIN_CITIES = 3;
	
	private static long START_TIME;
	private static long END_TIME;
	
	private static int city_count;

	/**
	 * Sets up and displays the game welcome page. Then asks for the amount of cities desired(3-6)
	 * @param args
	 */
	public static void main(String[] args) {
		
		getCityCount();
		
		// Create a team
		Team team = new Team(m);
		
		//Start timing the duration of the game
		START_TIME = System.nanoTime();
		
		//Variable for checking if the team is alive
		boolean alive = true;
		for (int i = 0; i < city_count-1 && alive; i++) {
			//Iterate through all but the last city
			new City(team, false, m);
			//Check that the Team has not perished
			alive = team.checkHealth();
		}
		
		if(alive) {
			//Run the last city
			new City(team, true, m);
			//Check that the Team has not perished 
			alive = team.checkHealth();
			
		}
		
		END_TIME = System.nanoTime();
		
		if(alive) {
			scoreScreen(team);			
		} else {
			endScreen();
		}
		
		
	}

	/**
	 * Displays a screen that chides the player for killing their Heros
	 */
	private static void endScreen() {
		
		//Create chiding messages
		String title = "You are a Failure";
		String body = "All your Heroes perished under your command.\nYou should be ashamed.";
		//Create GUI panel
		InformationPanel info = new InformationPanel(title, body);
		//Display panel
		m.updatePanel(info);
		//Wait till the user has acknowledged the message
		info.blockTillOK();
	}

	/**
	 * Displays a screen congratulating the user for winning the game
	 * @param team The Team used to win the game
	 */
	private static void scoreScreen(Team team) {
		
		//Calculate the time taken to complete the game
		long total_time = (long) ((END_TIME - START_TIME) / 1e6);//Dividing by 1e6 to convert from ns to ms
		
		//Format the time to a string
		String formatted_time = formatTime(total_time);
		
		String title = "Congratulations";
		String body = "You completed the game with " + city_count + ""
				+ "Cities, using " + team.getTeamSize() + "Heroes, in " + formatted_time + 
				":\n\nThe Status of your team on completion is\n" + team.getStatus();
		
		InformationPanel info = new InformationPanel(title, body);
		m.updatePanel(info);
		info.blockTillOK();
		
	}

	/**
	 * Takes an elapsed time in milliseconds and returns it formatted in a string as
	 * minutes:seconds:milliseconds
	 * @param milliseconds The elapsed time in milliseconds
	 * @return The elapsed time formatted as minutes:seconds:milliseconds
	 */
	private static String formatTime(long milliseconds) {
		
		//Calculate larger units of time
		int minutes = (int) (milliseconds / 60000);
		milliseconds = milliseconds % 60000;
		int seconds = (int) (milliseconds / 1000);
		milliseconds = milliseconds % 1000;
		
		//Format and return string
		return (minutes + ":" + seconds + ":" + milliseconds);
		
	}

	private static void getCityCount() {
		
		// Welcome message 
		String title = "Welcome to Hero Game";
		String body = "<html>How many Cities would you like to explore?<BR>"
				+ "Please enter an integer from " + MIN_CITIES + " to " + MAX_CITIES + "</html>";
		
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		
		city_count = 0;
		
		// Only allow input to be integers and between MIN_CITIES and MAX_CITIES
		while (city_count == 0) {
			try {
				city_count = Integer.parseInt(Sget.getUserString());
				
				//Provide a method to skip Team creation if Debugging
				if (city_count == -314159265 && DEBUG) {
					city_count = 0;
					testing();
				}
				else if (city_count < MIN_CITIES || city_count > MAX_CITIES) {
					city_count = 0;
					JOptionPane.showMessageDialog(m.getFrame(),
							"Value must be from " + MIN_CITIES + " to " + MAX_CITIES,
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
	}

	private static void testing() {
		
		Hero hero1 = new Explorer().createHero();
		hero1.setName("Hero 1");
		Hero hero2 = new Dandy().createHero();
		hero2.setName("Hero 2");
		Hero[] heros = {hero1, hero2};
		Team team = new Team(m, heros, true);
		City city1 = new City(team, false, m);
		City city2 = new City(team, true, m);
		
	}

}

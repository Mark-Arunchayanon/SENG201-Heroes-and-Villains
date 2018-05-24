package game_head;
import javax.swing.JOptionPane;

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

	/**
	 * Sets up and displays the game welcome page. Then asks for the amount of cities desired(3-6)
	 * @param args
	 */
	public static void main(String[] args) {
		
		int city_count = getCityCount();
		
		// Create a team
		Team team = new Team(m);
		//Variable for checking if the team is alive
		boolean alive = true;
		for (int i = 0; i < city_count-1 && alive; i++) {
			City city = new City(team, false, m);
			
			alive = team.checkHealth();
		}
		
		City city = new City(team, true, m);
		
		
	}

	private static int getCityCount() {
		
		// Welcome message 
		String title = "Welcome to Hero Game";
		String body = "How many Cities would you like to explore?\n"
				+ "Please enter an integer from " + MIN_CITIES + " to " + MAX_CITIES;
		
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		
		int city_count = 0;
		
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
		
		return city_count;
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

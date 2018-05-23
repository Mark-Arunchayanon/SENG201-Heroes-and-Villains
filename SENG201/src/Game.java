import javax.swing.JOptionPane;

/**
 * Sets up the game with the desired amount of cities(3-6), also advances through the cities
 * 
 * @author fer25
 * @author par116
 */
public class Game {
	
	private static final int MAX_CITIES = 6;
	private static final int MIN_CITIES = 3;

	/**
	 * Sets up and displays the game welcome page. Then asks for the amount of cities desired(3-6)
	 * @param args
	 */
	public static void main(String[] args) {
		
		MenuSystem m = new MenuSystem();
		
		int city_count = 0;
		// Welcome message 
		String title = "Welcome to Hero Game";
		String body = "How many Cities would you like to explore?\n"
				+ "Please enter an integer from " + MIN_CITIES + " to " + MAX_CITIES;
		
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		
		// Only allow input to be integers and between MIN_CITIES and MAX_CITIES
		while (city_count == 0) {
			try {
				city_count = Integer.parseInt(Sget.getUserString());
				
				if (city_count < MIN_CITIES || city_count > MAX_CITIES) {
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
		// Create a team
		Team team = new Team(m);
		boolean alive = true;
		for (int i = 0; i < city_count-1 && alive; i++) {
			City city = new City(team, false, m);
		}
		
		City city = new City(team, true, m);
		
		
	}

}

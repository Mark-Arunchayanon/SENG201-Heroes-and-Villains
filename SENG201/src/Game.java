import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game {
	
	private static final int MAX_CITIES = 6;
	private static final int MIN_CITIES = 3;

	public static void main(String[] args) {
		
		MenuSystem m = new MenuSystem();
		
		int city_count = 0;
		
		String title = "Welcome to Hero Game";
		String body = "How many Cities would you like to explore?\n"
				+ "Please enter an integer from " + MIN_CITIES + " to " + MAX_CITIES;
		
		StringGetPanel Sget = new StringGetPanel(title, body);
		m.updatePanel(Sget);
		
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
		
		Team team = new Team(m);
		boolean alive = true;
		for (int i = 0; i < city_count-1 && alive; i++) {
			City city = new City(team, false, m);
		}
		
		City city = new City(team, true, m);
		
		
	}

}

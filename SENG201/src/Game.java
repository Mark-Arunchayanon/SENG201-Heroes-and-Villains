import javax.swing.JPanel;

public class Game {
	
	public static MenuSystem m = new MenuSystem();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//GuessANumber game = new GuessANumber(m);
		Team team = new Team(m);
		
		City c = new City(team, false, m);
		
		
	}

}

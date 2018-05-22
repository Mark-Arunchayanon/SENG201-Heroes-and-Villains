import javax.swing.JPanel;

public class Game {
	
	public static MenuSystem m = new MenuSystem();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GuessANumber game = new GuessANumber(m);
		game.play("Bob", new Hero(100, 100, 100, 100));
		Team team = new Team(m);
		
		City c = new City(team, false, m);
		
		
	}

}

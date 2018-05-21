import javax.swing.JPanel;

public class Game {
	
	public static MenuSystem m = new MenuSystem();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Team team = new Team(m);
		
		City c = new City(team, false, m);
		
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}


public class Game implements GUIPanel {
	
	public static MenuSystem m = new MenuSystem();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Team team = new Team(m);
		
		City c = new City(team, false, m);
		
		
	}

}


public class Game {
	
	public MenuSystem menu = new MenuSystem();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Team t = new Team();
		
		Lair l = new Lair();
		
		l.travelTo(t, false);
		
	}

}

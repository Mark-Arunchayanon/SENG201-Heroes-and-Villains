import java.util.Random;

public class Lair implements Location {
	
	private String[] names = {"Billy", "Ray", "Ricky", "Bobby", "Joe", "Johnny",
			"Jimmy", "Jake", "Willy", "Kenny", "Cletus", "Russel", "Renny",
			"Benny", "Bob", "Steve"}; 
	
	private String[] taunts = {"He He He He", "Fe Fi Fo Fum, I advise you to run",
			"Go away", "I really cannot be bothered dealing with you right now",
			"Your time is over", "Pasta Lavista baby"};
	
	
	Random r = new Random();
	
	MenuSystem m = new MenuSystem();
	
	private String villain_name = names[r.nextInt(names.length)];
	
	private String villain_taunt = taunts[r.nextInt(taunts.length)];

	@Override
	public void travelTo(Team team) {
		
		m.displayMessage(villain_name + " the Villain says \"" +  villain_taunt
				+ "\"");
		
		String message = "Would you like to enter the Lair of " + villain_name + "?";
		
		String[] options = {"Yes", "No"};
		
		int result = m.displayMenu(message, options);
		
		if (result == 1) {
			
			m.displayMessage("Ok. Returning to home base then");
			
			return;
			
		}
		
		String[] heros = team.HeroStrings();
		
		if(heros.length > 1) {
			
			message = "Which hero is going to fight " + villain_name;
			
			m.displayMenu(message, heros);
			
		} else
		
	}

}

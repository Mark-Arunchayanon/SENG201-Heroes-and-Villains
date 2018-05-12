import java.util.Random;

public class Lair implements Location {
	
	private static final int MIN_DAMAGE_STD = 30;
	private static final int MAX_DAMAGE_STD = 50;
	private static final int MIN_DAMAGE_SUPER = 40;
	private static final int MAX_DAMAGE_SUPER = 55;
	
	private static final String[] NAMES = {"Billy", "Ray", "Ricky", "Bobby", "Joe", "Johnny",
			"Jimmy", "Jake", "Willy", "Kenny", "Cletus", "Russel", "Renny",
			"Benny", "Bob", "Steve"}; 
	
	private static final String[] TAUNTS = {"He He He He", "Fe Fi Fo Fum, I advise you to run",
			"Go away", "I really cannot be bothered dealing with you right now",
			"Your time is over", "Pasta Lavista baby"};
	

	private static final VillainGame[] GAMES = {(VillainGame) new PaperScissorsRock(),
			(VillainGame) new GuessANumber(), (VillainGame) new DiceRoll()};

	
	Random r = new Random();
	
	MenuSystem m = new MenuSystem();
	
	private String villain_name = NAMES[r.nextInt(NAMES.length)];
	
	private String villain_taunt = TAUNTS[r.nextInt(TAUNTS.length)];
	
	private VillainGame villain_game = GAMES[r.nextInt(GAMES.length)];
	
	private int villain_lives = 3;

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		m.displayMessage(villain_name + " the Villain says \"" +  villain_taunt
				+ "\"");
		
		String message = "Would you like to enter the Lair of " + villain_name + "?";
		
		String[] options = {"Yes", "No"};
		
		int result = m.displayMenu(message, options);
		
		if (result == 1) {
			
			m.displayMessage("Ok. Returning to home base");
			
			return;
			
		}
		
		String[] heros = team.heroIdentifiers();
		
		Hero playing_hero;
		
		if(heros.length > 1) {
			
			message = "Which hero is going to fight " + villain_name + " in a game of "
			+ villain_game.gameType();
			
			playing_hero = team.getHero(m.displayMenu(message, heros));
			
		} else {
			
			playing_hero = team.getHero(0);
			
			message = heros[0] + " is playing " + villain_name + " in a game of "
			+ villain_game.gameType();
			
		}
		
		if (villain_game.play(villain_name, playing_hero.getName())) {
			
			int health;
			
			if(last_city) {
				
				health = -MIN_DAMAGE_SUPER - r.nextInt(MAX_DAMAGE_SUPER - MIN_DAMAGE_SUPER);
				
			} else {
				
				health = -MIN_DAMAGE_STD - r.nextInt(MAX_DAMAGE_STD - MIN_DAMAGE_STD);
				
			}
			
			m.displayMessage(playing_hero.getName() + " lost " + Math.abs(health) + " health");
			
			playing_hero.adjustHealth(health);
			
			team.check_health();
			
		} else {
			
			villain_lives--;
			
			if (villain_lives == 0) {
				
				if (last_city) {
					
					m.displayMessage("Congratulations, You have completed the game!");
					
				} else {
				
					m.displayMessage("Congratulations, You have cleared this city. You now travel to your home base in the next city!");
					
				}
				
			} else {
			
				m.displayMessage("You now travel back to your Home Base for rest and recouperation before you defeat " + villain_name + " " + villain_lives + " more times");
				
			}
			
		}
		
	}

}

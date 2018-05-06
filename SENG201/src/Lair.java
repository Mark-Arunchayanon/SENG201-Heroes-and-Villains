import java.util.Random;

public class Lair implements Location {
	
	private static final int MIN_DAMAGE_STD = 30;
	private static final int MAX_DAMAGE_STD = 50;
	private static final int MIN_DAMAGE_SUPER = 40;
	private static final int MAX_DAMAGE_SUPER = 55;
	
	private String[] names = {"Billy", "Ray", "Ricky", "Bobby", "Joe", "Johnny",
			"Jimmy", "Jake", "Willy", "Kenny", "Cletus", "Russel", "Renny",
			"Benny", "Bob", "Steve"}; 
	
	private String[] taunts = {"He He He He", "Fe Fi Fo Fum, I advise you to run",
			"Go away", "I really cannot be bothered dealing with you right now",
			"Your time is over", "Pasta Lavista baby"};
	
	private VillainGame[] games = {(VillainGame) new PaperScissorsRock(),
			(VillainGame) new GuessANumber()};//, (VillainGame) new DiceRoll()};
	
	Random r = new Random();
	
	MenuSystem m = new MenuSystem();
	
	private String villain_name = names[r.nextInt(names.length)];
	
	private String villain_taunt = taunts[r.nextInt(taunts.length)];
	
	private VillainGame villain_game = games[r.nextInt(games.length)];
	
	private int villainLives = 3;

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
		
		String[] heros = team.HeroStrings();
		
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
			
		} else {
			
			m.displayMessage("You now travel back to your Home Base");
			
			return;
			
		}
		
	}

}

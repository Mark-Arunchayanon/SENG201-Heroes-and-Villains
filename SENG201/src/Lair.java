import java.util.Random;

/**
 * A location that incorporates a Villain. Allows the user to play the Villain at a game
 * 
 * @author fer25, par116
 *
 */

public class Lair implements Location {
	
	//Define the amount of damage the Villain does to the Hero on a loss
	private static final int MIN_DAMAGE_STD = 30;
	private static final int MAX_DAMAGE_STD = 50;
	//Define the amount of damage the Super Villain does to the Hero on a loss
	private static final int MIN_DAMAGE_SUPER = 40;
	private static final int MAX_DAMAGE_SUPER = 55;
	
	//Define some potential Villain names
	private static final String[] NAMES = {"Billy", "Ray", "Ricky", "Bobby", "Joe", "Johnny",
			"Jimmy", "Jake", "Willy", "Kenny", "Cletus", "Russel", "Renny",
			"Benny", "Bob", "Steve"}; 
	
	//Define some potential Villain taunts
	private static final String[] TAUNTS = {"He He He He", "Fe Fi Fo Fum, I advise you to run",
			"Go away", "I really cannot be bothered dealing with you right now",
			"Your time is over", "Pasta Lavista baby"};
	
	//Create all potential VillainGames
	private static final VillainGame[] GAMES = {(VillainGame) new PaperScissorsRock(),
			(VillainGame) new GuessANumber(), (VillainGame) new DiceRoll()};

	
	private Random r = new Random();
	private MenuSystem m;
	
	//Set the Villain's parameters
	private String villain_name = NAMES[r.nextInt(NAMES.length)];
	private String villain_taunt = TAUNTS[r.nextInt(TAUNTS.length)];
	private VillainGame villain_game = GAMES[r.nextInt(GAMES.length)];
	private int villain_lives = 3;

	public Lair(MenuSystem m) {

		this.m = m;
		
	}

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		m.displayMessage(villain_name + " the Villain says \"" +  villain_taunt
				+ "\"");
		
		//Query the user on whether or not they would like to enter the lair
		String message = "Would you like to enter the Lair of " + villain_name + "?";
		String[] options = {"Yes", "No"};
		int result = m.displayMenu(message, options);
		
		//Go back to home base of they do not wish to enter
		if (result == 1) {	
			
			m.displayMessage("Ok. Returning to home base");			
			return;
			
		}
		
		//Select a Hero to challenge the Villain
		Hero playing_hero = select_hero(team);
		
		//Play the game and react to the result
		if (villain_game.play(villain_name, playing_hero)) {
			
			//The player lost the game. Calculate the amount of health the Hero looses
			int health;
			
			if(last_city) {
				//If this is the last city the Hero was a Super Villain and does more damage
				health = MIN_DAMAGE_SUPER + r.nextInt(MAX_DAMAGE_SUPER - MIN_DAMAGE_SUPER);				
			} else {				
				health = MIN_DAMAGE_STD + r.nextInt(MAX_DAMAGE_STD - MIN_DAMAGE_STD);				
			}
			
			m.displayMessage(playing_hero.getName() + " lost " + health + " Health Points");
			playing_hero.adjustHealth(-health);
			//Remove any Heros that have dropped below 0 Health
			team.checkHealth();
			
		} else {
			
			//The player won the game. Congratulate them and take a life off the Villain
			villain_lives--;
			
			if (villain_lives == 0) {
				//If Villain defeated
				if (last_city) {
					//If the game is defeated
					m.displayMessage("Congratulations, You have completed the game!");					
				} else {				
					m.displayMessage("Congratulations, You have cleared this city."
							+ "You now travel to your home base in the next city!");					
				}				
			} else {
				m.displayMessage("You now travel back to your Home Base for rest"
						+ "and recouperation before you defeat " + villain_name + " "
						+ villain_lives + " more times");				
			}
			
		}
		
	}
	
	/**
	 * Selects the Hero that the player wants to play the Villain in the game
	 * @param team The team containing the hero that will play the Villain
	 * @return The Hero that will play the Villain
	 */
	private Hero select_hero(Team team) {
		
		//Select the hero that is going to combat the Villain
		String[] heros = team.heroIdentifiers();
		Hero playing_hero;
		//If there is more than one Hero query the player on which Hero they want to use
		if(heros.length > 1) {
			
			String message = "Which hero is going to fight " + villain_name + " in a game of "
			+ villain_game.gameType();			
			playing_hero = team.getHero(m.displayMenu(message, heros));
			
		} else { //Otherwise select the only available hero
			
			playing_hero = team.getHero(0);			
			String message = heros[0] + " is playing " + villain_name + " in a game of "
			+ villain_game.gameType();			
			m.displayMessage(message);
			
		}
		
		return playing_hero;
		
	}
	
	/**
	 * Checks if the villain has been defeated 3 times
	 * 
	 * @return True if the villain has been, False otherwise
	 */
	public boolean checkVillainDefeated() {
		
		if(villain_lives == 0) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	@Override
	public String getType() {
		
		return "Lair";
		
	}

}

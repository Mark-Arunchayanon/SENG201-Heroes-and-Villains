import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * A location that incorporates a Villain. Allows the user to play the Villain at a game
 * 
 * @author fer25, par116
 *
 */

public class Lair implements Location{
	
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

	private String villain_name;
	private String villain_taunt;
	private VillainGame villain_game;
	private int villain_lives;
	
	private static Random r = new Random();
	private MenuSystem m;	

	public Lair(MenuSystem m) {

		this.m = m;
		
		//Create all potential VillainGames
		final VillainGame[] GAMES = {(VillainGame) new PaperScissorsRock(m),
				(VillainGame) new GuessANumber(m), (VillainGame) new DiceRoll(m)};
		
		//Set the Villain's parameters
		villain_name = NAMES[r.nextInt(NAMES.length)];
		villain_taunt = TAUNTS[r.nextInt(TAUNTS.length)];
		villain_game = GAMES[r.nextInt(GAMES.length)];
		villain_lives = 3;
		
	}

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		Hero selected_hero = displayHeroSelect(team);
		
		//Go back to home base of they do not wish to enter
		if (selected_hero == null) {	
			
			return;
			
		}
		
		//Play the game and react to the result
		if (villain_game.play(villain_name, selected_hero)) {
			
			//The player lost the game. Calculate the amount of health the Hero looses
			int health;
			
			if(last_city) {
				//If this is the last city the Hero was a Super Villain and does more damage
				health = MIN_DAMAGE_SUPER + r.nextInt(MAX_DAMAGE_SUPER - MIN_DAMAGE_SUPER);				
			} else {				
				health = MIN_DAMAGE_STD + r.nextInt(MAX_DAMAGE_STD - MIN_DAMAGE_STD);				
			}
			
			selected_hero.adjustHealth(-health);
			
			String title = "Failure";
			String body = selected_hero.getName() + " was defeated by " + villain_name + "and lost " +
			health + " Health Points. They now have " + selected_hero.getHealth() + " Health Points";
			
			if(selected_hero.getHealth() == 0) {
				body += " and have died";
			}
			
			InformationPanel info = new InformationPanel(title, body);			
			m.updatePanel(info);			
			info.blockTillOK();
			
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
	
	private Hero displayHeroSelect(Team team) {
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		
		ItemSelector selector = new ItemSelector(villain_taunt, "Select a hero", heros);
		
		m.updatePanel(selector);
		
		return (Hero) selector.getSelectedObject();
		
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

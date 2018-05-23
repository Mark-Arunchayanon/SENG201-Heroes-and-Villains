package locations;
import java.util.Random;

import gui.InformationPanel;
import gui.ItemSelector;
import gui.MenuSystem;
import saleable.Selectable;
import team.Hero;
import team.Team;

/**
 * A location that incorporates a Villain. Allows the user to play the Villain at a game
 * 
 * @author fer25
 * @author par116
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
		final VillainGame[] GAMES = {(VillainGame) new PaperScissorsRock(m, r),
				(VillainGame) new GuessANumber(m, r), (VillainGame) new DiceRoll(m, r)};
		
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
			// Displays remaining health and whether the hero has died
			String title = "Failure";
			String body = selected_hero.getName() + " was defeated by " + villain_name + " and lost " +
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
			String body;
			if (villain_lives == 0 && last_city) {
				return;
			}
			if (villain_lives == 0) {
				//If Villain defeated			
				body = "Congratulations, You have cleared this city."
						+ "You now travel to your home base in the next city";
			} else {
				body = "You now travel back to your Home Base for rest"
						+ " and recouperation before you defeat " + villain_name + " "
						+ villain_lives + " more times";				
			}
			
			InformationPanel info = new InformationPanel("Congratulations", body);
			m.updatePanel(info);
			info.blockTillOK();
			
		}
		
	}
	
	/**
	 * Displays heroes to be selected
	 * 
	 * @param team
	 * @return Hero selected
	 */
	private Hero displayHeroSelect(Team team) {
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		
		ItemSelector selector = new ItemSelector(villain_taunt, "Select a hero", heros, true);
		
		m.updatePanel(selector);
		
		return (Hero) selector.getSelectedObject();
		
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

import java.util.Random;

import javax.swing.JPanel;

public class PaperScissorsRock implements VillainGame {

	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	
	private MenuSystem m;
	private Random r = new Random();
	
	public PaperScissorsRock(MenuSystem m) {

		this.m = m;
		
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		int player_score = 0, villain_score = 0;
		int illusion = playing_hero.getIllusion();
		
		m.displayMessage("This is a best of three game of Paper Scissors Rock.");
		
		String message = "Choose your Weapon!";
		
		String[] options = {"Paper", "Scissors", "Rock"};
		
		while(player_score < 2 && villain_score < 2) {
			
			int player_choice = m.displayMenu(message, options);
			
			int villain_choice = r.nextInt(options.length);
			
			m.displayMessage(villain_name + " chose " + options[villain_choice]);
			
			if(player_choice == villain_choice) {
				m.displayMessage("Both opponents chose " + options[player_choice] + ". Draw!");
			} else if(player_choice == villain_choice - 1 || (player_choice == 2 && villain_choice == 0)) {
				m.displayMessage(playing_hero.getName() + " loses this round");
				villain_score++;
			} else {
				m.displayMessage(playing_hero.getName() + " wins this round");
				player_score++;
			}
			
			m.displayMessage("Current scores are:\n" + playing_hero.getName() + "'s score: " + player_score + "\n" + villain_name +
					"'s score: " + villain_score);

		}
		
		int ran_chance = r.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		if(player_score == 2) {
			m.displayMessage("Congratualtions. " + playing_hero.getName() + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + playing_hero.getName() + " lost this game");
			if (ran_chance >= win_chance) {
				m.displayMessage("But " + playing_hero.getName() + " used illusion skill to trick " + villain_name + " into winning the game");
				return false;
			} else {
				return true;
			}
		}
		
	}

	@Override
	public String gameType() {
		
		return "Paper Scissors Rock";
		
	}

}

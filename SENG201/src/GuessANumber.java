import java.util.Random;

import javax.swing.JPanel;

public class GuessANumber implements VillainGame, GUIPanel {

	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	
	MenuSystem m;
	Random num = new Random();
	
	public GuessANumber(MenuSystem m) {

		this.m = m;
		
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		// TODO Auto-generated method stub
		
		String message = "Pick a number";
		
		boolean not_finish = true;
		int num_guesses = 3;
		int player_guess = 0;
		int villian_num = num.nextInt(10);
		int illusion = playing_hero.getIllusion();
		
		m.displayMessage("You are allowed 3 guesses");
		
		String[] options = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		
		while (not_finish && num_guesses != 0) {
			player_guess = m.displayMenu(message, options);
			
			num_guesses--;
			
			if (player_guess == villian_num) {
				m.displayMessage(playing_hero.getName() + " guessed the correct number!");
				not_finish = false;
			} else if (player_guess < villian_num) {
				m.displayMessage("Higher! " + num_guesses + " guesses left");
			} else {
				m.displayMessage("Lower! " + num_guesses + " guesses left");
			}
		}
		
		int ran_chance = num.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		if (not_finish == false) {
			m.displayMessage("Congratualtions. " + playing_hero.getName() + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + playing_hero.getName() + " lost this game");
			if (ran_chance >= win_chance) {
				m.displayMessage("But " + playing_hero.getName() + " useds illusion skill to trick " + villain_name + " into winning the game");
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public String gameType() {
		return "Guess a Number between One and Ten";
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}

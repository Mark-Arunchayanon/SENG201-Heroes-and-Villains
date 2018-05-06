import java.util.Random;

public class GuessANumber implements VillainGame {

	@Override
	public boolean play(String villain_name, String hero_name) {
		// TODO Auto-generated method stub
		
		String message = "Pick a number";
		
		MenuSystem m = new MenuSystem();
		Random num = new Random();
		
		boolean not_finish = true;
		int num_guesses = 3;
		int player_guess = 0;
		int villian_num = num.nextInt(10);
		
		m.displayMessage("You are allowed 3 guesses");
		
		String[] options = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		
		while (not_finish && num_guesses != 0) {
			player_guess = m.displayMenu(message, options);
			
			num_guesses--;
			
			if (player_guess == villian_num) {
				m.displayMessage(hero_name + " guessed the correct number!");
				not_finish = false;
			} else if (player_guess < villian_num) {
				m.displayMessage("Higher! " + num_guesses + " guesses left");
			} else {
				m.displayMessage("Lower! " + num_guesses + " guesses left");
			}
		}
		
		if (not_finish == false) {
			m.displayMessage("Congratualtions. " + hero_name + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + hero_name + " lost this game");
			return true;
		}
	}

	@Override
	public String gameType() {
		return "Guess a Number between Zero and Nine";
	}

}

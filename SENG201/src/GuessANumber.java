import java.util.Random;

public class GuessANumber implements VillainGame {

	@Override
	public boolean play(String villain_name) {
		// TODO Auto-generated method stub
		
		String message = "Pick a number";
		
		
		MenuSystem m = new MenuSystem();
		Random num = new Random();
		
		boolean not_finish = true;
		int num_guesses = 2;
		int player_guess = 0;
		int villian_num = num.nextInt(10);
		
		m.displayMessage("You are allowed 3 guesses");
		
		String[] options = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		
		while (not_finish && num_guesses != 0) {
			player_guess = m.displayMenu(message, options);
			
			m.displayMessage("You guessed " + options[player_guess]);
			
			if (player_guess == villian_num) {
				m.displayMessage("You've guessed the correct number!");
				not_finish = false;
			} else if (player_guess < villian_num) {
				m.displayMessage("Higher! " + num_guesses + " guesses left.");
				num_guesses--;
			} else {
				m.displayMessage("Lower! " + num_guesses + " guesses left.");
				num_guesses--;
			}
		}
		
		return false;
	}

	@Override
	public String gameType() {
		return "Guess a Number between Zero and Nine";
	}

}

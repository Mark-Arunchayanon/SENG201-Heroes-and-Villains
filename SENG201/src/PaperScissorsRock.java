import java.util.Random;

public class PaperScissorsRock implements VillainGame {

	@Override
	public boolean play(String villain_name, String hero_name) {
		
		int player_score = 0, villain_score = 0;
		
		MenuSystem m = new MenuSystem();
		Random r = new Random();
		
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
				m.displayMessage(hero_name + " loses this round");
				villain_score++;
			} else {
				m.displayMessage(hero_name + " wins this round");
				player_score++;
			}
			
			m.displayMessage("Current scores are:\n" + hero_name + "'s score: " + player_score + "\n" + villain_name +
					"'s score: " + villain_score);

		}
		
		if(player_score == 2) {
			m.displayMessage("Congratualtions. " + hero_name + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + hero_name + " lost this game");
			return true;
		}
		
	}

	@Override
	public String gameType() {
		
		return "Paper Scissors Rock";
		
	}

}

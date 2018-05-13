import java.util.Random;

public class PaperScissorsRock implements VillainGame {

	private static final int MAX_VAL = 1000;
	private static final double CHANCE_VAL = 700;
	
	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		int player_score = 0, villain_score = 0;
		double illusion = playing_hero.getIllusion();
		
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
				m.displayMessage(playing_hero + " loses this round");
				villain_score++;
			} else {
				m.displayMessage(playing_hero + " wins this round");
				player_score++;
			}
			
			m.displayMessage("Current scores are:\n" + playing_hero + "'s score: " + player_score + "\n" + villain_name +
					"'s score: " + villain_score);

		}
		
		int ran_chance = r.nextInt(MAX_VAL);
		int win_chance = (int) (CHANCE_VAL / illusion);
		
		if(player_score == 2) {
			m.displayMessage("Congratualtions. " + playing_hero + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + playing_hero + " lost this game");
			if (ran_chance >= win_chance) {
				m.displayMessage("But " + playing_hero + " useds illusion skill to trick " + villain_name + " into winning the game");
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

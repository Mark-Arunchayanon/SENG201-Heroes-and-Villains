import java.util.Random;

public class GuessANumber implements VillainGame {

	@Override
	public boolean play(String villain_name) {
		// TODO Auto-generated method stub
		
		MenuSystem m = new MenuSystem();
		Random num = new Random();
		
		int player_guess = 0;
		int villian_num = num.nextInt(10);
		
		m.displayMessage("You are allowed 3 guesses");
		
		
		return false;
	}

	@Override
	public String gameType() {
		return "Guess a Number between Zero and Nine";
	}

}

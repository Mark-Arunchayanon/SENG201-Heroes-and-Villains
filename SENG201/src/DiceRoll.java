import java.util.Random;
import java.util.Scanner;

public class DiceRoll implements VillainGame {

	@Override
	public boolean play(String villain_name, String hero_name) {
		// TODO Auto-generated method stub
		
		String message = "Press space and enter to roll the dice";
		
		MenuSystem m = new MenuSystem();
		Random num = new Random();
		Scanner scanner = new Scanner(System.in);
		
		int villain_score = 0;
		int player_score = 0;
		int hero_roll = 0;
		int villain_roll = 0;
		
		m.displayMessage("This is a best of three games of roll the dice, roll a higher number to win");
		
		int[] options = {1, 2, 3, 4, 5, 6};
		
		while(player_score < 2 && villain_score < 2) {
			System.out.println(message);
			String player_input = scanner.nextLine();
			
			
			if (player_input == " ") {
				hero_roll = num.nextInt(6);
				villain_roll = num.nextInt(6);
				m.displayMessage(hero_name + " rolled a " + options[hero_roll] + " and " + villain_name + "rolled a " + options[villain_roll]);
				if (hero_roll > villain_roll) {
					player_score++;
					m.displayMessage(hero_name + " rolled a higher number");
				} else if (hero_roll < villain_roll) {
					villain_score++;
					m.displayMessage(villain_name + " rolled a higher number");
				} else if (hero_roll == villain_roll) { 
					m.displayMessage(hero_name + " and " + villain_name + " rolled the same number");
				}
			} else {
				System.out.println(message);
			}
			m.displayMessage("Current scores are");
			m.displayMessage(hero_name + ": " + player_score + "\n" + villain_name + ": " + villain_score);
		}
		
		if(scanner != null) {
		    scanner.close();
		}
		
		if (player_score == 2) {
			m.displayMessage("Congratualtions. " + hero_name + " beat " + villain_name);
			return false;
		} else {
			m.displayMessage("Unfortunately, " + hero_name + " lost this game");
			return true;
		}
		
	}

	@Override
	public String gameType() {
		return ("Dice Roll, roll a higher number");
	}

}

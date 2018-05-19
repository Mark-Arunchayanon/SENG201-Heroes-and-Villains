import java.util.Random;
import java.util.Scanner;

public class DiceRoll implements VillainGame {
	
	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 700;
	
	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		// TODO Auto-generated method stub
		
		String message = "Press 1 and enter to roll the dice";
		
		MenuSystem m = new MenuSystem();
		Random num = new Random();
		Scanner scanner = new Scanner(System.in);
		
		int villain_score = 0;
		int player_score = 0;
		int hero_roll = 0;
		int villain_roll = 0;
		int illusion = playing_hero.getIllusion();
		
		m.displayMessage("This is a best of three games of roll the dice, roll a higher number to win");
		
		int[] options = {1, 2, 3, 4, 5, 6};
		
		while(player_score < 2 && villain_score < 2) {
			System.out.println(message);
			int player_input = scanner.nextInt();
			
			if (player_input == 1) {
				hero_roll = num.nextInt(6);
				villain_roll = num.nextInt(6);
				m.displayMessage(playing_hero.getName() + " rolled a " + options[hero_roll] + " and " + villain_name + "rolled a " + options[villain_roll]);
				if (hero_roll > villain_roll) {
					player_score++;
					m.displayMessage(playing_hero.getName() + " rolled a higher number");
				} else if (hero_roll < villain_roll) {
					villain_score++;
					m.displayMessage(villain_name + " rolled a higher number");
				} else if (hero_roll == villain_roll) { 
					m.displayMessage(playing_hero.getName() + " and " + villain_name + " rolled the same number");
				}
			} else {
				System.out.println("Invalid");
			}
			m.displayMessage("Current scores are");
			m.displayMessage(playing_hero.getName() + ": " + player_score + "\n" + villain_name + ": " + villain_score);
		}
		
		if(scanner != null) {
		    scanner.close();
		}
		
		int ran_chance = num.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		if (player_score == 2) {
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
		return ("Dice Roll, roll a higher number");
	}

}

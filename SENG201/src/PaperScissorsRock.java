import java.util.Random;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;

public class PaperScissorsRock implements VillainGame {

	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	private Random r = new Random();
	private Object synchronizer = new Object();
	private JPanel panel = new JPanel();
	private String label1;
	private String label2;
	public PaperScissorsRock(MenuSystem m) {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.5, 1.0, 2.0, 0.5, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPaperScissorsRock = new JLabel("Paper Scissors Rock");
		lblPaperScissorsRock.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPaperScissorsRock = new GridBagConstraints();
		gbc_lblPaperScissorsRock.gridwidth = 3;
		gbc_lblPaperScissorsRock.insets = new Insets(0, 0, 5, 0);
		gbc_lblPaperScissorsRock.gridx = 0;
		gbc_lblPaperScissorsRock.gridy = 0;
		panel.add(lblPaperScissorsRock, gbc_lblPaperScissorsRock);
		
		JButton button1 = new JButton("New button");
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.gridx = 0;
		gbc_button1.gridy = 2;
		panel.add(button1, gbc_button1);
		
		JButton button2 = new JButton("New button");
		GridBagConstraints gbc_button2 = new GridBagConstraints();
		gbc_button2.insets = new Insets(0, 0, 5, 5);
		gbc_button2.gridx = 1;
		gbc_button2.gridy = 2;
		panel.add(button2, gbc_button2);
		
		JButton button3 = new JButton("New button");
		GridBagConstraints gbc_button3 = new GridBagConstraints();
		gbc_button3.insets = new Insets(0, 0, 5, 0);
		gbc_button3.gridx = 2;
		gbc_button3.gridy = 2;
		panel.add(button3, gbc_button3);
		
		JLabel lblNewLabel = new JLabel("This is a best of three game of Paper Scissors Rock");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);


		
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		int player_score = 0, villain_score = 0;
		int illusion = playing_hero.getIllusion();
		
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

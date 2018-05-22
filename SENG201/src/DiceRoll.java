import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
/**
 * This is a game called Dice Roll, roll a higher number to win
 * Numbers range from 1-6 and it is best out three games
 * 
 * @author fer25
 * @author par116
 */
public class DiceRoll implements VillainGame {
	
	// Define variables to calculate the chances of illusion skill working on villain
	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	
	JPanel panel = new JPanel();
	MenuSystem m;
	Random num = new Random();
	private Object synchronizer = new Object();
	
	
	public DiceRoll(MenuSystem m) {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1};
		gbl_panel.rowWeights = new double[]{1.0, 2.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lab_1 = new JLabel("New");
		lab_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lab_1 = new GridBagConstraints();
		gbc_lab_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lab_1.insets = new Insets(0, 0, 5, 0);
		gbc_lab_1.gridx = 0;
		gbc_lab_1.gridy = 0;
		panel.add(lab_1, gbc_lab_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					//TODO try except
					try {
						int temp_guess = Integer.parseInt(txtType.getText());
						if (temp_guess < 11 && temp_guess > 0) guess = temp_guess;
						synchronizer.notify();
					} catch (NumberFormatException f) {
						// Player is notified to only type integers in the text box
					}
					
					
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		this.m = m;
		
		
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		//  Create new variables
		int villain_score = 0;
		int player_score = 0;
		int hero_roll = 0;
		int villain_roll = 0;
		int illusion = playing_hero.getIllusion();
		
		m.displayMessage("This is a best of three games of roll the dice, roll a higher number to win");
		
		int[] options = {1, 2, 3, 4, 5, 6};
		
		// Runs the game until one of the player's score reaches 2. Press 1 and enter to roll, other input will not be accepted.
		while(player_score < 2 && villain_score < 2) {
			
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
			
			m.displayMessage("Current scores are");
			m.displayMessage(playing_hero.getName() + ": " + player_score + "\n" + villain_name + ": " + villain_score);
		}
		
		
		int ran_chance = num.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		// Announce winner or loser. If Hero was defeated, the illusion skill may work and change the defeat into a win
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


	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

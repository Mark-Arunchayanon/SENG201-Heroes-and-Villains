import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
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
	
	// Create new class objects
	private JPanel panel = new JPanel();
	private Random num = new Random();
	private Object synchronizer = new Object();
	// Create labels for panel
	private JLabel lab_1;
	private JLabel lab_2;
	private JLabel lab_3;
	private JLabel lab_4;
	// Creating string variables for labels
	private String lab2;
	private String lab3;
	private String lab4;
	
	private MenuSystem m;
	
	public DiceRoll(MenuSystem m) {
		this.m = m;
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		// Calls a method to setup GUI panel
		DisplayGame();
		
		//  Create new variables
		int villain_score = 0;
		int player_score = 0;
		int hero_roll = 0;
		int villain_roll = 0;
		int illusion = playing_hero.getIllusion();
		
		int[] options = {1, 2, 3, 4, 5, 6};
		
		// Runs the game until one of the player's score reaches 2. Press 1 and enter to roll, other input will not be accepted.
		while(player_score < 2 && villain_score < 2) {
			// Waits for button to be pressed to continue the loop
			synchronized(synchronizer) {
				try {
					synchronizer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Random number for hero and villain between 1-6
			hero_roll = num.nextInt(6);
			villain_roll = num.nextInt(6);
			
			// Sets a message to labels on what the hero and villain rolled
			lab2 = playing_hero.getName() + " rolled a " + options[hero_roll] + " and " + villain_name + " rolled a " + options[villain_roll];
			if (hero_roll > villain_roll) {
				player_score++;
				lab3 = playing_hero.getName() + " rolled a higher number";
			} else if (hero_roll < villain_roll) {
				villain_score++;
				lab3 = villain_name + " rolled a higher number";
			} else if (hero_roll == villain_roll) { 
				lab3 = playing_hero.getName() + " and " + villain_name + " rolled the same number";
			}
			
			lab4 = "Current scores are " + playing_hero.getName() + ": " + player_score + ", " + villain_name + ": " + villain_score;
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					lab_2.setText(lab2);
					lab_3.setText(lab3);
					lab_4.setText(lab4);
				}
			});
		}
		
		// Calculating the chance of illusion working
		int ran_chance = num.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		// Announce winner or loser. If Hero was defeated, the illusion skill may work and change the defeat into a win
		if (player_score == 2) {
			lab2 = "Congratualtions. " + playing_hero.getName() + " beat " + villain_name;
		} else {
			lab2 = "Unfortunately, " + playing_hero.getName() + " lost this game";
			if (ran_chance >= win_chance) {
				lab3 = "But " + playing_hero.getName() + " useds illusion skill to trick " + villain_name + " into winning the game";
			}
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				lab_2.setText(lab2);
				lab_3.setText(lab3);
			}
		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Returns false if the hero wins and true if villain wins	
		if (player_score == 2) {
			return false;
		} else {
			if (ran_chance >= win_chance) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	 * Creates a panel with the components and actions needed for the game and updates the panel
	 * Resets the panel this method is called
	 */
	private void DisplayGame() {
		
		panel.removeAll();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1};
		gbl_panel.rowWeights = new double[]{0.5, 1.0, 1.0, 0.5, 0.5, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lab_1 = new JLabel("Roll the Dice");
		lab_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lab_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lab_1 = new GridBagConstraints();
		gbc_lab_1.fill = GridBagConstraints.BOTH;
		gbc_lab_1.insets = new Insets(0, 0, 5, 0);
		gbc_lab_1.gridx = 0;
		gbc_lab_1.gridy = 0;
		panel.add(lab_1, gbc_lab_1);
		
		lab_2 = new JLabel("");
		lab_2.setVerticalAlignment(SwingConstants.CENTER);
		lab_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lab_2 = new GridBagConstraints();
		gbc_lab_2.fill = GridBagConstraints.BOTH;
		gbc_lab_2.insets = new Insets(0, 0, 5, 0);
		gbc_lab_2.gridx = 0;
		gbc_lab_2.gridy = 1;
		panel.add(lab_2, gbc_lab_2);
		
		JButton btnNewButton = new JButton("Click to roll the dice");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					//TODO try except
					synchronizer.notify();
				}
			}
		});
		
		lab_3 = new JLabel("");
		GridBagConstraints gbc_lab_3 = new GridBagConstraints();
		gbc_lab_3.insets = new Insets(0, 0, 5, 0);
		gbc_lab_3.gridx = 0;
		gbc_lab_3.gridy = 2;
		panel.add(lab_3, gbc_lab_3);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		lab_4 = new JLabel("This is a best of three games of roll the dice, roll a higher number to win");
		GridBagConstraints gbc_lab_4 = new GridBagConstraints();
		gbc_lab_4.gridx = 0;
		gbc_lab_4.gridy = 4;
		panel.add(lab_4, gbc_lab_4);
		
		m.updatePanel(panel);
	}

	@Override
	public String gameType() {
		return ("Dice Roll, roll a higher number");
	}

}

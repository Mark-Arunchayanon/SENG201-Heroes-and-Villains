package locations;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.MenuSystem;
import team.Hero;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
/**
 * This is a game called Paper Scissors Rock, rock beats scissors,
 * scissors beats paper and paper beats rock
 * 3 Options to choose from, paper scissors or rock
 * 
 * @author fer25
 * @author par116
 */
public class PaperScissorsRock extends VillainGame {
	
	// Variables to calculate the chance of illusion skill working
	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	// Create new class objects
	private Object synchronizer = new Object();
	private JPanel panel = new JPanel();
	// Creating string variables for labels
	private String label2;
	private String label3;
	private String label4;
	// Creating labels for GUI panel
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	
	private int player_choice;
	
	/**
	 * store the MenuSystem and Random locally on object construction
	 * @param m MenuSystem containing GUI
	 * @param r pseudo random number generator
	 */
	public PaperScissorsRock(MenuSystem m, Random r) {
		super(m,r);
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		// Calls a method to setup GUI panel
		DisplayGame();
		// Variables for hero and villain score, and hero illusion skill
		int player_score = 0, villain_score = 0;
		int illusion = playing_hero.getIllusion();
		
		// List of the options to choose from
		String[] options = {"Paper", "Scissors", "Rock"};
		
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
			
			// Sets the labels to display what the hero and villain chose
			int villain_choice = r.nextInt(options.length);
			label2 = villain_name + " chose " + options[villain_choice] + ".";
			
			if(player_choice == villain_choice) {
				label3 = "Both opponents chose " + options[player_choice] + ". Draw!";
			} else if(player_choice == villain_choice - 1 || (player_choice == 2 && villain_choice == 0)) {
				label3 = playing_hero.getName() + " loses this round";
				villain_score++;
			} else {
				label3 = playing_hero.getName() + " wins this round";
				player_score++;
			}
			
			label4 = "Current scores are:\n" + playing_hero.getName() + "'s score: " + "  " + player_score + "\n" + villain_name +
					"'s score: " + villain_score;

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					label_2.setText(label2);
					label_3.setText(label3);
					label_4.setText(label4);
					label3 = "";
				}
			});
		}
		// Calculating the chance of illusion skill working
		int ran_chance = r.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		boolean return_val;
		
		// Sets the label to a message on who lost and who won
		if(player_score == 2) {
			label2 += " Congratualtions! " + playing_hero.getName() + " beat " + villain_name;
			return_val = false;
		} else {
			label2 += " Unfortunately, " + playing_hero.getName() + " lost this game";
			return_val = true;
			if (ran_chance >= win_chance) {
				label3 = "However, " + playing_hero.getName() + " used ther Illusion skill to trick " + villain_name + " and won regardless";
				return_val = false;
			}
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label_2.setText(label2);
				label_3.setText(label3);
			}
		});
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Returns false if hero wins, returns true if villain wins
		return return_val;
		
	}
	
	/**
	 * Creates a panel with the components and actions needed for the game and updates the panel
	 * Resets the panel this method is called
	 */
	private void DisplayGame() {
		
		panel.removeAll();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.5, 1.0, 1.0, 2.0, 0.5, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_1 = new JLabel("Paper Scissors Rock");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 36));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 3;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		
		label_2 = new JLabel("");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 3;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel.add(label_2, gbc_label_2);
		
		label_3 = new JLabel("Choose one of the options!");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 3;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 2;
		panel.add(label_3, gbc_label_3);
		
		JButton button1 = new JButton("Paper");
		button1.setFont(new Font("Tahoma", Font.BOLD, 24));
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					player_choice = 0;
					synchronizer.notify();
				}
			}
		});
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.gridx = 0;
		gbc_button1.gridy = 3;
		panel.add(button1, gbc_button1);
		
		JButton button2 = new JButton("Scissors");
		button2.setFont(new Font("Tahoma", Font.BOLD, 24));
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					//TODO try except
					player_choice = 1;
					synchronizer.notify();
				}
			}
		});
		GridBagConstraints gbc_button2 = new GridBagConstraints();
		gbc_button2.insets = new Insets(0, 0, 5, 5);
		gbc_button2.gridx = 1;
		gbc_button2.gridy = 3;
		panel.add(button2, gbc_button2);
		
		JButton button3 = new JButton("Rock");
		button3.setFont(new Font("Tahoma", Font.BOLD, 24));
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					//TODO try except
					player_choice = 2;
					synchronizer.notify();
				}
			}
		});
		GridBagConstraints gbc_button3 = new GridBagConstraints();
		gbc_button3.insets = new Insets(0, 0, 5, 0);
		gbc_button3.gridx = 2;
		gbc_button3.gridy = 3;
		panel.add(button3, gbc_button3);
		
		label_4 = new JLabel("This is a best of three game of Paper Scissors Rock");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.gridwidth = 3;
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		panel.add(label_4, gbc_label_4);
		
		m.updatePanel(panel);
	}

	@Override
	public String gameType() {
		
		return "Paper Scissors Rock";
		
	}

}

package locations;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import gui.MenuSystem;
import team.Hero;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
/**
 * This is a game called Guess a Number, guess the correct number to win
 * Numbers range from 1-10 and it is best out three games
 * 
 * @author fer25
 * @author par116
 */
public class GuessANumber extends VillainGame {
	
	// Variables to calculate the chance of illusion skill working	
	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	// Create new class objects
	private JPanel panel = new JPanel();
	
	private int villain_num;
	private int guess;
	
	private JLabel label_1;
	private JLabel label_2;
	private String label1;
	private String label2 = "";
	
	private Object synchronizer = new Object();
	
	
	/**
	 * store the MenuSystem locally on the object construction
	 * @param m
	 */
	public GuessANumber(MenuSystem m, Random r) {
		super(m,r);	
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		
		villain_num = r.nextInt(10);
		
		// Calls a method to setup GUI panel
		DisplayGame();
		
		boolean not_finish = true;
		int num_guesses = 3;
		int illusion = playing_hero.getIllusion();
		
		while (not_finish && num_guesses != 0) {
			// Waits for button to be pressed to continue the loop
			synchronized(synchronizer) {
				try {
					synchronizer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			num_guesses--;
			// Displays whether the number was correct or that the number is lower or higher
			if (guess == villain_num) {
				label1 = playing_hero.getName() + " guessed the correct number!";
				not_finish = false;
			} else if (guess < villain_num) {
				label1 = "Higher! " + num_guesses + " guesses left";
			} else {
				label1 = "Lower! " + num_guesses + " guesses left";
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					label_1.setText(label1);
				}
			});
		}
		// Calculating the chance of illusion working
		int ran_chance = r.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		//Clear label2
		label2 = "";
		// Sets labels to display the winner and loser
		if (not_finish == false) {
			label1 += " Congratualtions! " + playing_hero.getName() + " beat " + villain_name;
		} else {
			label1 = "<html>The correct number was " + villain_num + ".<br>Unfortunately, " + playing_hero.getName() + " lost this game</html>";
			if (ran_chance >= win_chance) {
				label2 = "However, " + playing_hero.getName() + " used their Illusion skill to trick " + villain_name + " and won regardless";
			}
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label_2.setText(label2);
				label_1.setText(label1);
			}
		});
		
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// Interruption should be impossible
			e.printStackTrace();
		}
		// Returns false if hero wins and false if villain wins
		if (not_finish == false) {
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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.5, 1.0, 1.0, 1.0, 1.0, 0.5};
		panel.setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Guess A Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 5;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 5;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel.add(label_2, gbc_label_2);
		
		JButton button1 = new JButton("1");
		button1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 1;
					synchronizer.notify();
				}
			}
		});
		gbc_button1.fill = GridBagConstraints.BOTH;
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.gridx = 0;
		gbc_button1.gridy = 3;
		panel.add(button1, gbc_button1);
		
		JButton button2 = new JButton("2");
		button2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button2 = new GridBagConstraints();
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 2;
					synchronizer.notify();
				}
			}
		});
		gbc_button2.fill = GridBagConstraints.BOTH;
		gbc_button2.insets = new Insets(0, 0, 5, 5);
		gbc_button2.gridx = 1;
		gbc_button2.gridy = 3;
		panel.add(button2, gbc_button2);
		
		JButton button3 = new JButton("3");
		button3.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button3 = new GridBagConstraints();
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 3;
					synchronizer.notify();
				}
			}
		});
		gbc_button3.fill = GridBagConstraints.BOTH;
		gbc_button3.insets = new Insets(0, 0, 5, 5);
		gbc_button3.gridx = 2;
		gbc_button3.gridy = 3;
		panel.add(button3, gbc_button3);
		
		JButton button4 = new JButton("4");
		button4.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button4 = new GridBagConstraints();
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 4;
					synchronizer.notify();
				}
			}
		});
		gbc_button4.fill = GridBagConstraints.BOTH;
		gbc_button4.insets = new Insets(0, 0, 5, 5);
		gbc_button4.gridx = 3;
		gbc_button4.gridy = 3;
		panel.add(button4, gbc_button4);
		
		JButton button5 = new JButton("5");
		button5.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button5 = new GridBagConstraints();
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 5;
					synchronizer.notify();
				}
			}
		});
		gbc_button5.fill = GridBagConstraints.BOTH;
		gbc_button5.insets = new Insets(0, 0, 5, 0);
		gbc_button5.gridx = 4;
		gbc_button5.gridy = 3;
		panel.add(button5, gbc_button5);
		
		JButton button6 = new JButton("6");
		button6.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button6 = new GridBagConstraints();
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 6;
					synchronizer.notify();
				}
			}
		});
		gbc_button6.fill = GridBagConstraints.BOTH;
		gbc_button6.insets = new Insets(0, 0, 5, 5);
		gbc_button6.gridx = 0;
		gbc_button6.gridy = 4;
		panel.add(button6, gbc_button6);
		
		JButton button7 = new JButton("7");
		button7.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button7 = new GridBagConstraints();
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 7;
					synchronizer.notify();
				}
			}
		});
		gbc_button7.fill = GridBagConstraints.BOTH;
		gbc_button7.insets = new Insets(0, 0, 5, 5);
		gbc_button7.gridx = 1;
		gbc_button7.gridy = 4;
		panel.add(button7, gbc_button7);
		
		JButton button8 = new JButton("8");
		button8.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button8 = new GridBagConstraints();
		button8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 8;
					synchronizer.notify();
				}
			}
		});
		gbc_button8.fill = GridBagConstraints.BOTH;
		gbc_button8.insets = new Insets(0, 0, 5, 5);
		gbc_button8.gridx = 2;
		gbc_button8.gridy = 4;
		panel.add(button8, gbc_button8);
		
		JButton button9 = new JButton("9");
		button9.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button9 = new GridBagConstraints();
		button9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 9;
					synchronizer.notify();
				}
			}
		});
		gbc_button9.fill = GridBagConstraints.BOTH;
		gbc_button9.insets = new Insets(0, 0, 5, 5);
		gbc_button9.gridx = 3;
		gbc_button9.gridy = 4;
		panel.add(button9, gbc_button9);
		
		JButton button10 = new JButton("10");
		button10.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_button10 = new GridBagConstraints();
		button10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(synchronizer) {
					guess = 10;
					synchronizer.notify();
				}
			}
		});
		gbc_button10.fill = GridBagConstraints.BOTH;
		gbc_button10.insets = new Insets(0, 0, 5, 0);
		gbc_button10.gridx = 4;
		gbc_button10.gridy = 4;
		panel.add(button10, gbc_button10);
		
		JLabel label_3 = new JLabel("Pick a number from the above");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 5;
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 5;
		panel.add(label_3, gbc_label_3);
		
		m.updatePanel(panel);
		
	}

	@Override
	public String gameType() {
		return "Guess a Number between One and Ten";
	}
}

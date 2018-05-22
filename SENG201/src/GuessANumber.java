import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Font;

public class GuessANumber implements VillainGame {
	JPanel panel = new JPanel();
		
		MenuSystem m;
		Random num = new Random();
		
	private static final int MAX_VAL = 1000;
	private static final int CHANCE_VAL = 70000;
	private final int VILLAIN_NUM = num.nextInt(10);
	private int guess = -1;
	JLabel label_1;
	JLabel label_2;
	private String label1;
	private String label2 = "";
	
	private Object synchronizer = new Object();
	
	private JTextField txtType;
	
	public GuessANumber(MenuSystem m) {
		panel.setLayout(null);
		
		txtType = new JTextField();
		txtType.setBounds(0, 58, 450, 66);
		txtType.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtType);
		txtType.setColumns(10);
		
		JButton btnNewButton = new JButton("Click to guess");
		btnNewButton.setBounds(105, 161, 240, 66);
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
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Enter your number and click the button above to guess the number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(0, 254, 450, 46);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 13, 450, 32);
		panel.add(label_1);
		
		JLabel lblNewLabel_2 = new JLabel("You are allowed 3 guesses");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(140, 237, 170, 32);
		panel.add(lblNewLabel_2);
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 124, 450, 32);
		panel.add(label_2);

		this.m = m;
		
	}

	@Override
	public boolean play(String villain_name, Hero playing_hero) {
		// TODO Auto-generated method stub
		
		boolean not_finish = true;
		int num_guesses = 3;
		int villian_num = VILLAIN_NUM;
		int illusion = playing_hero.getIllusion();
		
		
		//m.displayMessage("You are allowed 3 guesses");
		
		//String[] options = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		
		while (not_finish && num_guesses != 0) {
			
			synchronized(synchronizer) {
				try {
					synchronizer.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			num_guesses--;
			
			if (guess == villian_num) {
				label1 = playing_hero.getName() + " guessed the correct number!";
				not_finish = false;
			} else if (guess < villian_num) {
				label1 = "Higher! " + num_guesses + " guesses left";
			} else {
				label1 = "Lower! " + num_guesses + " guesses left";
			}
			guess = -1;
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					label_1.setText(label1);
				}
			});
		}
		
		int ran_chance = num.nextInt(MAX_VAL);
		int win_chance = CHANCE_VAL / illusion;
		
		if (not_finish == false) {
			label1 = "Congratualtions. " + playing_hero.getName() + " beat " + villain_name;
			return false;
		} else {
			label1 = "Unfortunately, " + playing_hero.getName() + " lost this game";
			if (ran_chance >= win_chance) {
				label2 = "But " + playing_hero.getName() + " useds illusion skill to trick " + villain_name + " into winning the game";
			}
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label_2.setText(label2);
				label_1.setText(label1);
			}
		});
		
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

	@Override
	public String gameType() {
		return "Guess a Number between One and Ten";
	}
}

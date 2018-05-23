import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StringGetPanel extends JPanel {

	private String user_text;
	private Object synchronizer = new Object();
	
	private JTextPane txtpndescription;
	private JTextPane textPane;
	
	public StringGetPanel(String title) {
		this(title, "");
	}
	
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public StringGetPanel(String title, String body) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnTitle.setEditable(false);
		txtpnTitle.setText(title);
		GridBagConstraints gbc_txtpnTitle = new GridBagConstraints();
		gbc_txtpnTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnTitle.gridx = 0;
		gbc_txtpnTitle.gridy = 0;
		add(txtpnTitle, gbc_txtpnTitle);
		
		txtpndescription = new JTextPane();
		txtpndescription.setEditable(false);
		txtpndescription.setText(body);
		GridBagConstraints gbc_txtpndescription = new GridBagConstraints();
		gbc_txtpndescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtpndescription.fill = GridBagConstraints.BOTH;
		gbc_txtpndescription.gridx = 0;
		gbc_txtpndescription.gridy = 1;
		add(txtpndescription, gbc_txtpndescription);
		
		textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 2;
		add(textPane, gbc_textPane);
		
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(synchronizer) {
					user_text = textPane.getText();
					synchronizer.notify();
				}
			}
		});
		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.gridx = 0;
		gbc_btn.gridy = 3;
		add(btn, gbc_btn);		

	}
	
	public void bodyTextSet(String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtpndescription.setText(text);
			}
		});
	}
	
	public String getUserString() {
		
		synchronized(synchronizer) {
			try {
				synchronizer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textPane.setText("");
			}
		});
		
		return user_text;
	}

}

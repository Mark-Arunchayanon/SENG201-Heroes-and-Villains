package gui;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StringGetPanel extends JPanel {

	private String user_text;
	private Object synchronizer = new Object();
	
	private JLabel txtpndescription;
	private JTextField textPane;
	
	public StringGetPanel(String title) {
		this(title, "");
	}
	
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public StringGetPanel(String title, String body) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {10};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.5, 1.0, 1.0, 0.5};
		setLayout(gridBagLayout);
		
		JLabel txtpnTitle = new JLabel();
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		txtpnTitle.setFocusable(false);
		txtpnTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtpnTitle.setText(title);
		GridBagConstraints gbc_txtpnTitle = new GridBagConstraints();
		gbc_txtpnTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnTitle.gridx = 0;
		gbc_txtpnTitle.gridy = 0;
		add(txtpnTitle, gbc_txtpnTitle);
		
		txtpndescription = new JLabel();
		txtpndescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtpndescription.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtpndescription.setFocusable(false);
		txtpndescription.setText(body);
		GridBagConstraints gbc_txtpndescription = new GridBagConstraints();
		gbc_txtpndescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtpndescription.fill = GridBagConstraints.BOTH;
		gbc_txtpndescription.gridx = 0;
		gbc_txtpndescription.gridy = 1;
		add(txtpndescription, gbc_txtpndescription);
		
		textPane = new JTextField();
		textPane.setHorizontalAlignment(SwingConstants.CENTER);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane.setColumns(10);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		//gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 2;
		add(textPane, gbc_textPane);
		
		JButton btn = new JButton("Submit");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 24));
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

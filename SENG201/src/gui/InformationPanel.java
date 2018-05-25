package gui;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * Replaces the current panel with a panel that displays some information and a button.
 * Click the button to return to the previous panel
 * 
 * @author par116
 * @author fer25
 */
@SuppressWarnings("serial")
public class InformationPanel extends JPanel {
	
	private Object synchronizer = new Object();
	/**
	 * Constructing the panel with a title and a message
	 * 
	 * @param Title String title info
	 * @param Body String description or statement
	 */
	public InformationPanel(String Title, String Body) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTextPane TitlePane = new JTextPane();
		TitlePane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TitlePane.setEditable(false);
		TitlePane.setText(Title);
		GridBagConstraints gbc_txtpnTitle = new GridBagConstraints();
		gbc_txtpnTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnTitle.gridx = 0;
		gbc_txtpnTitle.gridy = 0;
		add(TitlePane, gbc_txtpnTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JTextPane BodyPane = new JTextPane();
		BodyPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BodyPane.setEditable(false);
		scrollPane.setViewportView(BodyPane);
		BodyPane.setText(Body);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				synchronized (synchronizer) {
					synchronizer.notify();
				}				
			}
		});
		GridBagConstraints OKButton = new GridBagConstraints();
		OKButton.gridx = 0;
		OKButton.gridy = 2;
		add(btnOK, OKButton);
		
	}
	
	/**
	 * Blocks the program until the button gets clicked
	 */
	public void blockTillOK() {
		// Waits till button is clicked
		synchronized(synchronizer) {
			try {
				synchronizer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return;
		
	}

}

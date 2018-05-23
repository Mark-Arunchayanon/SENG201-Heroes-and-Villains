import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Replaces the current panel with a panel that displays some information and a button.
 * Click the button to return to the previous panel
 * 
 * @author par116
 * @author fer25
 */
public class InformationPanel extends JPanel {
	
	private Object synchronizer = new Object();
	/**
	 * Constructing the panel with a title and a message
	 * 
	 * @param Title
	 * @param Body
	 */
	public InformationPanel(String Title, String Body) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setText(Title);
		GridBagConstraints gbc_txtpnTitle = new GridBagConstraints();
		gbc_txtpnTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnTitle.gridx = 0;
		gbc_txtpnTitle.gridy = 0;
		add(txtpnTitle, gbc_txtpnTitle);
		
		JTextPane txtpnBody = new JTextPane();
		txtpnBody.setText(Body);
		GridBagConstraints gbc_txtpnBody = new GridBagConstraints();
		gbc_txtpnBody.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnBody.fill = GridBagConstraints.BOTH;
		gbc_txtpnBody.gridx = 0;
		gbc_txtpnBody.gridy = 1;
		add(txtpnBody, gbc_txtpnBody);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				synchronized (synchronizer) {
					synchronizer.notify();
				}				
			}
		});
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.gridx = 0;
		gbc_btnOK.gridy = 2;
		add(btnOK, gbc_btnOK);
		
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

package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Provides functionality for a simple number based menu system
 * 
 * @author fer25
 * @author par116
 */
public class MenuSystem {
	//
	private static final JFrame frame = new JFrame("Heroes Game");
	
	public MenuSystem() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				frame.setSize(800, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
			
		});
	}
	
	/**
	 * Updates the current panel with panel from the parameter
	 * @param panel JPanel new panel to update
	 */
	public void updatePanel(JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Remove current GUI content
				frame.getContentPane().removeAll();
				// Place new GUI content
				frame.getContentPane().add(panel);
				// Recalculates the layout of the components on the panel
				frame.revalidate();
				// Removes artifacts from previous GUI
				frame.repaint();
			}			
		});
		
	}
	

	/**
	 * Returns the frame that MenuSystem uses for the GUI
	 * @return The GUI frame
	 */
	public JFrame getFrame() {
		return frame;
	}

}

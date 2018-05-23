import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Provides functionality for a simple number based menu system
 * 
 * @author fer25
 *
 */
public class MenuSystem {
	
	private static final JFrame frame = new JFrame("Heros Game");
	
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
	 * @param panel
	 */
	public void updatePanel(JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(panel);
				frame.revalidate();
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

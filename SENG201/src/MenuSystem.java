import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides functionality for a simple number based menu system
 * 
 * @author fer25
 *
 */
public class MenuSystem {
	
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Displays a simple number based menu as below
	 * 
	 * <message>
	 * Please select an option by entering a number:
	 * 
	 * 1 - <option 1>
	 * 2 - <option 2>
	 * 		:
	 * n - <option n>
	 * 
	 * The MenuSystem will block until a valid option is selected
	 * 
	 * @param message
	 * The heading message to be displayed above the menu
	 * @param options
	 * A list of Strings to be displayed as options
	 * @return
	 * The integer corresponding to the option the user selected
	 */
	public int displayMenu(String message, String[] options) {
		
		boolean valid_result = false;
		
		int selected = -1;
		
		while(!valid_result) {
			
			System.out.println(message);		
			System.out.println("Please select an option by entering a number:\n");
		
			for(int i = 1; i <= options.length; i++) {
				
				System.out.println(i + " - " + options[i - 1]);
				
			}
			
			try {
				
				selected = scanner.nextInt();
				
			} catch(InputMismatchException e) {
				
				scanner.next();
				
			}
			
			//Adjust so selected number matches index
			selected -= 1;
			
			if(selected >= 0 && selected < options.length) {
				
				valid_result = true;
				
			} else {
				
				System.out.println("\n\n\n\nPlease type an integer that corresponds to one of the given options\n");
				
			}
			
		}
		
		System.out.println("You selected: " + options[selected] + "\n\n");
		
		return selected;
		
	}
	
	/**
	 * Displays a message to the Player
	 * @param message The message to be displayed
	 */
	public void displayMessage(String message) {
		
		System.out.println(message + "\n");
		
	}

	/**
	 * Displays provided message and a prompt for the user to enter a String.
	 * This String is then returned as input
	 * @param message
	 * @return The String entered by the User
	 */
	public String displayStringRequest(String message) {
		
		System.out.println(message);
		System.out.println("Please enter your desired string");
		
		String input = scanner.next();
		
		return input;
	}

}

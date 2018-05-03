import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides functionality for a simple number based menu system
 * 
 * @author frase
 *
 */
public class MenuSystem {
	
	Scanner scanner = new Scanner(System.in);
	
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
	int displayMenu(String message, ArrayList<String> options) {
		
		boolean valid_result = false;
		
		int selected = -1;
		
		while(!valid_result) {
			
			System.out.println(message);		
			System.out.println("Please select an option by entering a number:\n");
		
			for(int i = 0; i < options.size(); i++) {
				
				System.out.println(i + " - " + options.get(i));
				
			}
			
			try {
				
				selected = scanner.nextInt();
				
			} catch(InputMismatchException e) {
				
				scanner.next();
				
			}
			
			if(selected >= 0 && selected < options.size()) {
				
				valid_result = true;
				
			} else {
				
				System.out.println("\n\n\n\nPlease type an integer that corresponds to one of the given options\n");
				
			}
			
		}
		
		System.out.println("You selected: " + options.get(selected));
		
		return selected;
		
	}
	
	public void displayMessage(String message) {
		
		System.out.println(message + "\n");
		
	}


}

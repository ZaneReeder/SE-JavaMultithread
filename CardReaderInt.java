import java.util.Scanner;

public class CardReaderInt {
	
	public CardReaderInt() {
		
	}
	
	static Scanner userInput = new Scanner(System.in);
	
	
	public String readCard() {
		String cardNum = "Error";
		String line = null;
		System.out.println("\n---Card Reader Interface---");
		System.out.println("Reading Card Number...");
		System.out.println("Please Enter Card 16-Digit Card Number (0123456789123456): ");
		while (line == null) {
			line = userInput.nextLine();
			try {
				cardNum = line;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Expected numerical value, not " + line + ", please try again.");
				line = null;
				System.out.println("To Check Out Customer, Enter 3 digit Room Number: ");
			}
		}
		
		return cardNum;
	}
	
}
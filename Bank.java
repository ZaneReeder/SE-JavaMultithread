import java.util.Scanner;

public class Bank {
	public static int authorizationNum = 1234;
	
	public Bank() {
		
	}
	static Scanner userInput = new Scanner(System.in);
	
	public static long chargeCreditCard(String cardNum, double total) {
		long referenceNum = 0;
		System.out.println("\n---Bank Proxy Interface---");
		System.out.println("Card Number: " + cardNum);
		System.out.println("Total: " + Double.toString(total));
		System.out.println("To authorize purchase, enter Authorization Number (1234). ");
		if (userInput.hasNextInt()) {
			int inputAuth = userInput.nextInt();
			if (inputAuth == authorizationNum) {
				referenceNum = (long)(Math.random() * 1000000);
				System.out.println("\nTransaction Reference Number: " + Long.toString(referenceNum));
			} else {
				System.out.println("Authorization Denied");
			}
		}
		return referenceNum;
	}
	
	
}

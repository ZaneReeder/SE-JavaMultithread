import java.util.Scanner;

public class DeskClerkInt implements Runnable {
	
	Buffer buffer;
	
	public DeskClerkInt(Buffer buff) {
		buffer = buff;
	}
	
	public void run() {
		Bill bill = checkOut();
		displayBill(bill);
		char c = promptChargeType();
		if (c == 'a') {
			System.out.println("This part of System is out of Scope of Assignment");
		} else if (c == 'c') {
			Message msg = new Message("payByCash", bill, null, 000, 0.0);
			buffer.send(msg);			
		}
		
		while(true) {
			evalReply(this.buffer.getReply());
			
		}
		
	}
	
	public void evalReply(Message rply) {
		switch (rply.functionCall) {
		case "inputCash" :
			double amt = inputCash();
			Message msg = new Message(null, null, null, 000, amt);
			this.buffer.send(msg);
			break;
		case "displayChange":
			double change = rply.dblParam;
			displayChangeAmount(change);
			break;
		}
	}
	
	
	public Bill checkOut() {
		Scanner userInput = new Scanner(System.in);
		String line = null;
		Bill bill = null;
		int room = 000;
		System.out.println("\n---Desk Clerk Interface---\n");
		do {
			System.out.println("To Check Out Customer, Enter 3 digit Room Number: ");
			if (userInput.hasNextLine()) {
				line = userInput.nextLine();
				try {
					room = Integer.valueOf(line);
				} catch (NumberFormatException e) {
					System.out.println("Expected numerical value, not " + line + ", please try again.");
					line = null;
				}
				//public Message(String func, Bill billP, String stringP, int intP)
				Message msg = new Message("requestBill", null, null, room, 0.0);
				this.buffer.send(msg); //sends room number as string for consistent type
				bill = this.buffer.getReply().billParam;
				if (bill == null) {
					System.out.println("Could not find Bill with Room Number " + Integer.toString(room));
				}
			}
		} while (bill == null);
		
		return bill; 
	}
	
	public char promptChargeType() {
		Scanner userInput = new Scanner(System.in);
		char command ='z';
		System.out.println("\n---Desk Clerk Interface---\n");
		do {
			System.out.println("To Charge By Credit Card Enter A");
			System.out.println("To Charge By Cash Enter C");
			
			if (userInput.hasNextLine()) {
				command = userInput.nextLine().charAt(0);
				if (command == 'c' || command == 'C') {
					command = 'c';
				}
				else if (command == 'A' || command == 'a') {
					command = 'a';
				}
				else { 
					command = 'z';
				}
			}
		}
		while (command == 'z');		
		return command;
	}
	
	public double inputCash() {
		Scanner userInput = new Scanner(System.in);
		
		double amount = 0.0;
		System.out.println("\n---Desk Clerk Interface---\n");
		System.out.print("Enter Cash Amount: ");
		if (userInput.hasNextLine()) {
			amount = userInput.nextDouble();
		}
		
		return amount;
	}
	
	
	public void displayChangeAmount(double change) {
		System.out.println("\n---Desk Clerk Interface---\n");
		System.out.print("Change: " + Double.toString(change) + "\n");
	}
	
	public String createBillStr(Bill bill) {
		return "---Room:" + Integer.toString(bill.roomNum) + "---\n\n" + "Name: " + bill.name + "\n" + "Total: " + Double.toString(bill.total) + "\n";
	}
	
	public void displayBill(Bill bill) {
		System.out.println("\n---Desk Clerk Interface---");
		System.out.println(createBillStr(bill));
	}
	
	public void displayBillPrinted() {
		System.out.println("\n---Desk Clerk Interface---\n");
		System.out.println("Bill Print Confirmed");
	}
	
	public void displayCreditCardDenied() {
		System.out.println("\n---Desk Clerk Interface---\n");
		System.out.println("Credit Card Denied");
	}	
	
	public void displayRoomReleased(int num) {
		System.out.println("\n---Desk Clerk Interface---\n");
		System.out.println("Room " + Integer.toString(num) + " was released");
	}
}

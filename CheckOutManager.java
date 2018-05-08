import org.apache.commons.lang3.ArrayUtils;

public class CheckOutManager implements Runnable {
	//Initialize Objects of System by example
	Bill bill1 = new Bill ("John", 100.00, 301);
	Bill bill2 = new Bill ("Sam", 200.00, 303);
	
	Room room1 = new Room(301, 1);
	Room room2 = new Room(302, 0);
	Room room3 = new Room(303, 1);
	
	Customer cust1 = new Customer(301, "John Smith", "806-333-1234");
	Customer cust2 = new Customer(303, "Sam Dunder", "806-333-4567");
	
	public Bill[] bills = {bill1, bill2};
	public Room[] rooms = {room1, room2, room3}; //All Rooms in system
	public Customer[] customers = {cust1, cust2}; //All Customers in system
	public Bank bankProxy = new Bank();
	public CardReaderInt cardReader = new CardReaderInt();
	public ReceiptPrinterInt receiptPrinter = new ReceiptPrinterInt();
	public BillPrinterInt billPrinter = new BillPrinterInt();
	
	public Buffer buffer;
	
	
	//basic constructor
	public CheckOutManager(Buffer buff) {
		this.buffer = buff;
	}
	
	//custom constructor
	public CheckOutManager(Bill[] billArr, Room[] roomArr, Customer[] custArr, 
			Bank bankP, DeskClerkInt deskClerkI, CardReaderInt cardReaderI, 
			ReceiptPrinterInt receiptPrinterI, BillPrinterInt billPrinterI) {
		bills = billArr;
		rooms = roomArr;
		customers = custArr;
		bankProxy = bankP;
		cardReader = cardReaderI;
		receiptPrinter = receiptPrinterI;
		billPrinter = billPrinterI;
	}
	
	//Methods
	
	public void run() {
		while (true) {
			evalMessage(this.buffer.receive());
		}
	}
	
	public void evalMessage(Message msg) {
		switch (msg.functionCall) {
		case "requestBill" :
			Bill bill = requestBill(msg.intParam);
			Message rply = new Message(null, bill, null, 000, 0.0);
			this.buffer.reply(rply);
			break;
		case "payByCash":
			payByCash(msg.billParam);
			break;
		}
	}
	
	public Bill requestBill(int roomNum) {
		Bill reqBill = null;
		for (Bill bill : bills) {
			if (roomNum == bill.roomNum) { 
				reqBill = bill; 
				break; 
			}
		}
		return reqBill;
	}
	
	public double calculateCashChange(double input, double total) {
		return input - total;
	}
	
//	public void payByCreditCard(String cardNum, Bill bill) {	
//		
//		long referenceNum = Bank.chargeCreditCard(cardNum, bill.total);
//		
//		if (referenceNum != 0) { //approved
//			this.receiptPrinter.printReceipt(cardNum, bill.total, referenceNum);
//			bill.updateReference(referenceNum);
//			
//			//find matching room object
//			for(Room room : this.rooms) {
//				if (room.roomNum == bill.roomNum) {
//					room.releaseRoom(); //Release Room
//				}
//			}
//			
//			//Start Delete Customer
//			for (Customer cust: this.customers) { //find matching customer
//				if (cust.roomNum == bill.roomNum) {
//					cust.deleteCustomer(); //clear customer attributes
//					this.customers = ArrayUtils.removeElement(this.customers, cust); //pop from customers array
//					break;
//				}
//			}//End Delete Customer
//			
//			this.billPrinter.printBill(bill);
//			String billStr = createBillStr(bill);
//			this.buffer.reply(billStr);
//			}
//		
//		else { 
//			
//			buffer.reply("Credit Card Denied"); 
//		}
//	}
	
	public void payByCash(Bill bill) {
		Message msg;
		Message rply = new Message("inputCash", null, null, 000, 0.0);
		buffer.reply(rply);
		msg = buffer.receive();
		double cashAmount = msg.dblParam;
		
		while (cashAmount < bill.total) {
			buffer.reply(rply);
			msg = buffer.receive();
			cashAmount += msg.dblParam;
		}
		
		double change = this.calculateCashChange(cashAmount, bill.total);
		rply = new Message("displayChange", null, null, 000, change);
		buffer.reply(rply);
		
		//find matching room object
		for(Room room : this.rooms) {
			if (room.roomNum == bill.roomNum) {
				room.releaseRoom(); //Release Room
			}
		}
		
		//Start Delete Customer
		for (Customer cust: this.customers) { //find matching customer
			if (cust.roomNum == bill.roomNum) {
				cust.deleteCustomer(); //clear customer attributes
				this.customers = ArrayUtils.removeElement(this.customers, cust); //pop from customers array
				break;
			}
		}//End Delete Customer
		this.billPrinter.printBill(bill);
		rply = new Message("displayBillPrinted", null, null, 000, 0.0);
	}
}

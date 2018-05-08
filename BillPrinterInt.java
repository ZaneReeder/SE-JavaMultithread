
public class BillPrinterInt {
	
	public long printerId = 0000001;
	
	public BillPrinterInt() {
		
	}
	
	public Boolean printBill(Bill bill) {
		System.out.println("\n---Bill Printer Interface---");
		System.out.println("Printing Bill...");
		System.out.println("Customer Name: " + bill.name);
		System.out.println("Room Number: " + Integer.toString(bill.roomNum));
		System.out.println("Bill Reference Number: " + Double.toString(bill.referenceNum));
		System.out.println("Total: " + Double.toString(bill.total));
		
		return true;
		
	}
}

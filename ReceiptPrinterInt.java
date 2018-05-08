
public class ReceiptPrinterInt {
	
	public long printerId = 0000001;
	
	public ReceiptPrinterInt() {
		
	}
	
	public void printReceipt(String cardNum, double total, long referenceNum) {
		System.out.println("\n---Receipt Printer Interface---");
		System.out.println("Receipt Printer: Printing Receipt...");
		System.out.println("Card Number: " + cardNum);
		System.out.println("Total: " + Double.toString(total));
		System.out.println("Reference Number: " + Long.toString(referenceNum));
	}
}

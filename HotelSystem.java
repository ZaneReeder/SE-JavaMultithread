
/*
 * Based on the Pseudo Code provided, the Check Out Manager has access to
 * all entities and interfaces. Hence these objects must be stored
 * within the checkout manager class.
 * Otherwise, all of the objects must be called in the functions within the
 * manager class.
 * Another option would be to create a System entity which stored all of the objects
 * and ran the main function altering the parameters of each function a bit.
 * 
 * In my program I will create the System within the CheckOutManager class. This way,
 * the CheckOutManager has direct access to all of the objects within the System and the
 * Pseudo Code functions will be less altered.
 * 
 * Homework 7.
 * The types of message communication is unclear. 
 * The Pseudocode of the Connector implies a buffer with messages of a common type;
 */

public class HotelSystem {
	
	//MAIN
		public static void main(String[] args) {
			Buffer buffer = new Buffer();
			DeskClerkInt deskClerk = new DeskClerkInt(buffer);
			CheckOutManager manager = new CheckOutManager(buffer);
			
			Thread t1 = new Thread(deskClerk);
			Thread t2 = new Thread(manager);
			t1.start();
			t2.start();

		}
}

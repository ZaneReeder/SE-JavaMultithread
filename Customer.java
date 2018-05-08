
public class Customer {
	
	public int roomNum;
	public String name;
	public String phone;
	
	public Customer(int room, String fullName, String phoneNum) {
		roomNum = room;
		name = fullName;
		phone = phoneNum;
	}
	
	//currently all I understand about Java self destruction is to no longer
	//reference the object to be 'deleted' and to set all attributes to null;
	//The Manager will pop the customer from the array removing all instances of the customer
	//The garbage collector will delete the unreferenced object.
	public void deleteCustomer() {
		roomNum = 000;
		name = null;
		phone = null;
	}
}

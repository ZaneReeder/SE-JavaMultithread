
public class Bill {
	
	public double total;
	public String name;
	public long referenceNum = 0;
	public int roomNum;
	
	//constructor
	public Bill(String custName, double tot, int room) {
		name = custName;
		total = tot;
		roomNum = room;	
	}
	
	public void updateReference(long ref) {
		this.referenceNum = ref;
	}
}

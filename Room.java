public class Room {
	
	public int roomNum;
	public int status; //0:Vacant | 1:Occupied
	
	public Room(int num, int stat) {
		roomNum = num;
		status = stat;
	}
	
	public String releaseRoom() {
		status = 0;
		return "Released";
	}
	
}

package booking;

public class Seat {

	private int seatId;
	private boolean assigned;
	private int customerId = 0;
	
	public Seat(int seat_id){
		seatId = seat_id;
	}

	public int getSeatID(){
		return seatId;
	}
	
	public int getCustomerId(){
		return customerId;
	}
	
	public boolean isOccupied(){
		return assigned;
	}
	
	public void assign(int cust_id){
		customerId = cust_id;
		assigned = true;
	}
	
	public void unAssign(){
		assigned = false;
		customerId = 0;
	}
}

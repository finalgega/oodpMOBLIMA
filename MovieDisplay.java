package booking;

public class MovieDisplay {
	private Seat[][] seat;
	
	public MovieDisplay(int num){
		seat = new Seat[num / 2][num]; 
		int count = 0;
		for(int i = 0; i < num / 2; i++){
			for(int j = 0; j < num; j++){
				seat[i][j] = new Seat((count));
				count++;
			}			
		}
	}
	
	public void assignSeat(int row, char c, int cust_id){
		
		int pos = c - 'a' + 1;
		
		Seat targetSeat = null;
		
		for(int i = 0; i < seat.length; i++){
			for(int j = 0; j < seat[0].length; j++){
				if(seat[i][j].getSeatID() == row * seat[0].length + pos - 1){
					targetSeat = seat[i][j];
					break;
				};
			}	

		}
		if(pos > seat[0].length || row > seat.length){
			System.out.println("No such a seat!");
			return;
		}
		
		if(targetSeat == null){
			System.out.println("No such a seat!");
			return;
		}
		
		if(targetSeat.isOccupied()){
			System.out.println("Seat already assigned to a customer. ");
		}else{
			targetSeat.assign(cust_id);
			System.out.println("Seat assigned!");
		}
		
	}
	
	public void displaySeats(){
		
		System.out.print("  ");
		for(int j = 0; j < seat[0].length; j++){
			if(j == seat[0].length / 2){
				System.out.print(" ");
			}
			System.out.print((char)(j+'A'));
		}
		
		System.out.println(" ");
		
		for(int i = 0; i < seat.length; i++){
			System.out.print(i + " ");
			
			for(int j = 0; j < seat[0].length; j++){
				if(j == seat[0].length / 2){
					System.out.print(" ");
				}
				if(seat[i][j].isOccupied()){
					System.out.print("X");
				}else{
					System.out.print("0");
				}
			}			
			System.out.print(" " + i + "\n");
		}
	}
	
	public void unAssignSeat(int seatId){
		
		Seat targetSeat = seat[0][0];
		
		for(int i = 0; i < seat.length; i++){
			for(int j = 0; j < seat[0].length; j++){
				if(seat[i][j].getSeatID() == seatId){
					targetSeat = seat[i][j];
					break;
				};
			}	

		}
		
		targetSeat.unAssign();
		System.out.println("Seat Unassigned!");
	}
	
	
	/*
	
	public void showEmptySeats(){
		System.out.println("The following seats are empty:");
		
		for(int i = 0; i < seat.length; i++){
			if(!seat[i].isOccupied()){
				System.out.println("SeatID " + seat[i].getSeatID());
			}
		}
	}
	
	public Seat[] sortSeats(){
		Seat[] a = seat.clone();
		PlaneSeat temp;
		
		for(int i = 1; i < a.length; i++){
			for(int j = i; j > 0; j--){
				if(a[j].getCustomerId() < a[j-1].getCustomerId()){
					temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
				}else break;
			}
		}
		return a;
	}
	
	public void showNumEmptySeats(){
		int count = 0;
		for(int i = 0; i < seat.length; i++){
			if(!seat[i].isOccupied()){
				count++;
			}
		}
		numEmptySeat = count;
		System.out.println("There are " + numEmptySeat + " empty seats");
	}
	
	public void showEmptySeats(){
		System.out.println("The following seats are empty:");
		
		for(int i = 0; i < seat.length; i++){
			if(!seat[i].isOccupied()){
				System.out.println("SeatID " + seat[i].getSeatID());
			}
		}
	}
	
	public void showAssignedSeats(boolean bySeatId){
		System.out.println("The seat assignments are as follow:");
		if(bySeatId){
			for(int i = 0; i < seat.length; i++){
				if(seat[i].isOccupied()){
					System.out.println("  SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + seat[i].getCustomerId());
				};
			}
		}else{
			PlaneSeat[] a = this.sortSeats();
			for(int i = 0; i < seat.length; i++){
				if(a[i].isOccupied()){
					System.out.println("  SeatID " + a[i].getSeatID() + "assigned to CustomerID " + a[i].getCustomerId());
				};
			}
		}
	}
	*/
	

}

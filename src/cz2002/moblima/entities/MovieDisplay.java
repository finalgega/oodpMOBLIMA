package cz2002.moblima.entities;

public class MovieDisplay {
	
	 /**
	 * The layout of the cinema.
	 */ 
	
	private Seat[][] seat;
	
	 /**
	 * Initializes a new movie display and the layout for the display. 
	 * Maximum number of seats is 210 (21 x 10).
	 * @param num - the number of seats for the layout.
	 */ 
	
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
	
	 /**
	 * Assigns a seat to a customer. 
	 * @param row - the row number a seat.
	 * @param c - the letter of an aisle.
	 * @param cust-id - the customer id.
	 */ 
	
	public void assignSeat(int row, char c, int cust_id){
		
		int pos = c - 'a' + 1;
		
		Seat targetSeat = null;
		
		for(int i = 0; i < seat.length; i++){
			for(int j = 0; j < seat[0].length; j++){
				if(seat[i][j].getSeatID() == row * seat[0].length + pos - 1){
					targetSeat = seat[i][j];
					break;
				}
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
	
	 /**
	 * Displays the seats. 
	 */ 
	
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
	
	 /**
	 * Unassigns a seat. 
	  * @param cust_id - the seat id.
	 */ 
	
	public void unAssignSeat(int seatId){
		
		Seat targetSeat = seat[0][0];
		
		for(int i = 0; i < seat.length; i++){
			for(int j = 0; j < seat[0].length; j++){
				if(seat[i][j].getSeatID() == seatId){
					targetSeat = seat[i][j];
					break;
				}
			}	

		}
		
		targetSeat.unAssign();
		System.out.println("Seat Unassigned!");
	}	

}

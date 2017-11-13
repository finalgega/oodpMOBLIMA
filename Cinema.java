package test;

public class Cinema {
	
	 /**
	 * The number of seats.
	 */ 
	
	private int numberOfSeats;
	
	 /**
	 * Creates a new Cinema with the given size.
	 * @param num the number of seats in the cinema.
	 */ 

	public Cinema(int num){
		numberOfSeats = num;
	}
	
	/**
	* Returns the number of seats in a cinema
	@returns int - the number of seats in a cinema
	**/
	
	public int getNumberOfSeats(){
		return numberOfSeats;
	}
}

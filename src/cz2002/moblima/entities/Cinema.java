package cz2002.moblima.entities;

public class Cinema {

    ///	Think of a format for the code.
    private String cinemaCode;
    private int cineplexId;
    /**
	 * The number of seats.
      */
    private int numberOfSeats;
	
	 /**
	 * Creates a new Cinema with the given size.
	 * @param num the number of seats in the cinema.
	 */ 

	public Cinema(int num, int code, int cinplxId){
		cineplexId = cinplxId;
		cinemaCode = "C"+String.valueOf(code);
		System.out.println(cinemaCode);
		numberOfSeats = num;
	}
	
	public String getCinemaCode() {return cinemaCode;}
	/**
	* Returns the number of seats in a cinema
	 @return int - the number of seats in a cinema
	**/
	
	public int getNumberOfSeats(){
		return numberOfSeats;
	}
}

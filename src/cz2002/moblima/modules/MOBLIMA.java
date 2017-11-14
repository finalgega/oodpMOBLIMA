package cz2002.moblima.modules;

import cz2002.moblima.entities.Cinema;
import cz2002.moblima.entities.MovieDisplay;

import java.util.Scanner;

public class MOBLIMA {

	public static void initSeatAssignmenntModule() {
		
		Scanner sc = new Scanner(System.in);
		// At the moment 21 * 10 seats is the maximum
		Cinema c = new Cinema(20);
		MovieDisplay m = new MovieDisplay(c.getNumberOfSeats());
		
		System.out.println("(1) Display seats");
		System.out.println("(2) Assign a customer to a seat");
		System.out.println("(3) Remove a seat assignment");
		System.out.println("(4) Exit");
		System.out.println("");
		
		int choice = 0;
		
		while(choice != 4){
			System.out.print("  Enter the number of your choice: ");
			choice = sc.nextInt();
			
			switch(choice){
				case(1): 
					m.displaySeats();
					break;
				case(2):
					System.out.println("Assigning Seat .. ");
					System.out.print("  Please enter the aisle's letter ");
					char col = sc.next().toLowerCase().charAt(0);
					System.out.print("  Please enter row number ");
					int row = sc.nextInt();
					System.out.print("  Please enter Customer ID: ");
					int custId = sc.nextInt();
					m.assignSeat(row, col, custId);
					break;
				case(3):
					System.out.print("  Enter SeatID to unassign customer from: ");
					int seat_Id = sc.nextInt();
					m.unAssignSeat(seat_Id);
					break;
				case(4):
					break;
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
		
		sc.close();
			
	}
}

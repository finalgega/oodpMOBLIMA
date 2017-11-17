package cz2002.moblima.modules;

import cz2002.moblima.entities.Movie;
import cz2002.moblima.entities.MovieDisplay;
import cz2002.moblima.entities.User;
//import sun.security.krb5.internal.Ticket;

import java.util.Scanner;

public class SeatAssignmentModule {
    
    public static void bookMenu(Movie movie, User user, MovieDisplay movieDisplay) {
    	Scanner sc = new Scanner(System.in);
    	int choice = 0;
        while (choice != 3 && choice != 2) {
            System.out.println("(1) Display seats");
            System.out.println("(2) Book a sit");
            System.out.println("(3) Exit");
            System.out.println("");
            System.out.print("  Enter the number of your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case (1):
                    movieDisplay.displaySeats();
                    break;
                case (2):
                    System.out.println("Assigning Seat .. ");
                    boolean result = false;
                    char col = '?';
                    int row = 0;
                    while (!result) {
                        System.out.print("  Please enter the aisle's letter ");
                        col = Character.toLowerCase(sc.next().charAt(0));
                        System.out.print("  Please enter row number ");
                        row = sc.nextInt();
                        result = movieDisplay.assignSeat(row, col, user.getUserID());
                    }
                    System.out.println("Seat Assigned!");
                    String seatNumber = row + "<" + col;
                    TicketPrice ticket = new TicketPrice(movie, user, seatNumber,movieDisplay.getCinemaCode(),movieDisplay.getDisplayId());
                    ticket.initiateChargeForTicket();
                    break;
                case (3):
                    System.out.println("Exiting seat assignment module");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println(" ");
        }
    }


}
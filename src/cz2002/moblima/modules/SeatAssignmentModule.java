package cz2002.moblima.modules;

import cz2002.moblima.entities.Cinema;
import cz2002.moblima.entities.Movie;
import cz2002.moblima.entities.MovieDisplay;
import cz2002.moblima.entities.User;
import cz2002.moblima.utilities.FileIOController;
import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatAssignmentModule {

    public void init(Movie movie, User user,MovieDisplay movieDisplay) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> bookingInformation = new ArrayList<>();
        FileIOController.readFile(bookingInformation, "bookings.txt");
        ArrayList<String> seatDetails = new ArrayList<>();
        //	how to further separate only the movie in question... hmm
        String[] data;
        int custId = 0;
        int displayID = 0;
        for (int i = 0; i < bookingInformation.size(); i++) {
                data = bookingInformation.get(i).split("/");
                custId = Integer.parseInt(data[TicketPrice.USER_ID_INDEX]);
                displayID = Integer.parseInt(data[TicketPrice.DISPLAY_ID_INDEX]);
                seatDetails.add(data[TicketPrice.SEAT_ID_INDEX]);
                data = seatDetails.get(i).split("<");
                movieDisplay.assignSeat(Integer.parseInt(data[0]), data[1].charAt(0), custId);
        }
        int choice = 0;

        while (choice != 4) {
            System.out.println("(1) Display seats");
            System.out.println("(2) Assign a customer to a seat");
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
                        result = movieDisplay.assignSeat(row, col, custId);
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

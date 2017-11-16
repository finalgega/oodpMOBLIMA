package cz2002.moblima.modules;

import cz2002.moblima.entities.Cinema;
import cz2002.moblima.entities.Movie;
import cz2002.moblima.entities.MovieDisplay;
import cz2002.moblima.entities.User;
import cz2002.moblima.utilities.FileIOController;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatAssignmentModule {

    public static void init(Movie movie, User user) {

        Scanner sc = new Scanner(System.in);
        // At the moment 21 * 10 seats is the maximum
        Cinema c = new Cinema(20);
        MovieDisplay m = new MovieDisplay(c.getNumberOfSeats());

        ArrayList<String> bookingInformation = new ArrayList<>();
        FileIOController.readFile(bookingInformation, "bookings.txt");
        ArrayList<String> seatDetails = new ArrayList<>();
        //	how to further separate only the movie in question... hmm
        String[] data;
        int cust_id;
        for (int i = 0; i < bookingInformation.size(); i++) {
            data = bookingInformation.get(i).split("@");
            cust_id = Integer.parseInt(data[1]);
            seatDetails.add(data[2]);
            data = seatDetails.get(i).split("<");
            m.assignSeat(Integer.parseInt(data[0]), data[1].charAt(0), cust_id);

        }
        int choice = 0;

        while (choice != 4) {
            System.out.println("(1) Display seats");
            System.out.println("(2) Assign a customer to a seat");
            System.out.println("(3) Remove a seat assignment");
            System.out.println("(4) Exit");
            System.out.println("");
            System.out.print("  Enter the number of your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case (1):
                    m.displaySeats();
                    break;
                case (2):
                    System.out.println("Assigning Seat .. ");
                    boolean result = false;
                    char col = '?';
                    int row = 0;
                    while (!result) {
                        System.out.print("  Please enter the aisle's letter ");
                        col = sc.next().toLowerCase().charAt(0);
                        System.out.print("  Please enter row number ");
                        row = sc.nextInt();
                        System.out.print("  Please enter Customer ID: ");
                        int custId = sc.nextInt();
                        result = m.assignSeat(row, col, custId);
                    }
                    System.out.println("Seat Assigned!");
                    String seatNumber = row + "<" + col;
                    TicketPrice ticket = new TicketPrice(movie, user, seatNumber);
                    ticket.initiateChargeForTicket();
                    break;
                case (3):
                    System.out.print("  Enter SeatID to unassign customer from: ");
                    int seat_Id = sc.nextInt();
                    m.unAssignSeat(seat_Id);
                    break;
                case (4):
                    System.out.println("Exiting seat assignment module");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println(" ");
        }
    }

    public static void runSeatAssignmetn() {

	}
}

package cz2002.moblima.modules;

import cz2002.moblima.entities.Movie;
import cz2002.moblima.entities.User;
import cz2002.moblima.utilities.FileIOController;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

//	Ticket will need to know Cinema Code and What movie when we instantiated.

/**
 * Ticket is tied to the movie and cinema and user
 */
public class TicketPrice {

    private int displayID;
    private String cinemaCode;
    private User user;
    private Movie movie;
    private String seatNumber;
    private double ticketprice;
    private double BLOCKBUSTER_BASE_PRICE = 11.0;
    //  constant to hold 3D movie base price
    private double M3D_BASE_PRICE = 10;
    private double HOLIDAY_BASE_PRICE = 8.50;
    private static final double BASE_PRICE = 9.0;

    //  Indexes
    public static final int TRANSACTION_ID_INDEX = 0;
    public static final int USER_ID_INDEX = 1;
    public static final int DISPLAY_ID_INDEX = 2;
    public static final int SEAT_ID_INDEX = 3;
    public static final int TICKET_PRICE_INDEX = 4;

    public TicketPrice() {
    }

    public TicketPrice(Movie movie, User user, String seatNumber,String cinemaCode,int displayID) {
        this.movie = movie;
        this.user = user;
        this.seatNumber = seatNumber;
        this.cinemaCode = cinemaCode;
        this.displayID = displayID;
    }

    public void writeBookingToFile() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String transactionInformation = this.cinemaCode + localDate + localTime + "/" + user.getUserID() + "/" + this.displayID + "/"  + this.seatNumber  + "/" + this.ticketprice + "\n";
        FileIOController.writeFile(transactionInformation, "bookings.txt");
    }

    public double calculateTicketPrice()
    {
        this.ticketprice = chargeWithType(BASE_PRICE);
        return this.ticketprice;
    }


    public void initiateChargeForTicket() {
        makePayment();
        calculateTicketPrice();
        writeBookingToFile();
        System.out.println("Thank you for making your booking!");
        System.exit(0);
    }

    private void makePayment() {
        System.out.println("Request for payment information.");
    }

    public double chargeWithType(double basePrice){
        double price = basePrice;
        if(movie.getMovieType().contains("3D")){
            price = M3D_BASE_PRICE;
        }else if(movie.getMovieType().contains("Blockbuster")){
            price = BLOCKBUSTER_BASE_PRICE;
        }
        //else if(LocalDate.now().isHolidayDate()){ price = HOLIDAY_BASE_PRICE;}
        chargeWithAge(price);
        return price;
    }

//    public double chargeWithClass() {
//        TicketPrice t=new TicketPrice();
//        double price=t.chargeWithType();
//        String ch;
//        Scanner sc=new Scanner(System.in);
//        System.out.println("Please choose from Platinum, Gold, Ultima");
//        ch=sc.next();
//        if (ch.contains("Platinum")) {
//            price=1.5*price;
//            //System.out.println("Price: "+price);
//        } else if (ch.contains("Gold")) {
//            price=1.2*price;
//            //System.out.println("Price: "+price);
//        } else if(ch.contains("Ultima")) {
//            //System.out.println("Price: "+price);
//        }
//        return price;
//
//    }

    public double chargeWithAge(double price) {
        if (user.getCategoryOfUser() == User.USER_AGE_CATEGORY.CHILD) {
            price = 0.9 * price;
        } else if (user.getCategoryOfUser() == User.USER_AGE_CATEGORY.ADULT) {
            return price;
        } else if (user.getCategoryOfUser() == User.USER_AGE_CATEGORY.SENIOR_CITIZEN) {
            price = 0.85 * price;
        }
        price = chargeWithDay(price);
        return price;
    }

    public double chargeWithDay(double price) {
        LocalDate localDate = LocalDate.now();
        if(localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY){
            price = 1.25*price;
        }else if(localDate.getDayOfWeek() == DayOfWeek.MONDAY){
            price = 0.85 * price;
        }
        return price;
    }

}



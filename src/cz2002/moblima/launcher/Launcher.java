package cz2002.moblima.launcher;

import cz2002.moblima.modules.MainMenu;

/**
 * Main entry point class responsible for launching the application
 * and its modules.
 *
 * @author SeatAssignmentModule-CZ2002-SS1-GROUP-01
 * @version 1.0
 * @since 2017-10-26
 */
public class Launcher {
    private static String[] names = {"Aaron Goy", "Janaki", "Jonas", "Karri", "Lum Liying", "Sharon"};
    private final static String APP_VERSION = "0.05";

    /**
     * Entry point into program, main thread
     *
     * @param args list of arguments if applicable
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        MainMenu.init();
    }

//    private static void runSelectedModule() {
//        Scanner sc = new Scanner(System.in);
//        int choice = 0;
//        do {
//            System.out.print("  Enter the number of your choice: ");
//            choice = sc.nextInt();
//
//            switch(choice){
//                case 1:
//                    System.out.println("	----------- View All Movies -----------");
//                    //Display all movies
//                    System.out.println();
//                    System.out.println("	Enter number to view movie details: ");
//                    //call function to display movie summary
//                    //call movie menu
//                    System.out.println();
//                    //Change "Thor" to a movie name variable
////                    MovieMenu.main("Thor");
//                    break;
//                case 2:
//                    System.out.println("	----------- View Booking History -----------");
//                    //get email to display user's booking history
//                    System.out.println("	Enter your email:");
//                    System.out.println("	----------- Your Booking History -----------");
//                    // Display all booking history
//                    // If user has not made any prior booking, return "No booking has been made yet!"
//                    break;
//                case 3:
//                    System.out.println("	----------- View Top 5 Movies By Ticket Sales -----------");
//                    // Display top 5 movies by ticket sales
//                    break;
//                default:
//                    System.out.println("Invalid input");
//            }
//        }while(choice != 4);
//
//    }

    /**
     * Singleton method to print the startup message
     */
    private static void printWelcomeMessage() {
        System.out.println("SeatAssignmentModule Application");
        System.out.println("Group : CZ2002-SS1-GROUP-01");
        System.out.println("Authors : ");
        for (String name : names
                ) {
            System.out.println(name);
        }
        System.out.println("Version : " + APP_VERSION);
    }

}

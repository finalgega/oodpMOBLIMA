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

    /**
     * Singleton method to print the startup message
     */
    private static void printWelcomeMessage() {
        System.out.println("SeatAssignmentModule Application");
        System.out.println("Group : CZ2002-SS1-GROUP-01");
        System.out.print("Authors :");
        for (String name : names
                ) {
            System.out.print(name+", ");
        }
        System.out.println();
        System.out.println("Version : " + APP_VERSION);
    }

}

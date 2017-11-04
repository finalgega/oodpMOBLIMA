package oodpAssignment;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

	public static MovieMenu MovieMenu = new MovieMenu();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("----------- Welcome to MOBLIMA -----------");
		System.out.println("(1) View all movies");
		System.out.println("(2) View booking history");
		System.out.println("(3) View top 5 movies by ticket sales");
		System.out.println("(4) Login as staff");
		System.out.println("");
		
		int mainMenuChoice = 0;
		
		while(mainMenuChoice != 3){
			
			System.out.print("  Enter the number of your choice: ");
			mainMenuChoice = sc.nextInt();
			
			switch(mainMenuChoice){
				case(1): 
					System.out.println("	----------- View All Movies -----------");
					//Display all movies
					System.out.println();
					System.out.println("	Enter number to view movie details: ");
					//call function to display movie summary
					//call movie menu
					System.out.println();
					//Change "Thor" to a movie name variable
					MovieMenu.main("Thor");
					break;
				case(2):
					System.out.println("	----------- View Booking History -----------");
					//get email to display user's booking history
					System.out.println("	Enter your email:");
					System.out.println("	----------- Your Booking History -----------");
					// Display all booking history
					// If user has not made any prior booking, return "No booking has been made yet!"
					break;
				case(3):
					System.out.println("	----------- View Top 5 Movies By Ticket Sales -----------");
					// Display top 5 movies by ticket sales
					break;
					
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
		
		sc.close();
			
	}
}

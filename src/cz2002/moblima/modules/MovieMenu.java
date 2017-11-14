package cz2002.moblima.modules;

import cz2002.moblima.entities.Review;

import java.io.IOException;
import java.util.Scanner;

public class MovieMenu {

	public static void init(String movieTitle) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int movieMenuChoice = 0;
		
		while(movieMenuChoice != 4){
			System.out.println("	(1) Book Movie");
			System.out.println("	(2) View all reviews");
			System.out.println("	(3) Submit a review");
			System.out.println("	(4) Back");
			System.out.println("");
			
			System.out.print("  	Enter number of your choice: ");
			movieMenuChoice = sc.nextInt();
			
			switch(movieMenuChoice){
				case(1): 
					System.out.println("	----------- Book Movie -----------");
					//call booking function
					System.out.println("	----------- Your booking is successful! -----------");
					System.out.println();
					break;
				case(2):
					System.out.println("	----------- View All Reviews -----------");
					//call review function
					Review.ListAllReviews(movieTitle);
					System.out.println("	----------- End Of All Reviews -----------");
					break;
				case(3):
					System.out.println();
					System.out.println("	----------- Enter a new review -----------");
					// call new review function
					System.out.print("	Your email: ");
					sc.nextLine();
					String userEmail = sc.nextLine();
					System.out.print("	Movie rating (1-5): ");
					int rating = sc.nextInt();
					while (rating > 5 || rating < 0){
						System.out.println("	Please input a rating of 1-5.. ");
						System.out.print("	Movie rating (1-5 [Best]): ");
						rating = sc.nextInt();
					}
					System.out.print("	Movie rating description: ");
					sc.nextLine();
					String movieDesc = sc.nextLine();
					Review.writeFile(movieTitle, rating, movieDesc, userEmail);
					System.out.println();
					System.out.print("	----------- Your review submission is successful! -----------");
					System.out.println();
					break;
					
				case(4):
					System.out.println();
//					MainMenu.main(null);
					break;
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
		
		sc.close();
			
	}
}

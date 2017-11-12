package MainPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	
	private static ArrayList<User> allUsers = new ArrayList<User>();
	private static int size;
	private static boolean loggedIn = false;
	private static User customerUser;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("----------- Welcome to MOBLIMA -----------");
		allUsers = UserIO.readUserFile();
		size = allUsers.size();
		
		int mainMenuChoice = 0;
		
		while(mainMenuChoice != 6){
			System.out.println("(1) View all movies and access movie operations*");
			System.out.println("(2) View booking history");
			System.out.println("(3) View top 5 movies by ticket sales");
			if(!loggedIn) {
				System.out.println("(4) Login as customer");
			}else {
				System.out.println("(4) Logout from customer account");
			}
			System.out.println("(5) Login as staff");
			System.out.println(" * book movie, view and publish reviews");
			System.out.println("");
			
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
					movieMenu("Thor");
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
					
				case(4):
					// Connect as customer
					LogInOutMenu();
					break;
				
				case(5):
					// Connect as staff
					break;
				
				case(6):
					System.out.println("	----------- Program is terminating -----------");
					break;
					
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
		
		sc.close();	
	}
	
	public static void movieMenu(String movieTitle) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int movieMenuChoice = 0;
		
		while(movieMenuChoice != 5){
			System.out.println("	(1) Book Movie");
			System.out.println("	(2) View all reviews");
			System.out.println("	(3) Submit a review");
			if(!loggedIn) {
				System.out.println("	(4) Login as customer");
			}else {
				System.out.println("	(4) Logout from customer account");
			}
			System.out.println("	(5) Back");
			System.out.println("");
			
			System.out.print("  	Enter number of your choice: ");
			movieMenuChoice = sc.nextInt();
			
			switch(movieMenuChoice){
				case(1): 
					System.out.println("	----------- Book Movie -----------");
					if(loggedIn) {
						System.out.println("	----------- Your booking is successful! -----------");
					}else {
						System.out.println("	Login first to access this option");
					}
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
					// Connect as customer
					LogInOutMenu();
					break;
				case(5):
					System.out.println();
					break;
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
	}
	
	
	public static void LogInOutMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		if(loggedIn) {
			loggedIn = false;
			System.out.println("	----------- Your were successfully logged out! -----------");
		}else {
			System.out.println("	(1) Already registered");
			System.out.println("	(2) Create a new account");
			System.out.println("	(3) Back");
			int Choice = 0;
			String fN, lN, pW;
			int iD;
				Choice = sc.nextInt();
			switch(Choice){
			case(1):
				System.out.println("	Enter your customer iD");
				iD = sc.nextInt();
				System.out.println("	Enter your password");
				pW = sc.next();
				if(iD>size) {
					System.out.println("Wrong iD");
				}else {
					if(allUsers.get(iD-1).getPassWord().compareTo(pW)!=0) {
						System.out.println("Wrong iD or password");
						System.out.println(allUsers.get(iD-1).getPassWord());
					}else {
						customerUser = allUsers.get(iD-1);
						loggedIn = true;
						System.out.println("	----------- Your were succesfully logged in -----------");
					}
				}
				break;
			case(2):
				System.out.println("	Enter your first name");
				fN = sc.next();
				System.out.println("	Enter your last name");
				lN = sc.next();
				System.out.println("	Enter a password");
				pW = sc.next();
				customerUser = new User(allUsers.size()+1, fN, lN, pW);
				loggedIn = true;
				allUsers.add(customerUser);
				UserIO.addUsers(customerUser);
				size++;
				System.out.println("	----------- Your have succesfully created an account -----------");
				System.out.println("	----------- Your customer iD is : "+customerUser.getUserID()+" -----------");
				break;
			case(3):
				break;
			default:
				System.out.println("Invalid input");
			}
			
		}
	}
	
}
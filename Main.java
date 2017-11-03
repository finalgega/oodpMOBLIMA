import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("(1) Enter a review");
		System.out.println("(2) View review");
		System.out.println("(3) Exit");
		System.out.println("");
		
		int choice = 0;
		
		while(choice != 3){
			System.out.print("  Enter the number of your choice: ");
			choice = sc.nextInt();
			
			switch(choice){
				case(1): 
					System.out.print("	Enter movie title: ");
					sc.nextLine();
					String movieTitle = sc.nextLine();
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
					Review.writeFile(movieTitle, rating, movieDesc);
					Review.OverallRatingInt(movieTitle);
					break;
				case(2):
					System.out.print("	Enter movie title: ");
					sc.nextLine();
					movieTitle = sc.nextLine();
					Review.ListAllReviews(movieTitle);
				case(3):
					break;
				default:
					System.out.println("Invalid input");
			}
			System.out.println(" ");
		}
		
		sc.close();
			
	}

}
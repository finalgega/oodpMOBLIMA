package cz2002.moblima.modules;

import cz2002.moblima.entities.Review;
import cz2002.moblima.utilities.FileIOController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MoviesList {
    private static String MovieTitle;
    private static String MovieType;
    private static String Director;
    private static String Cast;
    private static String Synopsis;
    private static String MovieStatus;
    private static String Showtimes;
    private static String MovieClass;
    private static String PastReviews;
    private static String OverallRating;
    private static String ShowStart;
    private static String EndOfShowing;
    private static String TotalSales;
    private static int i;
    private static String collectNewMovie;
    private static String collectAll;
    private static List<String> newmovie = new ArrayList<String>();
    private static List<String> movieListings = new ArrayList<String>();
    private static File movieFile = new File("movies.txt");

    public static void administrateMovie() {
        // TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		char choice;
		/* do-while loop so staff can continuously create/update movies */
		do {
			System.out.println("enter -1 to terminate \nenter 1 to edit");
			i = sc.nextInt();
			if (i==-1) break;
			System.out.println("What do you want to do?");
			System.out.println("a: create movie");
			System.out.println("b: update movie");
			choice = sc.next().charAt(0);
			switch (choice) {
			case 'a':
			case 'A':
				/* program reads existing movies and stores into list movieListings*/
                FileIOController.readFile(movieListings, movieFile);

				/* string list newmovie stores new movie listing */
				/* get staff input */
				System.out.println("Enter movie tile");
				MovieTitle = sc.next();
				MovieTitle += sc.nextLine();
				newmovie.add("Movie title: " + MovieTitle);
				System.out.println("Enter movie type");
				MovieType = sc.next();
				MovieType += sc.nextLine();
				newmovie.add("Movie Type: "+MovieType);
				System.out.println("Enter movie director");
				Director = sc.next();
				Director += sc.nextLine();
				newmovie.add("Movie Director: "+Director);
				System.out.println("Enter Cast");
				Cast = sc.next();
				Cast += sc.nextLine();
				newmovie.add("Cast: "+Cast);
				System.out.println("Enter synopsis");
				Synopsis = sc.next();
				Synopsis += sc.nextLine();
				newmovie.add("Synopsis: "+Synopsis);
				System.out.println("Enter Movie Status");
				MovieStatus = sc.next();
				MovieStatus += sc.nextLine();
				newmovie.add("Movie Status: "+MovieStatus);
				System.out.println("Enter showtimes");
				Showtimes = sc.next();
				Showtimes += sc.nextLine();
				newmovie.add("Showtimes: "+Showtimes);
				System.out.println("Enter Movie Class");
				MovieClass = sc.next();
				MovieClass += sc.nextLine();
				newmovie.add("Movie Class: "+MovieClass);
				System.out.println("Enter past reviews");
				PastReviews = sc.next();
				PastReviews += sc.nextLine();
				newmovie.add("Past Reviews: "+PastReviews);
				System.out.println("Enter Overall Rating");
				OverallRating = sc.next();
				OverallRating += sc.nextLine();
				newmovie.add("Overall Rating: "+OverallRating);
				System.out.println("Enter Show Start");
				ShowStart = sc.next();
				ShowStart += sc.nextLine();
				newmovie.add("Show Start: "+ShowStart);
				System.out.println("Enter End of Showing");
				EndOfShowing = sc.next();
				EndOfShowing += sc.nextLine();
				newmovie.add("End of Showing: "+EndOfShowing);
				System.out.println("Enter Total Sales");
				TotalSales = sc.next();
				TotalSales += sc.nextLine();
				newmovie.add("Total Sales: "+TotalSales);
				
				/* list newmovie is added to movieListings, which contains previously existing movies */
				//moviesList.add(newmovie);
				
				/* string collectNewMovie stores listing of new movie from list newmovie as a string*/
				collectNewMovie = newmovie.stream().collect(Collectors.joining("\r\n"));
				System.out.println(collectNewMovie);
				
				/* string collectNewMovie is added to movieListings, which contains previously existing movies */
				movieListings.add(collectNewMovie);
				
				/* string collectAll 'converts' all movie listings from list to string, to make it easier to write into file */
				collectAll = movieListings.stream().collect(Collectors.joining("\r\n"));
                FileIOController.writeFile(collectAll, movieFile);
                break;
			case 'b':
			case 'B':
				int count = 0;
				ArrayList<List<String>> listoflists = new ArrayList<List<String>>();
                FileIOController.readFile(movieListings, movieFile);

				/* for loop to create sublists(each containing listing of one movie), 
				   from list movieListings(containing all movie listings) */
				for (int j =0; j<movieListings.size(); j=j+13) {
					   listoflists.add(movieListings.subList(j, j+13));
					   System.out.println(listoflists.get(count));
					   count++;
				   }
				
				/* get staff input */
				System.out.println("which movie do you want to update");
				String moviechoice = sc.next();
				for (int k=0;k<count;k++) {
					if (listoflists.get(k).get(0).contains(moviechoice)) {
						System.out.println("what do you want to update?");
						System.out.println("1: movie name");
						System.out.println("2: movie type");
						System.out.println("3: movie director");
						System.out.println("4: cast");
						System.out.println("5: synopsis");
						System.out.println("6: movie status");
						System.out.println("7: showtimes");
						System.out.println("8: class");
						System.out.println("9: past reviews");
						System.out.println("10: overall rating");
						System.out.println("11: show start");
						System.out.println("12: end of showing");
						System.out.println("13: total sales");
						int choice2 = sc.nextInt();
						switch (choice2) {
						case 1:
							System.out.println("enter new movie name");
							String movie = sc.next();
							movie += sc.nextLine();
							listoflists.get(k).set(0, "Movie Name: " + movie);
							break;
						case 2:
							System.out.println("enter new movie type");
							String type = sc.next();
							type += sc.nextLine();
							listoflists.get(k).set(1, "Movie Type: " + type);
							break;
						case 3:
							System.out.println("enter new movie director");
							String director = sc.next();
							director += sc.nextLine();
							listoflists.get(k).set(2, "Movie Director: "+ director);
							break;
						case 4:
							System.out.println("enter new cast");
							String cast = sc.next();
							cast += sc.nextLine();
							listoflists.get(k).set(3, "Cast: "+ cast);
							break;
						case 5:
							System.out.println("enter new synopsis");
							String synopsis = sc.next();
							synopsis += sc.nextLine();
							listoflists.get(k).set(4, "Synopsis: "+ synopsis);
							break;
						case 6:
							System.out.println("enter new movie status");
							String status = sc.next();
							status += sc.nextLine();
							listoflists.get(k).set(5, "Movie Status: "+ status);
							break;
						case 7:
							System.out.println("enter new showtimes");
							String showtimes = sc.next();
							showtimes += sc.nextLine();
							listoflists.get(k).set(6, "Showtimes: "+ showtimes);
							break;
						case 8:
							System.out.println("enter new class");
							String classtype = sc.next();
							classtype += sc.nextLine();
							listoflists.get(k).set(7, "Class: "+ classtype);
							break;
						case 9:
							Review.listAllReviews(moviechoice);
							break;
						case 10:
							Review.overallRatingInt(moviechoice);
							break;
						case 11:
							System.out.println("enter new show start");
							String start = sc.next();
							start += sc.nextLine();
							listoflists.get(k).set(10, "Show Start: "+ start);
							break;
						case 12:
							System.out.println("enter new end of showing");
							String ending = sc.next();
							ending += sc.nextLine();
							listoflists.get(k).set(11, "End of Showing: "+ ending);
							break;
						case 13:
							System.out.println("enter new total sales");
							String sales = sc.next();
							sales += sc.nextLine();
							listoflists.get(k).set(12, "Total Sales: "+ sales);
							break;	
						}
						/* string collectmovies 'converts' all movie listings from list to string, to make it easier to write into file */
						String collectmovies = movieListings.stream().collect(Collectors.joining("\r\n"));
						System.out.println(collectmovies);
                        FileIOController.writeFile(collectmovies, movieFile);
                    }
				}
			} 
		} while (i != -1);
	}
}

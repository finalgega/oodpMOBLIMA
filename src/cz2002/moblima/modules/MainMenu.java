package cz2002.moblima.modules;

import cz2002.moblima.controllers.MovieController;
import cz2002.moblima.entities.*;
import cz2002.moblima.utilities.FileIOController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	
	/*you can access the user Id through the method .getUserId(); however, the class contains no email.
    The reviews are independent form the user logged in.
	We are not required to publish the review history, given a user, therefore it is not a problem*/
    private static ArrayList<Movie> movieArrayList = MovieController.getInstance().getListOfMovies();
    private static ArrayList<MovieDisplay> movieDisplayArrayList = FileIOController.readMovieDisplayFile(movieArrayList);
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private static int size;

	private static boolean loggedIn = false;
	private static User customerUser;
	private static int nbrCineplexes = 3; //total number of cineplexes
	private static int nbrCinemas = 3; //number of cinemas per cineplexes
	private static int nbrSeats = 80; //number of seats per cinemas
	
	public static ArrayList<MovieDisplay> getAllDisplay(){
		return movieDisplayArrayList;
	}
	
    public static void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- Welcome to SeatAssignmentModule -----------");
        
        //LITTLE MODIFICATION
        // Assignement of the seats
        FileIOController.initSeats();
        
        // Initialising cineplexes and cinemas
        Cineplexes[] Ciplxs = new Cineplexes[nbrCineplexes];
        for(int i = 0; i <nbrCineplexes; i++) {
        	Ciplxs[i] = new Cineplexes(nbrCinemas, i, nbrSeats);
        }
        //Read the users from file
        allUsers = FileIOController.readUserFile();
        size = allUsers.size();
        
        int mainMenuChoice = 0;
		
		while(mainMenuChoice != 6){
			System.out.println("(1) View all movies and access movie operations*");
			System.out.println(" * book movie, view and publish reviews");
			System.out.println("(2) View booking history");
			System.out.println("(3) View top 5 movies by ticket sales");
			if(!loggedIn) {
				System.out.println("(4) Login as customer");
			}else {
				System.out.println("(4) Logout from customer account");
			}
			System.out.println("(5) Login as staff");
			System.out.println("(6) Exit");
			
			
			System.out.print("  Enter the number of your choice: ");
			mainMenuChoice = sc.nextInt();


			switch(mainMenuChoice){
                case (1): {
                    int counter = 1;
                    //Display all movies
                    for (Movie movie : movieArrayList
                            ) {
                        System.out.println("Movie Title : (" + counter + ") " + movie.getMovieTitle());
                        counter++;
                    }
                    System.out.println();
                    System.out.println("	Enter number to view movie details and access movie operations: ");
                    //call function to display movie summary
                    int movieChoice = 0;
                    movieChoice = sc.nextInt();
                    System.out.println("Movie Title : " + movieArrayList.get(movieChoice - 1).getMovieTitle());
                    System.out.println("Movie Synopsis : " + movieArrayList.get(movieChoice - 1).getMovieSynopsis());
                    //call movie menu
                    System.out.println();
                    movieMenu(movieArrayList.get(movieChoice - 1));
                    break;
                }
                case (2): {
                	if(loggedIn) {
	                    System.out.println("	----------- Your Booking History -----------");
	                    boolean displayedMade = FileIOController.displayBookingHist(customerUser);
	                    if(!displayedMade) {
	                    	System.out.println("No booking has been made yet.");
	                    }
                	} else {
                		System.out.println("	Login first to access this option");
                	}
                    break;
                }
                case (3): {
                    System.out.println("	----------- View Top 5 Movies By Ticket Sales -----------");
                    Movie[] top5Movie = new Movie[5];
                    int[] score = new int[5];
                    int i = 0;
                    int scoreM;
                    int firstScore = -1;
                    for(i=0; i<5; i++) {
                    	top5Movie[i] = movieArrayList.get(0);
                    	score[i] = firstScore;
                    }
                    for(Movie m : movieArrayList) {
                    	scoreM = FileIOController.ticketSalles(m);
                    	if (scoreM>score[0]) {
                    		top5Movie = shiftRank(top5Movie, 0, m);
                    		score = shiftRank(score, 0, scoreM);
                    	}else if(scoreM>score[1]){
                    		top5Movie = shiftRank(top5Movie, 1, m);
                    		score = shiftRank(score, 1, scoreM);
                    	}else if(scoreM>score[2]){
                    		top5Movie = shiftRank(top5Movie, 2, m);
                    		score = shiftRank(score, 2, scoreM);
                    	}else if(scoreM>score[3]){
                    		top5Movie = shiftRank(top5Movie, 3, m);
                    		score = shiftRank(score, 3, scoreM);
                    	}else if(scoreM>score[4]){
                    		top5Movie = shiftRank(top5Movie, 4, m);
                    		score = shiftRank(score, 4, scoreM);
                    	}
                    }
                    for(i=0; i<Math.min(movieArrayList.size(),5);i++) {
                    	System.out.println("Movie: "+top5Movie[i].getMovieTitle()+", total sales: "+score[i]);
                    }
                    break;
                }
                case (4): {
                    // Connect as customer
                    userLoginMenu();
                    break;
                }
                case (5): {
                    // Connect as staff
                    staffLogin();
                    break;
                }
                case (6): {
                    System.out.println("	----------- Program is terminating -----------");
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                }
            }
            System.out.println(" ");
        }
		
		sc.close();	
	}

    private static void staffLogin() {
        ArrayList<String> list = new ArrayList();
        FileIOController.readFile(list, "Staff.txt");
        ArrayList<User> staffAccounts = new ArrayList<>();
        for (String str : list
                ) {
            Scanner stringSpliiter = new Scanner(str).useDelimiter(":");
            User staff = new User(stringSpliiter.next().trim(), stringSpliiter.next().trim());
            staffAccounts.add(staff);
            if (list.isEmpty()) {
                stringSpliiter.close();
            }

        }
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        System.out.println("(1) Login as staff");
        System.out.println("(2) Create new staff account *For demonstration purposes*");
        choice = sc.nextInt();

        switch (choice) {
            case 1: {
                //  To clear the new line character in the buffer
                sc.nextLine();
                System.out.println("Enter Username : ");
                String username = sc.nextLine();
                System.out.println("Enter Password : ");
                String password = sc.nextLine();
                User user = new User(username, password);
                boolean isValidAccount = false;
                for (User usr : staffAccounts
                        ) {
                    if (user.getUsername().contentEquals(usr.getUsername()) && user.getPassword().contentEquals(usr.getPassword())) {
                        isValidAccount = true;
                    }
                }
                if (isValidAccount) {
                    System.out.println("You have successfully logged in as Staff!\nWelcome  " + username);
                    staffMenu();
                } else {
                    System.out.println("Invalid user!");
                }
                break;
            }
            case 2:
                createStaff();
                break;
            default:
                System.out.println("Invalid input!");
        }
    }

    private static void staffMenu() {
        System.out.println("Welcome to Staff Administrative Menu");
        System.out.println("(1) Unassign Seat from movie display");
        System.out.println("(2) Administrate Movies");
        System.out.println("Enter your choice :");
        Scanner staffScan = new Scanner(System.in);
        int choice = staffScan.nextInt();
        switch (choice){
            case 1:
                //  Unassign Seat function..
                SeatAssignmentModule seatAssignmentModule = new SeatAssignmentModule();
                break;
            case 2:
                MoviesList.administrateMovie();
        }
    }

    private static void createStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter desired staff account name : ");
        String username = sc.nextLine().trim();
        System.out.println("Enter desired staff password : ");
        String password = sc.nextLine().trim();
        String datastream = username + " : " + password;
        FileIOController.writeFile(datastream, "Staff.txt");
        System.out.println("Staff account successfully created!");
    }

    private static void movieMenu(Movie movie) {
        //don't try to close the scanners... if more than one ".close()", it generates errors.
        Scanner sc = new Scanner(System.in);

        int movieMenuChoice = 0;

        while (movieMenuChoice != 5) {
            System.out.println("	(1) Book Movie");
            System.out.println("	(2) View all reviews");
            System.out.println("	(3) Submit a review");
            if (!loggedIn) {
                System.out.println("	(4) Login as customer");
            } else {
                System.out.println("	(4) Logout from customer account");
            }
            System.out.println("	(5) Back");
            System.out.println("");

            System.out.print("  	Enter the number of your choice: ");
            movieMenuChoice = sc.nextInt();

            switch (movieMenuChoice) {
                case (1):
                    System.out.println("	----------- Book Movie -----------");
                    if (loggedIn) {
                      ArrayList<MovieDisplay> spefificMovieDisplay = extractDisplays(movie);
                    	int cnt=1;
                    	for(MovieDisplay mD:spefificMovieDisplay) {
                			System.out.println(cnt + " " + mD.getMovieDisplayed().getMovieTitle() + " Cinema Code n°" + mD.getCinemaCode());
                			cnt++;
                    	}
                        System.out.println("  	Enter the number associated to the movie display you want to book");
                        int displayChoice = sc.nextInt();
                        MovieDisplay chosenDisplay = spefificMovieDisplay.get(displayChoice-1);
                        System.out.println("Your display : "+chosenDisplay.getDisplayId());
                        SeatAssignmentModule.bookMenu(movie, customerUser,chosenDisplay);
                    } else {
                        System.out.println("	Login first to access this option");
                    }
                    System.out.println("");
                    break;
                case (2):
                    System.out.println("	----------- View All Reviews -----------");
                    //call review function
                    Review.listAllReviews(movie.getMovieTitle());
                    System.out.println("	----------- End Of All Reviews -----------");
                    break;
                case (3):
                    System.out.println();
                    System.out.println("	----------- Enter a new review -----------");
                    // call new review function
                    System.out.print("	Your email: ");
                    sc.nextLine();
                    String userEmail = sc.nextLine();
                    System.out.print("	Movie rating (1-5): ");
                    int rating = sc.nextInt();
                    while (rating > 5 || rating < 0) {
                        System.out.println("	Please input a rating of 1-5.. ");
                        System.out.print("	Movie rating (1-5 [Best]): ");
                        rating = sc.nextInt();
                    }
                    System.out.print("	Movie rating description: ");
                    sc.nextLine();
                    String movieDesc = sc.nextLine();
                    try {
                        Review.writeReview(movie.getMovieTitle(), rating, movieDesc, userEmail);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                    System.out.print("	----------- Your review submission is successful! -----------");
                    System.out.println();
                    break;

                case (4):
                    // Connect as customer
                    userLoginMenu();
                    break;
                case (5):
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println(" ");
        }
    }
  
    //function for the user login
    public static void userLoginMenu() {
        Scanner sc = new Scanner(System.in);
        if (loggedIn) {
            loggedIn = false;
            System.out.println("	----------- Your were successfully logged out! -----------");
        } else {
            System.out.println("	(1) Already registered");
            System.out.println("	(2) Create a new account");
            System.out.println("	(3) Back");
            int choice = 0;
            int iD;
            String firstName, lastName, username, password;
            choice = sc.nextInt();
            switch (choice) {
                case (1):
                    System.out.println("	Enter your customer iD");
                    iD = sc.nextInt();
                    System.out.println("	Enter your password");
                    password = sc.next();
                    if (iD > size) {
                        System.out.println("Wrong iD");
                    } else {
                        if (allUsers.get(iD - 1).getPassword().compareTo(password) != 0) {
                            System.out.println("Wrong iD or password");
                            System.out.println(allUsers.get(iD - 1).getPassword());
                        } else {
                            customerUser = allUsers.get(iD - 1);
                            loggedIn = true;
                            System.out.println("	----------- Your were succesfully logged in -----------");
                        }
                    }
                    break;
                case (2):
                    System.out.println("	Enter your first name");
                    firstName = sc.next();
                    System.out.println("	Enter your last name");
                    lastName = sc.next();
                    System.out.println("	Enter a password");
                    password = sc.next();
                    customerUser = new User(allUsers.size() + 1, firstName, lastName, password);
                    loggedIn = true;
                    allUsers.add(customerUser);
                    FileIOController.addUsers(customerUser);
                    size++;
                    System.out.println("	----------- Your have succesfully created an account -----------");
                    System.out.println("	----------- Your customer iD is : " + customerUser.getUserID() + " -----------");
                    break;
                case (3):
                    break;
                default:
                    System.out.println("Invalid input");
            }

        }
    }

    public static ArrayList<MovieDisplay> extractDisplays(Movie mv) {
    	ArrayList<MovieDisplay> specificDisplayList = new ArrayList<MovieDisplay>();
        for (MovieDisplay mD : movieDisplayArrayList) {
        	if (mv.getMovieTitle().compareTo(mD.getMovieDisplayed().getMovieTitle()) == 0 &&
        				mD.getMovieDisplayed().getMovieStatus().compareTo("Movie Status: now showing")==0) {
        		specificDisplayList.add(mD);
        	}
        }
        return specificDisplayList;
    }
    
    public static Movie[] shiftRank(Movie[] mTable, int rank, Movie m) {
    	Movie[] ret = new Movie[mTable.length];
    	int i;
    	for(i = 0; i<rank; i++) {
    		ret[i]=mTable[i];
    	}
    	ret[rank]=m;
    	for(i = rank; i<mTable.length-1; i++) {
    		ret[i+1]=mTable[i];
    	}
    	return ret;
    }
    
    public static int[] shiftRank(int[] sTable, int rank, int s) {
    	int[] ret = new int[sTable.length];
    	int i;
    	for(i = 0; i<rank; i++) {
    		ret[i]=sTable[i];
    	}
    	ret[rank]=s;
    	for(i = rank; i<sTable.length-1; i++) {
    		ret[i+1]=sTable[i];
    	}
    	return ret;
    }

}
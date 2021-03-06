package cz2002.moblima.utilities;

import cz2002.moblima.entities.Movie;
import cz2002.moblima.entities.MovieDisplay;
import cz2002.moblima.entities.User;
import cz2002.moblima.modules.MainMenu;
import cz2002.moblima.modules.TicketPrice;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIOController is a utility class in which all FileIO operations are executed from.
 *
 * @author SeatAssignmentModule-CZ2002-SS1-GROUP-01
 */
public class FileIOController {

    private static final String userFileName = "Users.txt";
    private static final String movieDisplayFileName = "movieDisplays.txt";
    private static final String bookingHistFileName = "BookingHistory.txt";
    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    /**
     * Attempts to open a handle to the specified file and adds the data stream to the list
     * Each element is separated by a newline character ['\n']
     * @param movieListings list container to hold information from file
     * @param movies    desired file to read
     */
    public static void readFile(List<String> movieListings, File movies) {
        BufferedReader fr = null;
        try {
            fr = new BufferedReader(new FileReader(movies));
            String str = fr.readLine();
            movieListings.add(str);
            while ((str = fr.readLine()) != null) {
                if (!(str.isEmpty())) {
                    movieListings.add(str);
                }
            }
            //System.out.println(movieListings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to open a handle to the specified filename and adds the data read from the file into the list provided.
     * Each element read is separated by a newline character in File.
     * @param list
     * @param fileName
     * @return
     */
    public static void readFile(List<String> list, String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String dataStream = "";
            while ((dataStream = bufferedReader.readLine()) != null) {
                if (!(dataStream.isEmpty())) {
                    list.add(dataStream);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException fex) {
            System.out.println("File does not exist or is a directory! Please check filename passed!\n File: " + fileName);
            fex.printStackTrace();
        } catch (IOException io) {
            System.out.println("Error occurred while preparing to read file!");
            io.printStackTrace();
        }
    }

    /**
     * Attempts to open a handle to the specified file and writes data to the file
     * @param collect2string    String stream of data to write to file
     * @param movies    desired file to write to
     */
    public static void writeFile(String collect2string, File movies) {
        try {
            FileWriter fw = new FileWriter(movies);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(collect2string);
            fw.write(collect2string);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Opens a handle to specified file with filename and starts
     * in APPEND MODE, i.e. all data is written to the file while retaining existing data
     * @param data
     * @param filename
     */
    public static void writeFile(String data, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addUsers(ArrayList<User> uL) {
        File f = new File(userFileName);
        Path myText_path = Paths.get(f.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        User u;
        for (int i = 0; i < uL.size(); i++) {
            u = uL.get(i);
            lines.add(String.valueOf(u.getUserID()) + " " + u.getFirstName() + " " + u.getLastName()+ " " + u.getPassword());
        }

        try {
            f.createNewFile();
            Files.write(myText_path, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void addUsers(User uL) {
        File f = new File(userFileName);
        Path myText_path = Paths.get(f.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.valueOf(uL.getUserID()) + " " + uL.getFirstName() + " " + uL.getLastName() + " " + uL.getPassword());

        try {
            f.createNewFile();
            Files.write(myText_path, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static ArrayList<User> readUserFile() {
        ArrayList<User> uL = new ArrayList<User>();
        try {
            File f = new File(userFileName);
            Path myText_path = Paths.get(f.toURI());
            List<String> listS = Files.readAllLines(myText_path);
            String[] splitLine;
            User u;
            for (int i = 0; i < listS.size(); i++) {
                splitLine = listS.get(i).split(" ");
                if (splitLine.length<=1){
                	return uL;
                }
                u = new User(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2], splitLine[3]);
                uL.add(u);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return uL;
    }

	public static void addMovieDisplay(MovieDisplay mD) {
        File f = new File(movieDisplayFileName);
        Path myText_path = Paths.get(f.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.valueOf(mD.getDisplayId())+" "+String.valueOf(mD.getSeatsNumber())+" "+mD.getCinemaCode()+" "+
        mD.getMovieDisplayed().getMovieTitle().replace(" ", "_")+" "+df.format(mD.getDateDisplayed()));

        try {
            f.createNewFile();
            Files.write(myText_path, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
	
	public static ArrayList<MovieDisplay> readMovieDisplayFile(ArrayList<Movie> MovieList) {
        ArrayList<MovieDisplay> mDL = new ArrayList<MovieDisplay>();
        try {
            File f = new File(movieDisplayFileName);
            Path myText_path = Paths.get(f.toURI());
            List<String> listS = Files.readAllLines(myText_path);
            String[] splitLine;
            MovieDisplay mD;
            int j;
            for (int i = 0; i < listS.size(); i++) {
                splitLine = listS.get(i).split(" ");
                if (splitLine.length<=1){
                	return mDL;
                }
                //find movie
                Movie m;
                j = 0;
                do {
                	m = MovieList.get(j);
                	j++;
                }while(m.getMovieTitle().compareTo(splitLine[3].replace("_", " "))!=0 && j<MovieList.size());
                if(m.getMovieTitle().compareTo(splitLine[3].replace("_", " "))==0) {
                	mD = new MovieDisplay(Integer.valueOf(splitLine[0]), Integer.valueOf(splitLine[1]), m, splitLine[2], df.parse(splitLine[4]+" "+splitLine[5]));
                	mDL.add(mD);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDL;
    }
  	
  	public static void initSeats() {
    	ArrayList<MovieDisplay> totalMovieDisplay = MainMenu.getAllDisplay();
        ArrayList<String> bookingInformation = new ArrayList<>();
        FileIOController.readFile(bookingInformation, "bookings.txt");
        String[] data;
        String[] seatDetails;
        int custId = 0;
        int displayID = 0;
        int j;
        for (int i = 0; i < bookingInformation.size(); i++) {
                data = bookingInformation.get(i).split("/");
                custId = Integer.parseInt(data[TicketPrice.USER_ID_INDEX]);
                displayID = Integer.parseInt(data[TicketPrice.DISPLAY_ID_INDEX]);
                seatDetails=data[TicketPrice.SEAT_ID_INDEX].split("<");
                j=0;
                while(j<totalMovieDisplay.size() && totalMovieDisplay.get(j).getDisplayId()!=displayID) {
					j++;
				}
				if(i<totalMovieDisplay.size()) {
					totalMovieDisplay.get(j).assignSeat(Integer.parseInt(seatDetails[0]), seatDetails[1].charAt(0), custId);
				}
        }
    }
  	
  	public static boolean displayBookingHist(User u) {
    	ArrayList<MovieDisplay> totalMovieDisplay = MainMenu.getAllDisplay();
        ArrayList<String> bookingInformation = new ArrayList<>();
        FileIOController.readFile(bookingInformation, "bookings.txt");
        String[] data;
        int custId = 0;
        int displayID = 0;
        int j;
        boolean r = false;
        for (int i = 0; i < bookingInformation.size(); i++) {
                data = bookingInformation.get(i).split("/");
                custId = Integer.parseInt(data[TicketPrice.USER_ID_INDEX]);
                displayID = Integer.parseInt(data[TicketPrice.DISPLAY_ID_INDEX]);
                j=0;
                while(j<totalMovieDisplay.size() && totalMovieDisplay.get(j).getDisplayId()!=displayID) {
					j++;
				}
				if(j<totalMovieDisplay.size() && custId==u.getUserID()) {
					System.out.println("transaction Id: "+data[0]+ " movie : "+
							totalMovieDisplay.get(i).getMovieDisplayed().getMovieTitle()+" ticket price: "+data[4]+"$");
					r = true;
				}
        }
        return r;
    }
  	
  	public static int ticketSalles(Movie m) {
    	ArrayList<MovieDisplay> totalMovieDisplay = MainMenu.getAllDisplay();
        ArrayList<String> bookingInformation = new ArrayList<>();
        FileIOController.readFile(bookingInformation, "bookings.txt");
        String[] data;
        int counter = 0;
        int displayID = 0;
        int j;
        for (int i = 0; i < bookingInformation.size(); i++) {
                data = bookingInformation.get(i).split("/");
                displayID = Integer.parseInt(data[TicketPrice.DISPLAY_ID_INDEX]);
                j=0;
                while(j<totalMovieDisplay.size() && totalMovieDisplay.get(j).getDisplayId()!=displayID) {
					j++;
				}
				if(j<totalMovieDisplay.size() && totalMovieDisplay.get(j).getMovieDisplayed().getMovieTitle().compareTo(m.getMovieTitle())==0) {
					counter++;
				}
        }
        return counter;
    }

}
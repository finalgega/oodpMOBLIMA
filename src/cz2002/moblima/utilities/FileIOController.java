package cz2002.moblima.utilities;

import cz2002.moblima.entities.User;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIOController is a utility class in which all FileIO operations are executed from.
 *
 * @author SeatAssignmentModule-CZ2002-SS1-GROUP-01
 */
public class FileIOController {

    private static final String userFileName = "Users.txt";

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

    public static void writeFile(String data, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
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
        File file = new File(userFileName);
        Path myText_path = Paths.get(file.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        User user;
        for (int i = 0; i < uL.size(); i++) {
            user = uL.get(i);
            lines.add(String.valueOf(user.getUserID()) + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword() + " " + user.getDateOfBirth());
        }

        try {
            file.createNewFile();
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
        ArrayList<User> userArrayList = new ArrayList<User>();
        try {
            File file = new File(userFileName);
            Path myText_path = Paths.get(file.toURI());
            List<String> listS = Files.readAllLines(myText_path);
            String[] splitLine;
            User user;
            for (int i = 0; i < listS.size(); i++) {
                splitLine = listS.get(i).split(" ");
                if (splitLine.length == 6)
                    user = new User(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2], splitLine[3], splitLine[4]);
                else
                    user = new User(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2], splitLine[3]);
                userArrayList.add(user);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return userArrayList;
    }


}

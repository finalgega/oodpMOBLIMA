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
 * @author MOBLIMA-CZ2002-SS1-GROUP-01
 */
public class FileIOController {

    /**
     * @param movieListings
     * @param movies
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
     * @param collect2string
     * @param movies
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

    private static final String userFileName = "Users.txt";

    public static void addUsers(User uL) {
        File f = new File(userFileName);
        Path myText_path = Paths.get(f.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        User u;
        for (int i = 0; i < uL.size(); i++) {
            u = uL.get(i);
            lines.add(String.valueOf(u.getUserID()) + " " + u.getFirstName() + " " + u.getLastName());
        }

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
                u = new User(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2]);
                uL.add(u);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return uL;
    }


}

package MainPackage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class Users {
	
	private static final String userFileName = "Users.txt";
	
	public static void addUsers(ArrayList<User> uL) {
		File f = new File(userFileName);
        Path myText_path = Paths.get(f.toURI());
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        User u;
        for(int i = 0; i<uL.size(); i++) {
        	u = uL.get(i);
        	lines.add(String.valueOf(u.getUserID())+" "+u.getFirstName()+" "+u.getLastName());
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
        	for(int i = 0; i < listS.size(); i++) {
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

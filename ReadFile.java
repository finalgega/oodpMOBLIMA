package moblima;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static void readFile (List<String> movieListings, File movies) {
		BufferedReader fr = null;
		try {   
			   fr = new BufferedReader(new FileReader(movies));
			   String str = fr.readLine();
			   movieListings.add(str);
			   while ((str=fr.readLine()) != null) {
				   if (!(str.isEmpty())) {
					   movieListings.add(str);
				   }
			    }
			   //System.out.println(movieListings);
		    }
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}

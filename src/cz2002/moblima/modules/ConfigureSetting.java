package MobileApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;


public class ConfigureSetting {
	BufferedReader br;
	FileReader fr;
	private static final String FILENAME="movies.txt";
	public boolean EndDate() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the movie of your choice: ");
		String moviename=sc.next();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Date ");

		String date = scanner.next();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date date2=null;
		Date date1=null;
		try {
		    //Parsing the String
		    date2 = dateFormat.parse(date);
			String line,line1;
			String []parts;
			fr=new FileReader(FILENAME);
			br=new BufferedReader(fr);
			while((line=br.readLine())!=null) {
				if (line.contains("Movie") && line.contains(moviename)) {
					while((line1=br.readLine())!=null) {
						if (line1.contains("end of showing"))
						{
							parts=line1.split(":");
							date1=dateFormat.parse(parts[1]);
							if (date1.compareTo(date2)>0) {
								//System.out.println("Sorry! The movie is not showing ");
								return true;
							}
						  }
					    }
					
						
					}
				  }
				
			    }
	
	    catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }
			
		catch(FileNotFoundException e) {
			System.out.println("Error openong the file!" +e.getMessage());
			System.exit(0);
		}
	
		catch (IOException e) {

			e.printStackTrace();

		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		return false;
	}
	
	public boolean BookingStatus()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the movie of your choice: ");
		String moviename=sc.next();
		Scanner scanner = new Scanner(System.in);
		
		try {
			String line,line1;
			fr=new FileReader(FILENAME);
			br=new BufferedReader(fr);
			while((line=br.readLine())!=null) {
				if (line.contains(moviename)) {
					while((line1=br.readLine())!=null) {
						if (line1.contains("Movie Status"))
							if (line1.contains("Preview")||line1.contains("Now Showing")){
								return true;
							}
					
						  }
					    }
					
						
					}
				  }
				

		catch(FileNotFoundException e) {
			System.out.println("Error openong the file!" +e.getMessage());
			System.exit(0);
		}
	
		catch (IOException e) {

			e.printStackTrace();

		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		return false;
		
	}
}

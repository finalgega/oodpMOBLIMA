package cz2002.moblima.modules;

import java.io.*;
import java.util.List;
import java.util.Scanner;


public class TicketPrice {
	
	//private Movie movie;
	private int cineplexNumber;
	private int cinemaNumber;
	private static final String FILENAME="movies.txt";
	FileReader fr;
	BufferedReader br;
	private static List<String> movieListings;
	private static double ticketprice;
	private static File movies;

	public static void initiateChargeForTicket() {
		TicketPrice t=new TicketPrice();
	    Boolean flag=true;
		Scanner sc=new Scanner(System.in);
		
		while (flag != false) {
			System.out.println("Enter 1 for charge with type");
			System.out.println("Enter 2 for charge with class");
			System.out.println("Enter 3 for charge with age");
			System.out.println("Enter 4 for charge with day");
			System.out.println("Enter 5 for exit");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:{
			    ticketprice=t.chargeWithType();
				System.out.println(ticketprice);
				break;
				}
			case 2:{
				ticketprice=t.chargeWithClass();
				System.out.println(ticketprice);
				break;
			}
			case 3:{
				ticketprice=t.chargeWithAge();
				System.out.println(ticketprice);
				break;
			}
			case 4:{
				ticketprice=t.chargeWithDay();
				System.out.println(ticketprice);
				break;
			}
			case 5: {
				flag=false;
			    break;
		    }
	     }
	  }
	}

	
	public double chargeWithType(){
		/*BufferedReader fr = null;
		try {   
			   fr = new BufferedReader(new FileReader(movies));
			   String str = fr.readLine();
			   movieListings.add(str);
			   while ((str=fr.readLine()) != null) {
				   if (!(str.isEmpty())) {
					   movieListings.add(str);
				   }
			    }
			   System.out.println(movieListings);
		    }
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}*/
	
		double price=0;
		String line;
		String line1;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the movie of your choice: ");
		String moviename=sc.next();
		try {
			
			fr=new FileReader(FILENAME);
			br=new BufferedReader(fr);
			while((line=br.readLine())!=null) {
				if (line.contains("Movie") && line.contains(moviename)) {
					line1=br.readLine();
					if (line1.contains("Movie Type")) {
						if (line1.contains("3D")) {
							price=10.0;
							//System.out.println("Price="+price);
							//System.out.println(line1);
						}
						
						else if(line1.contains("Blockbuster")) {
							price=8.0;
							//System.out.println("Price="+price);
							//System.out.println(line1);
						}
				    }
				
			    }
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening the file!" + e.getMessage());
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
		return price;
			
		}
	

	
	public double chargeWithClass() {
		TicketPrice t=new TicketPrice();
		double price=t.chargeWithType();
		String ch;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please choose from Platinum, Gold, Ultima");
		ch=sc.next();
		if (ch.contains("Platinum")) {
			price=1.5*price;
			//System.out.println("Price: "+price);
		}
		else if (ch.contains("Gold")) {
				price=1.2*price;
				//System.out.println("Price: "+price);
			}
		else if(ch.contains("Ultima")) {
			//System.out.println("Price: "+price);
		}
		return price;
	
	}
	
	
    public double chargeWithAge() {
    	TicketPrice t=new TicketPrice();
		double price=t.chargeWithType();
    	String ch;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the age: ");
		ch=sc.next();
		if (ch.contains("Student")) {
			price=0.9*price;
			//System.out.println("Price: "+price);
		}
		else if (ch.contains("Adult")) {
				//System.out.println("Price: "+price);
			}
		else if(ch.contains("SeniorCitizen")) {
			price=0.85*price;
			//System.out.println("Price: "+price);
		}
		return price;
		
	}
    
    public double chargeWithDay() {
    	TicketPrice t=new TicketPrice();
		double price=t.chargeWithType();
    	String ch;
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the day: ");
		ch=sc.next();
		if (ch.contains("Monday") || ch.contains("Tuesday") || ch.contains("Wednesday") || ch.contains("Thursday") || ch.contains("Friday")) {
			price=0.85*price;
			//System.out.println("Price: "+price);
		}
		else if (ch.contains("Saturday") || ch.contains("Sunday")) {
				price=1.25*price;
				//System.out.println("Price: "+price);
			}
		return price;
		}
	
  }



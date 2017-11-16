package cz2002.moblima.modules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StoreBooking {
	
	//enter new booking information after every new booking made
	public static void writeBookingInfo(String UserID, String TransactionID, String movieTitle, String DisplayID, String SeatID, int totalTicketPrice, int totalTickets) throws IOException {
	 
		BufferedWriter bw = new BufferedWriter(new FileWriter("BookingHistory.txt", true));
			
			bw.write("User ID:" + UserID + "/" + "Transaction ID:" + TransactionID + "/" + "Movie Title:" + movieTitle + "/"  +"Display ID:" + DisplayID + "/"  + "Seat ID:" +SeatID + "/"  + "Total Price:" + totalTicketPrice + "/" + "Total Tickets:" +totalTickets);
			bw.newLine();
		
		bw.close();
	}
	
	//get total sales for movies
	public static void totalSalesMovies(String movieTitle) {
		int ticketCount = 0;
		
		String line;
		 BufferedReader br = null;
		 try{
			 br = new BufferedReader(new FileReader("BookingHistory.txt"));
			 while((line = br.readLine()) != null){	
				 if (line.toLowerCase().contains("Movie Title:".toLowerCase() + movieTitle.toLowerCase()) == true){
					 ticketCount ++;
				 }
			 }
				 System.out.println("	Total sales: " + ticketCount);
			 }
		 catch(Exception e)
		 { e.printStackTrace();}
		 finally
		 {
			 if (br != null) {
				 try { br.close();} 
				 catch (IOException e) 
				 {e.printStackTrace();}
				}
		 }
      
    }
	
	//get booking history from one customer
		public static void getBookingHistory(String userID) {
			int historyCount = 0;
			
			String line;
			 BufferedReader br = null;
			 try{
				 br = new BufferedReader(new FileReader("BookingHistory.txt"));
				 while((line = br.readLine()) != null){	
					 if (line.toLowerCase().contains("User ID:".toLowerCase() + userID.toLowerCase()) == true){
						 historyCount++;
						 System.out.println("	"+historyCount + ": ");
						 String delimiter = "/";
						 String str = line;
						 String[] splitted = str.split(delimiter);

						 for (String s : splitted) {
						     System.out.println("	" + s);
						 }
						 System.out.println();
						 
					 }
				 }
					 //System.out.println("	Total sales: " + ticketCount);
				 }
			 catch(Exception e)
			 { e.printStackTrace();}
			 finally
			 {
				 if (br != null) {
					 try { br.close();} 
					 catch (IOException e) 
					 {e.printStackTrace();}
					}
			 }
	      
	    }
	
}

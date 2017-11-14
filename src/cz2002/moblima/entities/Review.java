package cz2002.moblima.entities;

import java.io.*;

public class Review {
	private int reviewID;
	
	public int getReviewID(){
		return reviewID;
	}
	
	public int setReviewID (int reviewID){
		return this.reviewID= reviewID;
	}
	
	private int movieRating;
	
	public int getMovieRating(){
		return movieRating;
	}
	
	public int setMovieRating (int movieRating){
		return this.movieRating= movieRating;
	}
	
	private String ratingDesc;
	
	public String getRatingDesc(){
		return ratingDesc;
	}
	
	public String setRatingDesc (String ratingDesc){
		return this.ratingDesc= ratingDesc;
	}
	
	/**
	 * Generates a new review.
	 */
	public static void writeFile(String movieTitle, int rating, String movieDesc, String userEmail) throws IOException {
	 
		BufferedWriter bw = new BufferedWriter(new FileWriter("Review.txt", true));
			
			bw.write("Movie Title: " + movieTitle + "/" + "Review ID: " + Review.createReviewID() + "/" + "Movie Rating: " +rating + "/" +"Rating Description: " + movieDesc + "/" + "Email: " + userEmail);
			bw.newLine();
			//bw.write("Review ID: " + Review.createReviewID());
			//bw.newLine();
			//bw.write("Movie Rating: " +rating);
			//bw.newLine();
			//bw.write("Rating Description: " + movieDesc);
			//bw.newLine();
			
		bw.close();
	}
	
	/**
	 * Generates a new review ID.
	 * @return a new review ID.
	 */
	public static int createReviewID(){
		int count = 1;
		
		String line;
		 BufferedReader br = null;
		 try{
			 br = new BufferedReader(new FileReader("Review.txt"));
			 while((line = br.readLine()) != null){	
				 if (line.contains("Review ID") == true){
					 count++;
				 }
			 }
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
		return count;
	}
	
	public static void ListAllReviews(String movieTitle) {
		int count = 0;
		String line;
		 BufferedReader br = null;
		 try{
			 br = new BufferedReader(new FileReader("Review.txt"));
			 while((line = br.readLine()) != null){	
				 if (line.toLowerCase().contains("Movie Title: ".toLowerCase() + movieTitle.toLowerCase()) == true){
					 count ++;
					 System.out.println("	"+count + ": ");
					 String delimiter = "/";
					 String str = line;
					 String[] splitted = str.split(delimiter);

					 for (String s : splitted) {
					     System.out.println("	" + s);
					 }
					 System.out.println();
					 
				 }
			 }
			 if (count == 0){
				 System.out.println();
				 System.out.println("	----------- There are no reviews made yet! -----------" );
			 }
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
	
	public static void OverallRatingInt(String movieTitle) {
		int avgCount = 0;
		int totalRating = 0;
		int count = 0;
		int ratingCurr = 0;
		
		
		String line;
		 BufferedReader br = null;
		 try{
			 br = new BufferedReader(new FileReader("Review.txt"));
			 while((line = br.readLine()) != null){	
				 if (line.toLowerCase().contains("Movie Title: ".toLowerCase() + movieTitle.toLowerCase()) == true){
					 String reivewLine = line;
				    //System.out.println(reivewLine.substring((reivewLine.lastIndexOf("/") -1)).charAt(0));
				 	ratingCurr = Integer.parseInt(String.valueOf((reivewLine.substring((reivewLine.lastIndexOf("/") -1)).charAt(0))));
				    totalRating = ratingCurr + totalRating;
				    count ++;
				 }
			 }
			 if (count == 0){
				 System.out.println("	----------- There are no reviews made yet! -----------");
			 }
			 else {
				 avgCount = totalRating / count;
				 System.out.println("	Overall Rating: " + avgCount);
			 }
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

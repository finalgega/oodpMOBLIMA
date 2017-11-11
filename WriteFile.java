package moblima;

import java.io.*;

public class WriteFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static void writeFile (String collect2string, File movies) {
		try {
			FileWriter fw = new FileWriter (movies);
			BufferedWriter bw = new BufferedWriter (fw);
			bw.write(collect2string);
			fw.write(collect2string);
			fw.close();
		}
		
		catch (FileNotFoundException e) 
		{
			System.out.println("Error opening the input file!" + e.getMessage());
			System.exit(0);
		}
		
		catch (IOException e) 
		{
			System.out.println("IO Error!" + e.getMessage());
			e.printStackTrace();
		}
	}

}

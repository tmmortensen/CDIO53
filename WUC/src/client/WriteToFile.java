package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	
	public static String filename   = "log.txt";
	
	 public static void save(int dato, int tid, int oprNr, int varenr, int afvejning, int lager) throws FileNotFoundException {
		 try {
			 FileWriter fw =new FileWriter(new File(filename), true);
			 fw.write("\n");
			 fw.write(dato + " | " + tid + " | " + oprNr + " |"+ varenr +"|" +afvejning +"|" + lager +"|");
			 fw.close();
			 } catch (IOException e) {
			 e.printStackTrace();
		 
		 
			 }
	 }
}

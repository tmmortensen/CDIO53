package client;

import java.io.*;

public class WriteToFile {
  
  public void writeLog( String date,String Opr_nummer,int varenummer, int afvejning) {
    String filename = "log.txt";

    try {
      FileWriter output = new FileWriter( filename );

      output.write( "dato " + date +  "\n" );
      output.write( "oprNr" + Opr_nummer + "\n" );
      output.write( "varenr" + varenummer + "\n" );
      output.write( "afvejning" + afvejning + "\n" );

      
      output.close();
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "Filen kunne ikke oprettes: " + filename );
    }
    catch ( IOException e ) {
      System.out.println( "Der opstod fejl under skrivning til: " + filename );
    }
    
  }

  public void readLog(){
	  String filename = "log.txt";
    try {
      FileReader input = new FileReader( filename );

      while ( input.ready() )
        System.out.print( (char) input.read() );

      input.close();
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "Filen findes ikke: " + filename );
    }
    catch ( IOException e ) {
      System.out.println( "Der opstod fejl under l?sning fra: " + filename );
    }
  }
  
  
  
  
}
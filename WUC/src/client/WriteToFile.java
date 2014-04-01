package client;

import java.io.*;

public class WriteToFile {
  
  public static void main( String[] argv ) {
    String filename = "test.txt";

    try {
      FileWriter output = new FileWriter( filename );

      output.write( "dato " + "\n" );
      output.write( "tid" + "\n" );
      output.write( "oprNr" + "\n" );
      output.write( "varenr" + "\n" );
      output.write( "afvejning" + "\n" );
      output.write( "lager" + "\n" );

      
      output.close();
    }
    catch ( FileNotFoundException e ) {
      System.out.println( "Filen kunne ikke oprettes: " + filename );
    }
    catch ( IOException e ) {
      System.out.println( "Der opstod fejl under skrivning til: " + filename );
    }

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
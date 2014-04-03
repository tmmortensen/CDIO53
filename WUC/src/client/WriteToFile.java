package client;
import java.io.*;


public class WriteToFile {
	BufferedReader inputreader;
	BufferedWriter writer;

	public void writeLog(String date, String Opr_nummer, int varenummer,
			int afvejning) {
		String filename = "log.txt";

		try {
			FileWriter output = new FileWriter(filename);

			output.write("dato " + date + "\n");
			output.write("oprNr" + Opr_nummer + "\n");
			output.write("varenr" + varenummer + "\n");
			output.write("afvejning" + afvejning + "\n");

			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Filen kunne ikke oprettes: " + filename);
		} catch (IOException e) {
			System.out.println("Der opstod fejl under skrivning til: "
					+ filename);
		}

	}

	public void readLog() {
		String filename = "log.txt";
		try {
			FileReader input = new FileReader(filename);

			while (input.ready())
				System.out.print((char) input.read());

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Filen findes ikke: " + filename);
		} catch (IOException e) {
			System.out
					.println("Der opstod fejl under l?sning fra: " + filename);
		}
	}

	public void writeStore() {
		if (new File("C:\\Users\\Morten\\workspace\\WUC\\Store.txt") != null){
		File outputfile = new File("Store.txt");
		try {
			writer = new BufferedWriter(new FileWriter(outputfile));
			writer.write("Mintpastillen");
			((BufferedWriter) writer).newLine();
			writer.write("Lækkerpillen");
			((BufferedWriter) writer).newLine();
			writer.write("Superpillen");
			((BufferedWriter) writer).newLine();
			writer.write("StivDolkpillen");
			((BufferedWriter) writer).newLine();
			writer.write("Usynlighedspillen");
			((BufferedWriter) writer).newLine();
			writer.write("Ildpillen");
			((BufferedWriter) writer).newLine();
			writer.write("AvForDen-pillen");
			((BufferedWriter) writer).newLine();
			writer.write("Anus-pillen");
			((BufferedWriter) writer).newLine();
			writer.write("Hurtigpillen");
			((BufferedWriter) writer).newLine();
			writer.write("Sovepillen (if you know what i mean)");
			((BufferedWriter) writer).newLine();
			writer.close();
		} catch (IOException e) {}
		
		
		}
		else {
			System.out.println("Jammen du har jo filen skattebasse");
		}
		
	}

	

	public void readStore() {

		try {
			File inputfile = new File("Store.txt");
			inputreader = new BufferedReader(new FileReader(inputfile));
			String fileText = inputreader.readLine();
			System.out.println(fileText);
			inputreader.close();

		} catch (Exception ex) {
			System.out.println("Omg someting went wrong");
		}

	}

}
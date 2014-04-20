package client;

import java.io.*;

public class WriteToFile {
	BufferedReader inputreader;
	BufferedWriter writer;

	public void writeLog(String date, String Opr_nummer, int varenummer,
			double netto) {
		String filename = "log.txt";

		try {
			FileWriter output = new FileWriter(filename);

			output.write("dato " + date + "\n ");
			output.write("oprNr " + Opr_nummer + "\n ");
			output.write("varenr " + varenummer + "\n ");
			output.write("netto " + netto + "\n ");

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
		if (new File("Store.txt").exists()) {
			System.out.println("Filen eksistere allerede");
		} else {
			File outputfile = new File("Store.txt");
			try {
				writer = new BufferedWriter(new FileWriter(outputfile));
				writer.write("Mintpastillen");
				((BufferedWriter) writer).newLine();
				writer.write("L�kkerpillen");
				((BufferedWriter) writer).newLine();
				writer.write("Superpillen");
				((BufferedWriter) writer).newLine();
				writer.write("StivDolkpillen");
				((BufferedWriter) writer).newLine();
				writer.write("Usynlighedspillen");
				((BufferedWriter) writer).newLine();
				writer.write("Ildpillen");
				((BufferedWriter) writer).newLine();
				writer.write("AvAvAv-pillen");
				((BufferedWriter) writer).newLine();
				writer.write("*****-pillen");
				((BufferedWriter) writer).newLine();
				writer.write("Hurtigpillen");
				((BufferedWriter) writer).newLine();
				writer.write("Sovepillen (if you know what i mean)");
				((BufferedWriter) writer).newLine();
				writer.close();
				System.out.println("yaay filen blev skrevet");
			} catch (IOException e) {
				System.out
						.println("Der skete en fejl under skrivningen af filen");
			}

		}

	}

	public String readStore(int varenummer) {

		try {
			File inputfile = new File("Store.txt");
			inputreader = new BufferedReader(new FileReader(inputfile));
			LineNumberReader ln = new LineNumberReader(inputreader);
			ln.setLineNumber(varenummer);
			
			if (ln.getLineNumber() <= 10) {
				if (varenummer == 0) {
					String x = ln.readLine();
					System.out.println(x);
					return x;
				}
				for (int i = 0; i + 1 <= varenummer; i++) {
					ln.readLine();
					if (i + 1 == varenummer) {
						String x = ln.readLine();
						System.out.println(x);
						return x;
					}

				}

			}
			else {
				System.out.println("Du skulle have valgt et vare nummer mellem 0 og 9");
			}

			// String fileText = inputreader.readLine();
			// System.out.println(fileText);

			inputreader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Filen blev ikke fundet");
		} catch (Exception ex) {
			System.out.println("Noget gik galt under l�sningen af filen");
		}
		return null;
	}

}

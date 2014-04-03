package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class TCPConnect {

	public void Run() {
		boolean notFound = true;
		Scanner input = new Scanner(System.in);
		String sentence, varenavn, choice;
		String modifiedSentence;
		Integer varenummer = 0, afvejning = 0, Tara = 0, Netto = 0;
		WriteToFile writeToFile = new WriteToFile();

		System.out.println("Ønsker du at lave en afvejning:   y/n?");
		String startChoice = input.next();

		if (startChoice.equalsIgnoreCase("y")) {
			try {

				// kode til at oprette de forskellige TCP lyttere og skrivere s�
				// vi kan kommunikere med v�gten.
				Socket clientSocket = new Socket("localhost", 8000);

				DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());

				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

				// her påbegyndes sekvensen til vægten, der bliver skrevet RM20
				// 4 kommandoen ud og der programmet fortsætter først når der
				// modtages en RM20 A sekvens.
				sentence = "RM20 4" + "\" indtast operatoer nummer\"" + "\"\""
						+ "\"\"";
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine();
				if (modifiedSentence.substring(0, 5).equals("RM20 A")) {
					String OprNummer = modifiedSentence.substring(6);
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
							.format(Calendar.getInstance().getTime());
					writeToFile.writeLog(timeStamp, OprNummer, varenummer,
							afvejning);

					// her tjekkes der om en vare med det givne varenummer rent
					// faktisk findes, hvis ikke bliver man sendt tilbage igen.
					while (notFound == true) {
						sentence = "RM20 4" + "\" Vare nr?\"" + "\"\"" + "\"\"";
						outToServer.writeBytes(sentence + "\r\n");
						modifiedSentence = inFromServer.readLine();
						if (writeToFile
								.readStore(modifiedSentence.substring(6)) != null) {
							varenavn = writeToFile.readStore(modifiedSentence
									.substring(6));
							System.out
									.println("er dette den ønskede vare: y/n? "
											+ varenavn);
							choice = input.next();
							// nu startes selve afvejningsproceduren, hvis det
							// var den ønskede vare man fik frem
							if (choice.equalsIgnoreCase("y")) {
								notFound = false;
								writeToFile.writeLog(timeStamp, OprNummer,
										varenummer, afvejning);
								System.out
										.println("påsæt skål og lignende og tast enter");
								input.next();
								outToServer.writeBytes("T");
								outToServer.writeBytes("S");
								Tara = Integer.parseInt(inFromServer.readLine()
										.substring(2));
								System.out
										.println("påfyld nu varen og tast enter");
								input.next();
								outToServer.writeBytes("S");
								Netto = Integer.parseInt(inFromServer
										.readLine().substring(2));
								System.out
										.println("vejningen er nu slut og resultatet er blevet gemt.");
								outToServer.writeBytes("T");

							}

						}

					}

				}

				clientSocket.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		input.close();
	}

}
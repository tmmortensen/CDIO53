package client;

import java.awt.SystemTray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.lang.*;

public class SocketConnect {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private double brutto = 0, tara = 0, netto = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;

	public SocketConnect() {

	}

	public void initiate() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.writeBytes("S" + "\r\n");
			modifiedSentence = inFromServer.readLine().substring(9, 14);
			System.out.println("modtaget " + modifiedSentence);

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public String identify() {
		String return_sentence = null;
		try {
			sentence = "RM20 4 \"indtast operatoer nummer\" \"dette slettes\" \"nr\"";
			outToServer.writeBytes(sentence + "\r\n");
			modifiedSentence = inFromServer.readLine();
			System.out.println(modifiedSentence);
			while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
				modifiedSentence = inFromServer.readLine();
				System.out.println(modifiedSentence);
			}
			return_sentence = modifiedSentence.substring(7);

		}

		catch (IOException e) {
			e.getMessage();
		}
		return return_sentence;
	}

	public int varenummer() {

		try {

			sentence = "RM20 4 \"indtast varenr\" \" \" \" \"";
			outToServer.writeBytes(sentence + "\r\n");
			modifiedSentence = inFromServer.readLine();
			while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
				modifiedSentence = inFromServer.readLine();

			}
			int varenummer = Integer.parseInt(modifiedSentence.substring(7));
			return varenummer;
		} catch (IOException e) {
			e.getMessage();
		}
		return -1;
	}

	public void weightTara() {
		try {

			outToServer.writeBytes("T");

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public double waitForBowl() {
		try {

			outToServer.writeBytes("S" + "\r\n");
			modifiedSentence = inFromServer.readLine().substring(9, 14);

			tara = Double.parseDouble(modifiedSentence);
			int retval = Double.compare(tara, 0.0);
			while (retval <= 0) {
				outToServer.writeBytes("S" + "\r\n");
				modifiedSentence = inFromServer.readLine().substring(9, 14);

				tara = Double.parseDouble(modifiedSentence);
				retval = Double.compare(tara, 0.0);
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return tara;
	}

	public double getWeight() {
		try {

			outToServer.writeBytes("S" + "\r\n");
			modifiedSentence = inFromServer.readLine().substring(9, 14);

			netto = Double.parseDouble(modifiedSentence);
			System.out.println("netto modtaget: " + netto);
		} catch (Exception e) {
			e.getMessage();
		}

		return netto;
	}

	public double getBrutto() {
		try {

			outToServer.writeBytes("S" + "\r\n");
			modifiedSentence = inFromServer.readLine().substring(8, 14);

			brutto = Double.parseDouble(modifiedSentence);

			while (brutto >= 0.0) {
				outToServer.writeBytes("S" + "\r\n");
				modifiedSentence = inFromServer.readLine().substring(8, 14);

				brutto = Double.parseDouble(modifiedSentence);

			}

		} catch (Exception e) {
			e.getMessage();
		}
		return brutto;
	}

	public void closeConnection() {
		try {
			clientSocket.close();
			outToServer.close();
			inFromServer.close();
		} catch (Exception e) {
			e.getMessage();
		}

	}
}

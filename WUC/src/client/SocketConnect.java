package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketConnect {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private int afvejning = 0, Tara = 0, Netto = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;

	public SocketConnect() {

	}

	public String identify() {
		String return_sentence = null;
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

		} catch (Exception e) {
			e.getMessage();
		}
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

	public void initiate() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.writeBytes("S" + "\r\n");

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public int varenummer() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

		} catch (Exception e) {
			e.getMessage();
		}
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
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.writeBytes("T");

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public boolean waitForBowl() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes("S");
			Double doubleSentence = Double.parseDouble(inFromServer.readLine()
					.substring(3));
			while (doubleSentence == 0.0) {
				outToServer.writeBytes("S");
				doubleSentence = Double.parseDouble(inFromServer.readLine()
						.substring(3));
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}
}

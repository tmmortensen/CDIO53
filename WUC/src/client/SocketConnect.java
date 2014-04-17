package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketConnect {
	String sentence, varenavn, choice;
	String modifiedSentence;
	Integer varenummer = 0, afvejning = 0, Tara = 0, Netto = 0;
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
			sentence = "\"RM20 4\" \"indtast operatoer nummer\" \"\" \"nr\"";
			outToServer.writeBytes(sentence + "\r\n");
			modifiedSentence = inFromServer.readLine();
			while (!modifiedSentence.substring(0, 5).equals("RM20 A")) {
				modifiedSentence = inFromServer.readLine();

			}
			return_sentence = modifiedSentence.substring(6);

		}

		catch (IOException e) {
			e.getMessage();
		}
		return return_sentence;
	}

	public String varenavn() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

		} catch (Exception e) {
			e.getMessage();
		}
		try {

			sentence = "\"RM20 4\" \"indtast varenr\" \"\" \"\"";
			outToServer.writeBytes(sentence + "\r\n");
			modifiedSentence = inFromServer.readLine();
			while (!modifiedSentence.substring(0, 5).equals("RM20 A")) {
				modifiedSentence = inFromServer.readLine();

			}
			varenavn = modifiedSentence.substring(6);

		} catch (IOException e) {
			e.getMessage();
		}
		return varenavn;
	}

}

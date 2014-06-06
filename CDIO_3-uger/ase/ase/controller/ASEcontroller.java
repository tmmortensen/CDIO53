package ase.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ASEcontroller {
	
	
	public ASEcontroller() {

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
	
	
}
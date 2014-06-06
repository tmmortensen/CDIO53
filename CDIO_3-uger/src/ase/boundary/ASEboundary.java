package ase.boundary;
import java.io.IOException;
import java.awt.SystemTray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.lang.*;
import java.util.Scanner;

public class ASEboundary {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private double brutto = 0, tara = 0, netto = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	Scanner scan = new Scanner(System.in);

	
public String nameidentify() {
	System.out.println("Indtast deres brugerID");
	int tempID = scan.nextInt();
	
	//find det navn tilhørende det modtaget id
	
	outToServer.writeBytes("RM20 8 \"Goddag \" " + getUserName(tempID) + "\"Er dette dem?\"");

		String return_sentence = null;
		try {
			sentence = "RM20 8 \"Er dit navn\" + Opr_navn \"dette slettes\" \"nr\"";
			outToServer.writeBytes("RM20 8  \"Er dit navn\"" + Opr_navn "\"dette slettes\" \"nr\"" + "\r\n");
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

}
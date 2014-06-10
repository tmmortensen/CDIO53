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
	private double brutto = 0, tara = 0, netto = 0, beholderTara = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private int tempProduktBatch;
	
	public boolean name() {
		//Skal bede om ID på bruger, sætte navnet på vægten og bede om at godkende.
		
		return true;
		
	}

	public int produktbatch() {
		//Skal vise navnet på vægten og returnere produktbatch nummeret.
		
		return 5;
	}

	public void ulastet() {
		// Beder operatøren om at aflaste vægten og trykke op.
		
	}

	public double beholder() {
		// Skal bede operatøren om at sætte en beholder på vægten og trykke ok.
		// Returnere en double tara
		return 2.2;
	}

	public void tara() {
		// Vægten bliver tareret.
		
	}

	public int raavareBatch() {
		// Beder operatøren om at indtaste et raavarenummer(På vægten) og returnere så dette nr.
		return 0;
	}

	public double weight() {
		// Skal returnere vægten når man har trykket ok på vægten.
		return 2.5;
	}

	public boolean doMore() {
		// Spørg om han vil veje mere, return true hvis han trykker ok.
		return false;
	}
}

//
//public string write()
//public String getName(int tempID){
//	outToServer.writeBytes("RM20 8 \"Hello\"" + "\"" + getUsername(tempID) + "\"" + "\" is this you\"" );
//	return sentence;
//}
//
//public boolean validate(){
//	if()	
//}
//
//public String nameidentify() {
//	System.out.println("Indtast deres brugerID");
//	int tempID = scan.nextInt();
//	
//	//find det navn tilh�rende det modtaget id
//	try{
//		outToServer.writeBytes("RM20 8 \"Goddag \" " + getUserName(tempID) + "\"Er dette dem?\"");
//		modifiedSentence = inFromServer.readLine();
//		System.out.println(modifiedSentence);
//			if(!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
//				System.out.println("Dette var ikke dem. Indtast venligst et andet brugerID.");
//				modifiedSentence = inFromServer.readLine();
//			}
//	}
//	catch(IOException e){
//		e.getMessage();
//	}
//	System.out.println("Indtast et produktbatch nummer");
//	tempProduktBatch = scan.nextInt();
//
//	try{
//		outToServer.writeBytes("RM20 8 \"De ønsker at lave en recept på følgende:\"" + "\"" + getReceptNavn(tempProduktBatch) + "\"" + "\"Aflast venligst vægten\"")
//		modifiedSentence = inFromServer.readLine();
//			while(!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")){
//				modifiedSentence = inFromServer.readLine();
//			}
//	}
//	catch(IOException e){
//		e.getMessage();
//	}
//	try{
//		outToServer.writeBytes("RM20 \"Påsæt venligst første tara beholder");
//		modifiedSentence = inFromServer.readLine();
//			while(!modifiedSentence.subSequence(0, 6).equals("RM20 A")){
//				modifiedSentence = inFromServer.readLine();
//			}
//			outToServer.writeBytes("B");
//			beholderTara = inFromServer.readLine();
//	}
//	catch(IOException e){
//		e.getMessage();
//	}
//		String return_sentence = null;
//		try {
//			sentence = "RM20 8 \"Er dit navn\" + Opr_navn \"dette slettes\" \"nr\"";
//			outToServer.writeBytes("RM20 8  \"Er dit navn\"" + Opr_navn "\"dette slettes\" \"nr\"" + "\r\n");
//			modifiedSentence = inFromServer.readLine();
//			System.out.println(modifiedSentence);
//			while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
//				modifiedSentence = inFromServer.readLine();
//				
//				System.out.println(modifiedSentence);
//			}
//			return_sentence = modifiedSentence.substring(7);
//
//		}
//
//		catch (IOException e) {
//			e.getMessage();
//		}
//		return return_sentence;
//	}
//
//public int varenummer() {
//
//	try {
//
//		sentence = "RM20 4 \"indtast varenr\" \" \" \" \"";
//		outToServer.writeBytes(sentence + "\r\n");
//		modifiedSentence = inFromServer.readLine();
//		while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
//			modifiedSentence = inFromServer.readLine();
//
//		}
//		int varenummer = Integer.parseInt(modifiedSentence.substring(7));
//		return varenummer;
//	} catch (IOException e) {
//		e.getMessage();
//	}m
//	return -1;
//}
//
//}
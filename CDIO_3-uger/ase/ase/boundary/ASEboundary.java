package ase.boundary;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.net.Socket;

import ase.controller.ASEcontroller;

public class ASEboundary {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private double brutto = 0, tara = 0, netto = 0, beholderTara = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private int tempProduktBatch;

	ASEcontroller controller;

	public boolean name() {
		// Skal bede om ID på bruger, sætte navnet på vægten og bede om at
		// godkende.

		int tempOpr_ID = 0;
//		try {
			sentence = "RM20 4 \"indtast operatoer nummer\" \"dette slettes\" \"nr\"";
			// outToServer.writeBytes(sentence + "\r\n");
			// modifiedSentence = inFromServer.readLine();
			System.out.println(sentence + "\n");

//			while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
//				modifiedSentence = inFromServer.readLine();
//
//			}
			// tempOpr_ID =
			// Integer.parseInt(modifiedSentence.substring(7).trim());
			tempOpr_ID = 1;
//		}
//
//		catch (IOException e) {
//			e.getMessage();
//		}

		String oprName = controller.getOprName(tempOpr_ID);
		try {
			System.out.println("Goddag " + oprName
					+ "hvis dette er dem tryk 1, ellers tryk 2");
			// outToServer.writeBytes("RM20 8 \"Goddag \" " + oprName
			// + "\"hvis dette er dem tryk 1, ellers tryk 2\"");
			// modifiedSentence = inFromServer.readLine().trim();
			modifiedSentence = "1";
			if (modifiedSentence.equals("1")) {
				return true;
			} else {

				if (modifiedSentence.equals("2")) {
					sentence = "RM20 4 \"Dette var ikke dem. vi starter forfra \" \"dette slettes\" \"nr\"";
					outToServer.writeBytes(sentence + "\r\n");
					name();
				}

			}

			return false;
		}

		catch (IOException e) {
			e.getMessage();
		}
		return false;

	}

	// public int produktbatch() {
	// // Skal vise navnet p� produktbatch p� v�gt og returnere produktbatch
	// // nummeret.
	//
	// int inputproductbatch = 0;
	// try {
	// sentence =
	// "RM20 4 \"indtast det �snkede produktbatch nummer\" \"dette slettes\" \"nr\"";
	// outToServer.writeBytes(sentence + "\r\n");
	// modifiedSentence = inFromServer.readLine();
	//
	// while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
	// modifiedSentence = inFromServer.readLine();
	//
	// }
	// inputproductbatch =
	// Integer.parseInt(modifiedSentence.substring(7).trim());
	// }
	//
	// catch (IOException e) {
	// e.getMessage();
	// }
	//
	// // String productbatchname = ;
	// // try {
	// // sentence = "RM20 8 \"Det valgte produktbatch er: \" " +
	// productbatchname + "\" ";
	// // outToServer.writeBytes(sentence + "\r\n");
	// // modifiedSentence = inFromServer.readLine();
	// //
	// // while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
	// // modifiedSentence = inFromServer.readLine();
	// //
	// // }
	// // inputproductbatch =
	// Integer.parseInt(modifiedSentence.substring(7).trim());
	// // }
	//
	// catch (IOException e) {
	// e.getMessage();
	// }
	//
	// return inputproductbatch;
	// }

	public void aflastet() {
		// Beder operatøren om at aflaste vægten og trykke op.

	}

	public double beholder() {
		// Skal bede operatøren om at sætte en beholder på vægten og trykke
		// ok.
		// Returnere en double for vægten.
		return 2.2;
	}

	public void tara() {
		// Vægten bliver tareret.

	}

	public int raavareBatch() {
		// Beder operatøren om at indtaste et raavarenummer(På vægten) og
		// returnere så dette nr.
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

	public boolean stop() {
		// Skal returnere true, hvis der bliver trykket cancel på vægten.
		// "RM20_C"
		return false;
	}

	public boolean newUser() {
		// Skal spørge om man vil indtaste et andet brugerID.
		return false;
	}
}

//
// public string write()
// public String getName(int tempID){
// outToServer.writeBytes("RM20 8 \"Hello\"" + "\"" + getUsername(tempID) + "\""
// + "\" is this you\"" );
// return sentence;
// }
//
// public String nameidentify() {
// System.out.println("Indtast deres brugerID");
// int tempID = scan.nextInt();
//
// //find det navn tilh�rende det modtaget id
// try{
// outToServer.writeBytes("RM20 8 \"Goddag \" " + getUserName(tempID) +
// "\"Er dette dem?\"");
// modifiedSentence = inFromServer.readLine();
// System.out.println(modifiedSentence);
// if(!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
// System.out.println("Dette var ikke dem. Indtast venligst et andet brugerID.");
// modifiedSentence = inFromServer.readLine();
// }
// }
// catch(IOException e){
// e.getMessage();
// }
// System.out.println("Indtast et produktbatch nummer");
// tempProduktBatch = scan.nextInt();
//
// try{
// outToServer.writeBytes("RM20 8 \"De ønsker at lave en recept på følgende:\""
// + "\"" + getReceptNavn(tempProduktBatch) + "\"" +
// "\"Aflast venligst vægten\"")
// modifiedSentence = inFromServer.readLine();
// while(!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")){
// modifiedSentence = inFromServer.readLine();
// }
// }
// catch(IOException e){
// e.getMessage();
// }
// try{
// outToServer.writeBytes("RM20 \"Påsæt venligst første tara beholder");
// modifiedSentence = inFromServer.readLine();
// while(!modifiedSentence.subSequence(0, 6).equals("RM20 A")){
// modifiedSentence = inFromServer.readLine();
// }
// outToServer.writeBytes("B");
// beholderTara = inFromServer.readLine();
// }
// catch(IOException e){
// e.getMessage();
// }
// String return_sentence = null;
// try {
// sentence = "RM20 8 \"Er dit navn\" + Opr_navn \"dette slettes\" \"nr\"";
// outToServer.writeBytes("RM20 8  \"Er dit navn\"" + Opr_navn
// "\"dette slettes\" \"nr\"" + "\r\n");
// modifiedSentence = inFromServer.readLine();
// System.out.println(modifiedSentence);
// while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
// modifiedSentence = inFromServer.readLine();
//
// System.out.println(modifiedSentence);
// }
// return_sentence = modifiedSentence.substring(7);
//
// }
//
// catch (IOException e) {
// e.getMessage();
// }
// return return_sentence;
// }
//
// public int varenummer() {
//
// try {
//
// sentence = "RM20 4 \"indtast varenr\" \" \" \" \"";
// outToServer.writeBytes(sentence + "\r\n");
// modifiedSentence = inFromServer.readLine();
// while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
// modifiedSentence = inFromServer.readLine();
//
// }
// int varenummer = Integer.parseInt(modifiedSentence.substring(7));
// return varenummer;
// } catch (IOException e) {
// e.getMessage();
// }m
// return -1;
// }
//
// }
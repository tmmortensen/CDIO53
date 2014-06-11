package ase.boundary;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.net.Socket;

import ase.controller.ASEcontroller;

public class ASEboundary {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private double brutto = 0, tara = 0, netto = 0, beholderTara = 0,
			weight = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private int tempProduktBatch;
	int tempOpr_ID;
	ASEcontroller controller;

	public boolean name() {
		// Skal bede om ID pÃ¥ bruger, sÃ¦tte navnet pÃ¥ vÃ¦gten og bede om at
		// godkende.

		// try {
		sentence = "RM20 4 \"indtast operatoer nummer\" \"dette slettes\" \"nr\"";
		// outToServer.writeBytes(sentence + "\r\n");
		// modifiedSentence = inFromServer.readLine();
		System.out.println(sentence + "\n");

		// while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A"))
		// {
		// modifiedSentence = inFromServer.readLine();
		//
		// }
		// tempOpr_ID =
		// Integer.parseInt(modifiedSentence.substring(7).trim());
		tempOpr_ID = 1;
		System.out.println("NÃ¥r vi hertil? " + tempOpr_ID);
		int opr_ID = tempOpr_ID;
		sendOprIDToDB();

		// }
		//
		// catch (IOException e) {
		// e.getMessage();
		// }
		// String oprName = controller.getOprName(opr_ID);
		// try {
		// System.out.println("Goddag " + oprName
		// + "hvis dette er dem tryk 1, ellers tryk 2");
		// // outToServer.writeBytes("RM20 8 \"Goddag \" " + oprName
		// // + "\"hvis dette er dem tryk 1, ellers tryk 2\"");
		// // modifiedSentence = inFromServer.readLine().trim();
		// modifiedSentence = "1";
		// if (modifiedSentence.equals("1")) {
		// return true;
		// } else {
		//
		// if (modifiedSentence.equals("2")) {
		// sentence =
		// "RM20 4 \"Dette var ikke dem. vi starter forfra \" \"dette slettes\" \"nr\"";
		// outToServer.writeBytes(sentence + "\r\n");
		// name();
		// }
		//
		// }
		//
		// return false;
		// }
		//
		// catch (IOException e) {
		// e.getMessage();
		// }
		return false;

	}

	public int sendOprIDToDB() {
		// ID sendt til controlleren
		return tempOpr_ID;

	}

	 public int produktbatch() {
	 // Skal vise navnet pï¿½ produktbatch pï¿½ vï¿½gt og returnere
	// produktbatch nummeret.
	
	 int inputproductbatch = 0;
	 try {
	 sentence =
	 "RM20 4 \"indtast det ï¿½snkede produktbatch nummer\" \"dette slettes\" \"nr\"";
	 outToServer.writeBytes(sentence + "\r\n");
	 modifiedSentence = inFromServer.readLine();
	
	 while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
	 modifiedSentence = inFromServer.readLine();
	
	 }
	 inputproductbatch =
	 Integer.parseInt(modifiedSentence.substring(7).trim());
	 }
	
	 catch (IOException e) {
	 e.getMessage();
	 }
	
	  String productbatchname = // en metode til controller som skal til data der titter i sqldatabasen ; ;
	  try {
	  sentence = "RM20 8 \"Det valgte produktbatch er: \" " +
	 productbatchname + "\" ";
	  outToServer.writeBytes(sentence + "\r\n");
	  modifiedSentence = inFromServer.readLine();
	 
	  while (!modifiedSentence.substring(0, 6).equalsIgnoreCase("RM20 A")) {
	  modifiedSentence = inFromServer.readLine();
	 
	  }
	  inputproductbatch =
	 Integer.parseInt(modifiedSentence.substring(7).trim());
	  }
	
	 catch (IOException e) {
	 e.getMessage();
	 }
	
	 return inputproductbatch;
	 }

	public void aflastet() {
		// Beder operatÃ¸ren om at aflaste vÃ¦gten og trykke op.
		do {
			try {
				sentence = "RM20 A \"aflast venligst v稴en og tryk p報\" \"nr\"";
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine().substring(7);
			} catch (IOException e) {
				e.getMessage();
			}

		} while (!modifiedSentence.equals("1"));
	}

	public double beholder() {
		// Skal bede operatÃ¸ren om at sÃ¦tte en beholder pÃ¥ vÃ¦gten og trykke
		// ok.
		// Returnere en double for vÃ¦gten.

		do {
			try {
				sentence = "RM20 A \"S素venligst tom beholder p塶稴 og tryk '1', n沠du er klar\" \"nr\"";
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine().substring(7);
			} catch (IOException e) {
				e.getMessage();
			}

		} while (!modifiedSentence.equals("1"));

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

	public void tara() {
		// VÃ¦gten bliver tareret.
		try {

			outToServer.writeBytes("T");

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public int raavareBatch() {
		// Beder operatÃ¸ren om at indtaste et raavarenummer(PÃ¥ vÃ¦gten) og
		// returnere sÃ¥ dette nr.
		return 0;
	}

	public double weight() {
		// Skal returnere vÃ¦gten nÃ¥r man har trykket ok pÃ¥ vÃ¦gten.
		do {
			try {
				sentence = "RM20 A \"tryk '1', n沠v稴en skal gemme den nuv糥nde v糤i\" \"nr\"";
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine().substring(7);
			} catch (IOException e) {
				e.getMessage();
			}

		} while (!modifiedSentence.equals("1"));

		try {
			outToServer.writeBytes("S" + "\r\n");
			modifiedSentence = inFromServer.readLine().substring(9, 14);

			weight = Double.parseDouble(modifiedSentence);
		} catch (Exception e) {
			e.getMessage();
		}

		return weight;
	}

	public boolean doMore() {
		// SpÃ¸rg om han vil veje mere, return true hvis han trykker ok.
		try {
			sentence = "RM20 4 \"Vil du veje mere? (tryk '1' for ja og '2' for nej)\" \"\" \"nr\"";
			outToServer.writeBytes(sentence + "\r\n");
			modifiedSentence = inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (modifiedSentence == "1") {
			return true;
		} else
			return false;
	}

//	public boolean stop() {
//		// Skal returnere true, hvis der bliver trykket cancel pÃ¥ vÃ¦gten.
//		// "RM20_C"
//		return false;
//	}
//
//	public boolean newUser() {
//		// Skal spÃ¸rge om man vil indtaste et andet brugerID.
//		return false;
//	}
}
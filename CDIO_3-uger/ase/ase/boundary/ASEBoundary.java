package ase.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import simulator.data.IProgramState;

/**
 * Class to take input from a client
 * 
 * @author Gruppe 53
 * 
 */
public class ASEBoundary implements IASEBoundary {
	boolean IDforespørgelse = false, commoditycheck = false;
	BufferedReader consoleReader;
	IProgramState iprogramState;
	private DataOutputStream consoleOutput;
	private String modifiedSentence;
	private int userID, productBatchID;

	/**
	 * Constructor that makes the program ready for user inputs
	 */
	public ASEBoundary() {

		consoleReader = new BufferedReader(new InputStreamReader(System.in));

	}

	@Override
	public int getID() throws IOException {

		String sentence = "RM20 4 \"indtast operatoer nummer\" \"\" \"nr\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		modifiedSentence = consoleReader.readLine();

		userID = Integer.parseInt(modifiedSentence.substring(7).trim());

		return userID;
	}

	@Override
	public boolean sendUsername(String userName) throws IOException {

		String sentence = "RM20 8 \"Goddag  \" " + userName + "\"\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		while (true) {
			modifiedSentence = consoleReader.readLine().trim();
			if (modifiedSentence.equalsIgnoreCase("")) {
				continue;
			}
			if (modifiedSentence.equalsIgnoreCase("RM20A")) {
				return true;
			} else
				return false;
		}

	}

	@Override
	public int getProductBatchID() throws IOException {

		String sentence = "RM20 4 \"indtast batchnummer på det de øsnker at afveje \" \"\" \"nr\"";
		consoleOutput.writeBytes(sentence + "\r\n");
		modifiedSentence = consoleReader.readLine();

		productBatchID = Integer.parseInt(modifiedSentence.substring(7).trim());

		return productBatchID;
	}

	@Override
	public void drainWeight() throws IOException {

		String sentence = "RM20 4 \"Tøm venligst vægten og tryk 'ok'\" \"\" \"nr\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		do
			modifiedSentence = consoleReader.readLine().trim();
		while (!modifiedSentence.equalsIgnoreCase("RM20A"));

	}

	@Override
	public double getTara() throws IOException {
		consoleReader.readLine().substring(2).trim()
				.equalsIgnoreCase("T S" + "\r\n");

		double tareWeight = Double.parseDouble(consoleReader.readLine()
				.substring(2).trim());

		return tareWeight;
	}

	@Override
	public double getNettoWeight() throws IOException {
		consoleReader.readLine().substring(2).trim()
				.equalsIgnoreCase("S S" + "\r\n");

		double nettoWeight = Double.parseDouble(consoleReader.readLine()
				.substring(2).trim());

		return nettoWeight;

	}

	@Override
	public void outRaavareID(int raavareID) throws IOException {
		String sentence = "RM20 8 \"Afvej raavare \" " + raavareID + "\"\"";
		consoleOutput.writeBytes(sentence + "\r\n");
	}

	@Override
	public int getRaavareBatchID() throws IOException {

		String sentence = "RM20 8 \"indtast raavarebatch id \" " + "\"\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		int raavareBatchID = Integer.parseInt(modifiedSentence.substring(7)
				.trim());

		return raavareBatchID;
	}

	@Override
	public boolean getQuit() throws IOException {

		String sentence = "RM20 8 \"er du færdig? for ja tryk 'ok' for nej tryk  '1' \" "
				+ "\"\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		if (!modifiedSentence.equalsIgnoreCase("RM20 A")) {
			return false;
		} else
			return true;
	}

	public boolean retry() throws IOException {

		String sentence = "RM20 8 \"Hov der skete en fejl, vil du prøve igen? for ja tryk 'ok'. For nej tryk  '1' \" "
				+ "\"\"";
		consoleOutput.writeBytes(sentence + "\r\n");

		if (!modifiedSentence.equalsIgnoreCase("RM20 A")) {
			return false;
		} else
			return true;
	}

}

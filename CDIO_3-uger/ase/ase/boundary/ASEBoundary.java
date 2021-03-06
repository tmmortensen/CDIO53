package ase.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class to take input from a client
 * 
 * @author Gruppe 53
 * 
 */
public class ASEBoundary {
	BufferedReader socketReader;
	private DataOutputStream socketOutput;

	/**
	 * Constructor that makes the program ready for user inputs
	 */
	public ASEBoundary(String adress) {
		try {

			@SuppressWarnings("resource")
			Socket sock = new Socket(adress, 8000);
			socketReader = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			socketOutput = new DataOutputStream(sock.getOutputStream());

		} catch (Exception e) {
			System.out.println("Kunne ikke forbinde til vægten.");
			System.out.println(e.getMessage());
		}
	}

	public int getID() throws IOException {

		String sentence = "RM20 4 \"Indtast operatoer id\" \"\" \"nr\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do{
			input = socketReader.readLine();
			System.out.println(input);}
		while (input.equals("") || input.startsWith("RM20 B") || input.startsWith("RM20 I"));

		if (input.equals("RM20 C"))
			return -1;

		input = input.substring(input.indexOf("\"") + 1,
				input.lastIndexOf("\"")+1);
		input = input.replace("\"", "").trim();
		try {
			int id = Integer.parseInt(input);
			return id;
		} catch (NumberFormatException e) {
			return -1;
		} catch (Exception e) {
			System.err.println(input);
			return -1;
		}
	}

	public int getProductBatchID() throws IOException {

		String sentence = "RM20 4 \"Produktbatchnummer?\" \"\" \"nr\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B") || input.startsWith("RM20 I"));

		input = input.substring(input.indexOf("\"") + 1,
				input.lastIndexOf("\""));
		try {
			int id = Integer.parseInt(input);
			return id;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public boolean clearWeight() throws IOException {

		String sentence = "RM20 8 \"Toem vaegt\" \"Tryk OK\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B") || input.startsWith("RM20 I"));

		if (input.startsWith("RM20 A")) {
			socketOutput.writeBytes("T\r\n");
			return true;
		} else
			return false;

	}

	public double getTara() throws IOException {
		String sentence = "RM20 8 \"Placer beholder\" \"OK/Cancel\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (!input.startsWith("RM20") || input.startsWith("RM20 B") || input.startsWith("RM20 I"));
		if (!input.startsWith("RM20 A"))
			return -1.0;

		socketOutput.writeBytes("T\r\n");
		do
			input = socketReader.readLine();
		while (input.equals(""));
		if (input.startsWith("T S")) {
			try {
				return Double.parseDouble(input.replaceAll("[^\\d\\.]", ""));
			} catch (NumberFormatException e) {
			}
		}
		return -1.0;

	}

	public double getNettoWeight(double target, double tolerance)
			throws IOException {
		String sTol = "" + tolerance;
		String sTar = "" + target;
		try {
			sTol = sTol.substring(0, sTol.indexOf(".") + 2);
			sTar = sTar.substring(0, sTar.indexOf(".") + 5);
		} catch (IndexOutOfBoundsException e) {
		}
		String sentence = "P111 \"" + target + "kg\t" + sTol + "%\"";
		socketOutput.writeBytes(sentence + "\r\n");
		sentence = "RM30 \"\" \"\" \"\" \"\" \"OK\" \"Cancel\"";
		socketOutput.writeBytes(sentence + "\r\n");
		socketOutput.writeBytes("RM39 1\r\n");

		String input;

		double netto = -1.0;
		do {
			input = socketReader.readLine();
		} while (!input.startsWith("RM30 A"));

		if (!input.startsWith("RM30 A 5"))
			return -1.0;

		socketOutput.writeBytes("RM39 2\r\n");
		
		socketOutput.writeBytes("S\r\n");
		do {
			input = socketReader.readLine();
		} while (!input.startsWith("S S"));

		try {
			netto = Double.parseDouble(input.replaceAll("[^\\d\\.]", ""));
			return netto;
		} catch (NumberFormatException e) {
			return -1.0;
		}
	}

	public int getRaavareBatchID(int commodityID) throws IOException {
		String sentence = "RM20 4 \"Raavarebatch id\" \"\" \"ID\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B")|| input.startsWith("RM20 I"));
		if (!input.startsWith("RM20 A"))
			return -1;

		input = input.substring(input.indexOf("\"") + 1,
				input.lastIndexOf("\""));
		try {
			int id = Integer.parseInt(input);
			return id;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public boolean getQuit() throws IOException {
		String sentence = "RM20 8 \"Fortsaet Afvejning?\" \"OK = ja Cancel = nej\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B")|| input.startsWith("RM20 I"));
		if (input.startsWith("RM20 A"))
			return false;
		return true;
	}

	public boolean retry() throws IOException {

		String sentence = "RM20 8 \"Vil du proeve igen?\" \"OK = ja Cancel = nej\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B") || input.startsWith("RM20 I"));
		if (input.startsWith("RM20 A"))
			return true;
		return false;

	}

	public void sendError(String msg) throws IOException {
		String sentence = "RM20 8 \"" + msg + "\" \"\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B")|| input.startsWith("RM20 I"));

	}

	public boolean sendConfirm(String msg) throws IOException {
		String sentence = "RM20 8 \"" + msg + "\" \"\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (input.equals("") || input.startsWith("RM20 B")|| input.startsWith("RM20 I"));
		if (input.startsWith("RM20 A"))
			return true;
		return false;

	}

}

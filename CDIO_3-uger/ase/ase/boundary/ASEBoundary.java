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
public class ASEBoundary implements IASEBoundary {
	BufferedReader socketReader;
	private DataOutputStream socketOutput;

	/**
	 * Constructor that makes the program ready for user inputs
	 */
	public ASEBoundary(String adress) {
		try {

			@SuppressWarnings("resource")
			Socket sock = new Socket(adress, 8080);
			socketReader = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			socketOutput = new DataOutputStream(sock.getOutputStream());

		} catch (Exception e) {
			System.out.println("Kunne ikke forbinde til vægten.");
		}
	}

	@Override
	public int getID() throws IOException {

		String sentence = "RM20 4 \"Indtast operatoer id\" \"\" \"nr\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine();
		while (input.equals(""));

		if (input.equals("RM20_C"))
			return -1;

		try {
			int id = Integer.parseInt(input.substring(7).trim());
			return id;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public boolean sendUsername(String userName) throws IOException {

		String sentence = "RM20 8 \"Goddag\" \"" + userName + "\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");

		while (true) {
			String input = socketReader.readLine();
			if (input.equals("")) {
				continue;
			}
			if (input.startsWith("RM20 A")) {
				return true;
			} else
				return false;
		}

	}

	@Override
	public int getProductBatchID() throws IOException {

		String sentence = "RM20 4 \"Indtast batchnummer\" \"\" \"nr\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine();
		while (input.equals(""));

		try {
			int id = Integer.parseInt(input.substring(7).trim());
			return id;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public boolean clearWeight() throws IOException {

		String sentence = "RM20 4 \"Toem vaegt\" \"Tryk OK\" \"nr\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));

		if (input.startsWith("RM20A")) {
			socketOutput.writeBytes("T\r\n");
			return true;
		} else
			return false;

	}

	@Override
	public double getTara() throws IOException {
		String sentence = "RM20 8 \"Placer beholder\" \"OK/Cancel\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (!input.startsWith("RM20 A"))
			return -1.0;

		socketOutput.writeBytes("T\r\n");
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (input.startsWith("TS")) {
			try {
				return Double.parseDouble(input.substring(2));
			} catch (NumberFormatException e) {
			}
		}
		return -1.0;

	}

	@Override
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
		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (!input.startsWith("RM20A"))
			return -1.0;

		socketOutput.writeBytes("S\r\n");
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (input.startsWith("SS")) {
			try {
				return Double.parseDouble(input.substring(2));
			} catch (NumberFormatException e) {
			}
		}
		return -1.0;
	}

	@Override
	public int getRaavareBatchID(int commodityID) throws IOException {
		String sentence = "RM20 4 \"Indtast batch id\" \"Raavare id:"
				+ commodityID + "\" \"ID\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (!input.startsWith("RM20A"))
			return -1;

		try {
			int id = Integer.parseInt(input.substring(7));
			return id;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public boolean getQuit() throws IOException {
		String sentence = "RM20 8 \"Fortsaet Afvejning?\" \"J/N\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");

		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (input.startsWith("RM20AJ"))
			return false;
		return true;
	}

	public boolean retry() throws IOException {

		String sentence = "RM20 8 \"Vil du proeve igen?\" \"J/N\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (input.startsWith("RM20AJ"))
			return true;
		return false;

	}

	public void sendError(String msg) throws IOException {
		String sentence = "RM20 8 \"" + msg + "\" \"\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));

	}

	public boolean sendConfirm(String msg) throws IOException {
		String sentence = "RM20 8 \"" + msg + "\" \"\" \"\"";
		socketOutput.writeBytes(sentence + "\r\n");
		String input;
		do
			input = socketReader.readLine().trim();
		while (input.equals(""));
		if (input.startsWith("RM20A"))
			return true;
		return false;

	}

}

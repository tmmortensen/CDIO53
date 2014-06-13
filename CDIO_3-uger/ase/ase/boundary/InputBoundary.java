package ase.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import admin.data.CommodityDTO;
import admin.data.UserDTO;
import ase.data.ProgramState;
import simulator.data.IProgramState;

/**
 * Class to take input from a client
 * 
 * @author Gruppe 53
 * 
 */
public class InputBoundary implements IBoundary {
	boolean IDforespørgelse = false, commoditycheck = false;
	BufferedReader consoleReader;
	IProgramState iprogramState;
	private DataOutputStream consoleOutput;
	private String modifiedSentence;

	/**
	 * Constuctor that makes the program ready for user inputs
	 * 
	 * @param in
	 *            is a scanner that recieves keyboardinput
	 * @param programState
	 *            changes state of the program depending on the input
	 */
	public InputBoundary(IProgramState programState) {
		this.iprogramState = programState;
		consoleReader = new BufferedReader(new InputStreamReader(System.in));

	}

	ProgramState programstate = new ProgramState();

	@Override
	public void closeResources() {
		try {
			consoleReader.close();
			System.in.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		while (iprogramState.isRunning()) {
			while (IDforespørgelse == false) {

				int temp_id = 0;
				try {

					try {
						String sentence = "RM20 4 \"indtast operatoer nummer\" \"\" \"nr\"";
						consoleOutput.writeBytes(sentence + "\r\n");
						modifiedSentence = consoleReader.readLine();

						while (!modifiedSentence.substring(0, 6)
								.equalsIgnoreCase("RM20 A")) {
							modifiedSentence = consoleReader.readLine();
						}

						temp_id = Integer.parseInt(modifiedSentence
								.substring(7).trim());

						UserDTO operator = programstate.UserDTOgetUser(temp_id);

						String name = programstate.UserInfo(operator);

						consoleOutput
								.writeBytes("RM20 8 \"Goddag \" "
										+ name
										+ "\"hvis dette er dem tryk 1, ellers tryk 2\"");
						modifiedSentence = consoleReader.readLine().trim();

						if (modifiedSentence == "1") {
							IDforespørgelse = true;
						}

						else {
							sentence = "RM20 4 \"Dette var ikke dem. vi starter forfra \" \"\" \"nr\"";
							consoleOutput.writeBytes(sentence + "\r\n");

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
				}

			}
			while(commoditycheck==false){
				try{
				String sentence = "RM20 4 \"indtast batchnummer på det de øsnker at afveje \" \"\" \"nr\"";
				consoleOutput.writeBytes(sentence + "\r\n");
				modifiedSentence = consoleReader.readLine();

				while (!modifiedSentence.substring(0, 6)
						.equalsIgnoreCase("RM20 A")) {
					modifiedSentence = consoleReader.readLine();
				}

				int temp_commodityID = Integer.parseInt(modifiedSentence
						.substring(7).trim());
				
				
				CommodityDTO comOperator = programstate.comOperator(temp_commodityID);

				String comName = programstate.comOperator(comOperator);

				consoleOutput
						.writeBytes("RM20 8 \"er dette den rigtige batch? \" "
								+ comName
								+ "\"hvis det er det tryk 1, ellers tryk 2\"");
				modifiedSentence = consoleReader.readLine().trim();

				if (modifiedSentence == "1") {
					commoditycheck = true;
				}

				else {
					sentence = "RM20 4 \"Prøv at skriv batchnummeret igen \" \"\" \"nr\"";
					consoleOutput.writeBytes(sentence + "\r\n");

				}
				
				
				} catch (Exception e) {
					
				}
			}

			try {
				if (!consoleReader.ready())
					continue;
				String userInput = consoleReader.readLine();
				if (userInput.substring(2).trim()
						.equalsIgnoreCase("T S" + "\r\n")) {
					iprogramState.tare();

				} else if (userInput.equalsIgnoreCase("RM20 A 1" + "\r\n")) {
					while (!consoleReader.ready()) {
						if (!iprogramState.isRunning())
							return;
					}
					
					consoleOutput.writeBytes("S "+ "\r\n");					
					double weight = Double.parseDouble(consoleReader.readLine().substring(2).trim());
					
					try {
						iprogramState.setGross(weight);
					} catch (Exception e) {
					}
				} else if (userInput.equalsIgnoreCase("RM20 A 9" + "\r\n")) {
					iprogramState.quit();
					return;
				}

			} catch (Exception e) {
			}
		}
	}
}

package ase.boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import admin.data.CommodityDTO;
import admin.data.PrescriptionData;
import admin.data.ProductBatchCompDTO;
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
			// while løkke der tjekker for input fra vægten, hvad der skal ske
			// som det næste
			while (IDforespørgelse == false) {
				// while lykke vi hopper ind i når Operatør ikke er blevet
				// tjekket for id og navn, vi kommer ikke ud af denne før navnet
				// er accepteret

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
			while (commoditycheck == false) {
				// while løkke vi hopper ind i hvis vi ikke ved hvilket
				// receptkomponent operatøren vil veje vare i. Vi bliver indtil
				// operatør har accepteret navnet på det modtaget receptnavn
				try {
					String sentence = "RM20 4 \"indtast batchnummer på det de øsnker at afveje \" \"\" \"nr\"";
					consoleOutput.writeBytes(sentence + "\r\n");
					modifiedSentence = consoleReader.readLine();

					while (!modifiedSentence.substring(0, 6).equalsIgnoreCase(
							"RM20 A")) {
						modifiedSentence = consoleReader.readLine();
					}

					int temp_commodityID = Integer.parseInt(modifiedSentence
							.substring(7).trim());

					// mangler metoden til at hente receptnavn fra programstate

					consoleOutput
							.writeBytes("RM20 8 \"er dette den rigtige recept? \" "
									+ receptName
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

					// Hvis der trykkes Tarer, tarere vi og sender den tarede
					// vægt end i ProgramState

					double Tweight = Double.parseDouble(consoleReader
							.readLine().substring(2).trim());

					iprogramState.tare(Tweight);

				} else if (userInput.equalsIgnoreCase("RM20 A 1" + "\r\n")) {
					while (!consoleReader.ready()) {
						if (!iprogramState.isRunning())
							return;
					}
					// Hvis operatør trykker 1 indikere det at et produkt er
					// færdig med at veje og klare til at blive sendt ned i
					// databasen
					consoleOutput.writeBytes("S " + "\r\n");
					double weight = Double.parseDouble(consoleReader.readLine()
							.substring(2).trim());

					try {
						iprogramState.setGross(weight);
					} catch (Exception e) {
					}
				} else if (userInput.equalsIgnoreCase("RM20 A 9" + "\r\n")) {
					// når operatør trykker på tast 9 indikere det at han vil
					// lukke ptogrammet, og det lukkes så
					iprogramState.quit();
					return;
				}

			} catch (Exception e) {
			}
		}
	}
}

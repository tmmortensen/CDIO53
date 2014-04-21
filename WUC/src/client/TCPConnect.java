package client;

import java.text.SimpleDateFormat;
import java.util.*;

public class TCPConnect implements Runnable {
	boolean notFound = true, brutto_ok;
	Scanner input = new Scanner(System.in);
	String sentence, varenavn, choice;
	String modifiedSentence, oprId;
	Integer varenummer, afvejning = 0;
	double tara = 0, netto = 0, brutto = 0;

	WriteToFile writeToFile = new WriteToFile();

	public void run() {
		// initializing the weighting procedure by opening a connection to the
		// weight and test that it works.
		if (startWeight() == true) {
			SocketConnect socketConnection = new SocketConnect();
			socketConnection.initiate();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.getMessage();
			}

			// the operator identifies themselves, by writing their oprId on the
			// weight it is then saved in the log file
			oprId = socketConnection.identify();
			System.out.println("oprId modtaget:" + oprId);

			// we check whether the operator entered the correct item number, if
			// it
			// was not correct the let the user try again
			varenummer = socketConnection.varenummer();

			while (itemCheck(writeToFile.readStore(varenummer)) != true) {
				varenummer = socketConnection.varenummer();
			}

			while (brutto_ok != true) {
				// the operator is now prompted to place the bowl/cup on the
				// weight
				System.out
						.println("please place the desired item on the weight");
				tara = socketConnection.waitForBowl();
				System.out.println("tara modtaget: " + tara);
				System.out
						.println("did you place the desired bowl/cup on the weight?    y/n");
				System.out.print("write here: ");
				String choice = input.next();
				quitProgram(choice, choice, varenummer, netto);
				if (choice.equalsIgnoreCase("y")) {
					socketConnection.weightTara();
				}
				// The operator has to put the item in the bowl/cup to proceed
				// with
				// the
				// procedure.

				System.out
						.println("please place the desired item in the bowl/cup");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("did you put the item in the bowl?   y/n?");

				System.out.print("write here: ");
				String item_choice = input.next();
				quitProgram(item_choice, choice, varenummer, netto);
				while (!item_choice.equalsIgnoreCase("y")) {
					System.out
							.println("have you put the desired item in the bowl? y/n");
					item_choice = input.next();
					quitProgram(item_choice, choice, varenummer, netto);
				}
				netto = socketConnection.getWeight();
				System.out
						.println("please remove everything from the weight and write \'y\'");
				System.out.print("write here: ");
				String remove_choice = input.next();
				quitProgram(remove_choice, remove_choice, varenummer, netto);
				while (!remove_choice.equalsIgnoreCase("y")) {
					remove_choice = input.next();
					quitProgram(remove_choice, remove_choice, varenummer, netto);
				}
				brutto = socketConnection.getBrutto();
				brutto_ok = bruttoOK(tara, netto, brutto);
				if (brutto_ok != true) {
					System.out
							.println("there was an error in the weighting procedure please start over by");

				}
			}
			System.out.println("the weighting procedure was succesfull");
			writeLog(oprId, varenummer, netto);
		}
	}

	private boolean bruttoOK(double tara, double netto, double brutto) {
		boolean brutto_ok;

		if (((tara + netto) / (-1 * brutto)) * 100 <= 5.0) {
			brutto_ok = true;
		} else {
			brutto_ok = false;
		}

		return brutto_ok;

	}

	private boolean itemCheck(String itemName) {
		System.out.println("is this the desired item?:" + itemName + "(y/n)");
		String item_check_choice = input.next();
		if (item_check_choice.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}

	}

	private boolean startWeight() {
		System.out
				.println("do you wish to start the weighting procedure:    (y/n)");
		String start_choice = input.next();
		writeToFile.writeStore();
		while (!start_choice.equalsIgnoreCase("y")) {
			startWeight();
		}
		return true;

	}

	private void writeLog(String oprId, int varenummer, double netto) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());
		writeToFile.writeLog(timeStamp, oprId, varenummer, netto);
	}

	private void quitProgram(String quit, String oprId, int varenummer,
			double netto) {
		if (quit.equalsIgnoreCase("q")) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(Calendar.getInstance().getTime());
			writeToFile.writeLog(timeStamp, oprId, varenummer, netto);
			System.out.println("your session has been saved.");

		}
	}

}
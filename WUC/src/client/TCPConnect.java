package client;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.plaf.SliderUI;

public class TCPConnect implements Runnable {
	boolean notFound = true;
	Scanner input = new Scanner(System.in);
	String sentence, varenavn, choice;
	String modifiedSentence, oprId;
	Integer varenummer, afvejning = 0, Tara = 0, Netto = 0;
	WriteToFile writeToFile = new WriteToFile();

	public void run() {
		// initializing the weighting procedure by opening a connection to the
		// weight and test that it works.
		startWeight();
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
		writeOpr(oprId);

		// we check whether the operator entered the correct item number, if it
		// was not correct the let the user try again
		varenummer = socketConnection.varenummer();

		while (itemCheck(writeToFile.readStore(varenummer)) != true) {
			varenummer = socketConnection.varenummer();
		}

		writeItem(oprId, varenummer);

		// the operator is now prompted to place the bowl/cup on the weight
		System.out.println("please place the desired item on the weight");
		while (socketConnection.waitForBowl() != true)
			;
		System.out
				.println("did you place the desired bowl/cup on the weight?    y/n");
		if (input.next().equalsIgnoreCase("y")) {
			socketConnection.weightTara();
		}

	}

	private void writeOpr(String oprId) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());
		writeToFile.writeLog(timeStamp, oprId, varenummer, afvejning);

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

	private void writeItem(String oprId, int varenummer) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());
		writeToFile.writeLog(timeStamp, oprId, varenummer, afvejning);
	}

}
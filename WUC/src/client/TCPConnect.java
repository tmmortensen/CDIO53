package client;

import java.text.SimpleDateFormat;
import java.util.*;

public class TCPConnect implements Runnable {
	boolean notFound = true;
	Scanner input = new Scanner(System.in);
	String sentence, varenavn, choice;
	String modifiedSentence,oprId;
	Integer varenummer = 0, afvejning = 0, Tara = 0, Netto = 0;
	WriteToFile writeToFile = new WriteToFile();

	public void run() {
		
		startWeight();
		SocketConnect socketConnection = new SocketConnect();
		
		oprId=socketConnection.identify();
		writeOpr(oprId);
		
		while(itemCheck(socketConnection.varenavn())!=true);
		
		writeItem(oprId,10);
		

	}

	private void writeOpr(String oprId) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());
		writeToFile.writeLog(timeStamp, oprId, varenummer, afvejning);

		
	}
	private boolean itemCheck(String itemName){
		System.out.println("is this the desired item?:" + itemName + "(y/n)");
		String item_check_choice = input.next();
		if(item_check_choice.equalsIgnoreCase("y")){
			return true; 
		}
		else {
			return false; 
		}
		
	}
	private boolean startWeight(){
		System.out.println("do you wish to start the weighting procedure:    (y/n)");
		String start_choice = input.next();
		while(!start_choice.equalsIgnoreCase("y")){
			startWeight();
		}
		return true; 
	
	}
	private void writeItem(String oprId, int varenummer){
		
		
	}
	

}
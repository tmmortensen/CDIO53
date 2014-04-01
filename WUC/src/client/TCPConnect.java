package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class TCPConnect {

	public void Run() {

		Scanner input = new Scanner(System.in);
		String sentence;
		String modifiedSentence;
		Integer varenummer,afvejning; 
		WriteToFile writeToFile = new WriteToFile(); 

		System.out.println("ønsker du at lave en afvejning:   y/n?");
		String startChoice = input.next();

		if (startChoice.equalsIgnoreCase("y")) {
			try {

				// kode til at oprette de forskellige TCP lyttere og skrivere så
				// vi kan kommunikere med vægten.
				Socket clientSocket = new Socket("localhost", 8000);

				DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());

				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

				sentence = "RM20 4" + "\" indtast operatoer nummer\"" + "\"\""
						+ "\"\"";
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine();
				if(modifiedSentence.substring(0, 5).equals("RM20 8")){
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					writeToFile.WriteLog(timeStamp,modifiedSentence.substring(6),varenummer,afvejning);
					
				}
				


				clientSocket.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		input.close();
	}

}

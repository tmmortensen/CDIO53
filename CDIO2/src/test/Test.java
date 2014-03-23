package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Test implements Runnable {

	@Override
	public void run() {

		String sentence;
		String modifiedSentence;
		try {
			Socket clientSocket = new Socket("localhost", 8000);

			String[] inputs = new String[10];
			inputs[0] = "D \"det skal vises\"";
			inputs[1] = "S";
			inputs[2] = "P111 \"vis det her i bunden\"";
			inputs[3] = "RM20 8 <indtast her> <det overskrives> \"&3\" ";
			inputs[4] = "D \" nu vises det\"";
			inputs[5] = "D \"Hej med dig\"";
			inputs[6] = "P110";
			inputs[7] = "D hej hej hej";
			inputs[8] = "B 400";
			inputs[9] = "RM20 8 dette her en alt for lang String så den går "
					+ "ikke indtast her nr ";

			for (int i = 0; i < inputs.length; i++) {

				DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());

				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

				sentence = inputs[i];
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);
				Thread.sleep(5000);

			}

			clientSocket.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}

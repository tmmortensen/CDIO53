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
			String[] expected = new String[12];
			expected[0] = "D A";
			expected[1] = "S 0.0 ";
			expected[2] = "P111 A";
			expected[3] = "RM20 B";
			expected[4] = "D A";
			expected[5] = "D A";
			expected[6] = "";
			expected[7] = "D A";
			expected[8] = "B A";
			expected[9] = "RM20 L";
			expected[10] = "D L";
			expected[11] = "P111 L";

			String[] inputs = new String[12];
			inputs[0] = "D \"det skal vises\"";
			inputs[1] = "S";
			inputs[2] = "P111 \"vis det her i bunden\"";
			inputs[3] = "RM20 8 \"<indtast her>\" \"<det overskrives>"
					+ "\" \"&3\" ";
			inputs[4] = "D \" nu vises det\"";
			inputs[5] = "D \"Hej med dig\"";
			inputs[6] = "P110";
			inputs[7] = "D hej hej hej";
			inputs[8] = "B 400";
			inputs[9] = "RM20 8 dette her en alt for lang String så den går "
					+ "ikke  indtast her nr ";
			inputs[10] = "D \"det her er en alt for lang string til at vise "
					+ "i displayet\"";
			inputs[11] = "P111 \"det her er også en alt for lang string at "
					+ "vise men nu ligger den nede i det nederste display.\"";

			for (int i = 0; i < inputs.length; i++) {

				System.out.println("input to server: " + inputs[i]);
				System.out.println("expected output: " + expected[i]);
				System.out.print("recieved: ");

				DataOutputStream outToServer = new DataOutputStream(
						clientSocket.getOutputStream());

				BufferedReader inFromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));

				sentence = inputs[i];
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);
				Thread.sleep(7000);

			}

			clientSocket.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
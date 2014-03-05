package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class InputBoundary implements IBoundary {

	private static InputBoundary inputBoundary = new InputBoundary();

	public InputBoundary() {

	}

	public void run() {

	}

	/**
	 * The main method that starts our client tcp connection to the server.
	 * 
	 * @param argv
	 * @throws Exception
	 */
	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;
		inputBoundary.run();

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		Socket clientSocket = new Socket("localhost", 8000);

		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + "\r\n");
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);

		clientSocket.close();

	}

	public void showMenu(String[] menu) {
		for (int i = 0; i <= menu.length; i++) {
			System.out.println(menu[i]);
		}

	}

}

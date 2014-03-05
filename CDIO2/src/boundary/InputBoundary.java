package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import sun.tools.jar.Main;

public class InputBoundary implements IBoundary {

	private static InputBoundary inputBoundary = new InputBoundary();

	public InputBoundary() {

	}

	public void run() {

		String sentence;
		String modifiedSentence;

		try {
			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));

			Socket clientSocket = new Socket("localhost", 8000);

			DataOutputStream outToServer = new DataOutputStream(
					clientSocket.getOutputStream());

			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			while (true) {

				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + "\r\n");
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);

				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		inputBoundary.run();

	}

	public void showMenu(String[] menu) {
		for (int i = 0; i <= menu.length; i++) {
			System.out.println(menu[i]);
		}

	}

}

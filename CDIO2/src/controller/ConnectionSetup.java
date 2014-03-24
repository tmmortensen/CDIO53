package controller;

import java.net.ServerSocket;
import java.net.Socket;

import data.IProgramState;

/**
 * Class to create Socket connection
 * 
 * @author Gruppe 53
 * 
 */
public class ConnectionSetup implements Runnable {
	ServerSocket listener;
	Socket socket;
	IProgramState programState;

	ConnectionSetup(ServerSocket listenSocket, IProgramState programState) {
		listener = listenSocket;
		this.programState = programState;
	}

	@Override
	public void run() {
		try {
			System.out.println("Venter på connection på port "
					+ programState.getPort());
			System.out.println("Indtast eventuel portnummer som 1. argument");
			System.out.println("på kommando linien for andet portnr");
			socket = listener.accept();
			// indtil viddere regner vi kun med �n forbindelse.
			listener.close();
			programState.setAddress(socket.getInetAddress());
		} catch (Exception e) {
			if (programState.isRunning())
				System.out.println("Exception: " + e.getMessage());
			programState.quit();
			return;
		}

	}

	/**
	 * Method to get the socket
	 * 
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

}

package ase.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import simulator.data.IProgramState;

/**
 * Class to create Socket connection
 * 
 * @author Gruppe 53
 * 
 */
public class ConnectionSetup{
	Socket clientSocket;
	ServerSocket listener;
	Socket socket;
	IProgramState programState;
	public BufferedReader in;
	public DataOutputStream out;
	int portNumber = 8080;
	
	public void initiate() {
		try{
			clientSocket = new Socket ("localhost", 8080);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new DataOutputStream(clientSocket.getOutputStream());
		}catch(Exception e){
			System.out.println("Could not connect to weight");
		}	
	}
}

	
	
	
		
//	}
//	ConnectionSetup(ServerSocket listenSocket, IProgramState programState) {
//		listener = listenSocket;
//		this.programState = programState;
//	}
//
//	@Override
//	public void run() {
//		try {
//			System.out.println("Venter på connection på port "
//					+ programState.getPort());
//			System.out.println("Indtast eventuel portnummer som 1. argument");
//			System.out.println("på kommando linien for andet portnr");
//			socket = listener.accept();
//			// indtil viddere regner vi kun med ï¿½n forbindelse.
//			listener.close();
//			programState.setAddress(socket.getInetAddress());
//		} catch (Exception e) {
//			if (programState.isRunning())
//				System.out.println("Exception: " + e.getMessage());
//			programState.quit();
//			return;
//		}
//
//	}
//
//	/**
//	 * Method to get the socket
//	 * 
//	 * @return the socket
//	 */
//	public Socket getSocket() {
//		return socket;
//	}
//
//}

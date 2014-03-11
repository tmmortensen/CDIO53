package controller;


import java.net.ServerSocket;
import java.net.Socket;

import data.Global;

public class ConnectionSetup implements Runnable {
	ServerSocket listener;
	Socket socket;
	
	ConnectionSetup(ServerSocket listenSocket){
		listener = listenSocket;
	}
	
	@Override
	public void run() {
		try{
			System.out.println("Venter på connection på port " + Global.port);
			System.out.println("Indtast eventuel portnummer som 1. argument");
			System.out.println("på kommando linien for andet portnr");
			socket = listener.accept();
			// indtil viddere regner vi kun med �n forbindelse.
			listener.close();
			Global.address = socket.getInetAddress();
		}
		catch(Exception e){
			if(!Global.exit)
				System.out.println("Exception: " + e.getMessage());
			Global.exit = true;
			return;
		}
		
	}
	
	public Socket getSocket(){
		return socket;
	}

}

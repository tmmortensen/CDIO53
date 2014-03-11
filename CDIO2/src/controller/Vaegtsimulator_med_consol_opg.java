package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import data.IProgramState;
import data.ProgramState;
import boundary.IBoundary;
import boundary.InputBoundary;
import boundary.NetworkIOBoundary;
import boundary.OutputBoundary;

public class Vaegtsimulator_med_consol_opg {
	
	public static void main(String[] args) throws IOException {
		int portdst;
		IProgramState programState = new ProgramState();
		Socket socket = null;
		
		
		if(args.length > 0)
			try{
				portdst = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e){
				System.out.println("Port argument ugyldigt. Bruger default 8000.");
				portdst = 8000;
			}
		else
		{
			System.out.println("Bruger default port 8000.");
			portdst = 8000;
		}
		
		//input setup and run
		Scanner inputScanner = new Scanner(System.in);
		IBoundary input = new InputBoundary(inputScanner, programState);
		Thread inputThread = new Thread(input);
		inputThread.start();		

		//output setup and run
		OutputBoundary output = new OutputBoundary(programState);
		Thread outputThread = new Thread(output);
		outputThread.start();
		
		// Network setup and run
		programState.setPort(portdst);
		ServerSocket listenSocket = new ServerSocket(portdst);
		ConnectionSetup connection = new ConnectionSetup(listenSocket, programState);
		Thread connectionTread = new Thread(connection);
		connectionTread.start();
		
		// wait for a connection to be made or the program to close
		while(programState.isRunning() && (connection.getSocket() == null)){
			try{connectionTread.join(100);}
			catch (Exception e){}
		}
		
		// if the program is still running start up the networkIO
		if (programState.isRunning()){
			socket = connection.getSocket();
			IBoundary network = new NetworkIOBoundary(socket, programState);
			Thread networkThread = new Thread(network);
			networkThread.start();
		} 
		
		// we have a connection now and don't need the server socket anymore
		listenSocket.close();

		// wait until program is closed
		while(programState.isRunning()){
			try{Thread.sleep(100);}
			catch(Exception e){}
		}
		

		// close down resources used by the program
		// close down the input socket so the input thread will stop waiting for user input
		inputScanner.close();
		
		// close down the network socket so the network thread will stop waiting for network input
		try{socket.close();}
		catch(Exception e){}

		System.in.close();
		System.out.close();
		
		System.exit(0);
	}
}

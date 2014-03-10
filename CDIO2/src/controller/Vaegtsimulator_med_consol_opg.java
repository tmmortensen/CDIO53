package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import data.Global;
import boundary.IBoundary;
import boundary.InputBoundary;
import boundary.NetworkIOBoundary;
import boundary.OutputBoundary;

public class Vaegtsimulator_med_consol_opg {
	static ServerSocket listener;
	private static int portdst = 8000;

	public static void main(String[] args) throws IOException {
		Global.tara = 0;
		Global.brutto = 0;
		Global.display = "";
		Global.exit = false;
		Global.lastUpdate = System.currentTimeMillis();
		if(args.length > 0)
			try{
				portdst = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e){
				System.out.println("Port argument ugyldigt. Bruger default 8000.");
				portdst = 8000;
			}
		//input setup and run
		Scanner inputScanner = new Scanner(System.in);
		IBoundary input = new InputBoundary(inputScanner);
		Thread inputThread = new Thread(input);
		inputThread.start();		

		//output setup and run
		OutputBoundary output = new OutputBoundary();
		Thread outputThread = new Thread(output);
		outputThread.start();
		
		// Network setup and run
		Global.port = portdst;
		ServerSocket listenSocket = new ServerSocket(portdst);
		ConnectionSetup connection = new ConnectionSetup(listenSocket);
		Thread connectionTread = new Thread(connection);
		connectionTread.start();
		
		while(!Global.exit){
			try{
				connectionTread.join(100);
			} catch (Exception e){
				
			}
		}
		Socket socket = null;
		if (!Global.exit){
			socket = connection.getSocket();
			IBoundary network = new NetworkIOBoundary(socket);
			Thread networkThread = new Thread(network);
			networkThread.start();
		} else
			listenSocket.close();


		while(!Global.exit){
			try{
				Thread.sleep(100);
			}
			catch(Exception e){
				
			}
		}
		inputScanner.close();
		try{
			socket.close();
		}catch(Exception e){
			
		}
		
		//networkThread.interrupt();
		//inputThread.interrupt();
	}
}

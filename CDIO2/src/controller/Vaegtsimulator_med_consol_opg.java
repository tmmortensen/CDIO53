package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static double brutto = 0;
	private static double tara = 0;
	private static String inline;
	private static String InstruktionsDisplay = "";
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;

	
	

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
		IBoundary network = new NetworkIOBoundary(portdst);
		Scanner inputScanner = new Scanner(System.in);
		IBoundary input = new InputBoundary(inputScanner);
		OutputBoundary output = new OutputBoundary();
		Thread networkThread = new Thread(network);
		Thread inputThread = new Thread(input);
		networkThread.start();
		inputThread.start();
		output.run();
		while(!Global.exit){
			try{
				Thread.sleep(100);
			}
			catch(Exception e){
				
			}
			
		}
		inputScanner.close();
		Global.listener.close();
		try{
			Global.instream.close();
		}
		catch(Exception e){
			
		}
		//networkThread.interrupt();
		//inputThread.interrupt();
	}
}

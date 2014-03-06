package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import data.Global;

public class NetworkIOBoundary implements IBoundary {
	private String inLine;
	private static int portdst = 8001;
	private static Socket sock;
	private static DataOutputStream outstream;
	
	public NetworkIOBoundary(int port){
		portdst = port;
	}
	public void run() {	
		try{
			Global.listener = new ServerSocket(portdst);
			Global.port = portdst;
			System.out.println("Venter på connection på port " + portdst);
			System.out.println("Indtast eventuel portnummer som 1. argument");
			System.out.println("på kommando linien for andet portnr");
			sock = Global.listener.accept();
			Global.address = sock.getInetAddress();
			Global.instream = new BufferedReader(new InputStreamReader(
				sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
		}
		catch(Exception e){
			if(!Global.exit)
				System.out.println("Exception: " + e.getMessage());
			Global.exit = true;
			return;
		}
		try {
			while (!(inLine = Global.instream.readLine().toUpperCase()).isEmpty()) {
				if (inLine.startsWith("DN")) {
					// ikke implimenteret
				} else if (inLine.startsWith("D")) {
					if (inLine.equals("D"))
						Global.display = "";
					else
						Global.display = (inLine.substring(2,
								inLine.length()));
					//printmenu();
					outstream.writeBytes("DB" + "\r\n");
				} else if (inLine.startsWith("T")) {
					outstream.writeBytes("T " + (Global.tara) + " kg " + "\r\n");
					Global.tara = Global.brutto;
					//printmenu();
				} else if (inLine.startsWith("S")) {
					//printmenu();
					outstream.writeBytes("S " + (Global.brutto - Global.tara) + " kg "
							+ "\r\n");
				} else if (inLine.startsWith("B")) { // denne ordre findes
					// ikke p� en fysisk v�gt
					String temp = inLine.substring(2, inLine.length());
					Global.brutto = Double.parseDouble(temp);
					//printmenu();
					outstream.writeBytes("DB" + "\r\n");
				} else if ((inLine.startsWith("Q"))) {
					System.out.println("");
					System.out
							.println("Program stoppet Q modtaget p� com port");
					System.in.close();
					System.out.close();
					Global.instream.close();
					outstream.close();
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
	

}

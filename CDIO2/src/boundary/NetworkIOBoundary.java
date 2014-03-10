package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.DataInputStream;

import data.Global;

public class NetworkIOBoundary implements IBoundary {
	private Socket sock;
	private DataOutputStream outstream;
	private BufferedReader instream;
	
	public NetworkIOBoundary(Socket socket){
		sock = socket;
	}
	public void run() {	
		try{
			instream = new BufferedReader(new InputStreamReader(
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
			while (!(Global.networkString = instream.readLine().toUpperCase())
					.isEmpty()) {
				if (Global.networkString.startsWith("RM20 8")) {
					outstream.writeBytes("RM20 B"+ "indtast nr."+"\r\n");
					while(Global.networkString.startsWith("RM20 8"));{}
					outstream.writeBytes("RM20 A" + "\r\n");
				}
				else if(Global.networkString.startsWith("RESET")){
					Global.brutto=0.0;
					Global.tara=0.0;
					Global.display="";
					
					outstream.writeBytes("du har nulstillet programmet" + "\r\n");
					Global.lastUpdate=System.currentTimeMillis();
					
				}
				else if (Global.networkString.startsWith("P111")) {
					Global.display= (Global.networkString.substring(5,Global.networkString.length()));
					outstream.writeBytes("P111 A"+ "\r\n");
				} else if (Global.networkString.startsWith("D")) {
					if (Global.networkString.equals("D"))
						Global.display = "";
					else
						Global.display = (Global.networkString.substring(2, Global.networkString.length()));
					// printmenu();
					outstream.writeBytes("DB" + "\r\n");
					Global.lastUpdate=System.currentTimeMillis();
				} else if (Global.networkString.startsWith("T")) {
					outstream
							.writeBytes("T " + (Global.tara) + " kg " + "\r\n");
					Global.tara = Global.brutto;
					Global.lastUpdate=System.currentTimeMillis();
					// printmenu();
				} else if (Global.networkString.startsWith("S")) {
					// printmenu();
					outstream.writeBytes("S " + (Global.brutto - Global.tara)
							+ " kg " + "\r\n");
				} else if (Global.networkString.startsWith("B")) { // denne ordre findes
					// ikke p� en fysisk v�gt
					String temp = Global.networkString.substring(2, Global.networkString.length());
					Global.brutto = Double.parseDouble(temp);
					// printmenu();
					outstream.writeBytes("DB" + "\r\n");
					Global.lastUpdate=System.currentTimeMillis();
				} else if ((Global.networkString.startsWith("Q"))) {
					System.out.println("");
					System.out
							.println("Program stoppet Q modtaget p� com port");
					outstream
							.writeBytes("program  stoppet Q modtaget på com port");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

}

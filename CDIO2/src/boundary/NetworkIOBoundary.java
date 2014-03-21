package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import data.IProgramState;

/**
 * Class used to send/recieve network input and output
 * 
 * @author thomasmortensen
 * 
 */
public class NetworkIOBoundary implements IBoundary {
	private ServerSocket listener;
	private Socket sock;
	private DataOutputStream outstream;
	private BufferedReader instream;
	private IProgramState programState;
	private boolean needResponse = false;
	private long lastRequest;

	public NetworkIOBoundary(IProgramState programState) {
		this.programState = programState;
	}

	@Override
	public void closeResources() {
		try {
			// lukining af serversocket i tilfælde af at vi stadig venter på
			// connection
			listener.close();
		} catch (Exception e) {
		}
		try {
			// lukning af socket
			sock.close();
			instream.close();
			outstream.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {

		// Start af Socket
		try {
			listener = new ServerSocket(programState.getPort());
			System.out.println("Venter på connection på port "
					+ programState.getPort());
			System.out.println("Indtast eventuel portnummer som 1. argument");
			System.out.println("på kommando linien for andet portnr");
			sock = listener.accept();
			// indtil viddere regner vi kun med �n forbindelse.
			listener.close();
			programState.setAddress(sock.getInetAddress());
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			if (programState.isRunning())
				System.out.println("Exception: " + e.getMessage());
			programState.quit();
			return;
		}

		// håndtering af input fra socket
		try {
			String netString;
			while (programState.isRunning()) {

				if (needResponse && programState.haveNewUserInput(lastRequest)) {
					outstream.writeBytes("RM20 A \""
							+ programState.getUserInput() + "\"\r\n");
					needResponse = false;
				}

				if (!instream.ready())
					continue;

				if ((netString = instream.readLine().toUpperCase()).isEmpty())
					continue;

				programState.setNetString(netString);

				if (netString.startsWith("RM20 8")) {
					String argString;
					String args[];
					try {
						argString = netString.substring(8);
						args = argString.split("\" \"");
					} catch (Exception e) {
						outstream.writeBytes("RM20 L\r\n");
						continue;
					}
					if (args.length == 3 && args[2].equals("&3\"")) {
						outstream.writeBytes("RM20 B\r\n");
						programState.setDisplayText(args[0]);
						needResponse = true;
						lastRequest = System.currentTimeMillis();
					} else {
						outstream.writeBytes("RM20 L\r\n");
						outstream.writeBytes("Programmet understøtter kun "
								+ "følgende version af RM20_8:\r\n");
						outstream.writeBytes("RM20_8_\"<display text>\"_\""
								+ "<placeholder text>\"_\"&3\"\r\n");
					}
				} else if (netString.startsWith("RESET")) {
					programState.reset();
					outstream.writeBytes("du har nulstillet programmet\r\n");
				} else if (netString.startsWith("P111")) {
					if (netString.length() <= 37) {
						programState.setBotDisplay(netString.substring(6,
								netString.length()));
						outstream.writeBytes("P111 A\r\n");
					} else {
						outstream.writeBytes("P111 L\r\n");
					}
				} else if (netString.startsWith("P110")) {
					programState.setBotDisplay("");
				} else if (netString.equals("DW")) {
					programState.setDisplayText("");
					outstream.writeBytes("DW A\r\n");
				} else if (netString.startsWith("D ")) {
					if (!netString.matches("D \".+\"")) {
						outstream.writeBytes("D L\r\n");
						continue;
					}
					int end = netString.indexOf("\"", 3);
					netString = netString.substring(3, end);
					programState.setDisplayText(netString);
					outstream.writeBytes("D A\r\n");
				} else if (netString.equals("T")) {
					programState.tare();
					outstream.writeBytes("T S " + (programState.getGross())
							+ " kg " + "\r\n");
				} else if (netString.equals("S")) {
					outstream.writeBytes("S S " + (programState.getNet())
							+ " kg " + "\r\n");
				} else if (netString.startsWith("B ")) {
					try {
						double weight = Double.parseDouble(netString
								.substring(2));
						programState.setGross(weight);
						outstream.writeBytes("B A\r\n");
					} catch (Exception e) {
						outstream.writeBytes("B L\r\n");
					}
				} else if (netString.equals("Q")) {
					System.out
							.println("Program stoppet Q modtaget p� com port");
					outstream.writeBytes("program  stoppet Q modtaget på com "
							+ "port");
					programState.quit();
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}
}

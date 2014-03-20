package boundary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import data.IProgramState;

public class NetworkIOBoundary implements IBoundary {
	private Socket sock;
	private DataOutputStream outstream;
	private BufferedReader instream;
	private IProgramState programState;

	public NetworkIOBoundary(Socket socket, IProgramState programState) {
		sock = socket;
		this.programState = programState;
	}

	public void run() {
		try {
			instream = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			if (programState.isRunning())
				System.out.println("Exception: " + e.getMessage());
			programState.quit();
			return;
		}

		try {
			String netString;
			while (!(netString = instream.readLine().toUpperCase()).isEmpty()) {
				programState.setNetString(netString);
				if (netString.startsWith("RM20 8")) {
					boolean cond1 = true, cond2 = false, cond3 = false;

					// String netString1 =
					// "RM20 8 \"indtast nr\" \"dette vil blive slettet\" \"nr\"";

					String netString2 = netString.replaceAll("\\s+", "");

					for (int i = 6; i > 30; i++) {
						if (netString2.codePointAt(i) == 34) {
							cond1 = true;
							for (int j = i + 2; j < netString2.length(); j++) {
								if (netString2.codePointAt(j) == 34) {
									cond2 = true;
									for (int k = j + 2; k < netString2.length(); k++) {
										if (k > j + 11) {
											i = 31;
										}
										if (netString2.codePointAt(k) == 34) {
											cond3 = true;
											i = 31;

										}
									}
								}

							}

						}
					}
					if (cond1 && cond2 && cond3) {
						outstream.writeBytes("success the string was fine :) ");
					}

					outstream.writeBytes("RM20 B");
					programState.setDisplayText("indtast batch_nr: ");
					while (programState.getDisplayText().isEmpty()) {
					}
					outstream.writeBytes("RM20 A"
							+ programState.getDisplayText());

				} else if (netString.startsWith("RESET")) {
					programState.reset();
					outstream.writeBytes("du har nulstillet programmet"
							+ "\r\n");
				} else if (netString.startsWith("P111")) {

					if (netString.length() <= 39) {
						programState.setBotDisplay(netString.substring(5,
								netString.length()));
						outstream.writeBytes("P111 A" + "\r\n");

					} else {
						outstream.writeBytes("P111 L" + "\r\n");
					}
				} else if (netString.startsWith("P110")) {
					programState.setBotDisplay(" ");

				}

				else if (netString.startsWith("D")) {
					if (netString.equals("D"))
						programState.setDisplayText("");
					else
						programState.setDisplayText(netString.substring(2,
								netString.length()));
					outstream.writeBytes("DB" + "\r\n");
				} else if (netString.startsWith("T")) {
					programState.tare();
					outstream.writeBytes("T " + (programState.getGross())
							+ " kg " + "\r\n");
				} else if (netString.startsWith("S")) {
					outstream.writeBytes("S " + (programState.getNet())
							+ " kg " + "\r\n");
				} else if ((netString.startsWith("Q"))) {
					System.out.println("");
					System.out
							.println("Program stoppet Q modtaget p� com port");
					outstream
							.writeBytes("program  stoppet Q modtaget på com port");
				}
			}
			instream.close();
			outstream.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}
}
